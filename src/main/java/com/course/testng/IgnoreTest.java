package com.course.testng;

import org.testng.annotations.Test;

public class IgnoreTest {

    @Test
    public void ignore1(){
        System.out.println("ignore1 执行！");
        System.out.printf("Test111111111 Thread Id:%s%n",Thread.currentThread().getId());

    }

    @Test(enabled = false)
    public void ignore2(){
        System.out.println("ignore2 执行");
    }

    @Test
    public void ignore3(){
        System.out.println("ignore3 执行");
        System.out.printf("33333333333 Thread Id:%s%n",Thread.currentThread().getId());
    }
}
