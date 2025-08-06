package tests;

import common.Utils;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;

import java.io.IOException;

/**
 * ClassName: tests.registerTest
 * Description
 *
 * @Author wzy
 * @Create 2025/7/16 20:53
 * @Version 1.0
 */
public class registerTest extends Utils {
    public static String url = "http://8.155.1.153:8081/user/register";
    public registerTest() {
        super(url);
    }

    //注册成功
    void registerSuc() throws InterruptedException {

        String name = System.currentTimeMillis()/1000 + "";
        String password = System.currentTimeMillis()/1000 + "";

        driver.navigate().refresh();

        driver.findElement(By.cssSelector("#name")).sendKeys(name);
        driver.findElement(By.cssSelector("#username")).sendKeys(name);
        driver.findElement(By.cssSelector("#password")).sendKeys(password);
        driver.findElement(By.cssSelector("#rePassword")).sendKeys(password);
        driver.findElement(By.cssSelector("#root > div > div.content___1k5Ro > div.main___19HXK > div > form > button > span")).click();
        String txt = driver.findElement(By.cssSelector("#root > div > div.content___2zk1- > div.main___x4OjT > div > form > button")).getText();
        assert txt.equals("登录");

    }
    //注册失败
    void registerFail() throws InterruptedException, IOException {
        //用户已经存在
        driver.findElement(By.cssSelector("#name")).sendKeys("lili");
        driver.findElement(By.cssSelector("#username")).sendKeys("lili");
        driver.findElement(By.cssSelector("#password")).sendKeys("lilili");
        driver.findElement(By.cssSelector("#rePassword")).sendKeys("lilili");
        driver.findElement(By.cssSelector("#root > div > div.content___1k5Ro > div.main___19HXK > div > form > button > span")).click();
        String txt = driver.findElement(By.cssSelector("#root > div > div.content___1k5Ro > div.main___19HXK > div > form > div.ant-alert.ant-alert-error > div > div")).getText();
        assert txt.equals("账号已存在");

        String name = System.currentTimeMillis()/1000 + "";
        String password = System.currentTimeMillis()/1000 + "";

        //未填写确认密码
        driver.navigate().refresh();

        driver.findElement(By.cssSelector("#name")).sendKeys(name);
        driver.findElement(By.cssSelector("#username")).sendKeys(name);
        driver.findElement(By.cssSelector("#password")).sendKeys(password);
        driver.findElement(By.cssSelector("#root > div > div.content___1k5Ro > div.main___19HXK > div > form > button > span")).click();
        txt = driver.findElement(By.cssSelector("#rePassword_help > div")).getText();
        assert txt.equals("请再次输入密码");
    }
}
