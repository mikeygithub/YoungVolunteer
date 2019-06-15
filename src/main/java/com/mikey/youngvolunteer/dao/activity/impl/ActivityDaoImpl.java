package com.mikey.youngvolunteer.dao.activity.impl;

import com.mikey.youngvolunteer.common.PageBean;
import com.mikey.youngvolunteer.dao.activity.ActivityDao;
import com.mikey.youngvolunteer.dao.volunteer.VolunteerDao;
import com.mikey.youngvolunteer.model.VolunteerActivityEntity;
import com.mikey.youngvolunteer.model.VolunteerSignUpEntity;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.*;
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
public class ActivityDaoImpl implements ActivityDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Autowired
    private HibernateTemplate hibernateTemplate;

    @Autowired
    private VolunteerDao volunteerDao;

    /**
     * 添加
     * @param volunteerActivityEntity
     */
    @Override
    public void saveVolunteerActivity(VolunteerActivityEntity volunteerActivityEntity){

        sessionFactory.getCurrentSession().save(volunteerActivityEntity);
    }

    /**
     * 删除
     * @param volunteerActivityEntity
     */
    @Override
    public void deleteVolunteerActivity(VolunteerActivityEntity volunteerActivityEntity){

        sessionFactory.getCurrentSession().delete(volunteerActivityEntity);

    }

    /**
     * 修改
     * @param volunteerActivityEntity
     */
    @Override
    public void updateVolunteerActivity(VolunteerActivityEntity volunteerActivityEntity){

        sessionFactory.getCurrentSession().update(volunteerActivityEntity);

    }

    /**
     * 查询
     * @param volunteerActivityEntity
     * @return
     */
    @Override
    public VolunteerActivityEntity findOneVolunteery(VolunteerActivityEntity volunteerActivityEntity){

        Session session = sessionFactory.openSession();

        Criteria criteria = session.createCriteria(VolunteerActivityEntity.class);

        List list = criteria.add(
                Restrictions.or(Restrictions.eq("activityId", volunteerActivityEntity.getActivityId())))
                .setFirstResult(0)
                .setMaxResults(1).list();

        session.close();

        return list!=null&&list.size()>0? (VolunteerActivityEntity) list.get(0) :null;
    }

    /**
     * 查询
     * @param key
     * @param pageBean
     * @return
     */
    @Override
    public PageBean findAllVolunteerActivity(String key, PageBean<VolunteerActivityEntity> pageBean){

        Session session = sessionFactory.openSession();

        Criteria criteria = session.createCriteria(VolunteerActivityEntity.class);

        if (key != null && !key.equals("")) {
            //搜索
            List list = criteria.add(
                    Restrictions.or(
                            Restrictions.or(Restrictions.like("activityCode", key, MatchMode.ANYWHERE)),
                            Restrictions.or(Restrictions.like("activityTitle", key, MatchMode.ANYWHERE)),
                            Restrictions.or(Restrictions.like("activityContent", key, MatchMode.ANYWHERE)),
                            Restrictions.or(Restrictions.like("activityLeader", key, MatchMode.ANYWHERE))))
                    .setFirstResult((pageBean.getCurrPage() - 1) * pageBean.getPageSize() )
                    .setMaxResults((pageBean.getCurrPage() - 1) * pageBean.getPageSize() + pageBean.getPageSize()).list();
            pageBean.setRows(list);
        } else {
            pageBean.setRows(
                    criteria.setFirstResult((pageBean.getCurrPage() - 1) * pageBean.getPageSize())
                    .setMaxResults((pageBean.getCurrPage() - 1) * pageBean.getPageSize() + pageBean.getPageSize()).setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list());
        }

        session.close();

        return pageBean;
    }
    /**
     * 批量删除
     * @param Ids
     */
    @Override
    public void deleteAllVolunteerActivity(String[] Ids){
        List<VolunteerActivityEntity> list = new ArrayList<>();

        for (String id : Ids) {
            VolunteerActivityEntity volunteerActivityEntity = new VolunteerActivityEntity();
            volunteerActivityEntity.setActivityId(Integer.parseInt(id));
            list.add(volunteerActivityEntity);
        }
        hibernateTemplate.deleteAll(list);
    }

    /**
     * 查询已近加入的活动
     * @param key
     * @param setPageSize
     * @return
     */
    @Override
    public PageBean findAllVolunteerJoinActivity(String volunteerId,String key, PageBean<VolunteerActivityEntity> setPageSize) {

        Session session = sessionFactory.openSession();

        Criteria criteria = session.createCriteria(VolunteerActivityEntity.class,"activity");
        DetachedCriteria signup = DetachedCriteria.forClass(VolunteerSignUpEntity.class, "signup");

        signup.add(Restrictions.eq("volunteerId",volunteerId))
                .add(Property.forName("activity.activityId").eqProperty("signup.activityId"));

        criteria.add(Subqueries.exists(signup.setProjection(Projections.property("signup.signUpId"))));

        List list = criteria.list();
        setPageSize.setTotal(Math.toIntExact((Long) criteria.setProjection(Projections.rowCount()).uniqueResult()));
        return setPageSize.setRows(list);
    }

    /**
     * 查询未加入的活动
     * @param key
     * @param setPageSize
     * @return
     */
    @Override
    public PageBean findAllVolunteerNojoinActivity(String volunteerId,String key, PageBean<VolunteerActivityEntity> setPageSize) {

        Session session = sessionFactory.openSession();

        Criteria criteria = session.createCriteria(VolunteerActivityEntity.class,"activity");
        DetachedCriteria signup = DetachedCriteria.forClass(VolunteerSignUpEntity.class, "signup");

        signup.add(Restrictions.eq("volunteerId",volunteerId))
                .add(Property.forName("activity.activityId").eqProperty("signup.activityId"));

        criteria.add(Subqueries.notExists(signup.setProjection(Projections.property("signup.signUpId"))));

        List list = criteria.list();

        setPageSize.setTotal(Math.toIntExact((Long) criteria.setProjection(Projections.rowCount()).uniqueResult()));

        return setPageSize.setRows(list);
    }
}
