package tests;

import org.junit.Test;
import pages.WishlistProducts;
import pages.MainPage;
import webDriver.ShareData;

public class WishlistProductsTest extends ShareData {

    @Test
    public void wishlistProductsTest() throws InterruptedException {

        WishlistProducts wishlistProducts = new WishlistProducts(webDriver);

        // trebuie verificat cu un cont existent
        wishlistProducts.runTest("darius", "Myparola123#");

    }
}
