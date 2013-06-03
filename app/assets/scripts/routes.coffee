angular.module('app').config ['$routeProvider', ($routeProvider) ->
	$routeProvider
	.when '/github/:searchTerm',
		controller: 'gitHubController'
		reloadOnSearch: true
		resolve:
			changeTab: ['$rootScope', ($rootScope) ->
				console.log('broadcast changeTab#github')
				$rootScope.$broadcast 'changeTab#gitHub'
			]
	.when '/people/:id',
		controller: 'personDetailsController'
		reloadOnSearch: true
		resolve:
			changeTab: ['$rootScope', ($rootScope) ->
				console.log('broadcast changeTab#people')
				$rootScope.$broadcast 'changeTab#people'
			]
	.otherwise
		redirectTo: '/github'
]
