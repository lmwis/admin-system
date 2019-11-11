package com.fehead.open.admin.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.fehead.open.admin.dao.pojo.Admin;
import org.apache.ibatis.annotations.Select;

/**
 * @Description:
 * @Author lmwis
 * @Date 2019-10-16 19:30
 * @Version 1.0
 */
public interface AdminMapper extends BaseMapper<Admin> {

    @Select("select * from admin_info where username=#{username}")
    public Admin selectByUsername(String username);

}
