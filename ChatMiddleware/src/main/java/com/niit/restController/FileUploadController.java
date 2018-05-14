package com.niit.restController;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.niit.DAO.ProfilePictureDAO;
import com.niit.model.ProfilePicture;
import com.niit.model.UserDetails;

@RestController
public class FileUploadController 
{
	@Autowired
	ProfilePictureDAO profilePictureDAO;
	
	@RequestMapping(value="/doUpload",method=RequestMethod.POST)
	public ResponseEntity<String> uploadPicture(@RequestParam(value="file") CommonsMultipartFile profilePic, HttpSession session)
	{
		UserDetails userDetails = ((UserDetails)session.getAttribute("userDetails"));
		if(userDetails==null) 
		{
			System.out.println("User detail null in session");
			return new ResponseEntity<String>("Unauthorized user",HttpStatus.NOT_FOUND);
		}
		
		else 
		{
			System.out.println("Adding pic");
			ProfilePicture profilePicture = new ProfilePicture();
			profilePicture.setUserDetails(userDetails);
			profilePicture.setImage(profilePic.getBytes());
			profilePictureDAO.savePicture(profilePicture);
			return new ResponseEntity<String>("Profile pic upload success",HttpStatus.OK);
		}
	}
	
	@RequestMapping(value="/getImage/{loginname}",method=RequestMethod.GET)
	public @ResponseBody byte[] getProfilePicture(@PathVariable("loginname")String loginname,HttpSession session)
	{
		System.out.println("Login Name is "+loginname);
		ProfilePicture profilePicture = profilePictureDAO.viewProfilePicture(loginname);
		if(profilePicture==null)
			return null;
		else
			return profilePicture.getImage();
	}
}
