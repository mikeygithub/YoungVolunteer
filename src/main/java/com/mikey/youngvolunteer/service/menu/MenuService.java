package com.mikey.youngvolunteer.service.menu;

import com.mikey.youngvolunteer.common.PageBean;
import com.mikey.youngvolunteer.model.SysMenuEntity;

import java.util.List;

/**
 * @Program: YoungVolunteer
 * @Author: 麦奇
 * @Email： 1625017540@qq.com
 * @Create: 2019-06-04 00:09
 * @Describe：
 **/
public interface MenuService {
    /**
     * 添加
     */
    public void save(SysMenuEntity sysMenuEntity);

    /**
     * 删除
     */
    public void delete(SysMenuEntity sysMenuEntity);

    /**
     * 修改
     */
    public void update(SysMenuEntity sysMenuEntity);

    /**
     * 查询
     *
     * @return
     */
    public SysMenuEntity findById(SysMenuEntity sysMenuEntity);

    /**
     * 查询
     *
     * @param key
     * @param pageBean
     * @return
     */
    public PageBean findByPage(String key, PageBean<SysMenuEntity> pageBean);

    /**
     * 批量删除
     *
     * @param Ids
     */
    public void deleteBatch(String[] Ids);


    List<SysMenuEntity> getMenuByRoleType(String roleType);
}
