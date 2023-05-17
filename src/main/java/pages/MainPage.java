package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class MainPage {

    public WebDriver webdriver;
    public MainPage(WebDriver driver){
        this.webdriver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//a[text()=\"My Account\"]")
    private WebElement myAccount;

    @FindBy(xpath = "//a[@class='woocommerce-store-notice__dismiss-link']")
    private WebElement dismissButton;

    @FindBy(id = "username")
    private WebElement usernameLoginField;

    @FindBy(id = "password")
    private WebElement passwordLoginField;

    @FindBy(xpath = "//button[@name='login']")
    private WebElement loginButton;

    public void logIn(String username, String password){
        usernameLoginField.sendKeys(username);
        passwordLoginField.sendKeys(password);
        loginButton.click();
    }

    public void clickDismiss() {
        webdriver.manage().window().maximize();
        this.dismissButton.click();
    }

    public void clickMyAccount(){
        myAccount.click();
    }
}
