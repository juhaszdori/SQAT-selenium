import org.junit.Assert.*;
import org.junit.*;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.*;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.*;

import java.net.URL;
import java.net.MalformedURLException;

import java.util.List;
import java.util.ArrayList;

public class SeleniumTest
{
    private WebDriver driver;
    private ConfigReader configReader;

    private List<PageData> loadStaticPagesFromConfig()
    {
        List<PageData> pages = new ArrayList<>();
        int index = 1;

        while( true )
        {
            String url = configReader.getPageUrl( index );
            String title = configReader.getPageTitle( index );

            if( url == null || title == null )
            {
                break;
            }

            pages.add( new PageData( url, title ) );
            index++;
        }

        return pages;
    }

    @Before
    public void setup() throws MalformedURLException
    {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--disable-dev-shm-usage");

        this.driver = new RemoteWebDriver( new URL( "http://selenium:4444/wd/hub" ), options );
        this.driver.manage().window().maximize();
        
        this.configReader = new ConfigReader();
    }

    @Test
    public void testMainPageTitle()
    {
        MainPage mainPage = new MainPage( this.driver );
        String actualTitle = this.driver.getTitle();
        String expectedTitle = configReader.getBaseTitle();
        
        Assert.assertEquals( actualTitle, expectedTitle );
    }

    @Test
    public void testLogin()
    {
        MainPage mainPage = new MainPage( this.driver );

        LoginPage loginPage = mainPage.openLoginPage();
        AccountPage accountPage = loginPage.login( configReader.getEmail(), configReader.getPassword() );

        Assert.assertTrue( accountPage != null );
    }

    @Test
    public void testLogout()
    {
        MainPage mainPage = new MainPage( this.driver );

        LoginPage loginPage = mainPage.openLoginPage();
        AccountPage accountPage = loginPage.login( configReader.getEmail(), configReader.getPassword() );

        MainPage mainPageAfterLogin = accountPage.logout();

        Assert.assertTrue( mainPageAfterLogin != null);
    }

    @Test
    public void testFormSendingWithUser()
    {   
        MainPage mainPage = new MainPage( this.driver );
        mainPage.acceptCookies();

        LoginPage loginPage = mainPage.openLoginPage();
        AccountPage accountPage = loginPage.login( configReader.getEmail(), configReader.getPassword() );

        EmailSignUpPage emailSignUpPage = accountPage.openEmailSignUpPage();
        emailSignUpPage.signUpEmail( configReader.getEmail()
                                   , configReader.getFirstName()
                                   , configReader.getLastName()
                                   , configReader.getGender()
                                   , configReader.getBirthDay()
                                   , configReader.getBirthMonth()
                                   , configReader.getBirthYear() );

        Assert.assertTrue( emailSignUpPage.getFirstInputFieldText().equals( "" ) ); 
    }

    @Test
    public void testStaticPage()
    {
        MainPage mainPage = new MainPage( this.driver );
        mainPage.acceptCookies();

        PageBase aboutUsPage = mainPage.openAboutUsPage();
        Assert.assertTrue( aboutUsPage.getBodyText().contains( "ABOUT US" ) );
    }

    @Test
    public void testBrowserBackButton()
    {
        MainPage mainPage = new MainPage( this.driver );
        mainPage.acceptCookies();

        PageBase aboutUsPage = mainPage.openAboutUsPage();
        driver.navigate().back();

        String currentUrl = this.driver.getCurrentUrl();
        Assert.assertEquals( currentUrl, configReader.getBaseUrl() );
    }

    @Test
    public void testStaticPages()
    {
        List<PageData> pages = loadStaticPagesFromConfig();
        
        for( PageData page : pages )
        {
            this.driver.get( page.url );
            String actualTitle = this.driver.getTitle();

            Assert.assertEquals( actualTitle, page.title );
        }
    }

    @After
    public void close()
    {
        if ( this.driver != null )
        {
             this.driver.quit();
        }
    }
}