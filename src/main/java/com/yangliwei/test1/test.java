package com.yangliwei.test1;

import com.yangliwei.test1.entity.User;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class test {

    public static void main(String[] args) {
        List<User> userList = getUserList();
        testOne(userList);
    }

    private static void testOne(List<User> userList) {
        userList.stream()
                .distinct()
                .filter(user -> user.getAge() > 12)
                .forEach(System.out::println);
    }

    public static List<User> getUserList(){
        User user0 = new User("杨利伟0",10);
        User user1 = new User("杨利伟0",10);
        User user2 = new User("杨利伟2",12);
        User user3 = new User("杨利伟3",13);
        User user4 = new User("杨利伟4",14);
        User user5 = new User("杨利伟5",15);
        return new ArrayList<>(Arrays.asList(user0,user1,user2,user3,user4,user5));
    }
}
