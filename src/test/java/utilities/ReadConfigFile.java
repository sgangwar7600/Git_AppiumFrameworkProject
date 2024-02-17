package utilities;

import java.io.FileInputStream;
import java.util.Properties;

public class ReadConfigFile {

	
	Properties properties;  //making object of  properties class that is present in java for reading the config file values
	
	String path = "C:\\Users\\Shashikant\\eclipse-workspace\\AppiumFrameworkProject\\Configurationn\\configFile.properties";
	
	//constructor
	public ReadConfigFile() {
	
		try {
		
		//Initializing the object of  properties class 
		properties = new Properties();					//we will Initializing the object here , Properties is class name here
		
		FileInputStream fis = new FileInputStream(path);  //creating FileInputStream object for reading the property file path
	
		properties.load(fis);  //loading the property file
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}	
	}
	
	
	//here we have to make the methods for keys that is present in config file
	//methods
	
		public String getBaseUrl() {      //using return type as String , because value returning as string type 
		
		String value = properties.getProperty("baseurl"); //calling getProperty method along with or by using properties object  
		
		if(value!=null)
			return value;
		else 
			throw new RuntimeException("url not specified in config file ..");
	}
	
		public String getEmail()
		{
			String email = properties.getProperty("email");
			if(email!=null)
				return email;
			else
				throw new RuntimeException("email not specified in config file.");
			
		}

		public String getPassword()
		{
			String password = properties.getProperty("password");
			if(password!=null)
				return password;
			else
				throw new RuntimeException("password not specified in config file.");
			
		}
		
		public String getFirstName()
		{
			String firstName = properties.getProperty("firstName");
			if(firstName!=null)
				return firstName;
			else
				throw new RuntimeException("password not specified in config file.");
			
		}
		
		public String getLastName()
		{
			String firstName = properties.getProperty("lastName");
			if(firstName!=null)
				return firstName;
			else
				throw new RuntimeException("password not specified in config file.");
			
		}
		
		public String expectedUserName()
		{
			String expectedUserName = properties.getProperty("expectedUserName");
			if(expectedUserName!=null)
				return expectedUserName;
			else
				throw new RuntimeException("password not specified in config file.");
			
		}
		
	/*
	 * public String getBrowser() {
	 * 
	 * String value = properties.getProperty("browser");
	 * 
	 * if (value!=null) return value; else throw new
	 * RuntimeException("url not specified in config file .."); }
	 */
}
