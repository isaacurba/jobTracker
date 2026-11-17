package com.jotTracker.data.models;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserTest {

    private User user;

    @BeforeEach
    public void setUp(){
        user = new User();
    }

    @Test
    public void testUserFullName(){
        user.setFullName("John");
        assertEquals("John",user.getFullName());
    }

    @Test
    public void testUserEmail(){
        user.setEmail("isaac@mail.com");
        assertEquals("isaac@mail.com",user.getEmail());
    }

    @Test
    public void testUserPassword(){
        user.setPassword("12345");
        assertEquals("12345",user.getPassword());
    }
}