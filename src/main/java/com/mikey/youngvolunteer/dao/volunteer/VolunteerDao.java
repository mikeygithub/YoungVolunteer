package com.mikey.youngvolunteer.dao.volunteer;

import com.mikey.youngvolunteer.common.PageBean;
import com.mikey.youngvolunteer.model.SysVolunteerEntity;

/**
 * @Program: YoungVolunteer
 * @Author: 麦奇
 * @Email： 1625017540@qq.com
 * @Create: 2019-06-03 23:36
 * @Describe：
 **/
public interface VolunteerDao {

    /**
     * 添加
     * @param sysVolunteerEntity
     */
    public void saveSysVolunteer(SysVolunteerEntity sysVolunteerEntity);

    /**
     * 删除
     * @param sysVolunteerEntity
     */
    public void deleteSysVolunteer(SysVolunteerEntity sysVolunteerEntity);

    /**
     * 修改
     * @param sysVolunteerEntity
     */
    public void updateSysVolunteer(SysVolunteerEntity sysVolunteerEntity);

    /**
     * 查询
     * @param sysVolunteerEntity
     * @return
     */
    public SysVolunteerEntity findOneSysVolunteery(SysVolunteerEntity sysVolunteerEntity);

    /**
     * 查询
     * @return
     */
    public PageBean findAllSysVolunteer(String key, PageBean<SysVolunteerEntity> pageBean);
    /**
     * 批量删除
     * @param Ids
     */
    public void deleteAllSysVolunteer(String[] Ids);

}
