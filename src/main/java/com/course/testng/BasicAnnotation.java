package com.course.testng;

import org.testng.annotations.Test;

public class BasicAnnotation {

    @Test
    public void basicAnn(){
        System.out.printf("%d",22);
        System.out.printf("Test222222222222 Thread Id:%s%n",Thread.currentThread().getId());

    }
}
