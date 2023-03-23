package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class MainPageScooter {
    private final WebDriver driver;
    //кнопка Заказать в хедере страницы
    private final By headerOrderButton = By.xpath(".//button[@class='Button_Button__ra12g']");
    //кнопка Заказать в теле страницы
    private final By footerOrderButton = By.xpath(".//button[@class='Button_Button__ra12g Button_Middle__1CSJM']");
    //баттон в списке Вопросы о важном
    private final By questionButton= By.xpath(".//div[@class='accordion__button']");
    //текст элемента в списке
    private final By answerText = By.xpath(".//div[@class='accordion__panel']/p");
    //кнопка принятия куки
    private final By cookieButton = By.xpath(".//button[@class='App_CookieButton__3cvqF']");

    public MainPageScooter(WebDriver driver) {
        this.driver = driver;
    }

    //клик на кнопку Заказать вверху страницы
    public void clickHeaderOrderButton() {
        driver.findElement(headerOrderButton).click();
    }

    //клик на кнопку Заказать внизу страницы
    public void clickFooterOrderButton() {
        WebElement element = driver.findElement(footerOrderButton);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", element);
        element.click();
    }

    //выбираем на какую кнопку Заказать нажать
    public void chooseOrderButton(String buttonPlace){
        if (buttonPlace.equals("header")){
            clickHeaderOrderButton();
        } else {
            clickFooterOrderButton();
        }
    }

    //принять куки
    public void clickCookieButton(){
        driver.findElement(cookieButton).click();
    }

    //скролл до элемента раскрытия ответа и клик
    public void clickQuestionButton(int questionIndex) {
        WebElement element = driver.findElements(questionButton).get(questionIndex);
        ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();", element);
        element.click();
    }

    //получить текст ответа
    public String getAnswerText(int questionIndex) {
        List<WebElement> elements = driver.findElements(answerText);
        new WebDriverWait(driver, 3)
                .until(ExpectedConditions.visibilityOf(elements.get(questionIndex)));
        return elements.get(questionIndex).getText();
    }

}
