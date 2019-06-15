package com.mikey.youngvolunteer.service.login;

import com.mikey.youngvolunteer.model.SysUserEntity;
import com.mikey.youngvolunteer.vo.R;

/**
 * @Program: YoungVolunteer
 * @Author: 麦奇
 * @Email： 1625017540@qq.com
 * @Create: 2019-06-01 23:13
 * @Describe：
 **/
public interface LoginService {
    /**
     * 用户登入
     * @param sysUserEntity
     * @return
     */
    public R userLogin(SysUserEntity sysUserEntity);

    /**
     * 修改密码
     * @param sysUserEntity
     * @param newPassword
     * @return
     */
    R updatePassword(SysUserEntity sysUserEntity,String newPassword);
}
