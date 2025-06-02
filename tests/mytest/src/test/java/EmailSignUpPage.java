import org.openqa.selenium.*;

public class EmailSignUpPage extends PageBase
{
    private By emailInputLocator     = By.id  ( "email"     );
    private By firstNameInputLocator = By.id  ( "firstName" );
    private By lastNameInputLocator  = By.id  ( "lastName"  );
    private By genderInputLocator    = By.name( "gender"    );
    private By dayInputLocator       = By.name( "dob.day"   );
    private By monthInputLocator     = By.name( "dob.month" );
    private By yearInputLocator      = By.name( "dob.year"  );

    private By signUpButtonLocator   = By.xpath( "//*[@id=\"marketing_signup\"]/button" );

    public EmailSignUpPage( WebDriver driver )
    {
        super( driver );
    }

    public void signUpEmail( String email, String firstName, String lastName, String gender, String day, String month, String year )
    {
        enterTextInput( email    , emailInputLocator     );
        enterTextInput( firstName, firstNameInputLocator );
        enterTextInput( lastName , lastNameInputLocator  );

        selectOption( gender, genderInputLocator );
        selectOption( day   , dayInputLocator    );
        selectOption( month , monthInputLocator  );
        selectOption( year  , yearInputLocator   );
        
        click( signUpButtonLocator );
    }
    
    public String getFirstInputFieldText()
    {
        WebElement input = waitAndReturnElement( emailInputLocator );
        
        return input.getText();
    }
}