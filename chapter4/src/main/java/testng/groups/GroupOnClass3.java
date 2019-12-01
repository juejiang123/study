package testng.groups;

import org.testng.annotations.Test;

@Test(groups = "teacher")
public class GroupOnClass3 {

    public void tea1(){
        System.out.println("Groups on class3中的tea1");
        System.out.printf("444444444444 Thread Id:%s%n",Thread.currentThread().getId());
    }

    public void tea2(){
        System.out.println("Groups on class3中的tea2");
        System.out.printf("55555555555 Thread Id:%s%n",Thread.currentThread().getId());
    }
}
