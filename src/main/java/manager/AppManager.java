package manager;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public class AppManager {
    public WebDriver driver;
    public WebDriver getDriver(){
        return driver;
    }

    @BeforeMethod
    public void setup(){
        driver = new ChromeDriver();
        driver.manage().window().maximize();

    }

    @AfterMethod(enabled = false)
    //enabled = false: Отключает выполнение этого метода. Сейчас браузер
    // НЕ будет закрываться автоматически после теста
    // (сделано для отладки, чтобы видеть, что произошло на экране).
    public void tearDown(){
        if(driver != null){
            driver.quit();
        }
    }
}
