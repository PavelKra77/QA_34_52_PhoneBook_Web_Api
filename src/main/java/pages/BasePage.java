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

public abstract class BasePage {  //класс-«шаблон» для других конкретных страниц.
    static WebDriver driver;
    //static Делает поле единым для всего приложения. Если одна страница обновит driver, он изменится везде.

    public void setDriver(WebDriver wd) {
        driver = wd;
    }

    public boolean isTextInElementPresent(WebElement element, String text) {
        try {
            return new WebDriverWait(driver, Duration.ofSeconds(5))
                    .until(ExpectedConditions.textToBePresentInElement(element, text));
        } catch (RuntimeException e) {
            e.printStackTrace();
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


//setDriver(...) — связывает ваш BasePage с текущей сессией браузера.
// Обычно вызывается при старте тестов, чтобы передать активный WebDriver внутрь страниц.

    public void pause(int time) {
        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

}
