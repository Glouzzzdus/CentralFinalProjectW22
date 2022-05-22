package StepDefinitions;

import PageFactoryManager.PageFactoryManager;
import Pages.FeedPage;
import Pages.HomePage;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import static io.github.bonigarcia.wdm.WebDriverManager.chromedriver;
import static org.junit.Assert.*;

public class DefinitionsSteps
{
    private static final long TIMEOUT = 120;
    private static final int ITERATIONS = 10;
    private static final double LIMIT_OF_FREQUENCY = 0.6;
    WebDriver driver;
    HomePage homePage;
    FeedPage feedPage;
    PageFactoryManager pageFactoryManager;
    @Before
    public void testsSetUp()
    {
        ChromeOptions options = new ChromeOptions();
        options.setExperimentalOption("excludeSwitches", new String[]{"enable-automation"});
        chromedriver().setup();
        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        pageFactoryManager = new PageFactoryManager(driver);
    }
    @After
    public void tearDown() {
        driver.close();
    }

    @Given("Go to {string} page")
    public void openPage(final String url)
    {
        homePage = pageFactoryManager.getHomePage();
        homePage.openHomePage(url);
    }

    @When("Switch to {string} language using one of the links")
    public void switchToLanguageUsingOneOfTheLinks(final String language)
    {
        homePage.waitForPageLoadComplete(TIMEOUT);
        homePage.setLanguage(language);
    }

    @Then("Verify that the text of the first  element, which is the first paragraph, contains the {string}")
    public void verifyThatTheFirstParagraphContainsTheWord(final String word)
    {
        homePage.waitVisibilityOfElement(TIMEOUT, homePage.getFirstParagraph());
        assertTrue(homePage.getTextFromFirstParagraph().contains(word));
    }

    @When("Press “Generate Lorem Ipsum”")
    public void pressGenerateLoremIpsum()
    {
        homePage.waitForPageLoadComplete(TIMEOUT);
        homePage.clickGenerateButton();
    }

    @Then("Verify that the first paragraph starts with {string}")
    public void verifyThatTheFirstParagraphStartsWithText(final String text)
    {
        feedPage = pageFactoryManager.getFeedPage();
        feedPage.waitForPageLoadComplete(TIMEOUT);
        assertTrue(feedPage.getTextFromFirstGeneratedParagraph().startsWith(text));
    }

    @And("Click on {string} radio-button")
    public void clickOnRadioButton(final String radioButtonName)
    {
        homePage.setRadioButtonsSelect(radioButtonName);
    }

    @When("Input {int} into the number field")
    public void inputAmountIntoTheNumberField(final int amount)
    {
        homePage.setOfAmountItems(amount);
    }

    @Then("Verify the result has {int} appropriate {string} results")
    public void verifyTheAppropriateResults(final int resultOfGeneration, final String typeOfItems)
    {
        feedPage = pageFactoryManager.getFeedPage();
        feedPage.waitForPageLoadComplete(TIMEOUT);
        assertEquals(resultOfGeneration, feedPage.getGenerateResults(typeOfItems));
    }

    @And("Click on start checkbox")
    public void clickOnStartCheckbox()
    {
        homePage.clickStartCheckbox();
    }

    @Then("Verify that result no longer starts with {string}")
    public void verifyThatResultNoLongerStartsWithText(final String text)
    {
        feedPage = pageFactoryManager.getFeedPage();
        feedPage.waitForPageLoadComplete(TIMEOUT);
        assertFalse(feedPage.getTextFromFirstGeneratedParagraph().startsWith(text));
    }

    @Then("Check that the randomly generated text paragraphs containing the {string} less than limit if {int} paragraphs each generate")
    public void checkThatTheAverageNumberIsLessThanLimit(final String word, final int amount)
    {
        feedPage = pageFactoryManager.getFeedPage();
        homePage.waitForPageLoadComplete(TIMEOUT);
        double MatchCounter = 0;
        for(int i = 1; i <= ITERATIONS; i++)
        {

            homePage.clickGenerateButton();
            feedPage.waitForPageLoadComplete(TIMEOUT);
            for (WebElement paragraph: feedPage.getGeneratedParagraphs())
            {
               if(paragraph.getText().toLowerCase().contains(word)) MatchCounter++;
            }
            driver.navigate().back();
            homePage.waitVisibilityOfElement(TIMEOUT, homePage.getGenerateButton());
        }
        double averageCounter = MatchCounter / (ITERATIONS * amount);
        assertTrue(averageCounter < LIMIT_OF_FREQUENCY);
    }
}
