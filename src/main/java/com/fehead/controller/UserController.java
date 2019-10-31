package com.fehead.controller;

import com.fehead.dao.UserMapper;
import com.fehead.dao.pojo.User;
import com.fehead.error.BusinessException;
import com.fehead.error.EmBusinessError;
import com.fehead.response.CommonReturnType;
import com.fehead.response.FeheadResponse;
import com.fehead.response.MetronicDatatableType;
import com.fehead.response.MetronicMeta;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.util.List;

/**
 * @Description:
 * @Author lmwis
 * @Date 2019-10-16 19:27
 * @Version 1.0
 */
@RestController("UserController")
@CrossOrigin(allowedHeaders="*",allowCredentials = "true")
public class UserController extends BaseController{

    @Autowired
    UserMapper userMapper;

    /**
     * 获取所有用户列表
     * @param pageable
     * @return
     */
    @GetMapping("/users/list")
    public FeheadResponse userList(@PageableDefault(size = 10,page=1) Pageable pageable){


        List<User> users = userMapper.selectList(null);

        int totalPage = users.size()/pageable.getPageSize()+1;

        return MetronicDatatableType.create(users,generatorMeta(pageable,totalPage,users.size()));
    }

    /**
     * 根据用户id获取用户信息
     * @param id
     * @return
     * @throws BusinessException
     */
    @GetMapping("/user/{id}")
    public FeheadResponse user(@PathVariable("id") long id) throws BusinessException {

        if(id==0){
            throw new BusinessException(EmBusinessError.PARAMETER_VALIDATION_ERROR);
        }

        User user = userMapper.selectById(id);


        return CommonReturnType.create(user);
    }

    /**
     * 根据用户id修改用户信息
     * @param id
     * @param nickname
     * @param tel
     * @param schoolId
     * @return
     * @throws BusinessException
     */
    @PutMapping("/user/{id}")
    public FeheadResponse userUpdate(@PathVariable("id") long id, String nickname
            , String tel, @RequestParam("school_id") long schoolId, HttpServletRequest request) throws BusinessException {

        Principal userPrincipal = request.getUserPrincipal();
        System.out.println(userPrincipal.getName());
//        sessionStrategy.getAttribute(request,"user");

        if(!validateNull(nickname,tel)){
            throw new BusinessException(EmBusinessError.PARAMETER_VALIDATION_ERROR);
        }
        if(id==0){
            throw new BusinessException(EmBusinessError.PARAMETER_VALIDATION_ERROR);
        }

        // 用户校验
        User valda = userMapper.selectById(id);
        if(valda==null){
            throw new BusinessException(EmBusinessError.USER_NOT_EXIST);
        }
        // 用户合法性
        if(!StringUtils.equals(valda.getTel(),tel)){
            throw new BusinessException(EmBusinessError.USER_NOT_EXIST,"用户信息异常");
        }
        // 数据封装
        valda.setId(id);
        valda.setNickname(nickname);
        valda.setSchoolId(schoolId);

        userMapper.updateById(valda);

        return CommonReturnType.create(valda);
    }

    /**
     * 根据id删除用户
     * @param id
     * @return
     * @throws BusinessException
     */
    @DeleteMapping("user/{id}")
    public FeheadResponse userDel(@PathVariable("id") long id) throws BusinessException {

        if(id==0){
            throw new BusinessException(EmBusinessError.PARAMETER_VALIDATION_ERROR);
        }
        int i = userMapper.deleteById(id);
        return CommonReturnType.create(i);
    }

//    private MetronicMeta generatorMeta(Pageable pageable,Integer totalPage,Integer size){
//        MetronicMeta meta = new MetronicMeta();
//        meta.setPage(pageable.getPageNumber());
//        meta.setPerpage(pageable.getPageSize());
//        meta.setSort("asc");
//        meta.setTotal(size);
//        meta.setPages(totalPage);
//        meta.setField("id");
//        return meta;
//    }


}
