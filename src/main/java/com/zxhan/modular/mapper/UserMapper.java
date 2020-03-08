package com.zxhan.modular.mapper;

import com.zxhan.modular.entity.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 管理员表 Mapper 接口
 * </p>
 *
 * @author zxhan
 * @since 2020-02-19
 */
public interface UserMapper extends BaseMapper<User> {
    List<Map> selectStudent(String name);

}
