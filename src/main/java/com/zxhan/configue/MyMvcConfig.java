package com.zxhan.configue;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MyMvcConfig implements WebMvcConfigurer {
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
//        registry.addViewController("/login").setViewName("login.html");
        registry.addViewController("/").setViewName("/login.html");
        registry.addViewController("/index_student").setViewName("/index_student.html");
        registry.addViewController("/index_suguan").setViewName("/index_suguan.html");
        registry.addViewController("/suguan/building").setViewName("/suguan/building.html");
        registry.addViewController("/suguan/building/add").setViewName("/suguan/add/building.html");
        registry.addViewController("/suguan/student/add").setViewName("/suguan/add/student.html");



        registry.addViewController("/suguan/dormitory.html").setViewName("/suguan/dormitory.html");
        registry.addViewController("/suguan/menu.html").setViewName("suguan/menu.html");
        registry.addViewController("/suguan/role.html").setViewName("/suguan/role.html");
        registry.addViewController("/suguan/visitor.html").setViewName("/suguan/visitor.html");
        registry.addViewController("/suguan/repair.html").setViewName("/suguan/repair.html");
        registry.addViewController("/suguan/energy.html").setViewName("/suguan/energy.html");

    }

}