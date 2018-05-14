myApp.controller("JobController",function($scope,$rootScope,$http,$location)
{
	$scope.job={jobId:0,jobDesignation:'',company:'',salary:0,location:'',jobDesc:'',lastDateApply:''};
	
	$scope.allJobData;
	
	$scope.publishJob=function()
	{
		console.log('Adding job information');
		console.log($scope.job);
		$http.post('http://localhost:8077/ChatMiddleware/addJob',$scope.job)
		.then(function(response)
				{
			        console.log(response.statusCode);    
				});
	};
	
	function listJobs()
	{
		$http.get('http://localhost:8077/ChatMiddleware/listJobs')
		.then(function(response)
				{
			        $scope.allJobData=response.data;
				});
	}
	
	$scope.deleteJob=function(jobId)
	{
		console.log('Iam Deleting the job');
		$http.get('http://localhost:8077/ChatMiddleware/deleteJob/'+jobId)
		.then(function(response)
				{
			        console.log('job Deleted');
				});
	}

	listJobs();
});