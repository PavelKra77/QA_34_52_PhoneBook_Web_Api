package ui_tests;

import dto.UserLombok;
import manager.AppManager;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pages.ContactsPage;
import pages.HomePage;
import pages.LoginPage;
import static utils.PropertiesReader.*;

public class LoginTests extends AppManager {
    LoginPage loginPage;
    SoftAssert softAssert = new SoftAssert();

    @BeforeMethod
    public void goToLoginPage(){
        new HomePage(getDriver()).clickBtnLogin();
        loginPage = new LoginPage(getDriver());
    }

    @Test
    public void LoginPositiveTest(){
        UserLombok user = UserLombok.builder()
                .username(getProperty("base.properties", "email"))
                .password(getProperty("base.properties", "password"))
                .build();
        loginPage.typeLoginForm(user);
        loginPage.clickBtnLogin();
        Assert.assertTrue(new ContactsPage(getDriver())
                .validateTextInMessageNoContacts("No Contacts here!"));
    }

    @Test
    public void LoginNegativeEmptyAllFieldsTest() {
        loginPage.clickBtnLogin();
        Assert.assertTrue(loginPage.closeAlert().contains("Wrong email or password"));
    }

    @Test
    public void loginNegativeEmptyEmailTest(){
        UserLombok emptyUser = UserLombok.builder()
                .username("")
                .password(getProperty("base.properties", "password"))
                .build();
        loginPage.typeLoginForm(emptyUser);
        softAssert.assertTrue(loginPage.isBtnLoginEnabled(),
                "validate isBtnLoginEnabled()");
        loginPage.clickBtnLogin();
        softAssert.assertTrue(loginPage.closeAlert().contains("Wrong email or password"));
        softAssert.assertAll();

    }
}
