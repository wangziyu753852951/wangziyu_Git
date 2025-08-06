package tests;

import common.Utils;

import java.io.IOException;

/**
 * ClassName: tests.runCase
 * Description
 *
 * @Author wzy
 * @Create 2025/7/15 8:44
 * @Version 1.0
 */
public class runCase {
    public static void main(String[] args) throws InterruptedException, IOException {
        PageByNologin test = new PageByNologin();
        test.homePageByNoLogin();

        registerTest test1 = new registerTest();
        test1.registerFail();
        test1.registerSuc();
          loginTest test2 = new loginTest();
          test2.loginPageFail();
          test2.loginPageRight();

          mainPageTest test3 = new mainPageTest();
          test3.createSurvey();
        Utils.driver.quit();
    }
}
