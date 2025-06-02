import org.openqa.selenium.*;

public class AccountPage extends PageBase
{
    private By logoutButtonLocator      = By.xpath( "//*[@id=\"MainContent\"]/div/div/div/a"                  ); 
    private By addressBookButtonLocator = By.xpath( "//*[@id=\"MainContent\"]/div/section[2]/ul/li[2]/a"      );
    private By emailSignUpButtonLocator = By.xpath( "//*[@id=\"__next\"]/div[1]/header/div[1]/div/ul/li[4]/a" );

    public AccountPage( WebDriver driver )
    {
        super( driver );
    }

    public EmailSignUpPage openEmailSignUpPage()
    {
        click( emailSignUpButtonLocator );

        return new EmailSignUpPage( this.driver );
    }

    public MainPage logout()
    {
        /*WebElement logoutButton = waitAndReturnElement( logoutButtonLocator );
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript( "window.scrollTo( 0, arguments[0].getBoundingClientRect().top + window.scrollY - 200);", logoutButton );
        try
        {
            Thread.sleep( 1000 );
        }
        catch( InterruptedException e )
        {
            e.printStackTrace();
        }*/

        click( logoutButtonLocator );

        return new MainPage( this.driver );
    }
}
