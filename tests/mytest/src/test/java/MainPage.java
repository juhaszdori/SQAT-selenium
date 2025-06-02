import org.openqa.selenium.*;
import org.openqa.selenium.chrome.*;
import org.openqa.selenium.support.ui.*;

public class MainPage extends PageBase
{
    private By cookiesLocator       = By.id( "onetrust-accept-btn-handler"                          );
    private By aboutUsButtonLocator = By.xpath( "//*[@id=\"panel-pages-\"]/a[3]"                    );
    private By accountButtonLocator = By.xpath( "//*[@id=\"__next\"]/div[1]/header/div[2]/div[4]/a" );

    public MainPage( WebDriver driver )
    {
        super( driver );
        this.driver.get( "https://www.gymshark.com/" );
    }

    public LoginPage openLoginPage()
    {
        click( accountButtonLocator );

        return new LoginPage( this.driver );
    }

    public PageBase openAboutUsPage()
    {
        click( aboutUsButtonLocator );

        return new PageBase( this.driver );
    }

    public void acceptCookies()
    {
        WebElement acceptCookies = this.wait.until( ExpectedConditions.elementToBeClickable( cookiesLocator ) );
        acceptCookies.click();
    }
}