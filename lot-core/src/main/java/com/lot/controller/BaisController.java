package com.lot.controller;

import com.lot.Base.UrlDesc;
import com.lot.dao.LotAuthorityDao;
import com.lot.entity.LotAuthorityEntity;
import com.lot.util.IDKeyUtil;
import com.lot.util.Msg;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.mvc.condition.PatternsRequestCondition;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import java.lang.reflect.Method;
import java.util.*;

@Api(value = "基础信息")
@RestController
@RequestMapping("/lot")
public class BaisController {
    @Autowired
    WebApplicationContext applicationContext;
    @Autowired
    private LotAuthorityDao lotAuthorityDao;

    @GetMapping(value = "/unauth")
    public Msg unauth() {
        return Msg.noLogin();
    }

    @GetMapping(value = "/unauthorized")
    public Msg unauthorized() {
        return Msg.unauthorized();
    }

    @ApiOperation("处理权限路径（后台接口）")
    @GetMapping("/getAllUrl")
    @ResponseBody
    public List<UrlDesc> getAllUrl() throws ClassNotFoundException {
        RequestMappingHandlerMapping mapping = applicationContext.getBean(RequestMappingHandlerMapping.class);
        Map<RequestMappingInfo, HandlerMethod> mappingHandlerMethods = mapping.getHandlerMethods();

        List<UrlDesc> list = new ArrayList<>();
        List<LotAuthorityEntity> lotAuthorityList = new ArrayList<>();

        for (Map.Entry<RequestMappingInfo, HandlerMethod> map : mappingHandlerMethods.entrySet()) {
            UrlDesc urlDesc = new UrlDesc();
            LotAuthorityEntity lotAuthority = new LotAuthorityEntity();
            RequestMappingInfo info = map.getKey();
            HandlerMethod method = map.getValue();
            PatternsRequestCondition patternsCondition = info.getPatternsCondition();
            String className = method.getMethod().getDeclaringClass().getName();
            /**
             * 匹配包路径 根据自己的路径替换
             */
            if (className.contains("com.lot.controller")) {
                //获取类对象
                Class clazz = Class.forName(method.getMethod().getDeclaringClass().getName());
                String metName = method.getMethod().getName();
                /**
                 * 因为单独获取一个类对象要指定参数，不适合批量使用，所以获取所有的方法然后根据name筛选
                 */
                Method[] clazzDeclaredMethods = clazz.getDeclaredMethods();
                Arrays.stream(clazzDeclaredMethods).forEach(
                        c -> {
                            if (c.getName().equals(metName)) {
                                /* swagger注解 可以换成别的 */
                                ApiOperation annotation = c.getAnnotation(ApiOperation.class);
                                if (null != annotation) {
                                    urlDesc.setDesc(annotation.value());
                                    lotAuthority.setAuthorityDesc(annotation.value());
                                }
                            }
                        }
                );
                for (String url : patternsCondition.getPatterns()) {
                    urlDesc.setUrl(url);
                    lotAuthority.setCode(url);
                    lotAuthority.setId(IDKeyUtil.getStringId());
                }
                list.add(urlDesc);
                lotAuthorityList.add(lotAuthority);
            }
            if (className.contains("com.lot.wechatApi")) {
                //获取类对象
                Class clazz = Class.forName(method.getMethod().getDeclaringClass().getName());
                String metName = method.getMethod().getName();
                /**
                 * 因为单独获取一个类对象要指定参数，不适合批量使用，所以获取所有的方法然后根据name筛选
                 */
                Method[] clazzDeclaredMethods = clazz.getDeclaredMethods();
                Arrays.stream(clazzDeclaredMethods).forEach(
                        c -> {
                            if (c.getName().equals(metName)) {
                                /* swagger注解 可以换成别的 */
                                ApiOperation annotation = c.getAnnotation(ApiOperation.class);
                                if (null != annotation) {
                                    urlDesc.setDesc(annotation.value());
                                    lotAuthority.setAuthorityDesc(annotation.value());
                                }
                            }
                        }
                );
                for (String url : patternsCondition.getPatterns()) {
                    urlDesc.setUrl(url);
                    lotAuthority.setCode(url);
                    lotAuthority.setId(IDKeyUtil.getStringId());
                }
                list.add(urlDesc);
                lotAuthorityList.add(lotAuthority);
            }
        }
        lotAuthorityDao.saveAll(lotAuthorityList);
        return list;
    }

}
