package PageFactoryManager;

import Pages.FeedPage;
import Pages.HomePage;
import org.openqa.selenium.WebDriver;

public class PageFactoryManager
{
    WebDriver driver;

    public PageFactoryManager(WebDriver driver) {
        this.driver = driver;
    }

    public HomePage getHomePage() {
        return new HomePage(driver);
    }
    public FeedPage getFeedPage() { return new FeedPage(driver); }
}
