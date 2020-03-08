package com.zxhan.modular.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zxhan.core.msg.ErrorTip;
import com.zxhan.core.msg.SuccessTip;
import com.zxhan.modular.entity.Repair;
import com.zxhan.modular.mapper.DeptMapper;
import com.zxhan.modular.mapper.RepairMapper;
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
public class RepairController {

    @Autowired
    RepairMapper repairMapper;
    @Autowired
    DeptMapper deptMapper;

    //查找报修
    @ResponseBody
    @RequestMapping("/repair")
    Object repairIndex(Model model,String roomId){
        Map map=new HashMap();
        //构造查询条件
        QueryWrapper wrapper=null;
        if(roomId!=null&&!roomId.equals("")){
            wrapper=new QueryWrapper();
            wrapper.like("room_id",roomId);
        }
        //查找
        List repairs = repairMapper.selectList(wrapper);
        map.put("code",0);
        map.put("msg","");
        map.put("count",repairs.size());
        map.put("data",repairs);
        return map;
    }

    @RequestMapping("/student/repair.html")
    Object repairStu(Model model){
        List list=new ArrayList();
        QueryWrapper wrapper=new QueryWrapper();
        wrapper.ne("pids","0");
        List depts = deptMapper.selectList(wrapper);
        model.addAttribute("depts",depts);
        return "/student/repair.html";
    }

    @RequestMapping("/student/repair/add")
    Object visitor(Model model){
        List list=new ArrayList();
        QueryWrapper wrapper=new QueryWrapper();
        wrapper.ne("pids","0");
        List depts = deptMapper.selectList(wrapper);
        model.addAttribute("depts",depts);
        return "/student/add/repair.html";
    }

    //处理维修记录
    @ResponseBody
    @RequestMapping("/suguan/repairList/deal")
    Object del(String id){
        Repair repair = repairMapper.selectById(id);
        repair.setStatus("已处理");
        if(repair.updateById()==true){
            return new SuccessTip();
        }else{
            return new ErrorTip(500, "处理失败");
        }
    }

    @ResponseBody
    @RequestMapping("/suguan/repairList/add")
    Object addRepair(Repair repair){
        repair.setStatus("已提交");
        if(repair.insert()==true){
            return new SuccessTip();
        }else{
            return new ErrorTip(500,"插入失败");
        }
    }



}
