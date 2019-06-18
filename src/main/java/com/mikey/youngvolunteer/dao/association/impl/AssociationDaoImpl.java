package com.mikey.youngvolunteer.dao.association.impl;

import com.mikey.youngvolunteer.common.PageBean;
import com.mikey.youngvolunteer.dao.association.AssociationDao;
import com.mikey.youngvolunteer.model.CollegesEntity;
import com.mikey.youngvolunteer.model.VolunteerAssociationEntity;
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
public class AssociationDaoImpl implements AssociationDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Autowired
    private HibernateTemplate hibernateTemplate;

    @Override
    public void save(VolunteerAssociationEntity volunteerAssociationEntity) {
        sessionFactory.getCurrentSession().save(volunteerAssociationEntity);
    }

    @Override
    public void delete(VolunteerAssociationEntity volunteerAssociationEntity) {
        sessionFactory.getCurrentSession().delete(volunteerAssociationEntity);
    }

    @Override
    public void update(VolunteerAssociationEntity volunteerAssociationEntity) {
        sessionFactory.getCurrentSession().update(volunteerAssociationEntity);
    }

    @Override
    public VolunteerAssociationEntity findById(VolunteerAssociationEntity volunteerAssociationEntity) {
        Session session = sessionFactory.openSession();

        Criteria criteria = session.createCriteria(VolunteerAssociationEntity.class);

        List list = criteria.add(
                Restrictions.or(Restrictions.eq("id", volunteerAssociationEntity.getId())))
                .setFirstResult(0)
                .setMaxResults(1).list();

        session.close();

        return list!=null&&list.size()>0? (VolunteerAssociationEntity) list.get(0) :null;

    }

    @Override
    public PageBean findByPage(String key, PageBean<VolunteerAssociationEntity> pageBean) {
        Session session = sessionFactory.openSession();

        Criteria criteria = session.createCriteria(VolunteerAssociationEntity.class);

        if (key != null && !key.equals("")) {
            //搜索
            List list = criteria.add(
                    Restrictions.or(
                            Restrictions.or(Restrictions.like("associationCode", key, MatchMode.ANYWHERE)),
                            Restrictions.or(Restrictions.like("associationName", key, MatchMode.ANYWHERE))))
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
        List<VolunteerAssociationEntity> list = new ArrayList<>();

        for (String id : Ids) {
            VolunteerAssociationEntity volunteerAssociationEntity = new VolunteerAssociationEntity();
            volunteerAssociationEntity.setId(Integer.parseInt(id));
            list.add(volunteerAssociationEntity);
        }
        hibernateTemplate.deleteAll(list);
    }
}
