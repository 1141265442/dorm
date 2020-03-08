package com.zxhan.modular.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zxhan.core.msg.ErrorTip;
import com.zxhan.core.msg.SuccessTip;
import com.zxhan.modular.entity.Dept;
import com.zxhan.modular.entity.Visitor;
import com.zxhan.modular.mapper.DeptMapper;
import com.zxhan.modular.mapper.VisitorMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author
 * @since 2020-02-19
 */
@Controller
public class VisitorController {

    @Autowired
    VisitorMapper visitorMapper;
    @Autowired
    DeptMapper deptMapper;

    //跳转到添加寝室页面
    @RequestMapping("/suguan/visitor/add")
    Object visitor(Model model){
        List list=new ArrayList();
        QueryWrapper<Dept> wrapper=new QueryWrapper();
        wrapper.ne("pids","0");
        List<Dept> depts = deptMapper.selectList(wrapper);
        model.addAttribute("depts",depts);
        return "/suguan/add/visitor.html";
    }

    //添加寝室
    @ResponseBody
    @RequestMapping("/suguan/visitorList/add")
    Object addVisitor(Visitor visitor){
        if(visitor.insert()==true){
            return new SuccessTip();
        }else{
            return new ErrorTip(500,"插入失败");
        }
    }

    //跳转到修改访客记录界面
    @RequestMapping("/suguan/visitor/edit")
    Object menuEdit(String id,Model model){
        model.addAttribute("visitor",visitorMapper.selectById(id));
        return "suguan/edit/visitor.html";
    }
    //
    //修改访客记录
    @ResponseBody
    @RequestMapping("/suguan/visitorList/edit")
    Object edit(Visitor visitor){
        if(visitor.updateById()==true){
            return new SuccessTip();
        }else{
            return new ErrorTip(500,"插入失败");
        }
    }

    //删除访客记录
    @ResponseBody
    @RequestMapping("/suguan/visitorList/del")
    Object del(String id){
        if(visitorMapper.deleteById(id)==1){
            return new SuccessTip();
        }else{
            return new ErrorTip(500, "删除失败");
        }
    }

    //查找访客记录
    @ResponseBody
    @RequestMapping("/visitor")
    Object visitorIndex(Model model,String roomId){
        Map map=new HashMap();//封装返回的数据
        //构造查询条件
        QueryWrapper wrapper=null;
        if(roomId!=null&&!roomId.equals("")){
            wrapper=new QueryWrapper();
            wrapper.like("room_id",roomId);
        }
        //查找寝室
        List visitors = visitorMapper.selectList(wrapper);
        map.put("code",0);
        map.put("count",visitors.size());//总条数
        map.put("data",visitors);//将宿舍楼放入data中
        return map;
    }

}
