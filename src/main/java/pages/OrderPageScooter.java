package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;


public class OrderPageScooter {
    private final WebDriver driver;
    //локатор поля Имя
    private final By orderFirstName = By.xpath(".//input[contains(@placeholder, '* Имя')]");
    //локатор поля Фамилия
    private final By orderLastName = By.xpath(".//input[contains(@placeholder, '* Фамилия')]");
    //локатор поля адрес
    private final By orderAddress = By.xpath(".//input[contains(@placeholder, '* Адрес: куда привезти заказ')]");
    //локатор поля станция метро
    private final By orderMetroStation = By.className("select-search__input");
    //локатор выпадающего списка станций метро
    private final By orderMetroStationList = By.xpath(".//div[@class='select-search__select']");
    //локатор поля телефон
    private final By orderPhone = By.xpath(".//input[contains(@placeholder, '* Телефон: на него позвонит курьер')]");
    //локатор кнопки Далее
    private final By orderNextButton = By.xpath(".//button[@class='Button_Button__ra12g Button_Middle__1CSJM']");
    //локатор поля Когда привезти заказ
    private final By orderDate = By.xpath(".//input[contains(@placeholder, '* Когда привезти самокат')]");
    //локтор поля Срок аренды
    private final By orderPeriodRental = By.xpath(".//div[@class='Dropdown-placeholder' and text()='* Срок аренды']");
    //локатор выпадающего списка Срок аренды
    private final By orderPeriodRentalList= By.xpath(".//div[@class='Dropdown-option']");
    //локатор цвет самоката
    private final By orderColour = By.xpath(".//label[@class='Checkbox_Label__3wxSf']");
    //локатор поля Комментарий для курьера
    private final By orderComment = By.xpath(".//input[contains(@placeholder, 'Комментарий для курьера')]");
    //локатор кнопки Заказать
    private final By orderButton = By.xpath(".//button[@class='Button_Button__ra12g Button_Middle__1CSJM' and text()='Заказать']");
    //локатор кнопки Да в модальном окне подтверждения заказа
    private final By acceptOrderButton = By.xpath(".//div[@class = 'Order_Modal__YZ-d3']/div" +
            "/button[@class = 'Button_Button__ra12g Button_Middle__1CSJM']");
    //локатор окна подтверждения заказа
    private final By acceptOrderModal = By.xpath(".//div[@class='Order_ModalHeader__3FDaJ' and text()='Заказ оформлен']");

    public OrderPageScooter(WebDriver driver) {
        this.driver = driver;
    }

    //заполнение поля Имя
    public void setOrderFirstName(String userName) {
        driver.findElement(orderFirstName).sendKeys(userName);
    }

    //заполнение поля Фамилия
    public void setOrderLastName(String userLastName) {
        driver.findElement(orderLastName).sendKeys(userLastName);
    }

    //заполнение поля Адрес
    public void setOrderAddress(String userAddress) {
        driver.findElement(orderAddress).sendKeys(userAddress);
    }

    //заполнение поля Метро
    public void setOrderMetroStation(String userMetro) {
        driver.findElement(orderMetroStation).sendKeys(userMetro);
        driver.findElements(orderMetroStationList).get(0).click();
    }

    //заполнение поля Телефон
    public void setOrderPhone(String userPhone) {
        driver.findElement(orderPhone).sendKeys(userPhone);
    }

    //клик на кнопку Далее
    public void clickOrderNextButton() {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView();", driver.findElement(orderNextButton));
        driver.findElement(orderNextButton).click();
    }

    //метод для заполнения 1 части
    public void setContactInfo(String userName, String userLastName, String userAddress, String userMetro, String userPhone){
        setOrderFirstName(userName);
        setOrderLastName(userLastName);
        setOrderAddress(userAddress);
        setOrderMetroStation(userMetro);
        setOrderPhone(userPhone);
        clickOrderNextButton();
    }

    //заполнение поля Когда привезти самокат
    public void setOrderDate(String userDate) {
        driver.findElement(orderDate).sendKeys(userDate);
        driver.findElement(orderDate).sendKeys(Keys.ENTER);
    }

    //заполнение поля Срок аренды
    public void setOrderPeriodRental(int userDaysRent) {
        driver.findElement(orderPeriodRental).click();
        driver.findElements(orderPeriodRentalList).get(userDaysRent).click();
    }

    //выбор цвета
    public void clickOrderColour(int userColour){
        driver.findElements(orderColour).get(userColour).click();
    }

    //заполнение поля Комментарий для курьера
   public void setOrderComment(String userComment) {
        driver.findElement(orderComment).sendKeys(userComment);
    }

    //клик на кнопку Заказать
    public void clickOrderButton() {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView();", driver.findElement(orderButton));
        driver.findElement(orderButton).click();
   }

    //клик на Да
    public void clickAcceptOrderButton() {
        driver.findElement(acceptOrderButton).click();
    }

    //объединяем методы для заполнения 2 части
    public void setDeliveryInfo(String userDate, int userPeriodRental, int userColour, String userComment){
        setOrderDate(userDate);
        setOrderPeriodRental(userPeriodRental);
        clickOrderColour(userColour);
        setOrderComment(userComment);
        clickOrderButton();
        clickAcceptOrderButton();
    }

    //проверка что появилась модалка Заказ оформлен
    public boolean checkSuccessModal() {
            return driver.findElement(acceptOrderModal).isEnabled();
        }

    }


