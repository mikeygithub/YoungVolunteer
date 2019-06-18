package com.mikey.youngvolunteer.service.association.impl;

import com.mikey.youngvolunteer.common.PageBean;
import com.mikey.youngvolunteer.dao.association.AssociationDao;
import com.mikey.youngvolunteer.model.CollegesEntity;
import com.mikey.youngvolunteer.model.VolunteerAssociationEntity;
import com.mikey.youngvolunteer.service.association.AssociationService;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @Program: YoungVolunteer
 * @Author: 麦奇
 * @Email： 1625017540@qq.com
 * @Create: 2019-06-04 00:32
 * @Describe：
 **/
@Service
public class AssociationServiceImpl implements AssociationService {

    @Autowired
    private AssociationDao associationDao;

    @Override
    public void save(VolunteerAssociationEntity volunteerAssociationEntity) {
        associationDao.save(volunteerAssociationEntity);
    }

    @Override
    public void delete(VolunteerAssociationEntity volunteerAssociationEntity) {

        associationDao.delete(volunteerAssociationEntity);
    }

    @Override
    public void update(VolunteerAssociationEntity volunteerAssociationEntity) {

        associationDao.update(volunteerAssociationEntity);
    }

    @Override
    public VolunteerAssociationEntity findById(VolunteerAssociationEntity volunteerAssociationEntity) {
       return associationDao.findById(volunteerAssociationEntity);
    }

    @Override
    public PageBean findByPage(String key, PageBean<VolunteerAssociationEntity> pageBean) {

        return associationDao.findByPage(key,pageBean);
    }

    @Override
    public void deleteBatch(String[] Ids) {
       associationDao.deleteBatch(Ids);
    }
}
