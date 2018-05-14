package com.niit.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@SequenceGenerator(name="picidseq", sequenceName="mypic_seq")

public class ProfilePicture 
{
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE,generator="picidseq")
	@Column(name="picId")
    int picId;
	@OneToOne
	@JoinColumn(name="loginname")
    UserDetails userDetails;
	
	@Lob
    private byte[] image;
	
	
	
	
	public int getPicId() {
		return picId;
	}
	public void setPicId(int picId) {
		this.picId = picId;
	}
	public UserDetails getUserDetails() {
		return userDetails;
	}
	public void setUserDetails(UserDetails userDetails) {
		this.userDetails = userDetails;
	}
	public byte[] getImage() 
	{
		return image;
	}
	public void setImage(byte[] image) 
	{
		this.image = image;
	}
	
}
