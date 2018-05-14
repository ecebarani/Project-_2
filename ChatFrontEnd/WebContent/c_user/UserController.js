myApp.controller("UserController",function($scope,$rootScope,$http,$location,$cookieStore)
{
	
	$scope.User={"loginname":'',"address":'',"emailId":'',"mobileNo":'',"password":'',"role":'',"userName":''};
	
	$scope.login=function()
	{
		console.log('Entered into the Login function');
		$http.post('http://localhost:8077/ChatMiddleware/checkUser',$scope.User)
	
		.then(function(response)
				{
			        $scope.User=response.data;
			        $rootScope.currentUser=response.data;
			        $cookieStore.put('userDetails', response.data);
			        console.log($rootScope.currentUser);
			        $location.path("/UserHome");
				});
		
	}
	
	$scope.register=function()
	{
		console.log("Entered into the Register function");
		console.log("Entered into the Register function after role");		
		$http.post('http://localhost:8077/ChatMiddleware/registerUser',$scope.User)
		.then(function(response)
				{
			        console.log('Registeration completed');
			        console.log(response.statusText);
			        $location.path("/login");

				});
		
	}
	
	$rootScope.logout=function()
	{
		console.log('Enter into the logout function');
		delete $rootScope.currentUser;
		$cookieStore.remove('userDetails');
		$location.path("/logout");

	}
	
	
});