package com.zxhan.modular.controller;


import com.zxhan.modular.entity.User;
import com.zxhan.modular.mapper.UserMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

/**
 * <p>
 * 管理员表 前端控制器
 * </p>
 *
 * @author zxhan
 * @since 2020-01-20
 */
@Slf4j
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserMapper userMapper;

    @RequestMapping
    public Object getList(String name){
        log.info("查询用户");
        List<Map> maps = userMapper.selectStudent(name);
        Map map=new HashMap();
        map.put("code",0);
        map.put("msg","");
        map.put("count",maps.size());
        map.put("data",maps);
        return map;
    }

    @RequestMapping("getAllinfo")
    public Object getAllinfo(){
        Map map=new HashMap();
        map.put("code",0);
        map.put("msg","");
        map.put("count",200);
        map.put("data",userMapper.selectStudent(null));
        return map;
    }

}
