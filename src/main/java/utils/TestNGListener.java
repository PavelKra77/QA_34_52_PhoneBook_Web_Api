package utils;

import manager.AppManager;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class TestNGListener implements ITestListener {
    Logger logger = LoggerFactory.getLogger(TestNGListener.class);
    private WebDriver driver;


    @Override
    public void onTestStart(ITestResult result) {
        ITestListener.super.onTestStart(result);
        logger.info("start test --> "+ result.getName());
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        ITestListener.super.onTestSuccess(result);
        logger.info("test success --> "+ result.getName());
    }

    @Override
    public void onTestFailure(ITestResult result) {
        ITestListener.super.onTestFailure(result);
        logger.error("test failed -->" + result.getName()
                + "status --> " +result.getStatus());
        this.driver = ((AppManager)result.getInstance()).getDriver();
        TakeScreenShot.takeScreenShot((TakesScreenshot) driver);
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        ITestListener.super.onTestSkipped(result);
        logger.warn("test skipped -->" + result.getName());
    }


    @Override
    public void onTestFailedWithTimeout(ITestResult result) {
        ITestListener.super.onTestFailedWithTimeout(result);
        logger.error("test failed with timeout -->" + result.getName());
        this.driver = ((AppManager)result.getInstance()).getDriver();
        TakeScreenShot.takeScreenShot((TakesScreenshot) driver);
    }

    @Override
    public void onStart(ITestContext context) {
        ITestListener.super.onStart(context);
        logger.info(context.getName()+"test start on"
                + context.getStartDate());
    }

    @Override
    public void onFinish(ITestContext context) {
        ITestListener.super.onFinish(context);
        logger.info(context.getName()+ "test stop on"
                + context.getEndDate());
    }
}
// TestNGListener — это класс-слушатель (Listener), который перехватывает ключевые события жизненного цикла тестов в TestNG
// и позволяет выполнять определенный код в ответ на них.
//@Override — указывает, что этот метод переопределяет стандартный дефолтный метод из интерфейса ITestListener.
//public void onFinish(ITestResult result) — метод, который TestNG вызывает автоматически,
// когда какой-то тестовый метод начинает выполняться. В аргумент result он передает информацию о стартующем тесте.
//ITestListener.super.onTestStart(result); — вызов стандартной реализации интерфейса.
// Это нужно для того, чтобы не нарушать внутреннюю логику фреймворка TestNG.
//logger.info; — строка логирования. Она записывает в консоль или файл отчета сообщение о том, что тест запущен
//context.getName() — возвращает имя текущего набора тестов (suite) или группы тестов, указанных в файле testng.xml.
//
//        context.getEndDate() — возвращает точную дату и время, когда весь набор тестов завершил свое выполнение.
//
//        logger.info(...) — записывает в лог информационное сообщение в формате уровня INFO, объединяя имя сборочного пакета и время его окончания.