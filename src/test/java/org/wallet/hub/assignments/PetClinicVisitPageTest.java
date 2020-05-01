package org.wallet.hub.assignments;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.List;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class PetClinicVisitPageTest {

    private static String petClinicHomePage = null;
    private static String findOwners_Tab_XPath = null;
    private static String lastName_TextBox_Id = null;
    private static String searchable_LastName = null;
    private static String findOwners_Button_XPath = null;
    private static String addVisits_Links_PartialLink = null;

    @BeforeEach
    void setUp() {
        System.setProperty("webdriver.gecko.driver", "geckodriver.exe");
        petClinicHomePage = "http://localhost:8080/";
        findOwners_Tab_XPath = "//a[@title='find owners']";
        lastName_TextBox_Id = "lastName";
        searchable_LastName = "Novo";
        findOwners_Button_XPath = "//button[contains(text(),'Find')]";
        addVisits_Links_PartialLink = "Add Visit";
    }

    @Test
    void addVisitOfPet() {

        WebDriver webDriver = new FirefoxDriver();
        webDriver.get(petClinicHomePage);
        webDriver.manage().window().maximize();

        // Find Owners Tab
        WebElement findOwnersTab = webDriver.findElement(By.xpath(findOwners_Tab_XPath));
        boolean findOwnersTabClicked = false;
        if (findOwnersTab.isEnabled() || findOwnersTab.isDisplayed()) {
            findOwnersTab.click();
            findOwnersTabClicked = true;
        }
        assertTrue(findOwnersTabClicked);
        waitFor3Secs(webDriver);

        // Last Name Text Box
        WebElement lastNameTextBox = webDriver.findElement(By.id(lastName_TextBox_Id));
        boolean isLastNameTextBoxClicked = false;
        if (lastNameTextBox.isDisplayed() || lastNameTextBox.isEnabled()) {
            lastNameTextBox.click();
            isLastNameTextBoxClicked = true;
        }
        assertTrue(isLastNameTextBoxClicked);
        lastNameTextBox.sendKeys(searchable_LastName);
        assertEquals(searchable_LastName, lastNameTextBox.getAttribute("value"));

        // Find Owners Button
        WebElement findOwnersButton = webDriver.findElement(By.xpath(findOwners_Button_XPath));
        boolean findOwnersButtonClicked = false;
        if (findOwnersButton.isEnabled() || findOwnersButton.isDisplayed()) {
            findOwnersButton.submit();
            findOwnersButtonClicked = true;
        }
        assertTrue(findOwnersButtonClicked);
        waitFor3Secs(webDriver);

        // Add Visit Links
        List<WebElement> addVisitLinks = webDriver.findElements(By.partialLinkText(addVisits_Links_PartialLink));
        boolean isAddVisitLinkClicked = false;
        if (addVisitLinks.get(0).isEnabled()) {
            addVisitLinks.get(0).click();
            isAddVisitLinkClicked = true;
        }
        assertTrue(isAddVisitLinkClicked);
    }

    private void waitFor3Secs(WebDriver webDriver) {
        webDriver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
    }
}
