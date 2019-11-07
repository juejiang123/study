package com.course.testng.suite;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class LoginTest {

    @Test
    public void loginTaoBao(){

        System.out.println("login成功");
    }

    @BeforeMethod
    public void beforeMethod(){
        System.out.println("before Method");
    }

    @AfterMethod
    public void afterMethod(){
        System.out.println("After Method");
    }
}
