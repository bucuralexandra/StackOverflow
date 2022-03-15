package com.utcn.assignmentSD;

import com.utcn.assignmentSD.model.User;
import com.utcn.assignmentSD.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;


@SpringBootTest
@Transactional
public class UserTest {

    @Autowired
    UserService userService;


    @Test
    void saveUserTest()
    {
        User user = new User(22, "Bucur Alexandra", "Ale@yahoo.com", "abc");
        userService.saveUser(user);
        assert(userService.getUser(22).getName().equals("Bucur Alexandra"));
    }

    @Test
    void updateUserTest()
    {
        User user = new User(22, "Bucur Alexandr", "Ale@yahoo.com", "abc");
        userService.saveUser(user);
        user = new User(22, "Bucur Alexandraa", "Ale@yahoo.com", "abc");
        userService.updateUser(22,user);
        User u = userService.getUser(22);
        assert u.getName().equals("Bucur Alexandraa");
    }

    @Test
    void deleteUserTest()
    {
        List<User> users = userService.getAllUsers();
        User u = users.get(0);
        String s = userService.deleteUser(u.getId());
        assert Objects.equals(s, "Delete success.");
        assert userService.getUser(u.getId()) == null;
    }
}
