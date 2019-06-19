package com.mikey.youngvolunteer.action.member;

import com.mikey.youngvolunteer.common.PageBean;
import com.mikey.youngvolunteer.model.AssociationMemberEntity;
import com.mikey.youngvolunteer.model.CollegesEntity;
import com.mikey.youngvolunteer.model.SysUserEntity;
import com.mikey.youngvolunteer.service.colleges.CollegesService;
import com.mikey.youngvolunteer.service.member.MemberService;
import com.mikey.youngvolunteer.service.sysuser.SysUserService;
import com.mikey.youngvolunteer.vo.R;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;


/**
 * @Program: YoungVolunteer
 * @Author: 麦奇
 * @Email： 1625017540@qq.com
 * @Create: 2019-06-05 09:24
 * @Describe：
 **/
public class MemberAction extends ActionSupport implements ModelDriven<AssociationMemberEntity> {

    @Autowired
    private MemberService memberService;
    @Autowired
    private SysUserService sysUserService;
    //日志
    private static Logger logger = Logger.getLogger(AssociationMemberEntity.class);
    //模型驱动
    private AssociationMemberEntity associationMemberEntity = new AssociationMemberEntity();
    //
    private PageBean<AssociationMemberEntity> pageBean = new PageBean<>();
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


    /**
     * 添加
     */
    public String save() {


        logger.info("ASS:"+associationMemberEntity);
        memberService.save(associationMemberEntity);

        SysUserEntity sysUserEntity = new SysUserEntity();
        sysUserEntity.setUserId((int) System.currentTimeMillis());
        sysUserEntity.setLoginAccount(associationMemberEntity.getMemberCode());
        sysUserEntity.setLoginPassword("123456");
        sysUserEntity.setRoleType(2);
        sysUserEntity.setUserName(associationMemberEntity.getMemberName());
        sysUserEntity.setUserAvailable(1);

        sysUserEntity.setAssociation_member(associationMemberEntity);
        sysUserService.saveUser(sysUserEntity);

        r = R.ok();

        return SUCCESS;
    }

    /**
     * 删除
     */
    public String delete() {

        memberService.delete(associationMemberEntity);

        r = R.ok();

        return SUCCESS;
    }

    /**
     * 修改
     */
    public String update() {

        memberService.update(associationMemberEntity);

        r = R.ok();

        return SUCCESS;
    }

    /**
     * 查询
     *
     * @return
     */
    public String findById() {

        AssociationMemberEntity byId = memberService.findById(associationMemberEntity);

        r = R.ok().put("data", byId);

        return SUCCESS;
    }

    /**
     * 查询
     */
    public String findByPage() {

        PageBean byPage = memberService.findByPage(key, new PageBean<AssociationMemberEntity>().setCurrPage(page).setPageSize(limit));

        r = R.ok().put("data", byPage.getRows()).put("count", byPage.getTotal());

        logger.info("查询列表：" + r);

        return SUCCESS;
    }

    /**
     * 批量删除
     */
    public String deleteBatch() {


        String[] id = ids.split(",");


        memberService.deleteBatch(id);

        r = R.ok();

        return SUCCESS;
    }

    @Override
    public AssociationMemberEntity getModel() {
        return associationMemberEntity;
    }

    public AssociationMemberEntity getAssociationMemberEntity() {
        return associationMemberEntity;
    }

    public void setAssociationMemberEntity(AssociationMemberEntity associationMemberEntity) {
        this.associationMemberEntity = associationMemberEntity;
    }

    public PageBean<AssociationMemberEntity> getPageBean() {
        return pageBean;
    }

    public void setPageBean(PageBean<AssociationMemberEntity> pageBean) {
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

}
