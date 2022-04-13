package com.yangliwei.test1.common;

import com.yangliwei.test1.entity.User;
import com.yangliwei.test1.entity.vo.UserVo;
import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 *  对象拷贝工具类
 * @author ylw12
 */
public class BeanCopyUtils {

    private BeanCopyUtils(){}

    /**
     *  对象拷贝
     * @param source 源对象
     * @param target 目标对象
     */
     public static <T> T copyBean(Object source, Class<T> target) {
         // 创建目标对象
         T result = null;
         try {
             result = target.newInstance();
             BeanUtils.copyProperties(source, result);
         } catch (Exception e) {
             e.printStackTrace();
         }
         return result;
     }

    /**
     *  列表对象拷贝
     * @param objectList 列表对象
     * @param targetClass 目标class
     * @param <V> 源class
     * @param <T> 目标类型
     * @return 目标对象列表
     */
     public static <V,T> List<T> copyBeanList(List<V> objectList,Class<T> targetClass){
        return objectList.stream()
                 .map(object->copyBean(object,targetClass))
                 .collect(Collectors.toList());
     }



    public static void main(String[] args) {
        User user = new User();
        User user1 = new User();
        user.setId(123L);
        user.setAge(23);
        user.setName("张三");
        user.setEmail("123@qq.com");
        user1.setId(456L);
        user1.setAge(23);
        user1.setName("李四");
        List<User> userList = new ArrayList<>(Arrays.asList(user,user1));
        UserVo userVo = copyBean(user, UserVo.class);
        List<UserVo> userVos = copyBeanList(userList, UserVo.class);
        System.out.println(userVo.toString());
        System.out.println(userVos.toString());
    }

}
