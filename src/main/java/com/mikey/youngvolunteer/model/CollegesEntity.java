package com.mikey.youngvolunteer.model;

import javax.persistence.*;

/**
 * @Program: Ped_Moni_Gen
 * @Author: 麦奇
 * @Email： 1625017540@qq.com
 * @Create: 2019-06-19 09:16
 * @Describe：
 **/
@Entity
@Table(name = "colleges", schema = "young_volunteer", catalog = "")
public class CollegesEntity {
    private int collegesId;
    private String collegesName;
    private String collegesCode;
    private VolunteerAssociationEntity association;

    @Id
    @Column(name = "colleges_id", nullable = false)
    public int getCollegesId() {
        return collegesId;
    }

    public void setCollegesId(int collegesId) {
        this.collegesId = collegesId;
    }

    @Basic
    @Column(name = "colleges_name", nullable = true, length = 255)
    public String getCollegesName() {
        return collegesName;
    }

    public void setCollegesName(String collegesName) {
        this.collegesName = collegesName;
    }

    @Basic
    @Column(name = "colleges_code", nullable = true, length = 255)
    public String getCollegesCode() {
        return collegesCode;
    }

    public void setCollegesCode(String collegesCode) {
        this.collegesCode = collegesCode;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CollegesEntity that = (CollegesEntity) o;

        if (collegesId != that.collegesId) return false;
        if (collegesName != null ? !collegesName.equals(that.collegesName) : that.collegesName != null) return false;
        if (collegesCode != null ? !collegesCode.equals(that.collegesCode) : that.collegesCode != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = collegesId;
        result = 31 * result + (collegesName != null ? collegesName.hashCode() : 0);
        result = 31 * result + (collegesCode != null ? collegesCode.hashCode() : 0);
        return result;
    }

    @OneToOne(mappedBy = "colleges")
    public VolunteerAssociationEntity getAssociation() {
        return association;
    }

    public void setAssociation(VolunteerAssociationEntity association) {
        this.association = association;
    }
}
