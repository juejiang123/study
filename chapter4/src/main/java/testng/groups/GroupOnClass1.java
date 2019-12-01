package testng.groups;

import org.testng.annotations.Test;

@Test(groups = "stu")
public class GroupOnClass1 {

    @Test(enabled = false)
    public void stu1(){
        System.out.println("Groups on class1中的stu1");
    }

    public void stu2(){
        System.out.println("Groups on class1中的stu2");
    }
}
