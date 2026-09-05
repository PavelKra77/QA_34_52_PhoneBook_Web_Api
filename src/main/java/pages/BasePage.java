package pages;
//Это базовый класс Page Object паттерна (BasePage). От него наследуются все остальные страницы (LoginPage, HomePage).
// В нем хранятся методы и переменные, общие для всех страниц.
//static Делает поле единым для всего приложения. Если одна страница обновит driver, он изменится везде. делает поле общим для всех экземпляров класса
// abstract BasePage — это не реальная страница. Это шаблон c общим функционалом для всех остальных страниц

import org.openqa.selenium.Alert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.time.Duration;

public abstract class BasePage {
    static WebDriver driver;  // лучше protected WebDriver driver
    public Logger logger = LoggerFactory.getLogger(BasePage.class);



    public void setDriver(WebDriver wd) {  //Метод, чтобы передать браузер wd в driver из других страниц.
        driver = wd;
    }

    public boolean isTextInElementPresent(WebElement element, String text) {
        try {
            return new WebDriverWait(driver, Duration.ofSeconds(5))
                    .until(ExpectedConditions.textToBePresentInElement(element, text));
        }
        // Метод возвратит true (тип Boolean), если текст успеет появиться в элементе в течение 5 секунд.
        //WebDriverWait — встроенный класс Selenium для умных ожиданий.
        //textToBePresentInElement готовое встроенное правило, которое умеет проверять появление текста.

        catch (RuntimeException e) {
//            e.printStackTrace();                    //команда «Если ошибка, напечатай в консоль и почему она случилась
//            System.out.println("created exeption");
            logger.error("created exception",e);
        }
        return false;}
    // Если за 5 секунд текст не нашелся, программа «не падает», а перехватывает ошибку.
    //e — (сокращение от слова Exception) -это переменная, в которую записывается ошибка (объект перехваченного исключения).
    //Когда в блоке try происходит сбой, Java создаёт специальный объект ошибки (в данном случае класса RuntimeException или его наследника
    // и «пробрасывает» его в блок catch.e хранит в себе всю информацию об этой ошибке:Тип ошибки, Текст ошибки ..
    // logger.error(...) — метод для записи сообщений с уровнем важности ERROR.
    //пример  logger.error: 2026-09-03 21:12:00 [main] ERROR BasePage - created exception
    //    (переменнея е) org.openqa.selenium.TimeoutException: Expected condition failed: waiting for text to be present in element found by By.id: successMessage (tried for 5 second(s) with 500 milliseconds interval)
    //	  org.openqa.selenium.support.ui.WebDriverWait.timeoutException(WebDriverWait.java:84)
    //	  org.openqa.selenium.support.ui.WebDriverWait.until(WebDriverWait.java:228)
    //	  pages.BasePage.isTextIdPresent(BasePage.java:26)
    //    tests.LoginTests.LoginNegativeEmptyAllFieldsTest(LoginTests.java:45)
    // e.printStackTrace(); выводит стек ошибки только в консоль.
    // logger.error передает информацию в систему логирования, которая может записывать данные в файлы логов
    //  выводить их в консоль или отправлять на удаленный сервер.


    // или такая запись  boolean result = new WebDriverWait(driver, Duration.ofSeconds(5))
    //                   .until(ExpectedConditions.textToBePresentInElement(element, text));
    //                    return result;
    // textToBePresentInElement        ищет в  <div>, <span>, <p>, <h1>, <button> (между тегами <tag>Текст</tag>)
    // textToBePresentInElementValue   ищет в  <input>, <textarea>                (Внутри атрибута value="...")

    public boolean isUrlContainsText(String text){
        try {
            return new WebDriverWait(driver, Duration.ofSeconds(5))
                    .until(ExpectedConditions.urlContains(text));
        } catch (RuntimeException e) {
            e.printStackTrace();
        }
        return false;
    }


    public String closeAlert(){
        Alert alert = new WebDriverWait(driver, Duration.ofSeconds(5))   // Сохраняет всплывающее окно в переменную alert
        .until(ExpectedConditions.alertIsPresent());
        String text = alert.getText();   //Сохраняет текст из всплывающего окна, в переменную text
        alert.accept();
        return text;
    }

    public void pause(int time) {
        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

}
