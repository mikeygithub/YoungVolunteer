package com.mikey.youngvolunteer.service.association;

import com.mikey.youngvolunteer.common.PageBean;
import com.mikey.youngvolunteer.model.VolunteerAssociationEntity;

/**
 * @Program: YoungVolunteer
 * @Author: 麦奇
 * @Email： 1625017540@qq.com
 * @Create: 2019-06-04 00:09
 * @Describe：
 **/
public interface AssociationService {
    /**
     * 添加
     */
    public void save(VolunteerAssociationEntity volunteerAssociationEntity);

    /**
     * 删除
     */
    public void delete(VolunteerAssociationEntity volunteerAssociationEntity);

    /**
     * 修改
     */
    public void update(VolunteerAssociationEntity volunteerAssociationEntity);

    /**
     * 查询
     *
     * @return
     */
    public VolunteerAssociationEntity findById(VolunteerAssociationEntity volunteerAssociationEntity);

    /**
     * 查询
     *
     * @param key
     * @param pageBean
     * @return
     */
    public PageBean findByPage(String key, PageBean<VolunteerAssociationEntity> pageBean);

    /**
     * 批量删除
     *
     * @param Ids
     */
    public void deleteBatch(String[] Ids);
}
