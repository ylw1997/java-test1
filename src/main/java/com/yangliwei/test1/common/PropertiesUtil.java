package com.yangliwei.test1.common;

import org.springframework.context.EmbeddedValueResolverAware;
import org.springframework.stereotype.Component;
import org.springframework.util.StringValueResolver;

/**
 *  属性文件工具类
 * @author yangliwei
 */
@Component
public class PropertiesUtil implements EmbeddedValueResolverAware {

    private static StringValueResolver stringValueResolver;

    @Override
    public void setEmbeddedValueResolver(StringValueResolver stringValueResolver) {
        PropertiesUtil.stringValueResolver = stringValueResolver;
    }

    /**
     *  获取配置文件中的属性值
     * @param key 属性名
     * @return 属性值
     */
    public static String getProperty(String key){
        return stringValueResolver.resolveStringValue(key);
    }
}
