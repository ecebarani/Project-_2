package com.niit.DAO;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;


import com.niit.model.Forum;
import com.niit.model.ForumComment;

@Repository("forumDAO")
public class ForumDAOImpl implements ForumDAO {
	@Autowired
	SessionFactory sessionFactory;
    
	//@Override
	@Transactional
    public boolean addForum(Forum forum) {
		try
		{
			sessionFactory.getCurrentSession().save(forum);
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
	public boolean deleteForum(Forum forum) {
		try
		{
		   sessionFactory.getCurrentSession().delete(forum);
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
	public boolean updateForum(Forum forum) {
		try
		{
			sessionFactory.getCurrentSession().update(forum);
			return true;
		}
		catch(Exception e)
		{
			System.out.println("Exception here\n"+ e);	
		    return false;
		}
		
	}

	//@Override
	
	public List<Forum> listForums(String username) {
		try
		{
			Session session=sessionFactory.openSession();
			Query query=session.createQuery("from Forum where loginname=:username");
			query.setParameter("username", username);
			List<Forum> listForums=query.list();
			return listForums;
		}
		catch(Exception e)
		{
			System.out.println("Exception here\n"+ e);	
			return null;
        }
	}

	//@Override
	@Transactional
	public boolean approveForum(Forum forum) {
		try
		{   
			forum.setStatus("A");
			sessionFactory.getCurrentSession().update(forum);
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
	public boolean rejectForum(Forum forum) {
		try
		{   
			forum.setStatus("NA");
			sessionFactory.getCurrentSession().update(forum);
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
	public Forum getForum(int forumId) {
		try
		{
			Session session=sessionFactory.openSession();
			Forum forum=(Forum)session.get(Forum.class, forumId);
			return forum;
			
		}
		catch(Exception e)
		{
			System.out.println("Exception here\n"+ e);	
			return null;
		}
	}

	//@Override

	public List<Forum> listAllForums() {
		try
		{
			Session session=sessionFactory.openSession();
			Query query=session.createQuery("from Forum");
			List<Forum> listForums=query.list();
			return listForums;
		}
		catch(Exception e)
		{
			return null;
        }

	}

	//@Override
	@Transactional
	public boolean incrementLikes(Forum forum) {
		try
		{
			int likes=forum.getLikes();
			likes++;
			forum.setLikes(likes);
			sessionFactory.getCurrentSession().update(forum);
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
	public boolean addForumComment(ForumComment forumComment) {
		try
		{
			sessionFactory.getCurrentSession().save(forumComment);
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
	public boolean deleteForumComment(ForumComment forumComment) {
		try
		{
			sessionFactory.getCurrentSession().delete(forumComment);
			return true;
		}
		catch(Exception e)
		{
		return false;
		}
	}

	//@Override
	@Transactional
	public ForumComment getForumComment(int commentId) {
		try
		{
			Session session=sessionFactory.openSession();
			ForumComment forumComment=(ForumComment)session.get(ForumComment.class, commentId);
			return forumComment;	
		}
		catch(Exception e)
		{
			System.out.println("Exception here\n"+ e);	
			return null;
		}
	}

	//@Override
	public List<ForumComment> listForumComments(int forumid) {
		try
		{
			Session session=sessionFactory.openSession();
			Query query=session.createQuery("from ForumComment where forumid=:forumid");
			query.setParameter("forumid",new Integer(forumid));
			List<ForumComment> listForumComments=query.list();
			return listForumComments;
		}
		catch(Exception e)
		{
			System.out.println("Exception here\n"+ e);	
			return null;
        }
		

	}

}
