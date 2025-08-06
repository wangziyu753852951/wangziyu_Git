package tests;

import common.Utils;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.IOException;
import java.time.Duration;

/**
 * ClassName: tests.loginTest
 * Description
 *
 * @Author wzy
 * @Create 2025/7/16 20:43
 * @Version 1.0
 */
public class loginTest extends Utils {
    public static String url = "http://8.155.1.153:8081/user/login";

    public loginTest() {
        super(url);
    }

    //测试能否正常登录
    public void loginPageRight() throws IOException, InterruptedException {
        driver.navigate().refresh();

        driver.findElement(By.cssSelector("#username")).sendKeys("lili");
        driver.findElement(By.cssSelector("#password")).sendKeys("lilili");
        driver.findElement(By.cssSelector("#root > div > div.content___2zk1- > div.main___x4OjT > div > form > button")).click();
        //检测是否登陆成功

        getScreenShot(getClass().getName());
        String txt = driver.findElement(By.cssSelector("#sk-layout > div > div > section > div > main > div > div.ant-pro-grid-content > div > div > div > div.ant-col.ant-col-xs-24.ant-col-sm-24.ant-col-md-24.ant-col-lg-24.ant-col-xl-8 > div > div.ant-card-body > div > a:nth-child(1)")).getText();
        assert txt.equals("创建问卷");
    }

    //检测异常登录
    public void loginPageFail() throws InterruptedException, IOException {
        //不输入密码
//        driver.findElement(By.cssSelector("#username")).sendKeys("lili");
//        driver.findElement(By.cssSelector("#root > div > div.content___2zk1- > div.main___x4OjT > div > form > button")).click();
//        String txt = driver.findElement(By.cssSelector("#password_help > div")).getText();
//        assert txt.equals("密码是必填项!");

        //sql注入
        driver.findElement(By.cssSelector("#username")).sendKeys("lili");
        driver.findElement(By.cssSelector("#password")).sendKeys("1111 or 1");
        driver.findElement(By.cssSelector("#root > div > div.content___2zk1- > div.main___x4OjT > div > form > button")).click();
        String txt = driver.findElement(By.cssSelector("#root > div > div.content___2zk1- > div.main___x4OjT > div > form > div.ant-alert.ant-alert-error > div > div")).getText();
        assert txt.equals("错误的用户名和密码");
    }
}
