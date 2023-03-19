import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import io.github.bonigarcia.wdm.WebDriverManager;
import pages.MainPageScooter;
import org.openqa.selenium.firefox.FirefoxDriver;

import static сonstants.MainPageUrl.MAIN_PAGE_URL;
import static org.junit.Assert.assertEquals;

@RunWith(Parameterized.class)
public class MainPageScooterTest {
    private static WebDriver driver;
    private final int questionIndex;
    private final String answerText;
    private final String browserDriver;
    public MainPageScooterTest(int questionIndex, String answerText, String browserDriver) {
        this.questionIndex = questionIndex;
        this.answerText = answerText;
        this.browserDriver = browserDriver;
    }

    @Parameterized.Parameters
    public static Object[][] getData() {
        return new Object[][]{
                {0, "Сутки — 400 рублей. Оплата курьеру — наличными или картой.", "Chrome"},
                {1, "Пока что у нас так: один заказ — один самокат. Если хотите покататься с друзьями, можете просто сделать несколько заказов — один за другим.", "Chrome"},
                {2, "Допустим, вы оформляете заказ на 8 мая. Мы привозим самокат 8 мая в течение дня. Отсчёт времени аренды начинается с момента, когда вы оплатите заказ курьеру. Если мы привезли самокат 8 мая в 20:30, суточная аренда закончится 9 мая в 20:30.", "Chrome"},
                {3, "Только начиная с завтрашнего дня. Но скоро станем расторопнее.", "Chrome"},
                {4, "Пока что нет! Но если что-то срочное — всегда можно позвонить в поддержку по красивому номеру 1010.", "Chrome"},
                {5, "Самокат приезжает к вам с полной зарядкой. Этого хватает на восемь суток — даже если будете кататься без передышек и во сне. Зарядка не понадобится.", "Chrome"},
                {6, "Да, пока самокат не привезли. Штрафа не будет, объяснительной записки тоже не попросим. Все же свои.", "Chrome"},
                {7, "Да, обязательно. Всем самокатов! И Москве, и Московской области.", "Chrome"},
        };
    }

    @Before
    public void setUp() {
        if(browserDriver.equals("Chrome")) {
            WebDriverManager.chromedriver().setup();
            ChromeOptions options = new ChromeOptions();
            options.addArguments("--remote-allow-origins=*");
            driver = new ChromeDriver(options);
        } else {
            driver = new FirefoxDriver();
        }
        driver.get(MAIN_PAGE_URL);
}

    @Test
    public void checkQuestion(){
       MainPageScooter objMainPageScooter = new MainPageScooter(driver);
       objMainPageScooter.clickCookieButton();
       objMainPageScooter.clickQuestionButton(questionIndex);
       assertEquals(answerText, objMainPageScooter.getAnswerText(questionIndex));
    }

    @After
    public void tearDown() {
        driver.quit();
    }
}

