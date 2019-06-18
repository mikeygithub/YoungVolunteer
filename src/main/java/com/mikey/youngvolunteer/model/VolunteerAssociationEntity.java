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
@Table(name = "volunteer_association", schema = "young_volunteer", catalog = "")
public class VolunteerAssociationEntity {
    private int id;
    private String associationCode;
    private String associationName;

    @Id
    @Column(name = "id", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "association_code", nullable = false, length = 255)
    public String getAssociationCode() {
        return associationCode;
    }

    public void setAssociationCode(String associationCode) {
        this.associationCode = associationCode;
    }

    @Basic
    @Column(name = "association_name", nullable = false, length = 255)
    public String getAssociationName() {
        return associationName;
    }

    public void setAssociationName(String associationName) {
        this.associationName = associationName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        VolunteerAssociationEntity that = (VolunteerAssociationEntity) o;

        if (id != that.id) return false;
        if (associationCode != null ? !associationCode.equals(that.associationCode) : that.associationCode != null)
            return false;
        if (associationName != null ? !associationName.equals(that.associationName) : that.associationName != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (associationCode != null ? associationCode.hashCode() : 0);
        result = 31 * result + (associationName != null ? associationName.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "VolunteerAssociationEntity{" +
                "id=" + id +
                ", associationCode='" + associationCode + '\'' +
                ", associationName='" + associationName + '\'' +
                '}';
    }
}
