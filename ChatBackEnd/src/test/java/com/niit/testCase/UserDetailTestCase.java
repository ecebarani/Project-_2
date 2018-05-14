package com.niit.testCase;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.niit.DAO.UserDAO;
import com.niit.model.UserDetails;


public class UserDetailTestCase {
	static UserDAO userDAO;

	@BeforeClass
	public static void executeFirst()
	{
		AnnotationConfigApplicationContext context=new AnnotationConfigApplicationContext();
		context.scan("com.niit");
		context.refresh();
		
		userDAO=(UserDAO)context.getBean("userDAO");
	}
	@Ignore
	@Test
	public void registerUserTest()
	{
		UserDetails user=new UserDetails();
		user.setLoginname("user");
		user.setUserName("user");
		user.setMobileNo("9943443233");
		user.setPassword("user");
		user.setEmailId("user@gmail.com");
		user.setAddress("pondy");
		assertTrue("Problem in user registeration",userDAO.registerUser(user));
	} 
	
    @Ignore
	@Test
	public void checkUserTest()
	{
		UserDetails user=new UserDetails();
		user.setLoginname("user");
	    user.setPassword("user");
		assertTrue("Problem in login process",userDAO.checkCredential(user));
	}
	
	
    //@Ignore
	@Test
	public void getUserTest()
	{
		UserDetails user=new UserDetails();
		assertNotNull("Problem in accessing a user",userDAO.getUser("user"));
	 
		
	}

}
