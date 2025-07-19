package tests;

import common.Utils;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.IOException;
import java.time.Duration;

/**
 * ClassName: tests.registerTest
 * Description
 *
 * @Author wzy
 * @Create 2025/7/16 20:53
 * @Version 1.0
 */
public class registerTest extends Utils {
    public static String url = "http://115.175.7.89:8080/index.html";
    public registerTest() {
        super(url);
    }

    //注册成功
    void registerSuc() throws InterruptedException {

        String name = System.currentTimeMillis()/1000 + "";
        String password = System.currentTimeMillis()/1000 + "";
        driver.findElement(By.cssSelector("#username")).clear();
        driver.findElement(By.cssSelector("#password")).clear();
        driver.findElement(By.cssSelector("#username")).sendKeys(name);
        driver.findElement(By.cssSelector("#password")).sendKeys(password);
        driver.findElement(By.cssSelector("#submit")).click();
        //切换弹窗
        Thread.sleep(100);
        Alert alert = driver.switchTo().alert();
        String accept = alert.getText();
        alert.accept();
        //显示注册成功
        assert accept.equals("注册成功");

    }
    //注册失败
    void registerFail() throws InterruptedException, IOException {
        //用户已经存在
        driver.findElement(By.cssSelector("body > div > div.buttons > a:nth-child(2)")).click();
        driver.findElement(By.cssSelector("#username")).sendKeys("aaa");
        driver.findElement(By.cssSelector("#password")).sendKeys("aaa");
        driver.findElement(By.cssSelector("#submit")).click();
        //切换弹窗
        Thread.sleep(100);
        Alert alert = driver.switchTo().alert();
        String accept = alert.getText();
        getScreenShot(getClass().getName());
        alert.accept();
        //检测是否注册失败
        assert accept.equals("success:注册失败");

        String name = System.currentTimeMillis()/1000 + "";
        String password = System.currentTimeMillis()/1000 + "";
//用户为空
        driver.findElement(By.cssSelector("#username")).sendKeys();
        driver.findElement(By.cssSelector("#password")).sendKeys(password);
        driver.findElement(By.cssSelector("#submit")).click();
//切换弹窗
        Thread.sleep(100);
        alert = driver.switchTo().alert();
        accept = alert.getText();
        getScreenShot(getClass().getName());
        alert.accept();
//检测是否注册失败
        assert accept.equals("success:注册失败");

//密码为空
        driver.findElement(By.cssSelector("#username")).sendKeys(name);
        driver.findElement(By.cssSelector("#password")).sendKeys();
        driver.findElement(By.cssSelector("#submit")).click();
//切换弹窗
        Thread.sleep(100);
        alert = driver.switchTo().alert();
        accept = alert.getText();
        getScreenShot(getClass().getName());
        alert.accept();
//检测是否注册失败
        assert accept.equals("success:注册失败");

//密码采用特殊符号
        driver.findElement(By.cssSelector("#username")).sendKeys(name);
        driver.findElement(By.cssSelector("#password")).sendKeys("#*￥");
        driver.findElement(By.cssSelector("#submit")).click();
//切换弹窗
        Thread.sleep(100);
        alert = driver.switchTo().alert();
        accept = alert.getText();
        getScreenShot(getClass().getName());
        alert.accept();
//检测是否注册失败
        assert accept.equals("success:注册失败");
//密码过长
        driver.findElement(By.cssSelector("#username")).sendKeys(name);
        driver.findElement(By.cssSelector("#password")).sendKeys("111111111111111111111111111111");
        driver.findElement(By.cssSelector("#submit")).click();
//切换弹窗
        Thread.sleep(100);
        alert = driver.switchTo().alert();
        accept = alert.getText();
        getScreenShot(getClass().getName());
        alert.accept();
//检测是否注册失败
        assert accept.equals("success:注册失败");
    }
}
