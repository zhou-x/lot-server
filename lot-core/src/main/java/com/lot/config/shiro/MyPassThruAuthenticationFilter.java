package com.lot.config.shiro;

import com.lot.config.exception.UnauthorizedException;
import com.lot.service.LotAuthorityService;
import com.lot.util.JwtUtil;
import com.lot.util.StringUtils;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authc.PassThruAuthenticationFilter;
import org.apache.shiro.web.util.WebUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Set;

/**
 * 在ShiroConfig配置类的shiroFilter方法中配置使用MyPassThruAuthenticationFilter过滤器
 * 系统重定向会默认把请求头清空，通过在MyPassThruAuthenticationFilter的onAccessDenied方法中重新设置请求头，解决跨域问题 。
 * 当Shiro判定当前请求为非法请求（未登录）时，会调用该onAccessDenied方法，
 * 若当前请求地址是设定的loginUrl则放行继续执行之后的方法，在我们的配置中是执行/unauth请求的方法，返回json数据。
 * 若当前请求不是loginUrl方法则重定向到loginUrl方法
 */
public class MyPassThruAuthenticationFilter extends PassThruAuthenticationFilter {
    private Logger log = LoggerFactory.getLogger(this.getClass());
    private static final String AUTHORIZATION = "Authorization";

    @Autowired
    LotAuthorityService lotAuthorityService;

    //获取请求方法，若为OPTIONS请求直接返回True放行
    @Override
    public boolean onPreHandle(ServletRequest request, ServletResponse response, Object mappedValue) throws Exception {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;
        if (req.getMethod().equals(RequestMethod.OPTIONS.name())) {
            return true;
        }
//        return super.onPreHandle(request, response, mappedValue);

        if (lotAuthorityService == null) {
            lotAuthorityService = SpringContextUtils.getContext().getBean(LotAuthorityService.class);
        }
        //请求的url
        String requestURL = getPathWithinApplication(request);
        log.info("请求的Url : " + requestURL);

        String token = WebUtils.toHttp(request).getHeader(AUTHORIZATION);
        //判断是否有token，有token采取第二种方式验证
        if (!StringUtils.isEmpty(token)) {
            if (JwtUtil.checkToken(token)) {
                final Jws<Claims> claimsJws = JwtUtil.parseToken(token);
                final Claims body = claimsJws.getBody();
                boolean hasPermission = false;
                Set<String> permissionUrls = lotAuthorityService.getAuthorityByOpenId((String) body.get("userId"));
                for (String url : permissionUrls) {
                    // 这就表示当前用户有这个权限
                    if (url.equals(requestURL)) {
                        hasPermission = true;
                        break;
                    }
                }
                if (hasPermission)
                    return true;
            }
        }

        Subject subject = SecurityUtils.getSubject();
        if (!subject.isAuthenticated()) {
            // 如果没有登录
            WebUtils.issueRedirect(request, response, "/lot/unauth");
            return false;
        }
        // 看看这个路径权限里有没有维护，如果没有维护，一律放行(也可以改为一律不放行)
        boolean needInterceptor = lotAuthorityService.isAuthorityUrl(requestURL);
        if (!needInterceptor) {
            return false;       //不放行，必须要有这个权限才能放行...
        } else {
            boolean hasPermission = false;
            String userName = subject.getPrincipal().toString();
            Set<String> permissionUrls = lotAuthorityService.getAuthority(userName);
            for (String url : permissionUrls) {
                // 这就表示当前用户有这个权限
                if (url.equals(requestURL)) {
                    hasPermission = true;
                    break;
                }
            }
            if (hasPermission)
                return true;
            else {
                UnauthorizedException ex = new UnauthorizedException("当前用户没有访问路径 " + requestURL + " 的权限");

                subject.getSession().setAttribute("ex", ex);

                WebUtils.issueRedirect(request, response, "/lot/unauthorized");
                return false;
            }
        }
    }
}

