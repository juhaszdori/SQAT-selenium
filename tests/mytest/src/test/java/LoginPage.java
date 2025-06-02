import org.openqa.selenium.*;

public class LoginPage extends PageBase
{
    private By userNameInputLocator = By.id( "username" );
    private By passwordInputLocator = By.id( "password" );
    private By loginButtonLocator   = By.xpath( "/html/body/main/section/div/div/div/form/div[2]/button" );

    public LoginPage( WebDriver driver )
    {
        super( driver );
    }

    public AccountPage login( String username, String password )
    {
        enterTextInput( username, userNameInputLocator );
        enterTextInput( password, passwordInputLocator );
        click( loginButtonLocator );

        return new AccountPage( this.driver );
    }
}