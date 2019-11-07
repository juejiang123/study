package com.course.testng.parameter;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.lang.reflect.Method;

public class DataProviderTest {

    @Test(dataProvider="a")
    public void dataProviderTest(String name,int age){
        System.out.println("name="+name+",age="+age);
        System.out.println();
    }

    @DataProvider(name = "a")
    public Object[][] dataProvider(){
        Object[][] o = new Object[][]{
                {"zhangsan",10},
                {"lisi",15}
        };
        return o;
    }

//    @Test
//    public void print1(){
//        Object[][] o = new Object[][]{
//                {"zhangsan",10},
//                {"lisi",15}
//        };
//        System.out.println(o[1][1]);
//
//    }

    @Test(dataProvider="data")
    public void Test1(String name,int age){
        System.out.println("Test1111:name="+name+",age="+age);
    }

//    @Test(dataProvider="data")
//    public void Test2(String name,int age){
//        System.out.println("Test2222:name="+name+",age="+age);
//    }

    @DataProvider(name = "data")
    public Object[][] dataProvide(){
        Object[][] res = new Object[][]{
                {"zhangsan",10},
                {"lisi",15}
        };
        return res;
    }

//    @DataProvider(name="data")
//    public Object[][] methodTest(Method method) {
//        Object[][] result = null;
//        if(method.getName().equals("Test1")){
//            result = new Object[][]{
//                    {"zhangsan",15},
//                    {"lisi",20}
//            };
//        }else if(method.getName().equals("Test2")){
//            result = new Object[][]{
//                    {"wangwu", 15},
//                    {"zhaoliu", 20}
//            };
//        }return result;
//
//    }



}
