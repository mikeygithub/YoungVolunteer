package com.mikey.youngvolunteer.action.association;

import com.mikey.youngvolunteer.common.PageBean;
import com.mikey.youngvolunteer.model.CollegesEntity;
import com.mikey.youngvolunteer.model.VolunteerAssociationEntity;
import com.mikey.youngvolunteer.service.association.AssociationService;
import com.mikey.youngvolunteer.service.colleges.CollegesService;
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
public class AssociationAction extends ActionSupport implements ModelDriven<VolunteerAssociationEntity> {

    @Autowired
    private AssociationService associationService;
    //日志
    private static Logger logger = Logger.getLogger(VolunteerAssociationEntity.class);
    //模型驱动
    private VolunteerAssociationEntity volunteerAssociationEntity = new VolunteerAssociationEntity();
    //
    private PageBean<VolunteerAssociationEntity> pageBean = new PageBean<>();
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

    /////////////////////////////////////////

    /**
     * 添加
     */
    public String save() {

        logger.info("保存："+volunteerAssociationEntity);

        associationService.save(volunteerAssociationEntity);

        r = R.ok();

        return SUCCESS;
    }

    /**
     * 删除
     */
    public String delete() {

        associationService.delete(volunteerAssociationEntity);

        r = R.ok();

        return SUCCESS;
    }

    /**
     * 修改
     */
    public String update() {

        associationService.update(volunteerAssociationEntity);

        r = R.ok();

        return SUCCESS;
    }

    /**
     * 查询
     *
     * @return
     */
    public String findById() {
        VolunteerAssociationEntity byId = associationService.findById(volunteerAssociationEntity);

        r = R.ok().put("data", byId);

        return SUCCESS;
    }

    /**
     * 查询
     */
    public String findByPage() {

        PageBean byPage = associationService.findByPage(key, new PageBean<VolunteerAssociationEntity>().setCurrPage(page).setPageSize(limit));

        r = R.ok().put("data", byPage.getRows()).put("count", byPage.getTotal());

        logger.info("查询列表：" + r);

        return SUCCESS;
    }

    /**
     * 批量删除
     */
    public String deleteBatch() {


        String[] id = ids.split(",");


        associationService.deleteBatch(id);

        r = R.ok();

        return SUCCESS;
    }

    @Override
    public VolunteerAssociationEntity getModel() {
        return volunteerAssociationEntity;
    }

    public VolunteerAssociationEntity getVolunteerAssociationEntity() {
        return volunteerAssociationEntity;
    }

    public void setVolunteerAssociationEntity(VolunteerAssociationEntity volunteerAssociationEntity) {
        this.volunteerAssociationEntity = volunteerAssociationEntity;
    }

    public PageBean<VolunteerAssociationEntity> getPageBean() {
        return pageBean;
    }

    public void setPageBean(PageBean<VolunteerAssociationEntity> pageBean) {
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
/////////////////////////////////////////

}
