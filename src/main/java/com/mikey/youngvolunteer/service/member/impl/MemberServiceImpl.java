package com.mikey.youngvolunteer.service.member.impl;

import com.mikey.youngvolunteer.common.PageBean;
import com.mikey.youngvolunteer.dao.member.MemberDao;
import com.mikey.youngvolunteer.model.AssociationMemberEntity;
import com.mikey.youngvolunteer.model.CollegesEntity;
import com.mikey.youngvolunteer.service.member.MemberService;
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
public class MemberServiceImpl implements MemberService {

    @Autowired
    private MemberDao memberDao;

    @Override
    public void save(AssociationMemberEntity associationMemberEntity) {

        memberDao.save(associationMemberEntity);
    }

    @Override
    public void delete(AssociationMemberEntity associationMemberEntity) {

        memberDao.delete(associationMemberEntity);
    }

    @Override
    public void update(AssociationMemberEntity associationMemberEntity) {
        memberDao.update(associationMemberEntity);
    }

    @Override
    public AssociationMemberEntity findById(AssociationMemberEntity associationMemberEntity) {
       return memberDao.findById(associationMemberEntity);
    }

    @Override
    public PageBean findByPage(String key, PageBean<AssociationMemberEntity> pageBean) {
        return memberDao.findByPage(key,pageBean);
    }

    @Override
    public void deleteBatch(String[] Ids) {
       memberDao.deleteBatch(Ids);
    }
}
