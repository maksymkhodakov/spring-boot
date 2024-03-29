package com.mainacad.service;

import com.mainacad.ApplicationRunner;
import com.mainacad.entity.Profile;
import com.mainacad.entity.User;
import com.mainacad.service.interfaces.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringJUnitConfig(ApplicationRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class UserServiceTest {

    @Autowired
    UserService userService;

    @Test
    public void testGetAndUpdateAndDelete() {
        User user = new User();
        user.setEmail("ignatenko2207@gmail.com");
        user.setFirstName("Alex");
        user.setLastName("Ignatenko");
        user.setLogin("ignatenko2207");
        user.setPassword("12345");
        user.setProfile(Profile.ADMIN);

        user = userService.save(user);

        User savedUser = userService.findOne(user.getId());

        assertEquals(savedUser.getLogin(), "ignatenko2207");
        savedUser.setLogin("new_login");

        User updatedUser = userService.update(savedUser);

        assertEquals(updatedUser.getLogin(), "new_login");

        userService.delete(updatedUser.getId());
        Optional<User> deletedUser = userService.findById(savedUser.getId());

        assertEquals(deletedUser, Optional.empty());
    }

}