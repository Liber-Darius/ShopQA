package pages;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;

import java.time.Duration;
import java.util.List;

public class WishlistProducts extends MainPage{
    public WishlistProducts(WebDriver driver) {
        super(driver);
    }

    // shop elements
    @FindBy(xpath = "//div[@class=\"noo-product-inner\"]")
    private List<WebElement> shopProducts;

    @FindBy(xpath = "//a[@data-title=\"Add to Wishlist\"]")
    private WebElement addToWishlistButton;

    //a[@class="remove remove_from_wishlist"]
    @FindBy(xpath = "//a[@class=\"remove remove_from_wishlist\"]")
    private List<WebElement> removeFromWishlistButtonList;

    @FindBy(xpath = "//a[text()=\"My Wishlist\"]")
    private WebElement myWishlistLink;

    public void runTest(String username, String password) throws InterruptedException {

        clickDismiss();
        clickMyAccount();
        logIn(username, password);

        webdriver.navigate().to("https://shop.demoqa.com/shop/");

        // add to wishlist
        for (int i = 0; i < 3; i++) {
            Actions actions = new Actions(webdriver);
            Thread.sleep(Duration.ofSeconds(1));
            actions.moveToElement(shopProducts.get(i)).perform();
            shopProducts.get(i).findElement(By.xpath("//a[@data-title=\"Add to Wishlist\"]")).click();
        }

        Thread.sleep(Duration.ofSeconds(2));
        webdriver.navigate().to("https://shop.demoqa.com/wishlist/");

        Assert.assertTrue(removeFromWishlistButtonList.size() == 3);

    }
}
