package com.zxhan.modular.controller;


import com.zxhan.core.msg.ErrorTip;
import com.zxhan.core.msg.SuccessTip;
import com.zxhan.modular.entity.Role;
import com.zxhan.modular.mapper.RoleMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 角色表 前端控制器
 * </p>
 *
 * @author
 * @since 2020-02-19
 */
@Controller
public class RoleController {
    @Autowired
    RoleMapper roleMapper;
    @ResponseBody
    @RequestMapping("/role")
    Object roleList(){
        Map map=new HashMap();
        List<Role> roles = roleMapper.selectList(null);
        map.put("code",0);
        map.put("msg","");
        map.put("count",roles.size());
        map.put("data",roles);
        return map;
    }

    //跳转到修改角色界面
    @RequestMapping("/suguan/role/edit")
    Object menuEdit(String id,Model model){
        model.addAttribute("role",roleMapper.selectById(id));
        return "suguan/edit/role.html";
    }

    //修改菜单
    @ResponseBody
    @RequestMapping("/suguan/roleList/edit")
    Object edit(Role role){
        if(role.updateById()==true){
            return new SuccessTip();
        }else{
            return new ErrorTip(500,"插入失败");
        }
    }

}
