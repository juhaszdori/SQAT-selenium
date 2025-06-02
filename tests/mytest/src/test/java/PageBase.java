import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.*;

public class PageBase
{
    protected WebDriver driver;
    protected WebDriverWait wait;

    protected By bodyLocator = By.tagName( "body" );

    public PageBase( WebDriver driver)
    {
        this.driver = driver;
        this.wait = new WebDriverWait( driver, 15 );
    }

    protected WebElement waitAndReturnElement( By locator )
    {
        this.wait.until( ExpectedConditions.visibilityOfElementLocated( locator ) );

        return this.driver.findElement( locator );
    }

    public String getBodyText()
    {
        WebElement bodyElement = waitAndReturnElement( bodyLocator );
        
        return bodyElement.getText();
    }

    public void enterTextInput( String inputText, By locator )
    {
        WebElement textInput = waitAndReturnElement( locator );
        textInput.sendKeys( inputText );
    }

    public void selectOption( String optionText, By locator )
    {
        Select objSelect = new Select( driver.findElement( locator ) );
        objSelect.selectByVisibleText( optionText );
    }

    public void click( By locator )
    {
        JavascriptExecutor js = (JavascriptExecutor) this.driver;
        js.executeScript( "arguments[0].click();", this.driver.findElement( locator ) );
    }
}
