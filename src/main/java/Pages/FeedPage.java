package Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.nio.charset.StandardCharsets;
import java.util.List;

public class FeedPage extends BasePage
{
    @FindBy(xpath = "//div[@id='lipsum']/p[1]")
    private WebElement firstGeneratedParagraph;
    @FindBy(xpath = "//div[@id='lipsum']/p")
    private List<WebElement> generatedParagraphs;
    @FindBy(xpath = "//div[@id='lipsum']/ul")
    private List<WebElement> generatedLists;

    public String getTextFromFirstGeneratedParagraph()  { return firstGeneratedParagraph.getText(); }
    public List<WebElement> getGeneratedParagraphs()  { return generatedParagraphs; }
    public int getGenerateResults(String radioButtonName)
    {
        int resultOfGenerate = 0;
        switch (radioButtonName)
        {
            case "paragraphs": resultOfGenerate = generatedParagraphs.size();
                break;
            case "words":
                for (WebElement element: generatedParagraphs) {resultOfGenerate += element.getText().split(" ").length;}
                break;
            case "bytes":
                for (WebElement element: generatedParagraphs) {resultOfGenerate += element.getText().getBytes(StandardCharsets.UTF_8).length;}
                break;
            case "lists": resultOfGenerate = generatedLists.size();
                break;
        }
        return resultOfGenerate;
    }
    public FeedPage(WebDriver driver) { super(driver); }
}
