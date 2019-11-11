package com.fehead.open.admin.dao.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

/**
 * @Description:
 * @Author lmwis
 * @Date 2019-10-16 19:31
 * @Version 1.0
 */
@TableName("admin_info")
public class Admin {
    @TableId(type = IdType.AUTO)
    private long id;

    @TableField("username")
    private String username;

    @TableField("password")
    private String password;

    @TableField("authorities")
    private String authorities;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAuthorities() {
        return authorities;
    }

    public void setAuthorities(String authorities) {
        this.authorities = authorities;
    }
}
