package testng;

import org.testng.annotations.Test;

public class TimeOutTest {

    @Test(timeOut = 3000)
    public void testSuccess() throws InterruptedException {
        Thread.sleep(2000);

    }

    @Test(timeOut = 3000)
    public void testFail() throws InterruptedException {
        Thread.sleep(4000);
    }
}
