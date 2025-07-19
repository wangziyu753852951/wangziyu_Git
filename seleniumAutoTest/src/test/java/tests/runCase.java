package tests;

import common.Utils;

import java.io.IOException;
import java.sql.Driver;

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

        PageByNologin test4 = new PageByNologin();
        test4.game_hallPageByNoLogin();

        registerTest rtest = new registerTest();
        rtest.registerFail();
        rtest.registerSuc();

        loginTest test = new loginTest();
        test.loginPageFail();
        test.loginPageRight();

        game_hallTest test2 = new game_hallTest();
        test2.game_hallExist();
        test2.game_hallButton();

        game_roomTest test3 = new game_roomTest();
        test3.game_roomSuc();

        Utils.driver.quit();
    }
}
