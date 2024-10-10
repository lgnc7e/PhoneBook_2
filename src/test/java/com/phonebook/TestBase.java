package com.phonebook;

import com.phonebook.core.ApplicationManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.annotations.*;

import java.lang.reflect.Method;

public class TestBase {

    protected static ApplicationManager app = new ApplicationManager(System.getProperty("browser", "chrome"));
    Logger logger = LoggerFactory.getLogger(TestBase.class);

//    @BeforeClass
//    public void test2 (){
//        logger.info("Hello ****@BeforeSuit");
//    }
//
//    @BeforeSuite
//    public void test (){
//        logger.info("Hello **@BeforeSuit");
//    }

    @BeforeSuite //тесты запускаются в одном окне браузера
    public void setUp() {
        logger.info("***************** TESTING IN PROGRESS ******************");
        app.init();
    }

    @BeforeMethod
    public void startTest(Method method){
        logger.info("Test is startet: [" +method.getName() + "]");
    }

    @AfterMethod
    public void stopTest(Method method, ITestResult result, ITestContext context) {

        StringBuilder parameters = new StringBuilder();
        for (String key : context.getAttributeNames()) {
            Object value = context.getAttribute(key);
            parameters.append(key).append("=").append(value).append(", ");
        }

        if (parameters.length() > 0) {
            parameters.setLength(parameters.length() - 2);
        }

        logger.info("Test is startet with data: [" + parameters +"]");
        //logger.info("Test is startet with data: [" + method.getName() + "], with data: [" + parameters +"]");

        if (result.isSuccess()) {
            logger.info("Test is PASSED: [" + method.getName() + "]");
        } else {
            // Проверяем наличие алерта и закрываем его с помощью isAlertPresent
            if (app.getUserHelper().isAlertPresent()) {
                logger.warn("Alert was present and has been accepted.");
            } else {
                logger.info("No alert present.");
            }
            // Теперь делаем скриншот
            String screenshotPath = app.getUserHelper().takeScreenshot();
            logger.error("Test is FAILED: [" + method.getName() + "], Screenshot: [" + screenshotPath + "]");
        }
        logger.info("Test is ended: [" + method.getName() + "]");
    }

    @AfterSuite(enabled = true)
    public void tearDown() {
        logger.info("***************** ALL TEST END *****************");
        app.stop();
    }



}

