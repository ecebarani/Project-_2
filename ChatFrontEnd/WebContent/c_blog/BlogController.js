myApp.controller("BlogController",function($scope,$rootScope,$location,$http)
{
	$scope.blog={'blogId':0,'blogName':'','blogContent':'','createDate':'','likes':'','status':'','userDetails':{'loginname':''}};
	
	$scope.blogdata;
	
	$scope.allBlogdata;
	
	$scope.addBlog=function()
	{
		console.log('Adding Blog Information');
		console.log($scope.blog);
		console.log('Loginname is: '+$rootScope.currentUser.loginname);
		$http.post('http://localhost:8077/ChatMiddleware/addBlog',$scope.blog)
		    .then(function(response)
		 {
			$location.path('/blog');
		});
	};
	
	$scope.incrementLike=function(blogId)
	{
		console.log('Iam in Incrementing Like function');
		$http.get('http://localhost:8077/ChatMiddleware/incrementLike/'+blogId)
		.then(function(response)
				{
			        console.log('incremented');
				});
	};
	
	function listBlog()
	{
		console.log('List Blog');
		$http.get('http://localhost:8077/ChatMiddleware/ShowAllApprovedBlogs')
		.then(function(response)
		{
			console.log(response.data);
			$scope.blogdata=response.data;
		});
	}
	
	function listAllBlogs()
	{
		console.log('List All Blog');
		$http.get('http://localhost:8077/ChatMiddleware/ShowAllBlogs')
		.then(function(response)
		{
		    console.log(response.data);
		    $scope.allBlogdata=response.data;
	    });
	}
	
	listBlog();
	listAllBlogs();
	

	
	$scope.approve=function(blogId)
	{
		console.log('Iam approving the blog');
		$http.get('http://localhost:8077/ChatMiddleware/approvedBlog/'+blogId)
		.then(function(response)
				{
			        console.log('Blog Approved');
				});
	}
	
	$scope.reject=function(blogId)
	{
		console.log('Iam rejecting the blog');
		$http.get('http://localhost:8077/ChatMiddleware/rejectBlog/'+blogId)
		.then(function(response)
				{
			        console.log('Blog Rejected');
				});
	}
	
	$scope.deleteBlog=function(blogId)
	{
		console.log('Iam Deleting the blog');
		$http.get('http://localhost:8077/ChatMiddleware/deleteBlog/'+blogId)
		.then(function(response)
				{
			        console.log('Blog Deleted');
				});
	}


	
});