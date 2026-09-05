package ui_tests;

import data_providers.UserDataProvider;
import dto.UserLombok;
import manager.AppManager;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import pages.ContactsPage;
import pages.HomePage;
import pages.LoginPage;
import utils.TestNGListener;

import static utils.UserFactory.*;
import java.util.Random;
@Listeners(TestNGListener.class)

public class RegistrationTests extends AppManager {
    LoginPage loginPage;

    @BeforeMethod
    public void goToRegistrationLoginPage() {
        new HomePage(getDriver()).clickBtnLogin();
        loginPage = new LoginPage(getDriver());
    }
    //Создаётся new объект(страницу) класса HomePage. Через метод getDriver() берет текущий браузер WebDriver,
    // и передаёт его в конструктор HomePage. Вызывается метод клика по кнопке LOGIN из HomePage.
    // Объект HomePage здесь анонимный, без  переменной— он создаётся только для выполнения одного клика и дальше в памяти не сохраняется.
    //Если объект HomePage вам больше не понадобится в этом методе (нужно только один раз кликнуть по кнопке), сохранять его в переменную вообще не нужно:
    // в переменную loginPage создаётся объект класса LoginPage. В его конструктор снова передаётся актуальный getDriver().
    // Теперь все методы с аннотацией @Test могут использовать loginPage (например, loginPage.enterEmail("...")), не создавая объект страницы заново внутри каждого теста.

    @Test
    public void registrationPositiveTest() {
        int i = new Random().nextInt(1000);
        UserLombok user = UserLombok.builder()
                .username("vbfte" + i + "34@ew.bh")
                .password("Adfert23!")
                .build();
        loginPage.typeLoginRegistrationForm(user);
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
    public void registrationPositiveWithFakerTest() {
        UserLombok user = positiveUser();
        System.out.println(user);
        loginPage.typeLoginRegistrationForm(user);
        loginPage.clickBtnRegistration();
        Assert.assertTrue(new ContactsPage(getDriver())
                .validateTextInMessageNoContacts("No Contacts here!"));
    }
    // positiveUser Этот метод внутри себя Запускает faker.internet().emailAddress(), который генерирует случайный уникальный email
    // UserLombok user: Создаётся локальная переменная user класса UserLombok, в которую сохраняется этот сгенерированный объект.

    @Test
    public void registrationNegativeEmptyAllFieldsTest() {
        loginPage.clickBtnRegistration();
        Assert.assertTrue
                (loginPage.closeAlert().contains("Wrong email or password format"));
    }


    @Test
    public void registrationNegativeEmptyEmailFieldTest() {
        UserLombok user = positiveUser();
        user.setUsername("");
        loginPage.typeLoginRegistrationForm(user);
        loginPage.clickBtnRegistration();
        Assert.assertTrue
                (loginPage.closeAlert().contains("Wrong email or password format"));
    }

    @Test
    public void registrationNegativeEmptyPasswordFieldTest() {
        UserLombok user = positiveUser();
        user.setPassword("");
        loginPage.typeLoginRegistrationForm(user);
        loginPage.clickBtnRegistration();
        Assert.assertTrue
                (loginPage.closeAlert().contains("Wrong email or password format"));
    }

    @Test(dataProvider = "dataProviderWrongPasswordOrEmail", dataProviderClass = UserDataProvider.class)
    public void registrationNegativeWrongPasswordTest(UserLombok user) {
        loginPage.typeLoginRegistrationForm(user);
        loginPage.clickBtnRegistration();
        Assert.assertTrue
                (loginPage.closeAlert().contains("Wrong email or password format"));
    }

// "dataProviderWrongPasswordOrEmail" - название метода в классе UserDataProvider.
//dataProviderClass = UserDataProvider.class — название самого класса (UserDataProvider).
}
