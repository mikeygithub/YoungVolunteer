package com.mikey.youngvolunteer.dao.member.impl;

import com.mikey.youngvolunteer.common.PageBean;
import com.mikey.youngvolunteer.dao.member.MemberDao;
import com.mikey.youngvolunteer.model.AssociationMemberEntity;
import com.mikey.youngvolunteer.model.CollegesEntity;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * @Program: YoungVolunteer
 * @Author: 麦奇
 * @Email： 1625017540@qq.com
 * @Create: 2019-06-04 00:32
 * @Describe：
 **/
@Component
public class MemberDaoImpl implements MemberDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Autowired
    private HibernateTemplate hibernateTemplate;

    @Override
    public void save(AssociationMemberEntity associationMemberEntity) {
        sessionFactory.getCurrentSession().save(associationMemberEntity);
    }

    @Override
    public void delete(AssociationMemberEntity associationMemberEntity) {
        sessionFactory.getCurrentSession().delete(associationMemberEntity);
    }

    @Override
    public void update(AssociationMemberEntity associationMemberEntity) {
        sessionFactory.getCurrentSession().update(associationMemberEntity);
    }

    @Override
    public AssociationMemberEntity findById(AssociationMemberEntity associationMemberEntity) {
        Session session = sessionFactory.openSession();

        Criteria criteria = session.createCriteria(AssociationMemberEntity.class);

        List list = criteria.add(
                Restrictions.or(Restrictions.eq("id", associationMemberEntity.getId())))
                .setFirstResult(0)
                .setMaxResults(1).list();

        session.close();

        return list!=null&&list.size()>0? (AssociationMemberEntity) list.get(0) :null;

    }

    @Override
    public PageBean findByPage(String key, PageBean<AssociationMemberEntity> pageBean) {
        Session session = sessionFactory.openSession();

        Criteria criteria = session.createCriteria(AssociationMemberEntity.class);

        if (key != null && !key.equals("")) {
            //搜索
            List list = criteria.add(
                    Restrictions.or(
                            Restrictions.or(Restrictions.like("memberCode", key, MatchMode.ANYWHERE)),
                            Restrictions.or(Restrictions.like("memberName", key, MatchMode.ANYWHERE))))
                    .setFirstResult((pageBean.getCurrPage() - 1) * pageBean.getPageSize() )
                    .setMaxResults((pageBean.getCurrPage() - 1) * pageBean.getPageSize() + pageBean.getPageSize()).list();
            pageBean.setRows(list);
        } else {
            pageBean.setRows(
                    criteria.setFirstResult((pageBean.getCurrPage() - 1) * pageBean.getPageSize())
                            .setMaxResults((pageBean.getCurrPage() - 1) * pageBean.getPageSize() + pageBean.getPageSize()).setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list());
        }

        pageBean.setTotal(Math.toIntExact((Long) criteria.setProjection(Projections.rowCount()).uniqueResult()));
        session.close();

        return pageBean;
    }

    @Override
    public void deleteBatch(String[] Ids) {
        List<AssociationMemberEntity> list = new ArrayList<>();

        for (String id : Ids) {
            AssociationMemberEntity associationMemberEntity = new AssociationMemberEntity();
            associationMemberEntity.setId(Integer.parseInt(id));
            list.add(associationMemberEntity);
        }
        hibernateTemplate.deleteAll(list);
    }
}
