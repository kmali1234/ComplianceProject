package com.sysnet.compliancemaker;


		// TODO Auto-generated constructor stub
		package com.sysnet.compliancemaker;


		import java.io.File;
	import java.io.FileInputStream;
	import java.util.Properties;

	import org.apache.commons.io.FileUtils;
	import org.junit.After;
	import org.junit.Before;
	import org.junit.Test;
	import org.openqa.selenium.OutputType;
	import org.openqa.selenium.TakesScreenshot;
	import org.openqa.selenium.WebDriver;
	import org.openqa.selenium.firefox.FirefoxDriver;

	import com.sysnet.pageobjects.LoginPage;


	//import com.sun.java.util.jar.pack.Package.File;

		public class DoPersonalise {
			
			private Properties clientProps;
			private String baseurl;
			private String loginpageUrlSufix;
			private WebDriver driver;
			private String username;
			private String propertyfilepath;
			private Properties userprops;
			private String password;
			private String url;
			private String propertyfilepath1;
			private Object mySheet;
			private Object baseUrl;
			private Object https;
			

			@Before
			public void SetUp() throws Exception
			{
				
				
				propertyfilepath="test/integration/sysnetuslocators.properties";
				clientProps = new Properties();
				FileInputStream locatorStream = new FileInputStream(propertyfilepath);
				clientProps.load(locatorStream);
				baseUrl=clientProps.getProperty("baseUrl");
				loginpageUrlSufix=clientProps.getProperty("login.url.suffix");
				url = baseUrl+loginpageUrlSufix;
				
				
				propertyfilepath1= "/test/integration/personaliseobjects.properties";
				userprops = new Properties();
				FileInputStream UserLocators = new FileInputStream(propertyfilepath1);
				userprops.load(UserLocators);
				password = userprops.getProperty("password");
				confirmPassword = userprops.getProperty("confirmPassword");
				
			}
			
			
			@Test
			public void MerchantLogin() throws Exception
			{
				driver = new FirefoxDriver();
				driver.manage().window().maximize();
				driver.get(url);
				LoginPage lp = new LoginPage(driver, clientProps);
				lp.navigateTo();
				lp.TypeUserName(username);
				lp.TypePassword(password);
				lp.SubmitLogin();
				// take the screenshot at the end of every test
		        File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		        // now save the screenshto to a file some place
		        FileUtils.copyFile(scrFile, new java.io.File("C:\\Users\\kmali\\Desktop\\Kavitha\\"));
		        
			}
			
			
			
			@After
			public void Quit()
			{
				 driver.close();
				 
			}
			
			
	}



	}

}
