package com.mikey.youngvolunteer.dao.activity;

import com.mikey.youngvolunteer.common.PageBean;
import com.mikey.youngvolunteer.model.VolunteerActivityEntity;

/**
 * @Program: YoungVolunteer
 * @Author: 麦奇
 * @Email： 1625017540@qq.com
 * @Create: 2019-06-04 00:09
 * @Describe：
 **/
public interface ActivityDao {
    /**
     * 添加
     * @param volunteerActivityEntity
     */
    public void saveVolunteerActivity(VolunteerActivityEntity volunteerActivityEntity);

    /**
     * 删除
     * @param volunteerActivityEntity
     */
    public void deleteVolunteerActivity(VolunteerActivityEntity volunteerActivityEntity);

    /**
     * 修改
     * @param volunteerActivityEntity
     */
    public void updateVolunteerActivity(VolunteerActivityEntity volunteerActivityEntity);

    /**
     * 查询
     * @param volunteerActivityEntity
     * @return
     */
    public VolunteerActivityEntity findOneVolunteery(VolunteerActivityEntity volunteerActivityEntity);

    /**
     * 查询
     * @param key
     * @param pageBean
     * @return
     */
    public PageBean findAllVolunteerActivity(String key, PageBean<VolunteerActivityEntity> pageBean);
    /**
     * 批量删除
     * @param Ids
     */
    public void deleteAllVolunteerActivity(String[] Ids);

    /**
     * 已报名活动
     * @param volunteerId
     * @param key
     * @param setPageSize
     * @return
     */
    PageBean findAllVolunteerJoinActivity(String volunteerId, String key, PageBean<VolunteerActivityEntity> setPageSize);

    /**
     * 未参加活动
     * @param volunteerId
     * @param key
     * @param setPageSize
     * @return
     */
    PageBean findAllVolunteerNojoinActivity(String volunteerId, String key, PageBean<VolunteerActivityEntity> setPageSize);
}
