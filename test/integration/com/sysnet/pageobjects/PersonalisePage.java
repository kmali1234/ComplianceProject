package com.sysnet.pageobjects;

		import java.util.NoSuchElementException;
		import java.util.Properties;
		import java.util.concurrent.TimeUnit;

		import org.openqa.selenium.By;
		import org.openqa.selenium.WebDriver;
		import org.openqa.selenium.WebElement;

		import com.thoughtworks.selenium.webdriven.ElementFinder;
		import com.thoughtworks.selenium.webdriven.commands.IsElementPresent;

		public class PersonalisePage {

			

			private static WebDriver driver;
			private By usernameFieldLocator;
			private By conformUsernameLocator;
			private By passwordLocator;
			private By conformPasswordLocator;
			private By emailLocator;
			private By conformEmailLocators;
			private By submitButtonLocator;
			private static By acceptFieldLocator;
			private String url;
			private Properties props;
			

			public PersonalisePage(WebDriver driver, Properties props) {
				// TODO Auto-generated constructor stub
				this.driver = driver;
				
				this.url =props.getProperty("baseUrl")+props.getProperty("personalise.url.suffix");
				
				this.acceptFieldLocator=By.cssSelector(props.getProperty("personalise.button.accept.css"));
				this.usernameFieldLocator=By.id(props.getProperty("personalise.textfield.username.id"));
				this.conformUsernameLocator=By.id(props.getProperty("personalise.textfield.confirmUsername.id"));
				this.passwordLocator=By.id(props.getProperty("personalise.textfield.password.id"));
				this.conformPasswordLocator=By.id(props.getProperty("personalise.textfield.confirmPassword.id"));
				this.emailLocator=By.id(props.getProperty("personalise.textfield.email.id"));
				this.conformEmailLocators= By.id(props.getProperty("personalise.textfield.confirmEmail.id"));
				this.submitButtonLocator= By.cssSelector(props.getProperty("personalise.button.submit.css"));
				
			}

			public void doPersonalisation(String username) {
				// TODO Auto-generated method stub
				if(isElementDisplayed(acceptFieldLocator)){
					WebElement understandButton = driver.findElement(acceptFieldLocator);
					understandButton.click();
				}
				System.out.println("Checking wether personalised or not");
				isPerssonalised(username);
				
				
				
			}

			private void isPerssonalised(String username) {
				// TODO Auto-generated method stub
				if (isElementDisplayed(usernameFieldLocator)) {
					enterUserName(username);
					EnterPasswordEmail();
					submitForm();
					}else{
						System.out.println("Merchant is Personalised");
					}
				
				
			}

			private PersonalisePage enterUserName(String username) {
				// TODO Auto-generated method stub
				driver.findElement(usernameFieldLocator).clear();
				driver.findElement(usernameFieldLocator).sendKeys(username);
				driver.findElement(conformUsernameLocator).clear();
				driver.findElement(conformUsernameLocator).sendKeys(username);
				return this;
				
			}
			
			public  PersonalisePage EnterPasswordEmail(){
				driver.findElement(passwordLocator).sendKeys("password");
				driver.findElement(conformPasswordLocator).sendKeys("confirmPassword");
				driver.findElement(emailLocator).clear();
				driver.findElement(emailLocator).sendKeys("email");
				driver.findElement(conformEmailLocators).clear();
				driver.findElement(conformEmailLocators).sendKeys("confirmEmail");
				return this;
			}
			
			public  PersonalisePage submitForm(){
				driver.findElement(submitButtonLocator).submit();
				
				return this;
			}	


			private boolean isElementPresent(By by) {
				/*This boolean method checks if the element passed to it is present on a page or not
				*The method creates a list of WebElements that match the "By" variable passed to it as an argument
				*If the size of this list is greater than 0, then the Element is present on the page.
				*Because Selenium WebDriver can take a while to search for an element, the call to create the list
				*times out after 2 seconds*/
				try {
					driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
					return driver.findElements(by).size() > 0;
					
				} catch (NoSuchElementException e) {
					return false;
				}
			}

			
			
			private boolean isElementDisplayed(By by){
					driver.manage().timeouts().implicitlyWait(250, TimeUnit.MILLISECONDS);
					if (isElementPresent(by)) {
					    if (driver.findElement(by).isDisplayed()) {
					        return true;
					    } else
					        return false;
					} else
					        return false;
				}
				

		

	}


