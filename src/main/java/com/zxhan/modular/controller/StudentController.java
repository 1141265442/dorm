package com.zxhan.modular.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zxhan.core.msg.ErrorTip;
import com.zxhan.core.msg.SuccessTip;
import com.zxhan.modular.entity.Dept;
import com.zxhan.modular.entity.User;
import com.zxhan.modular.mapper.DeptMapper;
import com.zxhan.modular.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

@Controller
public class StudentController {

    @Autowired
    DeptMapper deptMapper;
    @Autowired
    UserMapper userMapper;

    @RequestMapping("/suguan/student.html")
    Object studentIndex(Model model){
        List list=new ArrayList();
        QueryWrapper<Dept> wrapper=new QueryWrapper();
        wrapper.ne("pids","0");
        List<Dept> depts = deptMapper.selectList(wrapper);
        model.addAttribute("depts",depts);
        return "suguan/student.html";
    }
    //跳转到修改学生界面
    @RequestMapping("/suguan/student/edit")
    Object studentEdit(String id,Model model){
        model.addAttribute("user",userMapper.selectById(id));
        return "suguan/edit/student.html";
    }

    //添加学生
    @ResponseBody
    @RequestMapping("/suguan/studentList/add")
    Object add(User user){
        user.setRoleid("1");
        user.setPassword("111111");
        if(user.insert()==true){
            return new SuccessTip();
        }else{
            return new ErrorTip(500,"插入失败");
        }
    }

    //修改学生
    @ResponseBody
    @RequestMapping("/suguan/studentList/edit")
    Object edit(User user){
        if(user.updateById()==true){
            return new SuccessTip();
        }else{
            return new ErrorTip(500,"修改失败");
        }
    }


    //删除学生
    @ResponseBody
    @RequestMapping("/suguan/studentList/del")
    Object del(String id){
        if(userMapper.deleteById(id)==1){
            return new SuccessTip();
        }else{
            return new ErrorTip(500, "删除失败");
        }
    }



}
