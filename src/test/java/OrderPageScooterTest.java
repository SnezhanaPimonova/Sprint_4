import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.junit.Test;
import pages.MainPageScooter;
import pages.OrderPageScooter;
import org.openqa.selenium.firefox.FirefoxDriver;

import static сonstants.MainPageUrl.MAIN_PAGE_URL;
import static org.junit.Assert.assertTrue;

@RunWith(Parameterized.class)

public class OrderPageScooterTest {
    private static WebDriver driver;
    private final String userFirstName;
    private final String userLastName;
    private final String userAddress;
    private final String userMetroStation;
    private final String userPhone;
    private final String userDate;
    private final int userPeriodRental;
    private final int userColour;
    private final String userComment;
    private final String buttonPlace;
    private final String browserDriver;

    public OrderPageScooterTest(String buttonPlace, String userFirstName, String userLastName, String userAddress, String userMetroStation,
                                String userPhone, String userDate, int userPeriodRental, int userColour, String userComment, String browserDriver) {
        this.buttonPlace = buttonPlace;
        this.userFirstName = userFirstName;
        this.userLastName = userLastName;
        this.userAddress = userAddress;
        this.userMetroStation = userMetroStation;
        this.userPhone = userPhone;
        this.userDate = userDate;
        this.userPeriodRental = userPeriodRental;
        this.userColour= userColour;
        this.userComment = userComment;
        this.browserDriver = browserDriver;
    }

    @Parameterized.Parameters
    public static Object[][] newOrderData() {
        return new Object[][]{
                {"header", "Лев", "Толстой", "Чистопрудный бул., 12, к. 2", "Чистые пруды", "+79155557779", "01.03.2023", 2, 0, "Уеду вдаль", "Chrom"},
                {"footer", "Сергей", "Рахманинов", "Цветной бульвар, 15", "Цветной бульвар", "+79871110001", "01.04.2023", 4, 1, "Вперед к мечте", "Chrom"},
        };
    }
    @Before
    public void setUp() {
        if (browserDriver.equals("Chrome")) {
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
    public void checkOrderPage() {
        MainPageScooter objMainPageScooter = new MainPageScooter(driver);
        objMainPageScooter.clickCookieButton();
        objMainPageScooter.chooseOrderButton(buttonPlace);
        OrderPageScooter objOrderPageScooter = new OrderPageScooter(driver);
        objOrderPageScooter.setContactInfo(userFirstName, userLastName, userAddress, userMetroStation, userPhone);
        objOrderPageScooter.setDeliveryInfo(userDate, userPeriodRental, userColour, userComment);
        assertTrue(objOrderPageScooter.checkSuccessModal());
    }

    /*   @After //закрыть браузер
    public void tearDown() {
        driver.quit();
    }*/
}
