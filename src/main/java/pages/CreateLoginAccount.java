package pages;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.time.Duration;
import java.util.List;

public class CreateLoginAccount extends MainPage{
    public CreateLoginAccount(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//a[text()=\"My Account\"]")
    private WebElement myAccount;

    @FindBy(id = "reg_username")
    private WebElement registerUsername;

    @FindBy(id = "reg_billing_first_name")
    private WebElement firstNameField;

    @FindBy(id = "reg_billing_last_name")
    private WebElement lastNameField;

    @FindBy(id = "reg_email")
    private WebElement regEmail;

    @FindBy(id = "reg_password")
    private WebElement passwordField;

    @FindBy(xpath = "//button[@name='register']")
    private WebElement registerButton;

    @FindBy(xpath = "//div[@class='woocommerce-message']")
    private WebElement successMessage;

    @FindBy(xpath = "//a[text()=\"Logout\"]")
    private WebElement logoutLink;

    @FindBy(xpath = "//a[text()='Addresses']")
    private WebElement addressesLink;

    //billing elements
    @FindBy(xpath = "//div[@class='u-columns woocommerce-Addresses col2-set addresses']//a[@class='edit']")
    private List<WebElement> addressesButtons;

    @FindBy(id = "billing_first_name")
    private WebElement billingFirstNameInput;

    @FindBy(id = "billing_last_name")
    private WebElement billinglastNameInput;

    @FindBy(id = "select2-billing_country-container")
    private WebElement billingCountryDropdown;

    @FindBy(id = "select2-billing_state-container")
    private WebElement billingStateDropdown;

    @FindBy(id = "billing_city")
    private WebElement billingTownTextBox;

    @FindBy(id = "billing_phone")
    private WebElement billingPhoneTextBox;

    @FindBy(id = "billing_address_1")
    private WebElement billingAddressTextBox;

    @FindBy(id = "billing_postcode")
    private WebElement billingPostcodeTextBox;

    @FindBy(xpath = "//button[@name=\"save_address\"]")
    private WebElement billingSaveAddressButton;

    @FindBy(xpath = "//div[@class=\"woocommerce-message\"]")
    private WebElement billingSuccessfullySavedMessage;

    // shipping details

    @FindBy(id = "shipping_first_name")
    private WebElement shippingFirstNameInput;

    @FindBy(id = "shipping_last_name")
    private WebElement shippingLastNameInput;

    @FindBy(id = "select2-shipping_country-container")
    private WebElement shippingCountryDropdown;

    @FindBy(id = "select2-shipping_state-container")
    private WebElement shippingStateDropdown;

    @FindBy(id = "shipping_city")
    private WebElement shippingTownTextBox;

    @FindBy(id = "shipping_address_1")
    private WebElement shippingAddressTextBox;

    @FindBy(id = "shipping_postcode")
    private WebElement shippingPostcodeTextBox;


    // account details
    @FindBy(xpath = "//a[text()='Account details']")
    private  WebElement accountDetailsLink;

    @FindBy(id = "account_first_name")
    private WebElement accountFirstNameInput;

    @FindBy(id = "account_last_name")
    private WebElement accountLastNameInput;

    @FindBy(id = "account_display_name")
    private WebElement accountDisplayNameInput;

    @FindBy(id = "account_email")
    private WebElement accountEmailInput;

    @FindBy( xpath = "//button[text()=\"Save changes\"]")
    private  WebElement accountSaveChangesButton;

    public void runTest(String regUsername, String email, String password, String firstNameBilling,
                        String lastNameBilling, String address1Billing, String cityBilling, String countryBilling,
                        String countyBilling, String postcodeBilling, String phoneBilling)
            throws InterruptedException {

        webdriver.manage().window().maximize();
        clickDismiss();
        myAccount.click();

        registerUsername.sendKeys(regUsername);
        regEmail.sendKeys(email);
        Thread.sleep(Duration.ofSeconds(4));
        passwordField.sendKeys(password);
        registerButton.click();

        Thread.sleep(Duration.ofSeconds(4));
        logoutLink.click();

        logIn(regUsername, password);
        Thread.sleep(Duration.ofSeconds(2));

        addressesLink.click();
        fillInBilling(firstNameBilling, lastNameBilling, address1Billing, cityBilling, countryBilling, countyBilling,
                postcodeBilling, phoneBilling);

        fillInShipping(firstNameBilling, lastNameBilling, address1Billing, cityBilling,
                       countryBilling, countyBilling, postcodeBilling);

        accountDetailsLink.click();

        fillInAccountDetails(firstNameBilling, lastNameBilling);

    }

    private void fillInAccountDetails(String firstNameBilling, String lastNameBilling) {

        accountFirstNameInput.sendKeys(firstNameBilling);
        accountLastNameInput.sendKeys(lastNameBilling);
        accountDisplayNameInput.sendKeys(firstNameBilling + " " + lastNameBilling);

        accountSaveChangesButton.click();

        String expectedMessage = "Account details changed successfully.";
        String actualMessage = billingSuccessfullySavedMessage.getText();
        Assert.assertTrue(actualMessage.contains(expectedMessage));
    }

    private void fillInShipping(String firstNameBilling, String lastNameBilling, String address1Billing,
                                String cityBilling, String countryBilling, String countyBilling, String postcodeBilling)
            throws InterruptedException {

        addressesButtons.get(1).click();
        shippingFirstNameInput.sendKeys(firstNameBilling);
        shippingLastNameInput.sendKeys(lastNameBilling);

        shippingCountryDropdown.click();
        String countryxpath = "//li[text()=\"" + countryBilling + "\"]";
        webdriver.findElement(By.xpath(countryxpath)).click();

        Thread.sleep(Duration.ofSeconds(1));
        shippingStateDropdown.click();
        String countyxpath = "//li[text()=\"" + countyBilling + "\"]";
        webdriver.findElement(By.xpath(countyxpath)).click();

        shippingAddressTextBox.sendKeys(address1Billing);
        shippingTownTextBox.sendKeys(cityBilling);
        shippingPostcodeTextBox.sendKeys(postcodeBilling);

        billingSaveAddressButton.click();

        String expectedMessage = "Address changed successfully.";
        String actualMessage = billingSuccessfullySavedMessage.getText();

        Assert.assertTrue(actualMessage.contains(expectedMessage));

    }

    private void fillInBilling(String firstNameBilling, String lastNameBilling, String address1Billing,
                               String cityBilling, String countryBilling, String countyBilling,
                               String postcodeBilling, String phoneBilling)
            throws InterruptedException {

        addressesButtons.get(0).click();

        billingFirstNameInput.sendKeys(firstNameBilling);
        billinglastNameInput.sendKeys(lastNameBilling);

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
        billingPostcodeTextBox.sendKeys(postcodeBilling);

        Thread.sleep(Duration.ofSeconds(4));
        billingSaveAddressButton.click();

        String expectedMessage = "Address changed successfully.";
        String actualMessage = billingSuccessfullySavedMessage.getText();

        Assert.assertTrue(actualMessage.contains(expectedMessage));

    }
}
