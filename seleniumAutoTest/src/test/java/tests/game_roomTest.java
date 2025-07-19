package tests;

import common.Utils;
import org.openqa.selenium.By;

import java.io.IOException;
import java.sql.Driver;

/**
 * ClassName: tests.game_roomTest
 * Description
 *
 * @Author wzy
 * @Create 2025/7/16 20:43
 * @Version 1.0
 */
public class game_roomTest extends Utils {
    public static String url = "http://115.175.7.89:8080/game_room.html";

    public game_roomTest() {
        super(url);
    }

    //检测落子
    void game_roomSuc() throws IOException {
        String text = driver.findElement(By.cssSelector("#screen")).getText();
        getScreenShot(getClass().getName());
        assert text.equals("轮到你落子了!");
        driver.findElement(By.cssSelector("#chess")).click();
        text = driver.findElement(By.cssSelector("screen")).getText();
        assert text.equals("轮到对方落子了!");
        getScreenShot(getClass().getName());
    }
}
