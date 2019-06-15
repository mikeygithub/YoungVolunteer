package com.mikey.youngvolunteer.service.volunteersignup.impl;

import com.mikey.youngvolunteer.common.PageBean;
import com.mikey.youngvolunteer.dao.volunteersignup.VolunteerSignUpDao;
import com.mikey.youngvolunteer.model.VolunteerSignUpEntity;
import com.mikey.youngvolunteer.service.volunteersignup.VolunteerSignUpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Program: YoungVolunteer
 * @Author: 麦奇
 * @Email： 1625017540@qq.com
 * @Create: 2019-06-04 00:31
 * @Describe：
 **/
@Service
public class VolunteerSignUpServiceImpl implements VolunteerSignUpService {

    @Autowired
    private VolunteerSignUpDao volunteerSignUpDao;
    /**
     * 添加
     * @param volunteerSignUpEntity
     */
    @Override
    public void saveVolunteerSignUp(VolunteerSignUpEntity volunteerSignUpEntity){
        volunteerSignUpDao.saveVolunteerSignUp(volunteerSignUpEntity);
    }

    /**
     * 删除
     * @param volunteerSignUpEntity
     */
    @Override
    public void deleteVolunteerSignUp(VolunteerSignUpEntity volunteerSignUpEntity){
        volunteerSignUpDao.deleteVolunteerSignUp(volunteerSignUpEntity);
    }

    /**
     * 修改
     * @param volunteerSignUpEntity
     */
    @Override
    public void updateVolunteerSignUp(VolunteerSignUpEntity volunteerSignUpEntity){
        volunteerSignUpDao.updateVolunteerSignUp(volunteerSignUpEntity);
    }

    /**
     * 查询
     * @param volunteerSignUpEntity
     * @return
     */
    @Override
    public VolunteerSignUpEntity findOneVolunteerSignUp(VolunteerSignUpEntity volunteerSignUpEntity){
        return volunteerSignUpDao.findOneVolunteerSignUp(volunteerSignUpEntity);
    }

    /**
     * 查询
     * @param key
     * @param pageBean
     * @return
     */
    @Override
    public PageBean findAllVolunteerSignUp(String key, PageBean<VolunteerSignUpEntity> pageBean){
        return volunteerSignUpDao.findAllVolunteerSignUp(key,pageBean);
    }
    /**
     * 批量删除
     * @param Ids
     */
    @Override
    public void deleteAllVolunteerSignUp(String[] Ids){
        volunteerSignUpDao.deleteAllVolunteerSignUp(Ids);
    }

    @Override
    public PageBean findAllVolunteerNoSignUp(String key, PageBean<VolunteerSignUpEntity> pageBean) {
        return volunteerSignUpDao.findAllVolunteerNoSignUp(key,pageBean);
    }

    @Override
    public PageBean findAllVolunteerScore(String key, PageBean<VolunteerSignUpEntity> pageBean) {
        return volunteerSignUpDao.findAllVolunteerScore(key,pageBean);
    }

    @Override
    public PageBean findAllVolunteerNoScore(String key, PageBean<VolunteerSignUpEntity> pageBean) {
        return volunteerSignUpDao.findAllVolunteerNoScore(key,pageBean);
    }

    @Override
    public void outVolunteerSignUp(VolunteerSignUpEntity volunteerSignUpEntity) {
        volunteerSignUpDao.outVolunteerSignUp(volunteerSignUpEntity);
    }

}
