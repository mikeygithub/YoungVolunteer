package com.mikey.youngvolunteer.model;

import javax.persistence.*;
import java.util.Set;

/**
 * @Program: Ped_Moni_Gen
 * @Author: 麦奇
 * @Email： 1625017540@qq.com
 * @Create: 2019-06-17 10:38
 * @Describe：
 **/
@Entity
@Table(name = "sys_menu", schema = "msosle", catalog = "")
public class SysMenuEntity {
    private int id;
    private String roleType;
    private String title;
    private String icon;
    private String href;
    private Byte spread;
    private Set<SysMenuChildrenEntity> children;

    @Id
    @Column(name = "id", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    @Basic
    @Column(name = "roleType", nullable = false, length = 255)
    public String getRoleType() {
        return roleType;
    }

    public void setRoleType(String roleType) {
        this.roleType = roleType;
    }

    @Basic
    @Column(name = "title", nullable = false, length = 255)
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Basic
    @Column(name = "icon", nullable = false, length = 255)
    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    @Basic
    @Column(name = "href", nullable = false, length = 255)
    public String getHref() {
        return href;
    }

    public void setHref(String href) {
        this.href = href;
    }

    @Basic
    @Column(name = "spread", nullable = true)
    public Byte getSpread() {
        return spread;
    }

    public void setSpread(Byte spread) {
        this.spread = spread;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SysMenuEntity that = (SysMenuEntity) o;

        if (id != that.id) return false;
        if (title != null ? !title.equals(that.title) : that.title != null) return false;
        if (icon != null ? !icon.equals(that.icon) : that.icon != null) return false;
        if (href != null ? !href.equals(that.href) : that.href != null) return false;
        if (spread != null ? !spread.equals(that.spread) : that.spread != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (title != null ? title.hashCode() : 0);
        result = 31 * result + (icon != null ? icon.hashCode() : 0);
        result = 31 * result + (href != null ? href.hashCode() : 0);
        result = 31 * result + (spread != null ? spread.hashCode() : 0);
        return result;
    }

    @OneToMany(mappedBy = "fatherMenu")
    public Set<SysMenuChildrenEntity> getChildren() {
        return children;
    }

    public void setChildren(Set<SysMenuChildrenEntity> children) {
        this.children = children;
    }
}
