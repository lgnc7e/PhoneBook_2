package com.phonebook;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class DeleteAllContacts extends TestBase{
    private final String CONTACT_NAME = "TestName";

    @BeforeMethod
    public void precondition() {
        app.getUserHelper().login("muster321@gmail.com", "Password@321");
        app.getContactHelper().addNewContactPositiveData(CONTACT_NAME);
    }

    @Test
    public void createOneAndDeleteOneContactTest() {
        int sizeBefore = app.getContactHelper().actualSizeOfContacts();
        System.out.println("Size before deletion: " + sizeBefore);
        app.getContactHelper().deleteOneContact();
        app.wait.until(ExpectedConditions.numberOfElementsToBeLessThan(By.className(app.getContactHelper().CONTACT_LOCATOR), sizeBefore));
        int sizeAfterDelete = app.getContactHelper().actualSizeOfContacts();
        System.out.println("Size after deletion: " + sizeAfterDelete);
        Assert.assertEquals(sizeBefore - 1, sizeAfterDelete, "Count is not equal");
    }

    @Test
    public void deleteAllContactsTests() {
        app.getContactHelper().deleteAllContacts();
        Assert.assertEquals(app.getContactHelper().actualSizeOfContacts(), 0, "Count is not equal");
    }

    @AfterMethod
    public void postCondition() {
        try {
            app.getUserHelper().logout();
        } catch (Exception e) {
            // throw new RuntimeException(e);
        }
    }

    //"muster321@gmail.com", "Password@321"

    //    @Test //Homework
//    public void deleteAllContacts() {
//        while (isElementPresent(By.className("contact-item_card__2SOIM"))) {
//            deleteOneContact();
//            waitForPageToUpdate();
//        }
//        Assert.assertTrue(!isElementPresent((By.className("contact-page_leftdiv__yhyke"))));
//        System.out.println("Отсутствуют доступные для удаления элементы");
//    }
//
//
//    public void waitForPageToUpdate() {
//        try {
//            Thread.sleep(2000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//    }


}







