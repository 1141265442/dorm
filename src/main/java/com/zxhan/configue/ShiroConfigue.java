package com.zxhan.configue;

import com.zxhan.core.shiro.ShiroDbRealm;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.LinkedHashMap;
import java.util.Map;

@Configuration
public class ShiroConfigue {
    /**
     * 项目自定义的Realm
     */
    @Bean
    public ShiroDbRealm shiroDbRealm() {
        return new ShiroDbRealm();
    }

    /**
     * 安全管理器
     */
    @Bean
    public DefaultWebSecurityManager securityManager() {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        securityManager.setRealm(this.shiroDbRealm());
//        securityManager.setRememberMeManager(rememberMeManager);
//        securityManager.setSessionManager(sessionManager);
        return securityManager;
    }

    @Bean
    public ShiroFilterFactoryBean shiroFilter(DefaultWebSecurityManager securityManager) {
        ShiroFilterFactoryBean shiroFilter = new ShiroFilterFactoryBean();
        shiroFilter.setSecurityManager(securityManager);

        shiroFilter.setLoginUrl("/login.html");
        /**
         * 登陆成功后跳转的url
         */
        shiroFilter.setSuccessUrl("/");

        /**
         * 没有权限跳转的url
         */
        shiroFilter.setUnauthorizedUrl("/global/error");

        /**
         * 覆盖默认的user拦截器(默认拦截器解决不了ajax请求 session超时的问题,若有更好的办法请及时反馈作者)
         */
//        HashMap<String, Filter> myFilters = new HashMap<>();
//        myFilters.put("user", new GunsUserFilter());
//        shiroFilter.setFilters(myFilters);

        Map<String, String> hashMap = new LinkedHashMap<>();
        //释放静态资源
        hashMap.put("/css/**", "anon");
        hashMap.put("/js/**", "anon");
        hashMap.put("/images/**", "anon");
        //释放特定接口
        hashMap.put("/src/main/webapp/static/**", "anon");
        hashMap.put("/login", "anon");
        hashMap.put("/global/sessionError", "anon");
        hashMap.put("/kaptcha", "anon");
        //拦截请求
//        hashMap.put("/**", "user");
        hashMap.put("/**", "anon");
        shiroFilter.setFilterChainDefinitionMap(hashMap);
        return shiroFilter;
    }



}
