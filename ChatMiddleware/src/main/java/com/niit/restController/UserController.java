package com.niit.restController;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.niit.DAO.UserDAO;
import com.niit.model.UserDetails;

@RestController
public class UserController {
	@Autowired
	UserDAO userDAO;
	
	@PostMapping(value="/registerUser")
	public ResponseEntity<String> registerUser(@RequestBody UserDetails userDetails)
	{
		if(userDAO.registerUser(userDetails))
		{
			return new ResponseEntity<String>("Successfully registered",HttpStatus.OK);
		}
		else
		{
			return new ResponseEntity<String>("Registeration problem",HttpStatus.UNAUTHORIZED);
		}	
	}
	
	@PostMapping(value="/checkUser")
	public ResponseEntity<UserDetails> checkUser(@RequestBody UserDetails userDetails, HttpSession session)
	{
		if(userDAO.checkCredential(userDetails))
		{
			UserDetails tempUser=userDAO.getUser(userDetails.getLoginname());
			session.setAttribute("userDetails", tempUser);	
	    	
			return new ResponseEntity<UserDetails>(tempUser,HttpStatus.OK);
		}
		else
		{
			return new ResponseEntity<UserDetails>(userDetails,HttpStatus.UNAUTHORIZED);
		}
	}
	
		
}
