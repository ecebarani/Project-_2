package com.niit.DAO;

import java.util.List;

import com.niit.model.Blog;
import com.niit.model.UserDetails;

public interface UserDAO {
	  
	  public boolean registerUser(UserDetails userDetails);
	  public boolean checkCredential(UserDetails userDetails);
      public UserDetails getUser(String loginname);

}
