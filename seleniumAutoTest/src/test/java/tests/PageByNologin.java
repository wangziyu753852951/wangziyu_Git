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
    public static String game_hallurl = "http://115.175.7.89:8080/game_hall.html";
    public static String game_roomurl = "http://115.175.7.89:8080/game_room.html";

    public PageByNologin() {
        super("http://115.175.7.89:8080/index.html");
    }


    public void game_hallPageByNoLogin() throws IOException {
        driver.get(game_hallurl);
        //列表页未登录处理
        driver.findElement(By.cssSelector("#match-button")).click();
        Alert alert = driver.switchTo().alert();
        String accept = alert.getText();
        alert.accept();
        //调整到登录页面
        String expect = driver.getTitle();

        getScreenShot(getClass().getName());

        assert expect.equals("登录");
    }

    public void game_roomPageByNoLogin() throws IOException {
        driver.get(game_roomurl);
        //列表页未登录处理
        Alert alert = driver.switchTo().alert();
        String accept = alert.getText();
        alert.accept();
        //调整到登录页面
        alert = driver.switchTo().alert();
        accept = alert.getText();
        alert.accept();
        //调整到登录页面
        String expect = driver.getTitle();

        getScreenShot(getClass().getName());

        assert expect.equals("登录");
    }
}
