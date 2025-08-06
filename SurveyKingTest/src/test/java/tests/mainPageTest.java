package tests;

import common.Utils;
import org.openqa.selenium.By;

import java.io.IOException;

/**
 * ClassName: mainPageTest
 * Description
 *
 * @Author wzy
 * @Create 2025/7/17 21:20
 * @Version 1.0
 */
public class mainPageTest extends Utils {
    public static String url = "http://8.155.1.153:8081/home";
    public mainPageTest() {
        super(url);
    }

//创建问卷
    void createSurvey() throws IOException {
        driver.findElement(By.cssSelector("#sk-layout > div > div > section > div > main > div > div.ant-pro-grid-content > div > div > div > div.ant-col.ant-col-xs-24.ant-col-sm-24.ant-col-md-24.ant-col-lg-24.ant-col-xl-8 > div > div.ant-card-body > div > a:nth-child(1)")).click();
        driver.findElement(By.cssSelector("#rc-tabs-1-panel-1 > div > div > dl:nth-child(2) > div > div:nth-child(2) > dd")).click();
        driver.findElement(By.cssSelector("#rc-tabs-1-panel-1 > div > div > dl:nth-child(2) > div > div:nth-child(2) > dd")).click();
        driver.findElement(By.cssSelector("#rc-tabs-1-panel-1 > div > div > dl:nth-child(2) > div > div:nth-child(2) > dd")).click();
        driver.findElement(By.cssSelector("#rc-tabs-1-panel-1 > div > div > dl:nth-child(2) > div > div:nth-child(2) > dd")).click();
        getScreenShot(getClass().getName());
        driver.findElement(By.cssSelector("#editor > div.survey-main-panel > div.survey-main-panel-toolbar > div:nth-child(2) > div > button.ant-btn.ant-btn-primary.ant-btn-sm > span")).click();
        //查看创建的问卷是否在项目中显示
        driver.findElement(By.cssSelector("#root > div > div.survey-main-navigator > div.actions-container > div:nth-child(7) > a > button > span:nth-child(2)")).click();
        getScreenShot(getClass().getName());
        //发布项目
        driver.findElement(By.cssSelector("#sk-layout > div > div > section > div > main > div > div.ant-pro-grid-content > div > div > div > div.ant-list.ant-list-split.ant-list-grid.survey-home-content > div > div > div > div:nth-child(1) > div > div > div > ul > li:nth-child(5) > span")).click();
        driver.findElement(By.cssSelector("body > div:nth-child(10) > div > div > ul > li:nth-child(3)")).click();
        driver.findElement(By.cssSelector("body > div:nth-child(11) > div > div.ant-modal-wrap > div > div.ant-modal-content > div > div > div.ant-modal-confirm-btns > button.ant-btn.ant-btn-primary > span")).click();
        String txt = driver.findElement(By.cssSelector("#sk-layout > div > div > section > div.ant-pro-layout-container > main > div > div.ant-pro-grid-content > div > div > div > div.ant-list.ant-list-split.ant-list-grid.survey-home-content > div > div > div > div:nth-child(1) > div > div > div > div > div > div.card-content > div.publish-status > span > span.ant-badge-status-text")).getText();
        assert txt.equals("收集中");
    }
}
