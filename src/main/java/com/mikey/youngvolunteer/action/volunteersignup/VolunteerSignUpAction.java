package com.mikey.youngvolunteer.action.volunteersignup;

import com.mikey.youngvolunteer.common.PageBean;
import com.mikey.youngvolunteer.model.SysUserEntity;
import com.mikey.youngvolunteer.model.SysVolunteerEntity;
import com.mikey.youngvolunteer.model.VolunteerActivityEntity;
import com.mikey.youngvolunteer.model.VolunteerSignUpEntity;
import com.mikey.youngvolunteer.service.activity.ActivityService;
import com.mikey.youngvolunteer.service.volunteer.VolunteerService;
import com.mikey.youngvolunteer.service.volunteersignup.VolunteerSignUpService;
import com.mikey.youngvolunteer.vo.R;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import java.sql.Timestamp;
import java.util.Date;

/**
 * @Program: YoungVolunteer
 * @Author: 麦奇
 * @Email： 1625017540@qq.com
 * @Create: 2019-06-05 12:23
 * @Describe：
 **/
public class VolunteerSignUpAction extends ActionSupport implements ModelDriven<VolunteerSignUpEntity> {

    @Autowired
    private VolunteerSignUpService volunteerSignUpService;
    @Autowired
    private VolunteerService volunteerService;
    @Autowired
    private ActivityService activityService;
    //日志
    private static Logger logger = Logger.getLogger(VolunteerSignUpEntity.class);
    //模型驱动
    private VolunteerSignUpEntity volunteerSignUpEntity = new VolunteerSignUpEntity();
    //
    private PageBean<VolunteerSignUpEntity> pageBean = new PageBean<>();
    //返回集
    private R r = new R();
    //搜索值
    private String key;
    //当前页
    private Integer page;
    //大小
    private Integer limit;
    //批量删除id
    private String ids;
    //用户id
    private String userId;


    //////////////////////////////////////////////////////
    /**
     * 添加
     */
    public String saveVolunteerSignUp(){

        //查询志愿者id
        VolunteerActivityEntity volunteerActivityEntity = new VolunteerActivityEntity();
        volunteerActivityEntity.setActivityId(Integer.parseInt(volunteerSignUpEntity.getActivityId()));
        volunteerActivityEntity = activityService.findOneVolunteery(volunteerActivityEntity);

        SysVolunteerEntity volunteery = volunteerService.findOneSysVolunteery(new SysVolunteerEntity().setVolunteerId(Integer.parseInt(userId)));


        volunteerSignUpEntity.setVolunteerId(userId);
        volunteerSignUpEntity.setVolunteerName(volunteery.getVolunteerName());
        volunteerSignUpEntity.setActivityName(volunteerActivityEntity.getActivityTitle());
        volunteerSignUpEntity.setSignUpTime(new Timestamp(System.currentTimeMillis()));
        logger.info("报名："+volunteerSignUpEntity);
        volunteerSignUpService.saveVolunteerSignUp(volunteerSignUpEntity);

        r = R.ok();

        return SUCCESS;
    }

    /**
     * 删除
     */
    public String deleteVolunteerSignUp(){

        volunteerSignUpService.deleteVolunteerSignUp(volunteerSignUpEntity);

        logger.info("删除："+volunteerSignUpEntity);

        r = R.ok();

        return SUCCESS;
    }

    /**
     * 退订
     * @return
     */
    public String outVolunteerSignUp(){

        volunteerSignUpService.outVolunteerSignUp(volunteerSignUpEntity);

        logger.info("退订："+volunteerSignUpEntity);

        r = R.ok();

        return SUCCESS;
    }

    /**
     * 修改
     */
    public String updateVolunteerSignUp(){

        volunteerSignUpService.updateVolunteerSignUp(volunteerSignUpEntity);

        r = R.ok();

        return SUCCESS;
    }

    /**
     * 签到
     * @return
     */
    public String volunteerSignIn(){

        VolunteerSignUpEntity volunteerSignUpEntity = new VolunteerSignUpEntity();

        volunteerSignUpEntity.setSignUpId(this.volunteerSignUpEntity.getSignUpId());

        logger.info(volunteerSignUpEntity);

        VolunteerSignUpEntity oneVolunteerSignUp = volunteerSignUpService.findOneVolunteerSignUp(volunteerSignUpEntity);

        //1：签到
        oneVolunteerSignUp.setSignIn(1);
        oneVolunteerSignUp.setSignInTime(new Timestamp(System.currentTimeMillis()));

        volunteerSignUpService.updateVolunteerSignUp(oneVolunteerSignUp);

        r = R.ok();

        return SUCCESS;
    }

