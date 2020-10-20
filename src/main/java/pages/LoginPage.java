package pages;

import com.automation.qa.page.BasicPage;
import io.cucumber.datatable.DataTable;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import com.automation.qa.util.Common;

import java.util.Map;
import java.util.logging.Logger;

/**
 * Created by Juhi Rana on 10/08/2020.
 */
public class LoginPage extends BasicPage {
    private static final Logger LOGGER = Logger.getLogger(String.valueOf(LoginPage.class));
    private WebDriverWait wait = new WebDriverWait(driver, 60);
    Common common = new Common();


    @FindBy(xpath = "//a[@class='login']")
    private WebElement Login;

    @FindBy(xpath = "//input[@id='email_create']")
    private WebElement SingUp_Enter_Email;

    @FindBy(xpath = "//button[@id='SubmitCreate']")
    private WebElement SubmitCreate;

    @FindBy(xpath = "//h1[@class='page-heading']")
    private WebElement CreateUser_Heading;

    @FindBy(xpath = "//input[@id='customer_firstname']")
    private WebElement CreateUser_FirstName;

    @FindBy(xpath = "//input[@id='customer_lastname']")
    private WebElement CreateUser_LastName;

    @FindBy(xpath = "//input[@id='email']")
    private WebElement CreateUser_Email;

    @FindBy(xpath = "//input[@id='passwd']")
    private WebElement CreateUser_Password;

    @FindBy(xpath = "//input[@id='firstname']")
    private WebElement CreateUser_Address_FirstName;

    @FindBy(xpath = "//input[@id='lastname']")
    private WebElement CreateUser_Address_LastName;

    @FindBy(xpath = "//input[@id='company']")
    private WebElement CreateUser_Address_Company;

    @FindBy(xpath = "//input[@id='address1']")
    private WebElement CreateUser_Address_AddLine1;

    @FindBy(xpath = "//input[@id='address2']")
    private WebElement CreateUser_Address_AddLine2;

    @FindBy(xpath = "//input[@id='city']")
    private WebElement CreateUser_Address_City;

    @FindBy(xpath = "//select[@id='id_state']")
    private WebElement CreateUser_Address_Select_State;

    @FindBy(xpath = "//input[@id='postcode']")
    private WebElement CreateUser_Address_Postal_Code;

    @FindBy(xpath = "//select[@id='id_country']")
    private WebElement CreateUser_Address_Select_Country;

    @FindBy(xpath = "//input[@id='phone_mobile']")
    private WebElement CreateUser_Address_Mobile;

    @FindBy(xpath = "//input[@id='alias']")
    private WebElement CreateUser_Address_Assign_Alias_Address_Ref;

    @FindBy(xpath = "//button[@id='submitAccount']")
    private WebElement CreateUser_Address_Submit;

    @FindBy(xpath = "//p[@class='info-account']")
    private WebElement CreateUser_Sucess_Message;


    /**
     * Initialize ProductDisplayPage elements
     *
     * @param driver
     * @throws Exception
     */
    public LoginPage(RemoteWebDriver driver) throws Exception {
        super(driver);
        //Initialize Elements
        PageFactory.initElements(driver, this);
//        driver.manage().timeouts().implicitlyWait(Constant.Implicit_Wait_Time, TimeUnit.SECONDS);
    }


    public boolean check_HomePage_Page_Title(String expectedTitle) throws Exception {
        return driver.getTitle().equals(expectedTitle);
    }

    public String navigate_To_SigUp_Page(String value){
        String email="";
        try {
            common.addHardWait(10);
            if (value.equals(null) || value.equals("##################")) {
                email = driver.getSessionId()+"@gmail.com";
                System.out.print("Email: "+email);
            }
            Login.click();
            SingUp_Enter_Email.sendKeys(email);
            SubmitCreate.click();
            common.addHardWait(10);
            LOGGER.info("Navigated to Sign Up page");
            return "Pass";

        } catch (Exception e) {
            e.getMessage();
            LOGGER.info("Failed to Navigate to Sign Up page");
            return "Fail";
        }
    }

    public String enter_SignUp_Data(DataTable datatable) {
        Map<String, String> dataMap = datatable.asMap(String.class, String.class);
             try {
            for (Map.Entry<String, String> entry : dataMap.entrySet()) {
                common.addHardWait(3);
                switch (entry.getKey()) {
                    case "FirstName":{
                        CreateUser_FirstName.sendKeys(entry.getValue());
                        break;}
                    case "LastName":{
                        CreateUser_LastName.sendKeys(entry.getValue());
                        break;}
                    case "Password":{
                        CreateUser_Password.sendKeys(entry.getValue());
                        break;}
                    case "Company":{
                        CreateUser_Address_Company.sendKeys(entry.getValue());
                        break;}
                    case "Address1":{
                        CreateUser_Address_AddLine1.sendKeys(entry.getValue());
                        break;}
                    case "Address2":{
                        CreateUser_Address_AddLine2.sendKeys(entry.getValue());
                        break;}
                    case "City":{
                        CreateUser_Address_City.sendKeys(entry.getValue());
                        break;}
                    case "State": {
                        Select list = new Select(CreateUser_Address_Select_State);
                        list.selectByVisibleText(entry.getValue());
                    }
                    break;
                    case "Postal_Code":{
                        CreateUser_Address_Postal_Code.sendKeys(entry.getValue());
                        break;}
                    case "Country": {
                        Select list = new Select(CreateUser_Address_Select_Country);
                        list.selectByVisibleText(entry.getValue());
                    }
                    break;
                    case "Mobile":{
                        CreateUser_Address_Mobile.sendKeys(entry.getValue());
                        break;}
                    case "Adress_Alias":{
                        CreateUser_Address_Assign_Alias_Address_Ref.sendKeys(entry.getValue());
                        break;}
                }

            }
            CreateUser_Address_Submit.click();
                 common.addHardWait(4);
            LOGGER.info("Successfully entered values on signup page");
            return "Pass";
        } catch (Exception e) {
            LOGGER.info("Failed to enter values on signup page with error message :"+e.getMessage());
            return "Fail";
        }
    }

    public String signUp_Status(String expectedValue){
        String actualValue ="";
       try {
           common.addHardWait(4);
           actualValue=  CreateUser_Sucess_Message.getText();
          if(expectedValue.equals(actualValue)){
              LOGGER.info("Expected message( "+expectedValue+") matched with actual message( "+actualValue+")");
              return "Pass";
          }
          else {
              LOGGER.info("Expected message( "+expectedValue+") did not matched with actual message( "+actualValue+")");
              return "Fail";
          }
       }catch (Exception e) {
           LOGGER.info("Expected message( "+expectedValue+") did not matched with actual message( "+actualValue+") error message is : "+e.getMessage());
           return "Fail";
       }
    }





}
