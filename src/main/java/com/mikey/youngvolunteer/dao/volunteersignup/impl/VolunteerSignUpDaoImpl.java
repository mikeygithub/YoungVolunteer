package com.mikey.youngvolunteer.dao.volunteersignup.impl;

import com.mikey.youngvolunteer.common.PageBean;
import com.mikey.youngvolunteer.model.VolunteerSignUpEntity;
import com.mikey.youngvolunteer.dao.volunteersignup.VolunteerSignUpDao;
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
 * @Create: 2019-06-04 00:31
 * @Describe：
 **/
@Component
public class VolunteerSignUpDaoImpl implements VolunteerSignUpDao {


    @Autowired
    private SessionFactory sessionFactory;

    @Autowired
    private HibernateTemplate hibernateTemplate;

    /**
     * 添加
     * @param volunteerSignUpEntity
     */
    @Override
    public void saveVolunteerSignUp(VolunteerSignUpEntity volunteerSignUpEntity){
        hibernateTemplate.save(volunteerSignUpEntity);
    }

    /**
     * 删除
     * @param volunteerSignUpEntity
     */
    @Override
    public void deleteVolunteerSignUp(VolunteerSignUpEntity volunteerSignUpEntity){
        hibernateTemplate.delete(volunteerSignUpEntity);
    }
    /**
     * 退订
     * @param volunteerSignUpEntity
     */
    @Override
    public void outVolunteerSignUp(VolunteerSignUpEntity volunteerSignUpEntity){

        Session currentSession = sessionFactory.openSession();

        Criteria criteria = currentSession.createCriteria(VolunteerSignUpEntity.class);

        List list = criteria.add(Restrictions.eq("activityId", volunteerSignUpEntity.getActivityId())).add(Restrictions.eq("volunteerId", volunteerSignUpEntity.getVolunteerId())).list();

        VolunteerSignUpEntity vsue = (VolunteerSignUpEntity) list.get(0);

        deleteVolunteerSignUp(vsue);

    }
    /**
     * 修改
     * @param volunteerSignUpEntity
     */
    @Override
    public void updateVolunteerSignUp(VolunteerSignUpEntity volunteerSignUpEntity){
        hibernateTemplate.update(volunteerSignUpEntity);
    }

    /**
     * 查询
     * @param volunteerSignUpEntity
     * @return
     */
    @Override
    public VolunteerSignUpEntity findOneVolunteerSignUp(VolunteerSignUpEntity volunteerSignUpEntity){

        Session currentSession = sessionFactory.openSession();

        Criteria criteria = currentSession.createCriteria(VolunteerSignUpEntity.class);

        List<VolunteerSignUpEntity> list = criteria.add(Restrictions.eq("signUpId", volunteerSignUpEntity.getSignUpId())).setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();

        currentSession.close();

        return list.size()>0&&list!=null?list.get(0):null;
    }

