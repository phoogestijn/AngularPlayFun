angular.module('app').controller 'personDetailsController', ['$log', '$scope', 'personService', ($log, $scope, personService) ->
	$scope.onRouteChange = (routeParams) ->
		console.log('personDetailsController received onRouteChange')
		id = routeParams.id

		personService.getPerson(id).then (results) ->
			$scope.person = results
]