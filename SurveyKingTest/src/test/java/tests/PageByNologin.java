package tests;

import common.Utils;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;

import java.io.IOException;

/**
 * ClassName: PageByNologin
 * Description
 *
 * @Author wzy
 * @Create 2025/7/17 8:55
 * @Version 1.0
 */
public class PageByNologin extends Utils {
    public static String homeUrl = "http://8.155.1.153:8081/home";
    public static String projectUrl = "http://8.155.1.153:8081/project";

    public PageByNologin() {
        super("http://8.155.1.153:8081/login");
    }


    public void homePageByNoLogin() throws IOException {
        driver.get(homeUrl);

        //测试未登录情况下访问首页界面，是否自动跳转到登录页面
        String expect = driver.findElement(By.cssSelector("#root > div > div.content___2zk1- > div.main___x4OjT > div > form > button > span")).getText();
        getScreenShot(getClass().getName());

        assert expect.equals("登录");
    }
    public void projectPageByNoLogin() throws IOException {
        driver.get(projectUrl);

        //测试未登录情况下访问首页界面，是否自动跳转到登录页面
        String expect = driver.findElement(By.cssSelector("#root > div > div.content___2zk1- > div.main___x4OjT > div > form > button > span")).getText();
        getScreenShot(getClass().getName());

        assert expect.equals("登录");
    }
}