    /**
     * 取消签到
     * @return
     */
    public String volunteerSignOut(){

        VolunteerSignUpEntity volunteerSignUpEntity = new VolunteerSignUpEntity();

        volunteerSignUpEntity.setSignUpId(this.volunteerSignUpEntity.getSignUpId());

        VolunteerSignUpEntity oneVolunteerSignUp = volunteerSignUpService.findOneVolunteerSignUp(volunteerSignUpEntity);

        //1：取消签到
        oneVolunteerSignUp.setSignIn(null);
        oneVolunteerSignUp.setSignInTime(null);

        volunteerSignUpService.updateVolunteerSignUp(oneVolunteerSignUp);

        r = R.ok();

        return SUCCESS;
    }
    /**
     * 查询
     * @return
     */
    public String findOneVolunteerSignUp(){

        VolunteerSignUpEntity oneVolunteerSignUp = volunteerSignUpService.findOneVolunteerSignUp(volunteerSignUpEntity);

        r = R.ok().put("data",oneVolunteerSignUp);

        return SUCCESS;
    }

    /**
     * 查询已签到
     */
    public String findAllVolunteerSignUp(){

        PageBean allUser = volunteerSignUpService.findAllVolunteerSignUp(key, new PageBean<VolunteerSignUpEntity>().setCurrPage(page).setPageSize(limit));

        r = R.ok().put("data",allUser.getRows()).put("count",allUser.getRows().size());

        return SUCCESS;
    }

    /**
     * 查询未签到
     * @return
     */
    public String findAllVolunteerNoSignUp(){

        PageBean allUser = volunteerSignUpService.findAllVolunteerNoSignUp(key, new PageBean<VolunteerSignUpEntity>().setCurrPage(page).setPageSize(limit));

        r = R.ok().put("data",allUser.getRows()).put("count",allUser.getTotal());

        return SUCCESS;
    }

    /**
     * 查询已评分
     * @return
     */
    public String findAllVolunteerScore(){

        PageBean allUser = volunteerSignUpService.findAllVolunteerScore(key, new PageBean<VolunteerSignUpEntity>().setCurrPage(page).setPageSize(limit));

        r = R.ok().put("data",allUser.getRows()).put("count",allUser.getTotal());

        return SUCCESS;
    }

    /**
     * 查询未评分
     * @return
     */
    public String findAllVolunteerNoScore(){

        PageBean allUser = volunteerSignUpService.findAllVolunteerNoScore(key, new PageBean<VolunteerSignUpEntity>().setCurrPage(page).setPageSize(limit));

        r = R.ok().put("data",allUser.getRows()).put("count",allUser.getTotal());

        return SUCCESS;
    }

    /**
     * 批量删除
     */
    public String deleteAllVolunteerSignUp(){


        String[] id = ids.split(",");

        volunteerSignUpService.deleteAllVolunteerSignUp(id);

        r = R.ok();

        return SUCCESS;
    }



    //////////////////////////////////////////////////////

    @Override
    public VolunteerSignUpEntity getModel() {

        return volunteerSignUpEntity;

    }

    public VolunteerSignUpEntity getVolunteerSignUpEntity() {
        return volunteerSignUpEntity;
    }

    public void setVolunteerSignUpEntity(VolunteerSignUpEntity volunteerSignUpEntity) {
        this.volunteerSignUpEntity = volunteerSignUpEntity;
    }

    public PageBean<VolunteerSignUpEntity> getPageBean() {
        return pageBean;
    }

    public void setPageBean(PageBean<VolunteerSignUpEntity> pageBean) {
        this.pageBean = pageBean;
    }

    public R getR() {
        return r;
    }

    public void setR(R r) {
        this.r = r;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getLimit() {
        return limit;
    }

    public void setLimit(Integer limit) {
        this.limit = limit;
    }

    public String getIds() {
        return ids;
    }

    public void setIds(String ids) {
        this.ids = ids;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
