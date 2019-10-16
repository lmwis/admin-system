package com.fehead.controller;

import com.fehead.dao.UserMapper;
import com.fehead.dao.pojo.User;
import com.fehead.error.BusinessException;
import com.fehead.error.EmBusinessError;
import com.fehead.response.CommonReturnType;
import com.fehead.response.FeheadResponse;
import com.fehead.response.MetronicDatatableType;
import com.fehead.response.MetronicMeta;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Description:
 * @Author lmwis
 * @Date 2019-10-16 19:27
 * @Version 1.0
 */
@RestController("UserController")
@CrossOrigin(allowedHeaders="*",allowCredentials = "true")
public class UserController {

    @Autowired
    UserMapper userMapper;

    @GetMapping("/users/list")
    public FeheadResponse userList(@PageableDefault(size = 10,page=1) Pageable pageable){


        List<User> users = userMapper.selectList(null);

        int totalPage = users.size()/pageable.getPageSize()+1;

        return MetronicDatatableType.create(users,generatorMeta(pageable,totalPage,users.size()));
    }

    @GetMapping("/user/{id}")
    public FeheadResponse user(@PathVariable("id") long id) throws BusinessException {

        if(id==0){
            throw new BusinessException(EmBusinessError.PARAMETER_VALIDATION_ERROR);
        }

        User user = userMapper.selectById(id);


        return CommonReturnType.create(user);
    }
    @PutMapping("/user")
    public FeheadResponse userUpter(@PathVariable("id") long id) throws BusinessException {

        if(id==0){
            throw new BusinessException(EmBusinessError.PARAMETER_VALIDATION_ERROR);
        }

        User user = userMapper.selectById(id);


        return CommonReturnType.create(user);
    }

    private MetronicMeta generatorMeta(Pageable pageable,Integer totalPage,Integer size){
        MetronicMeta meta = new MetronicMeta();
        meta.setPage(pageable.getPageNumber());
        meta.setPerpage(pageable.getPageSize());
        meta.setSort("asc");
        meta.setTotal(size);
        meta.setPages(totalPage);
        meta.setField("id");
        return meta;
    }


}
