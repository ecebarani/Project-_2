package com.niit.restController;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.niit.DAO.ForumDAO;
import com.niit.model.Blog;
import com.niit.model.Forum;

@RestController
public class ForumController {
	@Autowired
	ForumDAO forumDAO;
	
	@GetMapping("/ShowAllForum")
	public ResponseEntity<List<Forum>> ShowAllApprovedForums()
	{
		List<Forum> listForums=forumDAO.listForums("Deepi");
		if(listForums!=null)
		{
			return new ResponseEntity <List<Forum>>(listForums,HttpStatus.OK);
		}
		else
		{
			return new ResponseEntity<List<Forum>>(listForums,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}
	
	@PostMapping("/addForum")
	public ResponseEntity<String> addForum(@RequestBody Forum forum)
	{
		forum.setCreateDate(new java.util.Date());
		forum.setLikes(0);
		forum.setLoginname("Deepi");
		forum.setStatus("NA");
		
		System.out.println("Forum Name:"+forum.getForumName());
		System.out.println("Forum content:"+forum.getForumContent());
		
		if(forumDAO.addForum(forum))
		{
			return new ResponseEntity<String>("Forum Added",HttpStatus.OK);
		}
		else
		{
			return new ResponseEntity<String>("Failure",HttpStatus.INTERNAL_SERVER_ERROR);
		}	
	}
	
	@GetMapping("/approvedForum/{forumId}")
	public ResponseEntity<String> approvedForum(@PathVariable("forumId") int forumId)
	{
		Forum forum=(Forum)forumDAO.getForum(forumId);
		
		if(forumDAO.approveForum(forum))
		{
			return new ResponseEntity<String>("Approved",HttpStatus.OK);
		}
		
		else
		{
			return new ResponseEntity<String>("Error occured",HttpStatus.NOT_FOUND);
		}
	}
	
	@GetMapping("/rejectForum/{forumId}")
	public ResponseEntity<String> rejectForum(@PathVariable("forumId") int forumId)
	{
		Forum forum=(Forum)forumDAO.getForum(forumId);
		
		if(forumDAO.rejectForum(forum))
		{
			return new ResponseEntity<String>("Reject",HttpStatus.OK);
		}
		
		else
		{
			return new ResponseEntity<String>("Error occured",HttpStatus.NOT_FOUND);
		}
	}
	
	@GetMapping("/deleteForum/{forumId}")
	public ResponseEntity<String> deleteForum(@PathVariable("forumId") int forumId)
	{
		Forum forum=(Forum)forumDAO.getForum(forumId);
		
		if(forumDAO.deleteForum(forum))
		{
			return new ResponseEntity<String>("Delete",HttpStatus.OK);
		}
		
		else
		{
			return new ResponseEntity<String>("Error occured",HttpStatus.NOT_FOUND);
		}
	}
	
	@GetMapping("getAForum/{forumId}")
	public ResponseEntity<Forum> getAForum(@PathVariable("forumId") int forumId)
	{
		Forum forum=(Forum)forumDAO.getForum(forumId);
		
		if(forum!=null)
		{
			return new ResponseEntity<Forum>(forum,HttpStatus.OK);
		}
		
		else
		{
			return new ResponseEntity<Forum>(forum,HttpStatus.NOT_FOUND);
		}
	}
	
	

}
