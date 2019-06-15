package com.mikey.youngvolunteer.dao.login;

import com.mikey.youngvolunteer.model.SysUserEntity;
import com.mikey.youngvolunteer.vo.R;

/**
 * @Program: Ped_Moni
 * @Author: 麦奇
 * @Email： 1625017540@qq.com
 * @Create: 2019-05-01 20:55
 * @Describe：
 **/
public interface LoginDao {

    /**
     * 用户登入
     * @param sysUserEntity
     * @return
     */
    R userLogin(SysUserEntity sysUserEntity);

    /**
     * 修改密码
     * @param sysUserEntity
     * @return
     */
    R updatePassword(SysUserEntity sysUserEntity, String newPassword);

}
