package com.course.testng.groups;

import org.testng.annotations.Test;

@Test(groups = "stu")
public class GroupOnClass2 {

    public void stu1(){
        System.out.println("Groups on class2中的stu1");
        System.out.printf("666666666666 Thread Id:%s%n",Thread.currentThread().getId());
    }

    public void stu2(){
        System.out.println("Groups on class2中的stu2");
        System.out.printf("777777777777 Thread Id:%s%n",Thread.currentThread().getId());
    }
}
