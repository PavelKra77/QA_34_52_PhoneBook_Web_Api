package manager;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.lang.reflect.Method;

public class AppManager {
    private WebDriver driver;
    public WebDriver getDriver(){
        return driver;}

    //WebDriver это программный интерфейс (API), переводит код на понятный браузеру язык и выполняет действия на экране.
    // Модификатор private скрывает переменную внутри класса, а публичный метод getDriver()
    // дает к ней контролируемый доступ, чтобы любой тест мог попросить этот driver.Это ключевой принцип ООП — инкапсуляция.

    public Logger logger = LoggerFactory.getLogger(AppManager.class);
    //настраиваем логгер (инструмент для записи логов/журнала работы программы) с помощью фреймворка SLF4J.
    //LoggerFactory.getLogger(...) — специальная «фабрика логгеров» - дай логгер Напечатай и (get) отдай мне экземпляр для записи логов
    //AppManager.class — передаёт LoggerFactory данные класса AppManager чтобы в логах было видно, какой именно класс записал конкретное сообщение.
    //пример вывода - 12:00:01 ERROR  [AppManager] - Нажата кнопка Войти
    // logger принадлежит конкретному классу. Передавать её в другие файлы или делать одну на весь проект нельзя,
    // потому что каждый логгер привязан к своему классу (в скобках передается имя текущего класса — AppManager.class,
    // чтобы в отчете было видно, откуда именно пришло сообщение).
    //Где обычно создают логгер: В классах-слушателях (TestNGListener), чтобы фиксировать падения и успехи тестов,
    //В базовом классе для тестов (AppManager или BasePage), чтобы все дочерние страницы и тесты могли им пользоваться.
    // LoggerFactory.getLogger(...)) — пишется один раз в теле класса, снаружи методов.
    //Вызов логгера (logger.error(...)) — пишется внутри тех методов, где вы хотите перехватить и записать ошибку.


    @BeforeMethod
        public void setup(Method method){
            driver = new ChromeDriver();
            driver.manage().window().maximize();
            logger.info("Start testing with method-->"+ method.getName());
    }
   // метод выполняется перед каждым отдельным тестом (@Test). Это гарантирует,
    // что каждый тест запускается в чистом, свежем браузере ChromeDriver.
    //driver = new ChromeDriver(); Запускает новый чистый экземпляр браузера Google Chrome.
    //Method method — это специальный объект. TestNG передаёт сюда информацию о том тестовом методе, который собирается запуститься прямо сейчас.
    //method.getName() достаёт из объекта Method имя теста (например, loginWithValidDataTest).
   //logger.info(...) записывает красивое сообщение в консоль/файл логов.
    //пример вывода 18:15:02 INFO [AppManager] - Start testing with method-->loginWithValidDataTest

    @AfterMethod(enabled = true)
    public void tearDown(){
        if(driver != null){
            driver.quit();}

    //enabled = false: Отключает выполнение этого метода. Сейчас браузер
    // НЕ будет закрываться автоматически после теста
    // (сделано нами для отладки, чтобы видеть, что произошло на экране).
    }
}
