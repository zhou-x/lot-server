package com.lot.config.shiro;

import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.session.mgt.SessionManager;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.Filter;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

@Configuration
public class ShiroConfig {

    /**
     * 请求拦截
     *
     * @param securityManager
     * @return
     */
    @Bean
    public ShiroFilterFactoryBean shiroFilter(SecurityManager securityManager) {
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        shiroFilterFactoryBean.setSecurityManager(securityManager);
        Map<String, Filter> filtersMap = new HashMap<>();
        MyPassThruAuthenticationFilter authFilter = new MyPassThruAuthenticationFilter();
        filtersMap.put("authc", authFilter);
        shiroFilterFactoryBean.setFilters(filtersMap);
        // 拦截器.
        Map<String, String> filterMap = new LinkedHashMap<String, String>();
        filterMap.put("/lot/user/**", "anon");
        filterMap.put("/swagger-ui.html", "anon");
        filterMap.put("/swagger-resources/**", "anon");
        filterMap.put("/v2/**", "anon");
        filterMap.put("/webjars/**", "anon");
        filterMap.put("/lot/unauth","anon");
        filterMap.put("/lot/unauthorized","anon");
        filterMap.put("/lot/img/**","anon");
        filterMap.put("/lot/weixin/open/**","anon");
//        filterMap.put("/demo/login","anon");
        filterMap.put("/**","anon");
        filterMap.put("/lot/user/logout","logout");
//        filterMap.put("/**", "authc");
        shiroFilterFactoryBean.setLoginUrl("/lot/unauth");
        /*
         * 注意过滤器配置顺序 不能颠倒
         * anon. 配置不会被拦截的请求 顺序判断
         * authc. 配置拦截的请求
         * 配置shiro默认登录界面地址，前后端分离中登录界面跳转应由前端路由控制，后台仅返回json数据
         * 所有非法请求被重定向到/unauth，该请求返回一个json数据
         * shiroFilterFactoryBean.setLoginUrl("/unauth");
         */
        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterMap);
        return shiroFilterFactoryBean;
    }

    /**
     * @return SecurityManager
     * @Title: securityManager
     * @Description: SecurityManager，权限管理，这个类组合了登陆，登出，权限，session的处理
     */
    @Bean
    public SecurityManager securityManager() {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        securityManager.setRealm(myShiroRealm());
        // 自定义session管理
        securityManager.setSessionManager(sessionManager());
        return securityManager;
    }

    /**
     * 自定义认证
     *
     * @return MyShiroRealm
     * @Title: myShiroRealm
     * @Description: ShiroRealm，这是个自定义的认证类，继承自AuthorizingRealm，负责用户的认证和权限的处理
     */
    @Bean
    public Realm myShiroRealm() {
        MyRealm myRealm = new MyRealm();
        myRealm.setCredentialsMatcher(hashedCredentialsMatcher());
        return myRealm;
    }

    /**
     * 密码凭证匹配器，作为自定义认证的基础 （由于我们的密码校验交给Shiro的SimpleAuthenticationInfo进行处理了 ）
     *
     * @return
     */
    @Bean
    public HashedCredentialsMatcher hashedCredentialsMatcher() {
        HashedCredentialsMatcher hashedCredentialsMatcher = new HashedCredentialsMatcher();
        hashedCredentialsMatcher.setHashAlgorithmName("MD5");// 散列算法:这里使用MD5算法;
        hashedCredentialsMatcher.setHashIterations(2);// 散列的次数，比如散列两次，相当于 md5(md5(""));
        hashedCredentialsMatcher.setStoredCredentialsHexEncoded(true);
        return hashedCredentialsMatcher;
    }

    /**
     * 自定义sessionManager，用户的唯一标识，即Token或Authorization的认证
     */
    @Bean
    public SessionManager sessionManager() {
        MySessionManager mySessionManager = new MySessionManager();
        return mySessionManager;
    }

}
