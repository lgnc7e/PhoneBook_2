package com.phonebook;

import com.phonebook.model.User;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class CreateAccountTests extends TestBase {

    @BeforeMethod
    public void preCondition(){
    }


    @Test
    public void createAccountPositiveTest1() {
        app.getUserHelper().clickLoginLink();
        app.getUserHelper().fillInRegistrationForm(new User()
                .setEmail("muster543210@gmail.com")
                .setPassword("Password@321"));
        app.getUserHelper().clickRegistrationButton();
        Assert.assertTrue(app.getUserHelper().isSignOutButtonPresent());
    }

    @Test
   public void createAccountPositiveTest2(){
        String email = "muster" + System.currentTimeMillis() + "@gmail.com";
        String password = "Password@321";
        app.getUserHelper().register(email, password);

    }

    @Test
    public void createAccountLoginPositiveTest(){
        String email = "muster" + System.currentTimeMillis() + "@gmail.com";
        String password = "Password@321";
        app.getUserHelper().register(email, password);
        app.getUserHelper().logout();
        app.getUserHelper().login(email, password);
    }


    @Test
    public void createAccountNegativeTest(){
        SoftAssert softAssert = new SoftAssert();
        app.getUserHelper().clickLoginLink();
        app.getUserHelper().fillInRegistrationForm(new User()
                .setEmail("muster543210@gmail.com")
                .setPassword("Password@321"));
        app.getUserHelper().clickRegistrationButton();
//        Assert.assertTrue(isAlertPresent());
//        Assert.assertTrue(isErrorPresent());
        //softAssert.assertFalse(isAlertPresent());
        softAssert.assertTrue(app.getUserHelper().isAlertPresent());
        softAssert.assertTrue(app.getUserHelper().isError409Present());
        softAssert.assertAll();
    }

    @AfterMethod
    public void postCondition(){
        try {
            app.getUserHelper().logout();
        } catch (Exception e) {
            // throw new RuntimeException(e);
        }
    }

}

//"muster321@gmail.com", "Password@321"
