package com.niit.DAO;

import java.util.List;

import com.niit.model.Friends;
import com.niit.model.UserDetails;

public interface FriendDAO {
		
	public boolean sendFriendRequest(Friends friends);
	public boolean acceptFriendRequest(int friendId);
	public boolean deleteFriendRequest(int friendId);
	
	public List<Friends> viewFriendRequests(String loginname);
	public List<UserDetails> viewSuggestedFriendRequests(String loginname);
	public List<Friends> viewPendingFriendRequests(String loginname);
	
	
}
