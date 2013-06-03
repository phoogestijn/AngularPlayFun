angular.module('app').service 'messageService', ['$log', '$rootScope', ($log, $rootScope) ->
	self = @

	publish = (name, parameters) ->
		console.log('messageService.publish '+name)
		parameters.timeStamp = Date.now()

		$rootScope.$broadcast name, parameters

	subscribe = (name, listener) ->
		$rootScope.$on name, listener

	self.publish = publish
	self.subscribe = subscribe
]