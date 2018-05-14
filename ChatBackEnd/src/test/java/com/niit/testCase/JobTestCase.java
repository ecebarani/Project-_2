package com.niit.testCase;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.niit.DAO.ForumDAO;
import com.niit.DAO.JobDAO;
import com.niit.model.Blog;
import com.niit.model.Forum;
import com.niit.model.Job;

public class JobTestCase {
static JobDAO jobDAO;
	
    @BeforeClass
    public static void initialize()
    {
    	AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
    	context.scan("com.niit");
    	context.refresh();
      	jobDAO=(JobDAO)context.getBean("jobDAO");
    	
    }
    @Ignore
    @Test
    public void addJobTest()
    {
    	Job job=new Job();
    	job.setCompany("Roadmap");
    	job.setJobDesc("Trainee");
    	job.setJobDesignation("Java Developer");
    	job.setLocation("Chennai");
    	job.setLastDateApply(new java.util.Date());
    	job.setSalary("34000");
     	assertTrue("problem in job insertion", jobDAO.addJob(job));
    	
    }
    @Ignore
    @Test
    public void updateJobTest()
    {
		Job job=jobDAO.getJob(150);
		job.setJobDesignation("consultant");
		job.setLocation("Chennai");
		job.setSalary("54000");
		
		job.setJobDesc("Information Technology");
		assertTrue("Problem in Updation",jobDAO.updateJob(job));

    }
    @Ignore
    @Test
    public void deleteJobTest()
    {
    	Job job=jobDAO.getJob(50);
    	assertTrue("Problem in deleting job",jobDAO.deleteJob(job));
    }
    
    @Test
	public void listJobsTest()
	{
		List<Job> listJobs=jobDAO.listJobs();
		assertNotNull("No Jobs",listJobs.size()>0);
		System.out.println("JobDesignation \t\t JobLocation \t\t JobSalary \t\t LastDate \t\t\t  JobDesc \t Comapany");
		for(Job job:listJobs)
		{
			System.out.print(job.getJobDesignation()+"\t\t");
			System.out.print(job.getLocation()+"\t\t\t\t");
			System.out.print(job.getSalary()+"\t\t");
			System.out.print(job.getLastDateApply()+"\t\t");
			System.out.print(job.getJobDesc()+"\t\t");
			System.out.println(job.getCompany());
		}
	}


}
