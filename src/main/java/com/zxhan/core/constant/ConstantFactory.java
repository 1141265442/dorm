package com.zxhan.core.constant;

import com.zxhan.core.util.SpringContextHolder;
import com.zxhan.modular.entity.User;
import com.zxhan.modular.mapper.UserMapper;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@DependsOn("springContextHolder")
public class ConstantFactory {
    private UserMapper userMapper = SpringContextHolder.getBean(UserMapper.class);

    public static ConstantFactory me() {
        return SpringContextHolder.getBean("constantFactory");
    }

    public List<User> selectAllUser(){
        return userMapper.selectList(null);
    }
}
