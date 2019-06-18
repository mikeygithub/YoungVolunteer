package com.mikey.youngvolunteer.dao.colleges.impl;

import com.mikey.youngvolunteer.common.PageBean;
import com.mikey.youngvolunteer.dao.colleges.CollegesDao;
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
public class CollegesDaoImpl implements CollegesDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Autowired
    private HibernateTemplate hibernateTemplate;

    @Override
    public void save(CollegesEntity collegesEntity) {
        sessionFactory.getCurrentSession().save(collegesEntity);
    }

    @Override
    public void delete(CollegesEntity collegesEntity) {
        sessionFactory.getCurrentSession().delete(collegesEntity);
    }

    @Override
    public void update(CollegesEntity collegesEntity) {
        sessionFactory.getCurrentSession().update(collegesEntity);
    }

    @Override
    public CollegesEntity findById(CollegesEntity collegesEntity) {
        Session session = sessionFactory.openSession();

        Criteria criteria = session.createCriteria(CollegesEntity.class);

        List list = criteria.add(
                Restrictions.or(Restrictions.eq("collegesId", collegesEntity.getCollegesId())))
                .setFirstResult(0)
                .setMaxResults(1).list();

        session.close();

        return list!=null&&list.size()>0? (CollegesEntity) list.get(0) :null;

    }

    @Override
    public PageBean findByPage(String key, PageBean<CollegesEntity> pageBean) {
        Session session = sessionFactory.openSession();

        Criteria criteria = session.createCriteria(CollegesEntity.class);

        if (key != null && !key.equals("")) {
            //搜索
            List list = criteria.add(
                    Restrictions.or(
                            Restrictions.or(Restrictions.like("collegesCode", key, MatchMode.ANYWHERE)),
                            Restrictions.or(Restrictions.like("collegesName", key, MatchMode.ANYWHERE))))
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
        List<CollegesEntity> list = new ArrayList<>();

        for (String id : Ids) {
            CollegesEntity collegesEntity = new CollegesEntity();
            collegesEntity.setCollegesId(Integer.parseInt(id));
            list.add(collegesEntity);
        }
        hibernateTemplate.deleteAll(list);
    }
}
