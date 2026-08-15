package manager;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public class AppManager {
    private WebDriver driver;
    public WebDriver getDriver(){   // чтобы любой тест мог попросить этот driver.
        return driver;
    }
// Модификатор private скрывает переменную внутри класса, а публичный метод getDriver()
// дает к ней контролируемый доступ. Это ключевой принцип ООП — инкапсуляция.

    @BeforeMethod
        public void setup(){
            driver = new ChromeDriver();
            driver.manage().window().maximize();
    }
   // метод выполняется перед каждым отдельным тестом (@Test). Это гарантирует,
    // что каждый тест запускается в чистом, свежем браузере ChromeDriver.

    @AfterMethod(enabled = false)
    //enabled = false: Отключает выполнение этого метода. Сейчас браузер
    // НЕ будет закрываться автоматически после теста
    // (сделано нами для отладки, чтобы видеть, что произошло на экране).
    public void tearDown(){
        if(driver != null){
            driver.quit();
        }
    }
}
