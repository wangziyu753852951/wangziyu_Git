package tests;

import common.Utils;
import org.asynchttpclient.util.Assertions;
import org.openqa.selenium.By;

import java.io.IOException;

/**
 * ClassName: tests.game_hallTest
 * Description
 *
 * @Author wzy
 * @Create 2025/7/16 20:43
 * @Version 1.0
 */
public class game_hallTest extends Utils {
    public static String url = "http://115.175.7.89:8080/game_hall.html";
    public game_hallTest() {
        super(url);
    }
    void game_hallExist() throws IOException {
        driver.findElement(By.cssSelector("#screen"));
        getScreenShot(getClass().getName());
    }

    void game_hallButton() throws InterruptedException, IOException {

        driver.findElement(By.cssSelector("#match-button")).click();
        //强制等待，等按钮文字改变
        Thread.sleep(1000);
        String accept = driver.findElement(By.cssSelector("#match-button")).getText();
        getScreenShot(getClass().getName());
        assert accept.equals("匹配中...(点击停止)");
        driver.findElement(By.cssSelector("#match-button")).click();
        Thread.sleep(1000);
        accept = driver.findElement(By.cssSelector("#match-button")).getText();
        getScreenShot(getClass().getName());
//      assert accept.equals("开始匹配");
    }
    }
