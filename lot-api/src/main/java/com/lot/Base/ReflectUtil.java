package com.lot.Base;

import com.google.common.collect.Maps;
import org.apache.commons.beanutils.PropertyUtilsBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.beans.PropertyDescriptor;
import java.util.Map;

/**
 * 动态添加实体类的属性
 */
public class ReflectUtil {
    static Logger logger = LoggerFactory.getLogger(ReflectUtil.class);

    public static Object getTarget(Object dest, Map<String, Object> addProperties) {
        // 获取属性映射
        PropertyUtilsBean propertyUtilsBean = new PropertyUtilsBean();
        PropertyDescriptor[] descriptors = propertyUtilsBean.getPropertyDescriptors(dest);
        Map<String, Class> propertyMap = Maps.newHashMap();
        for (PropertyDescriptor d : descriptors) {
            if (!"class".equalsIgnoreCase(d.getName())) {
                propertyMap.put(d.getName(), d.getPropertyType());
            }
        }
        // 添加额外属性
        addProperties.forEach((k, v) -> {
            if (v != null)
                propertyMap.put(k, v.getClass());
        });
        // 生成新的动态Bean
        DynamicBean dynamicBean = new DynamicBean(dest.getClass(), propertyMap);
        // 添加旧值
        propertyMap.forEach((k, v) -> {
            try {
                // 筛选额外属性
                if (!addProperties.containsKey(k)) {
                    dynamicBean.setValue(k, propertyUtilsBean.getNestedProperty(dest, k));
                }
            } catch (Exception e) {
                logger.error(e.getMessage(), e);
            }
        });
        // 增加额外的值
        addProperties.forEach((k, v) -> {
            try {
                if (v == null) {
                    dynamicBean.setValue(k, null);
                } else {
                    dynamicBean.setValue(k, v);
                }
            } catch (Exception e) {
                logger.error(e.getMessage(), e);
            }
        });
        Object target = dynamicBean.getTarget();
        return target;
    }
}