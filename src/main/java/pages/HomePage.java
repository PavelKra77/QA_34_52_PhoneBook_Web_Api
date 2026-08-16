package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import utils.PropertiesReader;

import static utils.PropertiesReader.*;

public class HomePage extends BasePage{
    public HomePage(WebDriver driver){
        setDriver(driver);                                  //сохраняем driver через setDriver() в страницу HomePage
//        driver.get("https://telranedu.web.app/home");
          driver.get(PropertiesReader.getProperty("base.properties", "baseUrl"));
                                                            // обращаемся к сохраненному driver и направляем его по URL.
        PageFactory.initElements
                (new AjaxElementLocatorFactory(driver,10), this);
    }
    //PageFactory  находит на нашей веб-странице все кнопки, текстовые поля и картинки и делает их рабочими.
    //AjaxElementLocatorFactory ищет кнопку на экране 10 сек

    @FindBy(xpath = "//a[text()='LOGIN']")
    WebElement btnLogin;
    @FindBy(xpath = "//form/input[1]")
    WebElement inputEmail;

    public void clickBtnLogin(){
        btnLogin.click();
    }

    public void method(){
        WebElement login = driver.findElement
                (By.xpath("//a[text()='LOGIN']"));
        login.click();
        WebElement inputEmail = driver.findElement
                (By.xpath("//form/input[1]"));
        inputEmail.sendKeys("dfgrggd@drety.vbg");
    }

    public void ajaxMethod(){      // потом закомментировали в тестах
        btnLogin.click();
        inputEmail.sendKeys("fghte@dsgs.bnm");
    }
}

