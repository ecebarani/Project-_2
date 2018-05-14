package com.niit.testCase;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.niit.DAO.BlogDAO;
import com.niit.model.Blog;
import com.niit.model.BlogComment;
import com.niit.model.UserDetails;

public class BlogTestCase {
	static BlogDAO blogDAO;
	
    @BeforeClass
    public static void initialize()
    {
    	AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
    	context.scan("com.niit");
    	context.refresh();
    	
    	blogDAO=(BlogDAO)context.getBean("blogDAO");
    	
    }
    @Ignore
    @Test
    public void addBlogTest()
    {
    	Blog blog=new Blog();
    	
    	UserDetails userDetail = new UserDetails();
    	
    	blog.setBlogName("HTML");
    	blog.setBlogContent("Blog specific to HTML");
    	blog.setLikes(1);
    	blog.setStatus("A");
    	
		userDetail.setLoginname("user");
		blog.setUserDetails(userDetail);
		
    	blog.setCreateDate(new java.util.Date());
    	
    	assertTrue("problem in blog insertion", blogDAO.addBlog(blog));
    	
    }
    @Ignore
    @Test
    public void deleteBlogTest()
    {
    	Blog blog=blogDAO.getBlog(0);
    	assertTrue("Problem in blog deletion",blogDAO.deleteBlog(blog));
    }
    
    
    @Ignore
    @Test
    public void rejectBlogTest()
    {
    	Blog blog=blogDAO.getBlog(50);
    	assertTrue("Problem in blog rejection",blogDAO.rejectBlog(blog));
    }
    
    
    @Ignore
    @Test
    public void approvalBlogTest()
    {
    	Blog blog=blogDAO.getBlog(50);
    	assertTrue("Problem in blog approval",blogDAO.approveBlog(blog));
    }
    
   
    @Test
    public void listBlogsByUserTest()
    {

    	List<Blog> blogListForUser =  blogDAO.listAllUserBlogs("barani");
		assertNotNull("Problem in retrieving the blog list using login name", blogListForUser);
		System.out.println("Loginname \t Blog ID \t Status \t Likes \t Blog Name");
		for(Blog blog: blogListForUser) {
			System.out.print((blog.getUserDetails().getLoginname())+"\t\t");
			System.out.print(blog.getBlogId()+"\t\t");
			System.out.print(blog.getStatus()+"\t\t");
			System.out.print(blog.getLikes()+"\t");
			System.out.println(blog.getBlogName());
			
		}
    	
    }
    
    @Ignore
    @Test
    public void listApprovedBlogs()
    {

    	List<Blog> approvedBlog =  blogDAO.listApprovedBlogs();
		assertNotNull("Problem in retrieving the blog list using login name", approvedBlog);
		System.out.println("Loginname \t Blog ID \t Status \t Likes \t Blog Name");
		for(Blog blog: approvedBlog) {
			System.out.print((blog.getUserDetails().getLoginname())+"\t\t");
			System.out.print(blog.getBlogId()+"\t\t");
			System.out.print(blog.getStatus()+"\t\t");
			System.out.print(blog.getLikes()+"\t");
			System.out.println(blog.getBlogName());
			
		}
    	
    }

    @Ignore
    @Test
    public void incrementBlogLikeTest()
    {
    	Blog blog=blogDAO.getBlog(50);
    	assertTrue("Problem in incrementing likes",blogDAO.incrementLikes(blog));
    }
    @Ignore
    @Test
    public void addCommentTest()
    {
    	BlogComment comment=new BlogComment();
    	comment.setCommentText("This blog is very informative");
    	comment.setLoginname("user");
    	comment.setCommentDate(new java.util.Date());
    	assertTrue("Problem in insertion of Blog Comment ",blogDAO.addBlogComment(comment));
    }
    @Ignore
    @Test
    public void listAllBlogCommentTest()
    {
    	List<BlogComment> listBlogComments= blogDAO.listBlogComments(0);
    	assertTrue("Problem in retrieving all the BlogComments",listBlogComments.size()>0);
    	System.out.println("Blog ID \t Blog Comment \t Loginname \t");
    	for(BlogComment blogComment:listBlogComments)
    	{
    		System.out.println(blogComment.getBlogId()+"\t\t");
    		System.out.println(blogComment.getCommentText()+"\t\t");
    		System.out.println(blogComment.getLoginname()+"\t\t");
    	}
    }
    
}
