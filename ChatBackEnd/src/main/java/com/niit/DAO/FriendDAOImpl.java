package com.niit.DAO;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.niit.model.Friends;
import com.niit.model.UserDetails;
@Repository("friendDAO")
public class FriendDAOImpl implements FriendDAO{

	@Autowired
	SessionFactory sessionFactory;

	@Transactional
	public List<Friends> viewFriendRequests(String loginname) 
	{
		try
		{
			Session session=sessionFactory.openSession();
			Query query=session.createQuery("from Friends where (loginname=:myloginname or friendloginname=:friendlogin) and status='A'");
			query.setParameter("myloginname", loginname);
			query.setParameter("friendlogin", loginname);
			
			List<Friends> listFriends=(List<Friends>)query.list();
			return listFriends;
			
		}
		
		catch(Exception e)
		{
			System.out.println("Exception arraised here\n"+e);
			return null;
		}
		
	}

	@Transactional
	public boolean sendFriendRequest(Friends friends)
	{
		try
		{
			sessionFactory.getCurrentSession().save(friends);
			return true;
			
		}
		catch(Exception e)
		{
			System.out.println("Exception arraised here\n"+e);
			return false;
		}
	}
	
	@Transactional
	public boolean acceptFriendRequest(int friendId) {
		try
		{
			Session session=sessionFactory.openSession();
			Friends friend=(Friends) session.get(Friends.class, friendId);
			System.out.println("Friends login"+ friend.getLoginname());
		    friend.setStatus("A");
			
			session.update(friend);
			System.out.println("Updated");
			session.flush();
			session.close();
			return true;
			
		}
		catch(Exception e)
		{
			System.out.println("Exception arraised here\n"+e);
			return false;
		}
	}
	
	@Transactional
	
	public boolean deleteFriendRequest(int friendId) {
		try
		{
			Session session=sessionFactory.openSession();
			Friends friend=(Friends) session.get(Friends.class, friendId);
		   
			session.delete(friend);
		
			session.close();
			return true;
			
		}
		catch(Exception e)
		{
			System.out.println("Exception arraised here\n"+e);
			return false;
		}	}

	@Transactional
	
	public List<UserDetails> viewSuggestedFriendRequests(String loginname) {
		try
		{
			Session session=sessionFactory.openSession();
			Query query=session.createQuery("select loginname from UserDetails where loginname not in(select friendloginname from Friends where loginname='"+loginname+"') and loginname!='"+loginname+"'" );
			
			List<Friends> listFriends=(List<Friends>)query.list();
			ArrayList<UserDetails> listUserDetails=new ArrayList<UserDetails>();			
			int i=0;
			
			while(i<listFriends.size())
			{
				UserDetails userDetails=(UserDetails) session.get(UserDetails.class, (Serializable) listFriends.get(i));
				listUserDetails.add(userDetails);
				i++;
			}
			
			return listUserDetails;

		}
		
		catch(Exception e)
		{
			System.out.println("Exception arraised here\n"+e);
			return null;
		}	
		
	}


	@Transactional
	
	public List<Friends> viewPendingFriendRequests(String loginname) {
		try
		{

			Session session=sessionFactory.openSession();
			Query query=session.createQuery("from Friends where friendloginname=:myloginname and status='P'");
			query.setParameter("myloginname",loginname);
			List<Friends> listFriends=(List<Friends>)query.list();
			return listFriends;
			
		}
		
		catch(Exception e)
		{
			System.out.println("Exception arraised here\n"+e);
			return null;
		}	
		
	}

	
}
	


