package com.zxhan.modular.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zxhan.core.msg.ErrorTip;
import com.zxhan.core.msg.SuccessTip;
import com.zxhan.modular.entity.Energy;
import com.zxhan.modular.mapper.DeptMapper;
import com.zxhan.modular.mapper.EnergyMapper;
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
 * @author zxhan
 * @since 2020-02-19
 */
@Controller
public class EnergyController {

    @Autowired
    EnergyMapper energyMapper;

    @Autowired
    DeptMapper deptMapper;

    @RequestMapping("/suguan/energy/add")
    Object visitor(Model model){
        List list=new ArrayList();
        QueryWrapper wrapper=new QueryWrapper();
        wrapper.ne("pids","0");
        List depts = deptMapper.selectList(wrapper);
        model.addAttribute("depts",depts);
        return "/suguan/add/energy.html";
    }

    @ResponseBody
    @RequestMapping("/energy")
    Object energy(String type,String roomId){
        Map map=new HashMap();//封装返回的数据
        //构造查询条件
        QueryWrapper wrapper=null;
        if(type!=null&&!type.equals("")){
            wrapper=new QueryWrapper();
            wrapper.like("energy_type",type);
        }else if(roomId!=null&&!roomId.equals("")){
            wrapper=new QueryWrapper();
            wrapper.like("room_id",roomId);
        }
        //查找寝室
        List energy = energyMapper.selectList(wrapper);
        map.put("code",0);
        map.put("count",energy.size());//总条数
        map.put("data",energy);//将宿舍楼放入data中
        return map;
    }

    @RequestMapping("/student/energy.html")
    Object energyStu(Model model){
        List list=new ArrayList();
        QueryWrapper wrapper=new QueryWrapper();
        wrapper.ne("pids","0");
        List depts = deptMapper.selectList(wrapper);
        model.addAttribute("depts",depts);
        return "/student/energy.html";
    }

    @ResponseBody
    @RequestMapping("/suguan/energyList/add")
    Object addDorm(Energy energy){
        energy.setPay("否");
        if(energy.insert()==true){
            return new SuccessTip();
        }else{
            return new ErrorTip(500,"插入失败");
        }
    }

    @ResponseBody
    @RequestMapping("/suguan/energyList/del")
    Object del(String id){
        if(energyMapper.deleteById(id)==1){
            return new SuccessTip();
        }else{
            return new ErrorTip(500, "删除失败");
        }
    }

    @ResponseBody
    @RequestMapping("/suguan/energyList/payMoney")
    Object pay(String id){
        Energy energy = energyMapper.selectById(id);
        energy.setPay("已缴费");
        if(energy.updateById()==true){
            return new SuccessTip();
        }else{
            return new ErrorTip(500, "删除失败");
        }
    }



    //跳转到修改水电气页面
    @RequestMapping("/suguan/energy/edit")
    Object deptEditPage(String id,Model model){
        Energy energy = energyMapper.selectById(id);
        model.addAttribute("map",energy);
        return "suguan/edit/energy.html";
    }

    //修改水电气
    @ResponseBody
    @RequestMapping("/suguan/energyList/edit")
    Object edit(Energy energy){
        if(energy.updateById()==true){
            return new SuccessTip();
        }else{
            return new ErrorTip(500,"插入失败");
        }
    }




}
