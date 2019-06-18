package com.mikey.youngvolunteer.dao.member;

import com.mikey.youngvolunteer.common.PageBean;
import com.mikey.youngvolunteer.model.AssociationMemberEntity;
import com.mikey.youngvolunteer.model.CollegesEntity;

/**
 * @Program: YoungVolunteer
 * @Author: 麦奇
 * @Email： 1625017540@qq.com
 * @Create: 2019-06-04 00:09
 * @Describe：
 **/
public interface MemberDao {
    /**
     * 添加
     */
    public void save(AssociationMemberEntity associationMemberEntity);

    /**
     * 删除
     */
    public void delete(AssociationMemberEntity associationMemberEntity);

    /**
     * 修改
     */
    public void update(AssociationMemberEntity associationMemberEntity);

    /**
     * 查询
     *
     * @return
     */
    public AssociationMemberEntity findById(AssociationMemberEntity associationMemberEntity);

    /**
     * 查询
     *
     * @param key
     * @param pageBean
     * @return
     */
    public PageBean findByPage(String key, PageBean<AssociationMemberEntity> pageBean);

    /**
     * 批量删除
     *
     * @param Ids
     */
    public void deleteBatch(String[] Ids);
}
