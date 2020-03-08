package com.zxhan.modular.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zxhan.core.msg.ErrorTip;
import com.zxhan.core.msg.SuccessTip;
import com.zxhan.modular.entity.User;
import com.zxhan.modular.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/login")
public class LoginController {

    @Autowired
    UserMapper userMapper;
    @RequestMapping
    public Object login(String username,String password){
//        throw new RoadYaoException(TIME_OUT);
        System.out.println(username+password);
        QueryWrapper queryWrapper=new QueryWrapper();
        queryWrapper.eq("username",username);
        List<User> list = userMapper.selectList(queryWrapper);
        if(list.size()==0){
            return new ErrorTip(401,"账号不存在");
        }else{
            for (User user : list) {
                if(user.getPassword().equals(password)){
                    return new SuccessTip(200,user);
                }else{
                    return new ErrorTip(401,"密码错误");
                }
            }

        }
        return new SuccessTip();
    }
}
