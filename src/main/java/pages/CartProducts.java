package pages;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

import java.time.Duration;
import java.util.List;

public class CartProducts extends MainPage{
    public CartProducts(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//div[@class=\"noo-product-inner\"]")
    private List<WebElement> shopProducts;

    @FindBy(xpath = "//a[@class=\"button wp-element-button product_type_variable add_to_cart_button\"]")
    private List<WebElement> addToCartElements;

    @FindBy(id = "pa_color")
    private WebElement colorDropdown;
    @FindBy(id = "pa_size")
    private WebElement sizeDropdown;
    @FindBy(className = "single_add_to_cart_button")
    private WebElement addToCartButton;

    @FindBy(xpath = "//div[@class='woocommerce-message']")
    private WebElement addToCartMessage;

    //checkout

    @FindBy(id = "billing_first_name")
    private WebElement billingFirstNameInput;

    @FindBy(id = "billing_last_name")
    private WebElement billingLastNameInput;

    @FindBy(id = "select2-billing_country-container")
    private WebElement billingCountryDropdown;

    @FindBy(id = "select2-billing_state-container")
    private WebElement billingStateDropdown;

    @FindBy(id = "billing_city")
    private WebElement billingTownTextBox;

    @FindBy(id = "billing_postcode")
    private WebElement billingPostCodeTextBox;

    @FindBy(id = "billing_phone")
    private WebElement billingPhoneTextBox;

    @FindBy(id = "billing_address_1")
    private WebElement billingAddressTextBox;

    @FindBy(id = "billing_email")
    private WebElement billingEmail;

    @FindBy(xpath = "//input[@type=\"checkbox\"]")
    private WebElement licenceCheckbox;

    @FindBy(xpath = "//a[text()=\"Checkout\"]")
    private WebElement checkoutLink;

    @FindBy(xpath = "//td[@class=\"product-remove\"]")
    private List<WebElement> productRemoveButtonsList;

    @FindBy(xpath = "//button[text()=\"Place order\"]")
    private WebElement placeOrderButton;

    @FindBy(xpath = "//li[@class=\"order\"]//strong")
    private  WebElement orderNumberElement;

    @FindBy(xpath = "//a[text()=\"Orders\"]")
    private WebElement ordersLink;

    public void runTest(String username, String password, String postcodeBilling, String cityBilling,
                        String countyBilling, String countryBilling, String lastNameBilling, String firstNameBilling,
                        String address1Billing, String phoneBilling)
            throws InterruptedException {

        clickDismiss();
        clickMyAccount();
        logIn(username, password);

        webdriver.navigate().to("https://shop.demoqa.com/shop/");

        Thread.sleep(Duration.ofSeconds(1));

        for(int i = 0; i < 3; i++){
            Actions actions = new Actions(webdriver);
            actions.moveToElement(shopProducts.get(i)).perform();
            Thread.sleep(Duration.ofSeconds(2));
            addToCartElements.get(i).click();
            Thread.sleep(Duration.ofSeconds(3));
            addToCart();
            webdriver.navigate().to("https://shop.demoqa.com/shop/");
        }

        checkoutLink.click();
//        Assert.assertEquals("There are not 3 products in the cart", productRemoveButtonsList.size(),3);

        checkout(postcodeBilling, cityBilling, countyBilling, countryBilling, lastNameBilling, firstNameBilling,
                address1Billing, phoneBilling);

    }

    public void addToCart() throws InterruptedException {

        Select selectColor = new Select(colorDropdown);
        selectColor.selectByIndex(1);
        Select selectSize = new Select(sizeDropdown);
        selectSize.selectByIndex(1);

        addToCartButton.click();
        Thread.sleep(Duration.ofSeconds(2));

        String actualMessage = addToCartMessage.getText();
        String messageExpected = "has been added to your cart.";
        Assert.assertTrue(actualMessage.contains(messageExpected));

    }

    public void checkout(String postcodeBilling, String cityBilling, String countyBilling,
                         String countryBilling, String lastNameBilling, String firstNameBilling,
                         String address1Billing, String phoneBilling) throws InterruptedException {

        billingFirstNameInput.sendKeys(firstNameBilling);
        billingLastNameInput.sendKeys(lastNameBilling);

        billingCountryDropdown.click();
        String countryxpath = "//li[text()=\"" + countryBilling + "\"]";
        webdriver.findElement(By.xpath(countryxpath)).click();

        Thread.sleep(Duration.ofSeconds(1));
        billingStateDropdown.click();
        String countyxpath = "//li[text()=\"" + countyBilling + "\"]";
        webdriver.findElement(By.xpath(countyxpath)).click();

        billingAddressTextBox.sendKeys(address1Billing);
        billingTownTextBox.sendKeys(cityBilling);
        billingPhoneTextBox.sendKeys(phoneBilling);
        billingPostCodeTextBox.sendKeys(postcodeBilling);
        licenceCheckbox.click();
        Thread.sleep(Duration.ofSeconds(5));
        placeOrderButton.click();


    }
}
