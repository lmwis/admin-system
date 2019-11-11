package com.fehead;

import com.fehead.open.admin.dao.AdminMapper;
import com.fehead.open.admin.dao.UserMapper;
import com.fehead.open.admin.dao.pojo.Admin;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @Description:
 * @Author lmwis
 * @Date 2019-10-16 19:19
 * @Version 1.0
 */

@RunWith(SpringRunner.class)
@SpringBootTest
public class MapperTest {

    @Autowired
    UserMapper userMapper;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    AdminMapper adminMapper;

    @Test
    public void whenSelectFromUserInfo(){
        userMapper.selectList(null).forEach(k->{
            System.out.println(new ReflectionToStringBuilder(k));
        });
    }

    @Test
    public void whenCreateAdmin(){
        Admin admin = new Admin();
        admin.setUsername("lmwis");
        admin.setPassword(passwordEncoder.encode("admin"));
        admin.setAuthorities("admin");
//        adminMapper.insert(admin);
    }

}
