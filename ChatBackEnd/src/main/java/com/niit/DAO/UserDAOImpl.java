package com.niit.DAO;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.niit.model.UserDetails;

@Repository("userDAO")

public class UserDAOImpl implements UserDAO {
	
	@Autowired
	SessionFactory sessionFactory;

	//@Override
	@Transactional
	public boolean registerUser(UserDetails userDetails) {
		try
		{
			userDetails.setEnabled("Yes");
			userDetails.setRole("ROLE_USER");
			sessionFactory.getCurrentSession().save(userDetails);
			return true;
		}
		catch(Exception e)
		{
			System.out.println("Exception here\n"+ e);	
			return false;
		}
		
		
	}

	
	//@Override
	@Transactional
	public boolean checkCredential(UserDetails userDetails) {
		try
		{
		   Session session=sessionFactory.openSession();
		   Query query=session.createQuery("from UserDetails where loginname=:loginname and password=:password");
		   query.setParameter("loginname", userDetails.getLoginname());
		   query.setParameter("password", userDetails.getPassword());
		   UserDetails userDetails1=(UserDetails)query.list().get(0);
				   
		   System.out.println(userDetails1);
		   
		   if(userDetails1==null)
		   {
			   return false;
		   }
		   else
		   {
			   return true;
		   }   
				   
		}
		catch(Exception e)
		{
			System.out.println("Exception here\n"+ e);	
			return false;
					
		}

		
	}

    @Transactional
	//@Override
	public UserDetails getUser(String loginname) {
			Session session=sessionFactory.openSession();
			UserDetails userDetails=(UserDetails)session.get(UserDetails.class, loginname);
			return userDetails;
		
    }
    
    
    

}    
