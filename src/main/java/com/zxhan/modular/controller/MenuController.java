package com.zxhan.modular.controller;


import com.zxhan.core.msg.ErrorTip;
import com.zxhan.core.msg.SuccessTip;
import com.zxhan.modular.entity.Menu;
import com.zxhan.modular.mapper.MenuMapper;
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
 * 菜单表 前端控制器
 * </p>
 *
 * @author
 * @since 2020-02-19
 */
@Controller
public class MenuController {

    @Autowired
    MenuMapper menuMapper;
    @ResponseBody
    @RequestMapping("/menu")
    Object menuIndex(){
        Map map=new HashMap();
        List<Menu> menus = menuMapper.selectList(null);
        map.put("code",0);
        map.put("msg","");
        map.put("count",menus.size());
        map.put("data",menus);
        return map;
    }

    //跳转到修改菜单界面
    @RequestMapping("/suguan/menu/edit")
    Object menuEdit(String id,Model model){
        model.addAttribute("menu",menuMapper.selectById(id));
        return "suguan/edit/menu.html";
    }

    //修改菜单
    @ResponseBody
    @RequestMapping("/suguan/menuList/edit")
    Object edit(Menu menu){
        if(menu.updateById()==true){
            return new SuccessTip();
        }else{
            return new ErrorTip(500,"插入失败");
        }
    }
}
