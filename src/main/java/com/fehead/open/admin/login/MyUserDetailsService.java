package com.fehead.open.admin.login;

import com.fehead.open.admin.dao.AdminMapper;
import com.fehead.open.admin.dao.PasswordMapper;
import com.fehead.open.admin.dao.pojo.Admin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

/**
 * @author lmwis
 * @description:
 * @date 2019-08-29 16:08
 * @Version 1.0
 */

@Component
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    AdminMapper userMapper;
    @Autowired
    PasswordMapper passwordMapper;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {

        Admin admin = userMapper.selectByUsername(s);
        if(admin == null){
            throw new UsernameNotFoundException("用户名或密码不正确");
        }

        return new org.springframework.security.core.userdetails.User(String.valueOf(admin.getId())
                ,admin.getPassword(), AuthorityUtils.createAuthorityList("admin"));
    }
}
