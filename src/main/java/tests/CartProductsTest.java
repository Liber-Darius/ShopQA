package tests;

import org.junit.Test;
import pages.CartProducts;
import webDriver.ShareData;

public class CartProductsTest extends ShareData {

    @Test
    public void cartProductsTest() throws InterruptedException {

        CartProducts cartProducts = new CartProducts(webDriver);
        cartProducts.runTest("darius", "Myparola123#", "5450", "Lipova","Arad", "Romania", "Ion", "Marinescu", "Strada Principala", "0775645");

    }
}
