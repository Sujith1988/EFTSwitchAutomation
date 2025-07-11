package tests;
import java.io.IOException;
import org.testng.annotations.Test;
import base.TestBase;
import utils.popupWindwHandlr;
import dataprovider.ExcelDataProvider;
import pages.Home;
import pages.Login;

public class LoginTest extends TestBase {	
	
	/*---------Login Test scenario, +ve and -ve test cases*/
	@Test(dataProvider = "loginData", dataProviderClass = ExcelDataProvider.class)
	public void LoginFunc(String usrN, String passW) throws NumberFormatException, InterruptedException, IOException  {		
		// POM -- Login page (class object-instance created and constructor invoked)
		Login log = new Login();	
				
		// The actual login fuction testing
		log.userNameFill(usrN);
		log.userPassFill(passW);     Thread.sleep(log.slp_2);	
		log.loginbtnClick();         Thread.sleep(log.slp_2);
												
		//handling the alert window popup 💡	
		int alert_active = popupWindwHandlr.alertHandler();			
					
		try {
			// POM -- home page (class object-instance created and constructor invoked)
			Home hom = new Home();
			// Logout and printing the test status
			String LogoutText = hom.getButtonText();
			System.out.println(LogoutText);
			hom.clickButton(); // Thread.sleep(slp_1);
			String actual = hom.actualText();

			if (LogoutText.equals(actual)) {
				System.out.println("*******Login test-positive success and clicked on button :" + LogoutText + "*****");
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
};