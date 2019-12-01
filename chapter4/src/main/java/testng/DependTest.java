package testng;

import org.testng.annotations.Test;

public class DependTest {

    @Test
    public void stu1(){
        System.out.println("Groups on class1中的stu1");
        throw new RuntimeException();
    }

    @Test(dependsOnMethods ="stu1")
    public void stu2(){
        System.out.println("Groups on class2中的stu2");
    }
}
