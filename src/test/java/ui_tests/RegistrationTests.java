package ui_tests;

import dto.UserLombok;
import manager.AppManager;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.ContactsPage;
import pages.HomePage;
import pages.LoginPage;
import static utils.UserFactory.*;

import java.util.Random;

public class RegistrationTests extends AppManager {
    LoginPage loginPage;
    @BeforeMethod
    public void goToRegistrationLoginPage(){
        new HomePage(getDriver()).clickBtnLogin();
        loginPage = new LoginPage(getDriver());
    }


    //Создается объект главной страницы HomePage, в конструктор передается активный
    // браузер через getDriver().clickBtnLogin();: Вызывается метод клика по кнопке «Login/Registration»

    @Test
    public void registrationPositiveTest(){
        int i = new Random().nextInt(1000);
        UserLombok user = UserLombok.builder()
                .username("vbfte" + i + "34@ew.bh")
                .password("Adfert23!")
                .build();
         loginPage = new LoginPage(getDriver());
        //Создается объект страницы авторизации/регистрации LoginPage с текущей
        // сессией браузера getDriver().

        loginPage.typeLoginRegistrationForm(user);
        //Вызывается метод из класса LoginPage, который берет данные из созданного
        // объекта user(username и password) и вводит их в соответствующие поля формы на экране.

        loginPage.clickBtnRegistration();
        Assert.assertTrue(new ContactsPage(getDriver())
                .validateTextInMessageNoContacts("No Contacts here!"));



    }

//    @Test
//    public void testMethod(){
//        new HomePage(getDriver()).method();
//    }
//    @Test
//    public void testAjaxMethod(){
//        new HomePage(getDriver()).ajaxMethod();
//    }
    @Test
    public void registrationPositiveWithFakerTest(){
        UserLombok user = positiveUser();
        System.out.println(user);
        loginPage.typeLoginRegistrationForm(user);
        loginPage.clickBtnRegistration();
        Assert.assertTrue(new ContactsPage(getDriver())
                .validateTextInMessageNoContacts("No Contacts here!"));



    }

    @Test
    public void registrationNegativeEmptyAllFieldsTest(){
        loginPage.clickBtnRegistration();
        Assert.assertTrue
                (loginPage.closeAlert().contains("Wrong email or password format"));
    }
}
