package testng;

import org.testng.annotations.Test;

public class ExpectedException {
    /**
     * 什么时候用？
     * 在我们期望有一个异常时
     * 比如：输入了不合法的参数，程序抛出了异常
     */
    @Test(expectedExceptions = RuntimeException.class)
    public void runExceptionFailed(){
        System.out.println("这是一个失败的异常测试");
    }

    @Test(expectedExceptions = RuntimeException.class)
    public void runTimeExceptionFailed(){
        System.out.println("这是一个正常的异常测试");
        throw new RuntimeException();
    }


}
