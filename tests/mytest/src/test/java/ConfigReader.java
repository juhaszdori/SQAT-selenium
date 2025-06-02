import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigReader
{
    private Properties properties;
    private final String propertyFilePath = "src/test/resources/config.properties";

    public ConfigReader()
    {
       properties = new Properties();
       try( FileInputStream fileInputStream = new FileInputStream( propertyFilePath ) )
       {
           properties.load( fileInputStream );
       }
       catch( IOException e )
       {
           e.printStackTrace();
       }
    }

    public String getBaseUrl()    { return properties.getProperty( "base.url"        ); }
    public String getBaseTitle()  { return properties.getProperty( "base.title"      ); }
    public String getEmail()      { return properties.getProperty( "user.email"      ); }
    public String getPassword()   { return properties.getProperty( "user.password"   ); }
    public String getFirstName()  { return properties.getProperty( "user.firstname"  ); }
    public String getLastName()   { return properties.getProperty( "user.lastname"   ); }
    public String getBirthDay()   { return properties.getProperty( "user.birthday"   ); }
    public String getBirthMonth() { return properties.getProperty( "user.birthmonth" ); }
    public String getBirthYear()  { return properties.getProperty( "user.birthyear"  ); }
    public String getGender()     { return properties.getProperty( "user.gender"     ); }
    public String getPageUrl  ( int index ) { return properties.getProperty( "page" + index + ".url"   ); }
    public String getPageTitle( int index ) { return properties.getProperty( "page" + index + ".title" ); }
}