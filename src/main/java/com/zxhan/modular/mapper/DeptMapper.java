package com.zxhan.modular.mapper;

import com.zxhan.modular.entity.Dept;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 部门表 Mapper 接口
 * </p>
 *
 * @author zxhan
 * @since 2020-02-19
 */
public interface DeptMapper extends BaseMapper<Dept> {
    List<Map> selectDorm(String condition);
    int cancel(Integer id);
}
