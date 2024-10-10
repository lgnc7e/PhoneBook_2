package com.phonebook;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class AddContactTests extends TestBase {
    private final String CONTACT_NAME = "TestName";

    @BeforeMethod
    public void preCondition() {
        app.getUserHelper().login("muster321@gmail.com", "Password@321");
        try {
            app.getContactHelper().deleteAllContacts();
        } catch (Exception e) {
            // throw new RuntimeException(e);
        }
    }


    @Test(invocationCount = 1, priority = 1)
    public void addContactPositiveTest() {
        app.getContactHelper().addNewContactPositiveData(CONTACT_NAME);
        Assert.assertTrue(app.getContactHelper().isContactAdded(CONTACT_NAME));

    }


    @Test(invocationCount = 1, priority = 2) //количество запусков и приоритет при запуске метода
    public void addContactPositiveWithoutDescriptionTest() {
        app.getContactHelper().addNewContactPositiveDataWithoutDescription(CONTACT_NAME);
        Assert.assertTrue(app.getContactHelper().isContactAdded(CONTACT_NAME));

    }


    @AfterMethod
    public void postCondition() {
        //app.getContactHelper().deleteOneContact();
        try {
            app.getUserHelper().logout();
        } catch (Exception e) {
            // throw new RuntimeException(e);
        }
    }

    //"muster321@gmail.com", "Password@321"




}


