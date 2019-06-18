package com.mikey.youngvolunteer.model;

import javax.persistence.*;

/**
 * @Program: Ped_Moni_Gen
 * @Author: 麦奇
 * @Email： 1625017540@qq.com
 * @Create: 2019-06-18 15:52
 * @Describe：
 **/
@Entity
@Table(name = "association_member", schema = "young_volunteer", catalog = "")
public class AssociationMemberEntity {
    private int id;
    private String memberCode;
    private String memberName;
    private SysUserEntity user;
    private CollegesEntity college;

    @Id
    @Column(name = "id", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "member_code", nullable = false, length = 255)
    public String getMemberCode() {
        return memberCode;
    }

    public void setMemberCode(String memberCode) {
        this.memberCode = memberCode;
    }

    @Basic
    @Column(name = "member_name", nullable = false, length = 255)
    public String getMemberName() {
        return memberName;
    }

    public void setMemberName(String memberName) {
        this.memberName = memberName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AssociationMemberEntity that = (AssociationMemberEntity) o;

        if (id != that.id) return false;
        if (memberCode != null ? !memberCode.equals(that.memberCode) : that.memberCode != null) return false;
        if (memberName != null ? !memberName.equals(that.memberName) : that.memberName != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (memberCode != null ? memberCode.hashCode() : 0);
        result = 31 * result + (memberName != null ? memberName.hashCode() : 0);
        return result;
    }

    @OneToOne(mappedBy = "association_member")
    public SysUserEntity getUser() {
        return user;
    }

    public void setUser(SysUserEntity user) {
        this.user = user;
    }

    @OneToOne(mappedBy = "ossocation")
    public CollegesEntity getCollege() {
        return college;
    }

    public void setCollege(CollegesEntity college) {
        this.college = college;
    }
}
