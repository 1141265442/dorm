package com.zxhan.modular.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zxhan.core.msg.ErrorTip;
import com.zxhan.core.msg.SuccessTip;
import com.zxhan.modular.entity.Dept;
import com.zxhan.modular.entity.User;
import com.zxhan.modular.mapper.DeptMapper;
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
 * 部门表 前端控制器
 * </p>
 *
 * @author
 * @since 2020-02-19
 */
@Controller
public class DeptController {
    @Autowired
    DeptMapper deptMapper;

    //跳转到修改宿舍楼页面
    @RequestMapping("/suguan/building/edit")
    Object deptEditPage(String id,Model model){
        Dept dept = deptMapper.selectById(id);
        model.addAttribute("dept",dept);
        return "suguan/edit/building.html";
    }

    //跳转到修改寝室页面
    @RequestMapping("/suguan/dorm/edit")
    Object dormEditPage(String id,Model model){

        Dept dept = deptMapper.selectById(id);
        model.addAttribute("dept",dept);
        return "suguan/edit/dormitory.html";
    }

    //获取宿舍楼数据
    @ResponseBody
    @RequestMapping("/suguan/buildingList")
    Object buildingList(String name){
        //封装返回的数据
        Map map=new HashMap();
        map.put("code",0);
        //查找宿舍楼
        QueryWrapper<Dept> queryWrapper=new QueryWrapper();
        queryWrapper.eq("pids","0");//筛选出宿舍楼(pid为0)
        if(name!=null&&!name.trim().equals("")){//模糊查找和宿舍的宿舍楼名
            queryWrapper.like("name",name);
        }
        List<Dept> depts = deptMapper.selectList(queryWrapper);
        map.put("count",depts.size());//总条数
        map.put("data",depts);//将宿舍楼放入data中
       return map;
    }

    //获取寝室数据
    @ResponseBody
    @RequestMapping("/suguan/dormitoryList")
    Object dormitoryList(String condition){
        Map map=new HashMap();//封装返回的数据
        //查找寝室
        List<Map> depts = deptMapper.selectDorm(condition);
        map.put("code",0);
        map.put("count",depts.size());//总条数
        map.put("data",depts);//将宿舍楼放入data中
        return map;
    }

    //添加部门
    @ResponseBody
    @RequestMapping("/suguan/buildingList/add")
    Object add(Dept dept){
        if(dept.insert()==true){
            return new SuccessTip();
        }else{
            return new ErrorTip(500,"插入失败");
        }
    }


    @RequestMapping("/suguan/dormitory/add")
    Object addDormPage(Model model){
        QueryWrapper<Dept> wrapper=new QueryWrapper();
        wrapper.eq("pids","0");
        List<Dept> depts = deptMapper.selectList(wrapper);
        model.addAttribute("list",depts);
        return "suguan/add/dormitory.html";
    }

    @ResponseBody
    @RequestMapping("/suguan/dormList/add")
    Object addDorm(Dept dept){
        if(dept.insert()==true){
            return new SuccessTip();
        }else{
            return new ErrorTip(500,"插入失败");
        }
    }


    //删除部门
    @ResponseBody
    @RequestMapping("/suguan/buildingList/del")
    Object del(String id){
        if(deptMapper.deleteById(id)==1){
            return new SuccessTip();
        }else{
            return new ErrorTip(500, "删除失败");
        }
    }

    //修改宿舍楼
    @ResponseBody
    @RequestMapping("/suguan/buildingList/edit")
    Object edit(Dept dept){
        if(dept.updateById()==true){
            return new SuccessTip();
        }else{
            return new ErrorTip(500,"插入失败");
        }
    }

    //为学生分配宿舍
    @ResponseBody
    @RequestMapping("/suguan/dorm/distribute")
    Object distribute(Integer id,String deptId){
        User user=new User();
        user.setId(id);
        user.setDeptid(Integer.valueOf(deptId));
        if(user.updateById()==true){
            return new SuccessTip();
        }else{
            return new ErrorTip(500,"分配失败");
        }
    }

    //取消学生的宿舍分配
    @ResponseBody
    @RequestMapping("/suguan/dorm/cancel_distribute")
    Object cancel(Integer id){
        if(deptMapper.cancel(id)==1){
            return new SuccessTip();
        }else{
            return new ErrorTip(500,"分配失败");
        }
    }

}
