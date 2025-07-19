package tests;

import common.Utils;
import org.asynchttpclient.util.Assertions;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;

import java.io.IOException;

/**
 * ClassName: tests.loginTest
 * Description
 *
 * @Author wzy
 * @Create 2025/7/16 20:43
 * @Version 1.0
 */
public class loginTest extends Utils {
    public static String url = "http://115.175.7.89:8080/index.html";

    public loginTest() {
        super(url);
    }

    //测试能否正常登录
    public void loginPageRight() throws IOException, InterruptedException {
        driver.findElement(By.cssSelector("#username")).clear();
        driver.findElement(By.cssSelector("#password")).clear();
        driver.findElement(By.cssSelector("#username")).sendKeys("aaa");
        driver.findElement(By.cssSelector("#password")).sendKeys("aaa");
        driver.findElement(By.cssSelector("#submit")).click();

        Thread.sleep(100);
        //切换到弹窗进行弹窗处理
        Alert alert = driver.switchTo().alert();
        String accept = alert.getText();
        alert.accept();
        //检测是否登陆成功
        String txt = driver.findElement(By.cssSelector("#match-button")).getText();
        assert txt.equals("开始匹配");
        getScreenShot(getClass().getName());
        driver.navigate().back();

    }

    //检测异常登录
    public void loginPageFail() throws InterruptedException, IOException {
        driver.findElement(By.cssSelector("body > div.cover > div.buttons > a:nth-child(1)")).click();
        //用户为空
        driver.findElement(By.cssSelector("#username")).sendKeys("");
        driver.findElement(By.cssSelector("#password")).sendKeys("aaa");
        driver.findElement(By.cssSelector("#submit")).click();
        Thread.sleep(100);
        //切换到弹窗进行弹窗处理
        Alert alert = driver.switchTo().alert();
        String accept = alert.getText();
        alert.accept();
        assert accept.equals("登录失败!");

        //密码为空
        driver.findElement(By.cssSelector("#username")).clear();
        driver.findElement(By.cssSelector("#password")).clear();
        driver.findElement(By.cssSelector("#username")).sendKeys("aaa");
        driver.findElement(By.cssSelector("#password")).sendKeys("");
        getScreenShot(getClass().getName());
        driver.findElement(By.cssSelector("#submit")).click();
        Thread.sleep(100);
        //切换到弹窗进行弹窗处理
        alert = driver.switchTo().alert();
        accept = alert.getText();
        alert.accept();
        assert accept.equals("登录失败!");

        //密码错误
        driver.findElement(By.cssSelector("#username")).clear();
        driver.findElement(By.cssSelector("#password")).clear();
        driver.findElement(By.cssSelector("#username")).sendKeys("aaa");
        driver.findElement(By.cssSelector("#password")).sendKeys("aax");
        getScreenShot(getClass().getName());
        driver.findElement(By.cssSelector("#submit")).click();
        Thread.sleep(100);
        //切换到弹窗进行弹窗处理
        alert = driver.switchTo().alert();
        accept = alert.getText();
        alert.accept();
        assert accept.equals("登录失败!");

        //用户错误
        driver.findElement(By.cssSelector("#username")).clear();
        driver.findElement(By.cssSelector("#password")).clear();
        driver.findElement(By.cssSelector("#username")).sendKeys("aax");
        driver.findElement(By.cssSelector("#password")).sendKeys("aaa");
        getScreenShot(getClass().getName());
        driver.findElement(By.cssSelector("#submit")).click();
        Thread.sleep(100);
        //切换到弹窗进行弹窗处理
        alert = driver.switchTo().alert();
        accept = alert.getText();
        alert.accept();
        assert accept.equals("登录失败!");
    }
}
