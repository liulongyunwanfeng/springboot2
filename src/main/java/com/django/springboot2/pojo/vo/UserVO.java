package com.django.springboot2.pojo.vo;

import com.django.springboot2.Enum.SexEnum;
import com.django.springboot2.pojo.domain.Role;
import com.django.springboot2.pojo.domain.User;

import java.io.Serializable;
import java.util.List;

/**
 * @author liulongyun
 * @create 2019/5/29 12:45
 **/
public class UserVO implements Serializable {
    private Long id = null;
    private String userName=null;
    private int sex = 1;
    private String note = null;

    private List<Role> roles = null;


    private  Integer available;

    private String pwd;


    public UserVO() {
        super();
    }

    public UserVO(User user) {
        this.id=user.getId();
        this.userName=user.getUserName();
        this.note=user.getNote();
        this.pwd = user.getPwd();
        this.available = user.getAvailable();

        if(user.getSex().getId()== SexEnum.MALE.getId()){
            this.sex=SexEnum.MALE.getId();
        }else if(user.getSex().getId()== SexEnum.FEMALE.getId()){
            this.sex=SexEnum.FEMALE.getId();
        }

        this.roles = user.getRoles();
    }


    public Integer getAvailable() {
        return available;
    }

    public void setAvailable(Integer available) {
        this.available = available;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
}