    /**
     * 查询已经签到的
     * @param key
     * @param pageBean
     * @return
     */
    @Override
    public PageBean findAllVolunteerSignUp(String key, PageBean<VolunteerSignUpEntity> pageBean){

        List list = null;

        Session session = sessionFactory.openSession();

        Criteria criteria = session.createCriteria(VolunteerSignUpEntity.class);

        if (key != null && !key.equals("")) {
            //搜索
            list = criteria.add(Restrictions.isNotNull("signIn"))
                    .add(
                    Restrictions.or(Restrictions.like("signUpId", key, MatchMode.ANYWHERE),
                            Restrictions.or(Restrictions.like("signIn", key, MatchMode.ANYWHERE)),
                            Restrictions.or(Restrictions.like("volunteerId", key, MatchMode.ANYWHERE)),
                            Restrictions.or(Restrictions.like("activityId", key, MatchMode.ANYWHERE)),
                            Restrictions.or(Restrictions.like("volunteerScore", key, MatchMode.ANYWHERE))))
                    .setFirstResult((pageBean.getCurrPage() - 1) * pageBean.getPageSize() )
                    .setMaxResults((pageBean.getCurrPage() - 1) * pageBean.getPageSize() + pageBean.getPageSize()).setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();
            pageBean.setRows(list);
        } else {
            list = criteria
                    .add(Restrictions.isNotNull("signIn"))
                    .setFirstResult((pageBean.getCurrPage() - 1) * pageBean.getPageSize())
                    .setMaxResults((pageBean.getCurrPage() - 1) * pageBean.getPageSize() + pageBean.getPageSize()).setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();
            pageBean.setRows(list);
        }

        pageBean.setTotal(Math.toIntExact((Long) criteria.setProjection(Projections.rowCount()).uniqueResult()));

        session.close();
        return pageBean;
    }
    /**
     * 查询未签订到的
     * @param key
     * @param pageBean
     * @return
     */
    @Override
    public PageBean findAllVolunteerNoSignUp(String key, PageBean<VolunteerSignUpEntity> pageBean){

        List list = null;

        Session session = sessionFactory.openSession();

        Criteria criteria = session.createCriteria(VolunteerSignUpEntity.class);

        if (key != null && !key.equals("")) {
            //搜索
            list = criteria.add(Restrictions.isNull("signIn")).add(
                    Restrictions.or(
                            Restrictions.like("signUpId", key, MatchMode.ANYWHERE),
                            Restrictions.or(Restrictions.like("signIn", key, MatchMode.ANYWHERE)),
                            Restrictions.or(Restrictions.like("volunteerId", key, MatchMode.ANYWHERE)),
                            Restrictions.or(Restrictions.like("activityId", key, MatchMode.ANYWHERE)),
                            Restrictions.or(Restrictions.like("volunteerScore", key, MatchMode.ANYWHERE))))
                    .setFirstResult((pageBean.getCurrPage() - 1) * pageBean.getPageSize() )
                    .setMaxResults((pageBean.getCurrPage() - 1) * pageBean.getPageSize() + pageBean.getPageSize()).setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();
            pageBean.setRows(list);
        } else {
            list = criteria.add(Restrictions.isNull("signIn")).setFirstResult((pageBean.getCurrPage() - 1) * pageBean.getPageSize())
                    .setMaxResults((pageBean.getCurrPage() - 1) * pageBean.getPageSize() + pageBean.getPageSize()).setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();
            pageBean.setRows(list);
        }
        pageBean.setTotal(Math.toIntExact((Long) criteria.setProjection(Projections.rowCount()).uniqueResult()));

        session.close();
        return pageBean;
    }
    /**
     * 查询已经打分列表
     * @param key
     * @param pageBean
     * @return
     */
    @Override
    public PageBean findAllVolunteerScore(String key, PageBean<VolunteerSignUpEntity> pageBean){

        List list = null;

        Session session = sessionFactory.openSession();

        Criteria criteria = session.createCriteria(VolunteerSignUpEntity.class);

        if (key != null && !key.equals("")) {
            //搜索
            list = criteria.add(Restrictions.isNotNull("volunteerScore"))
                    .add(Restrictions.isNotNull("signIn"))
                    .add(Restrictions.or(
                            Restrictions.like("signUpId", key, MatchMode.ANYWHERE),
                            Restrictions.or(Restrictions.like("volunteerId", key, MatchMode.ANYWHERE)),
                            Restrictions.or(Restrictions.like("activityId", key, MatchMode.ANYWHERE)),
                            Restrictions.or(Restrictions.like("volunteerScore", key, MatchMode.ANYWHERE))))
                    .setFirstResult((pageBean.getCurrPage() - 1) * pageBean.getPageSize() )
                    .setMaxResults((pageBean.getCurrPage() - 1) * pageBean.getPageSize() + pageBean.getPageSize()).setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();
            pageBean.setRows(list);
        } else {
            list = criteria.add(Restrictions.isNotNull("volunteerScore")).add(Restrictions.isNotNull("signIn")).setFirstResult((pageBean.getCurrPage() - 1) * pageBean.getPageSize())
                    .setMaxResults((pageBean.getCurrPage() - 1) * pageBean.getPageSize() + pageBean.getPageSize()).setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();
            pageBean.setRows(list);
        }
        pageBean.setTotal(Math.toIntExact((Long) criteria.setProjection(Projections.rowCount()).uniqueResult()));

        session.close();
        return pageBean;
    }
    /**
     * 查询未评分的
     * @param key
     * @param pageBean
     * @return
     */
    @Override
    public PageBean findAllVolunteerNoScore(String key, PageBean<VolunteerSignUpEntity> pageBean){

        List list = null;

        Session session = sessionFactory.openSession();

        Criteria criteria = session.createCriteria(VolunteerSignUpEntity.class);

        if (key != null && !key.equals("")) {
            //搜索
            list = criteria.add(Restrictions.isNull("volunteerScore"))
                    .add(Restrictions.isNotNull("signIn"))
                    .add(Restrictions.or(
                            Restrictions.like("signUpId", key, MatchMode.ANYWHERE),
                            Restrictions.or(Restrictions.like("volunteerId", key, MatchMode.ANYWHERE)),
                            Restrictions.or(Restrictions.like("activityId", key, MatchMode.ANYWHERE)),
                            Restrictions.or(Restrictions.like("volunteerScore", key, MatchMode.ANYWHERE))))
                    .setFirstResult((pageBean.getCurrPage() - 1) * pageBean.getPageSize() )
                    .setMaxResults((pageBean.getCurrPage() - 1) * pageBean.getPageSize() + pageBean.getPageSize()).setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();
            pageBean.setRows(list);
        } else {
            list = criteria.add(Restrictions.isNull("volunteerScore")).add(Restrictions.isNotNull("signIn")).setFirstResult((pageBean.getCurrPage() - 1) * pageBean.getPageSize())
                    .setMaxResults((pageBean.getCurrPage() - 1) * pageBean.getPageSize() + pageBean.getPageSize()).setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();
            pageBean.setRows(list);
        }
        pageBean.setTotal(Math.toIntExact((Long) criteria.setProjection(Projections.rowCount()).uniqueResult()));

        session.close();
        return pageBean;
    }

    /**
     * 批量删除
     * @param Ids
     */
    @Override
    public void deleteAllVolunteerSignUp(String[] Ids){

        List<VolunteerSignUpEntity> list = new ArrayList<>();

        for (String id : Ids) {
            VolunteerSignUpEntity volunteerSignUpEntity = new VolunteerSignUpEntity();
            volunteerSignUpEntity.setSignUpId(Integer.parseInt(id));
            list.add(volunteerSignUpEntity);
        }
        hibernateTemplate.deleteAll(list);
    }

}
