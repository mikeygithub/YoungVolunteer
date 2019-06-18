package com.mikey.youngvolunteer.service.colleges;

import com.mikey.youngvolunteer.common.PageBean;
import com.mikey.youngvolunteer.model.CollegesEntity;

/**
 * @Program: YoungVolunteer
 * @Author: 麦奇
 * @Email： 1625017540@qq.com
 * @Create: 2019-06-04 00:09
 * @Describe：
 **/
public interface CollegesService {
    /**
     * 添加
     */
    public void save(CollegesEntity collegesEntity);

    /**
     * 删除
     */
    public void delete(CollegesEntity collegesEntity);

    /**
     * 修改
     */
    public void update(CollegesEntity collegesEntity);

    /**
     * 查询
     *
     * @return
     */
    public CollegesEntity findById(CollegesEntity collegesEntity);

    /**
     * 查询
     *
     * @param key
     * @param pageBean
     * @return
     */
    public PageBean findByPage(String key, PageBean<CollegesEntity> pageBean);

    /**
     * 批量删除
     *
     * @param Ids
     */
    public void deleteBatch(String[] Ids);
}
