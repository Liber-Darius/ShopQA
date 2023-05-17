package tests;

import org.junit.Test;
import pages.CreateLoginAccount;
import pages.MainPage;
import webDriver.ShareData;

public class CreateLoginAccountTest extends ShareData {

    @Test
    public void createLoginAccountTest() throws InterruptedException {


        CreateLoginAccount loginPage = new CreateLoginAccount(webDriver);
        //de rulat cu cont nou
        loginPage.runTest("dar1s3232123", "da41321@ddra.com", "Myparola123#",
                "Ion", "Badea", "Strada Principala", "Dej",
                "Romania", "Arad", "23421", "07321321");

    }
}




