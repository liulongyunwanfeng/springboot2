package com.django.springboot2.pojo.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import org.apache.ibatis.type.Alias;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.io.Serializable;

/**
 * @author liulongyun
 * @create 2019/6/4 16:55
 **/
@Document
@Alias("role")
@TableName("t_role")
public class Role implements Serializable {

    private static final long serialVersionUID =  987656789l;

    @TableId(type = IdType.AUTO)
    private Long id;
    @Field("role_name")
    private String roleName = null;
    private String note = null;




    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
}
