package pages;
//Это базовый класс Page Object паттерна (BasePage). От него наследуются все
// остальные страницы (LoginPage, HomePage).
// В нем хранятся методы и переменные, общие для всех страниц.

import org.openqa.selenium.Alert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public abstract class BasePage {  //класс-«шаблон» для других страниц.
    static WebDriver driver;      //static Делает поле единым для всего приложения. Если одна страница обновит driver, он изменится везде.

    public void setDriver(WebDriver wd) {  //Метод, чтобы передать браузер wd в driver из других страниц.
        driver = wd;
    }

    public boolean isTextInElementPresent(WebElement element, String text) {
        try {
            return new WebDriverWait(driver, Duration.ofSeconds(5))   //WebDriverWait — встроенный класс Selenium для умных ожиданий.
                    .until(ExpectedConditions.textToBePresentInElement(element, text));  //готовое встроенное правило, которое умеет проверять появление текста.
        }
        // или такая запись  boolean result = new WebDriverWait(driver, Duration.ofSeconds(5))
        //                   .until(ExpectedConditions.textToBePresentInElement(element, text));
        //                    return result;
        catch (RuntimeException e) {  // Если за 5 секунд текст не нашелся, программа «не падает», а перехватывает ошибку.
            e.printStackTrace();      //команда «Если ошибка, напечатай в консоль и почему она случилась
            System.out.println("created exeption");
        }
        return false;
    }


    public String closeAlert(){
        Alert alert = new WebDriverWait(driver, Duration.ofSeconds(5))
        .until(ExpectedConditions.alertIsPresent());
        String text = alert.getText();
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
