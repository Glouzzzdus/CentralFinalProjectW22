package Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

import java.nio.channels.SelectableChannel;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class HomePage extends BasePage
{
    @FindBy(xpath = "//div[@id='Languages']/a")
    private List<WebElement> languageButtons;
    @FindBy(xpath = "//div[@id='Panes']/div[1]/p")
    private WebElement firstParagraph;
    @FindBy(id = "generate")
    private WebElement generateButton;
    @FindBy(xpath = "//td//label")
    private List<WebElement> radioButtonsSelect;
    @FindBy(id = "amount")
    private WebElement inputOfAmount;
    @FindBy(xpath = "//input[@id='start']")
    private WebElement startCheckbox;
    public void openHomePage(String url) { driver.get(url); }
    public WebElement getLanguage(String language)
    {
        WebElement e = null;
        for (WebElement element: languageButtons)
        {
          if(element.getText().contains(language))
          {
              e = element;
              break;
          }
        }
        return e;
    }
    public void setLanguage(String language)
    {
        getLanguage(language).click();
    }
    public WebElement getFirstParagraph()  { return firstParagraph; }
    public String getTextFromFirstParagraph()    { return firstParagraph.getText(); }
    public WebElement getGenerateButton()  { return generateButton; }
    public void clickGenerateButton()  { generateButton.click(); }
    public void setRadioButtonsSelect(String radioButtonName)
    {
        for (WebElement element: radioButtonsSelect)
        {
            if(element.getText().contains(radioButtonName))
            {
                element.click();
                break;
            }
        }
    }
    public void setOfAmountItems(int amountItems)
    {
        inputOfAmount.clear();
        inputOfAmount.sendKeys(String.valueOf(amountItems));
    }
    public void clickStartCheckbox()  { startCheckbox.click(); }
    public HomePage(WebDriver driver) { super(driver); }
}
