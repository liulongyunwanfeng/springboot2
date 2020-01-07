package com.django.springboot2.pojo.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.django.springboot2.Enum.SexEnum;
import com.django.springboot2.pojo.vo.UserVO;
import org.apache.ibatis.type.Alias;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.io.Serializable;
import java.util.List;

/**
 * @author liulongyun
 * @create 2019/5/29 12:45
 **/
@Alias(value = "user") //mybatis 指定别名
@TableName(value = "t_user") //mybatisplus 指定的表名
@Document
public class User implements Serializable {

    private static final long serialVersionUID = 123456789l;
    @TableId(type = IdType.AUTO)
    private Long id = null;
    @Field("user_name")
    private String userName=null;

    // 使用mybatis 时 这个枚举类的封装需要用到typeHandler
    private SexEnum sex = null;
    private String note = null;

    private  Integer available;

    private String pwd;

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

    private  List<Role> roles = null;

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    public User() {
    }

    public User(UserVO userVO) {

        this.id= userVO.getId();
        this.userName=userVO.getUserName();
        this.note=userVO.getNote();

        this.pwd = userVO.getPwd();
        this.available = userVO.getAvailable();

        if(SexEnum.MALE.getId()==userVO.getSex()){
            this.sex = SexEnum.MALE;
        }else if(SexEnum.FEMALE.getId()==userVO.getSex()){
            this.sex = SexEnum.FEMALE;
        }

        this.roles = userVO.getRoles();

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

    public SexEnum getSex() {
        return sex;
    }

    public void setSex(SexEnum sex) {
        this.sex = sex;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
}
