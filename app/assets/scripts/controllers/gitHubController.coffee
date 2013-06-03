angular.module('app').controller 'gitHubController', ['$log', '$scope', '$location', 'gitHubService', ($log, $scope, $location, gitHubService) ->
	$scope.search = (searchTerm) ->
		$location.path "/github/#{searchTerm}"

	$scope.onRouteChange = (routeParams) ->
		console.log('gitHubController received onRouteChange')
		$scope.searchTerm = routeParams.searchTerm

		gitHubService.get($scope.searchTerm).then (results) ->
			console.log('received search result from gitHubService: '+results)
			$scope.repos = results
]