package com.course.testng.groups;

import org.testng.annotations.BeforeGroups;
import org.testng.annotations.Test;

public class GroupOnMethod {

    @Test(groups = "服务组")
    public void test1(){
        System.out.println("服务组1");
    }
    @Test(groups = "客户组")
    public void test2(){
        System.out.println("客户组2");
    }
    @Test(groups = "客户组")
    public void test3(){
        System.out.println("客户组3");
    }
    @BeforeGroups("客户组")
    public void beforetest1(){
        System.out.println("在客户组前");
    }
}
