define 'angular', ['webjars!angular-locale_en-us.js'], ->
	angular

require	
	shim:
		'controllers/gitHubController'       : 
			deps: ['app', 'services/gitHubService']
		'controllers/personController'       : 
			deps: ['app', 'services/personService'],
		'controllers/personDetailsController': 
			deps: ['app', 'services/personService'],
		'controllers/searchHistoryController': 
			deps: ['app', 'services/messageService'],
		'directives/ngController'            : 
			deps: ['app'],
		'directives/tab'                     : 
			deps: ['app'],
		'directives/tabs'                    : 
			deps: ['app', 'directives/tab'],
		'filters/twitterfy'                  : 
			deps: ['app'],
		'libs/angular-resource'              : 
			deps: ['angular'],
		'responseInterceptors/dispatcher'    : 
			deps: ['app'],
		'services/gitHubService'             : 
			deps: ['app', 'services/messageService'],
		'services/messageService'            : 
			deps: ['app'],
		'services/personService'             : 
			deps: ['app'],
		'app'                                : 
			deps: ['angular', 'webjars!angular-resource.js'],
		'bootstrap'                          : 
			deps: ['app'],
		'routes'                             : 
			deps: ['app'],
		'run'                                : 
			deps: ['app'],
		'views'                              : 
			deps: ['app']
	[
		'angular'
		'app'
		'webjars!angular-resource.js'
		'controllers/gitHubController'
		'controllers/personController'
		'controllers/personDetailsController'
		'controllers/searchHistoryController'
		'directives/ngController'
		'directives/tabs'
		'filters/twitterfy'
		'responseInterceptors/dispatcher'
		'run'
		'views'
	], (angular) ->

		angular.element(document).ready ->
			angular.bootstrap document, ['app']			
