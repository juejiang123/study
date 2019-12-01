package testng.suite;

import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;

public class SuiteConfig {

    @BeforeSuite
    public void beforeSuite(){
        System.out.println("before suite 运行了");
    }

    @AfterSuite
    public void afterSuite(){
        System.out.println("After suite 运行了");
    }

    @BeforeTest
    public void beforeTest(){
        System.out.println("before test 运行");
    }

    @AfterTest
    public void afterTest(){
        System.out.println("After test 运行");
    }
}
