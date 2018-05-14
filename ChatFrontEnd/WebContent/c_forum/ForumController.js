myApp.controller("ForumController",function($scope,$rootScope,$location,$http)
{
	$scope.forum={'forumId':0,'forumName':'','forumContent':'','createDate':'','likes':'','loginname':'','status':''}
	
	$scope.forumdata;
	
	$scope.allforumdata;
	
	$scope.addForum=function()
	{
		console.log('Adding Forum Information');
		console.log($scope.forum);
		$http.post('http://localhost:8077/ChatMiddleware/addForum',$scope.forum)
		    .then(function(response)
		 {
			$location.path('/forum');
		});
	};

	function listForum()
	{
		console.log('List Forum');
		$http.get('http://localhost:8077/ChatMiddleware/ShowAllForum')
		.then(function(response)
		{
			console.log(response.data);
			$scope.blogdata=response.data;
		});
	}
	
	$scope.approve=function(forId)
	{
		console.log('Iam approving the forum');
		$http.get('http://localhost:8077/ChatMiddleware/approvedForum/'+forumId)
		.then(function(response)
				{
			        console.log('Forum Approved');
				});
	}
	
	$scope.reject=function(forumId)
	{
		console.log('Iam rejecting the forum');
		$http.get('http://localhost:8077/ChatMiddleware/rejectForum/'+forumId)
		.then(function(response)
				{
			        console.log('Forum Rejected');
				});
	}
	
	$scope.deleteForum=function(forumId)
	{
		console.log('Iam Deleting the forum');
		$http.get('http://localhost:8077/ChatMiddleware/deleteForum/'+forumId)
		.then(function(response)
				{
			        console.log('Forum Deleted');
				});
	}

	listForum();


});