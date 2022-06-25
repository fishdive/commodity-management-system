package cn.jvtc.service.impl;

import cn.jvtc.po.User;
import cn.jvtc.service.UserService;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

public class UserServiceImplTest {
    ApplicationContext applicationContext = new ClassPathXmlApplicationContext("config/applicationContext.xml");
    UserService userService = applicationContext.getBean(UserServiceImpl.class);

    @Test
    public void add() throws Exception {
        User user = new User("叭叭");
        user.setCreator("admin");
        List<User> userList = userService.findAll(user);
        if (userList.size() > 0) {
            System.out.println("添加失败，已有相同用户存在，请检查用户名！");
            return;
        }
        int add = userService.add(user);
        System.out.println(add);
    }

    @Test
    public void findUser() {
        User user = new User("李四", "123456");
        try {
            System.out.println(userService.find(user).toString());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Test
    public void findAll() {
        User user = new User();
        user.setName("李");
        System.out.println(userService.findAll(user).toString());
    }

    @Test
    public void update() throws Exception {
        User user = new User("Tom");
        List<User> users = userService.findAll(user);
        if (users.size() > 0) {
            users.get(0).setPassword("12345678");
            users.get(0).setModifier("Tom");
            System.out.println(userService.update(users.get(0)));
        }
    }

    @Test
    public void delete() throws Exception {
        User user = new User("李四", "123456");
        System.out.println(userService.delete("六二"));

    }
}