package com.niit.restController;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.niit.DAO.BlogDAO;
import com.niit.model.Blog;
import com.niit.model.UserDetails;

@RestController
public class BlogController 
{
	@Autowired
	BlogDAO blogDAO;
	
	@GetMapping("/ShowAllApprovedBlogs")
	public ResponseEntity<List<Blog>> ShowAllApprovedBlogs(HttpSession session)
	{
		
		List<Blog> listBlogs=blogDAO.listApprovedBlogs();
		if(listBlogs!=null)
		{
			return new ResponseEntity <List<Blog>>(listBlogs,HttpStatus.OK);
		}
		else
		{
			return new ResponseEntity<List<Blog>>(listBlogs,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}
	
	@GetMapping("/ShowAllBlogsOfUser/{loginname}")
	public ResponseEntity<List<Blog>> showAllBlogsOfUser(@PathVariable("loginname") String loginname, HttpSession session)
	{
//		String loginname=((UserDetails)session.getAttribute("userDetails")).getLoginname();
		List<Blog> listBlogs=blogDAO.listAllUserBlogs(loginname);
		if(listBlogs!=null)
		{
			return new ResponseEntity <List<Blog>>(listBlogs,HttpStatus.OK);
		}
		else
		{
			return new ResponseEntity<List<Blog>>(listBlogs,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}
	
	@GetMapping("/ShowAllBlogs")
	public ResponseEntity<List<Blog>> ShowAllBlogs()
	{
		List<Blog> listAllBlogs=blogDAO.listAllBlogs();
		if(listAllBlogs!=null)
		{
			return new ResponseEntity <List<Blog>>(listAllBlogs,HttpStatus.OK);
		}
		else
		{
			return new ResponseEntity<List<Blog>>(listAllBlogs,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}

	@PostMapping("/addBlog")
	public ResponseEntity<String> addBlog(@RequestBody Blog blog, HttpSession session)
	{
		String loginname=((UserDetails)session.getAttribute("userDetails")).getLoginname();
//		String loginname=blog.getUserDetails().getLoginname();
		System.out.println(loginname);
		blog.setCreateDate(new java.util.Date());
		blog.setLikes(0);
		blog.setStatus("NA");
		
		UserDetails userDetails=new UserDetails();
		userDetails.setLoginname(loginname);
		blog.setUserDetails(userDetails);
		
		System.out.println("Blog Name:"+blog.getBlogName());
		System.out.println("Blog content:"+blog.getBlogContent());
		System.out.println("Login name:"+blog.getUserDetails().getLoginname());
		
		if(blogDAO.addBlog(blog))
		{
			return new ResponseEntity<String>("Blog Added",HttpStatus.OK);
		}
		else
		{
			return new ResponseEntity<String>("Failure",HttpStatus.INTERNAL_SERVER_ERROR);
		}	
	}
	
	@GetMapping("/approvedBlog/{blogId}")
	public ResponseEntity<String> approvedBlog(@PathVariable("blogId") int blogId)
	{
		Blog blog=(Blog)blogDAO.getBlog(blogId);
		
		if(blogDAO.approveBlog(blog))
		{
			return new ResponseEntity<String>("Approved",HttpStatus.OK);
		}
		
		else
		{
			return new ResponseEntity<String>("Error occured",HttpStatus.NOT_FOUND);
		}
	}
	
	@GetMapping("/rejectBlog/{blogId}")
	public ResponseEntity<String> rejectBlog(@PathVariable("blogId") int blogId)
	{
		Blog blog=(Blog)blogDAO.getBlog(blogId);
		
		if(blogDAO.rejectBlog(blog))
		{
			return new ResponseEntity<String>("Reject",HttpStatus.OK);
		}
		
		else
		{
			return new ResponseEntity<String>("Error occured",HttpStatus.NOT_FOUND);
		}
	}
	
	@GetMapping("/deleteBlog/{blogId}")
	public ResponseEntity<String> deleteBlog(@PathVariable("blogId") int blogId)
	{
		Blog blog=(Blog)blogDAO.getBlog(blogId);
		
		if(blogDAO.deleteBlog(blog))
		{
			return new ResponseEntity<String>("Delete",HttpStatus.OK);
		}
		
		else
		{
			return new ResponseEntity<String>("Error occured",HttpStatus.NOT_FOUND);
		}
	}
	
	@GetMapping("/incrementLike/{blogId}")
	public ResponseEntity<String> incrementLike(@PathVariable("blogId") int blogId)
	{
		Blog blog=(Blog)blogDAO.getBlog(blogId);
		
		if(blogDAO.incrementLikes(blog))
		{
			return new ResponseEntity<String>("Incremented",HttpStatus.OK);
		}
		
		else
		{
			return new ResponseEntity<String>("Error occured",HttpStatus.NOT_FOUND);
		}
	}
	
	@GetMapping("getABlog/{blogId}")
	public ResponseEntity<Blog> getABlog(@PathVariable("blogId") int blogId)
	{
		Blog blog=(Blog)blogDAO.getBlog(blogId);
		
		if(blog!=null)
		{
			return new ResponseEntity<Blog>(blog,HttpStatus.OK);
		}
		
		else
		{
			return new ResponseEntity<Blog>(blog,HttpStatus.NOT_FOUND);
		}
	}
	

}
