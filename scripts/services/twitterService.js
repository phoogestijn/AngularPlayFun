// Generated by CoffeeScript 1.3.1
/*global define
*/

define(['services/services', 'services/messageService'], function(services) {
  'use strict';

  var tweets;
  tweets = {};
  return services.factory('twitterService', [
    '$resource', 'messageService', function($resource, messageService) {
      return {
        tweets: tweets,
        get: function(criteria) {
          messageService.publish('search', {
            source: 'Twitter',
            criteria: criteria.q
          });
          tweets = $resource('http://search.twitter.com/:action', {
            action: 'search.json',
            q: 'twitter',
            callback: 'JSON_CALLBACK'
          }, {
            query: {
              method: 'JSONP'
            }
          });
          return tweets.query(criteria, function(Resource, getResponseHeaders) {
            return console.log('success', Resource, getResponseHeaders());
          }, function(obj) {
            return console.log('error', obj.config, obj.headers(), obj.status);
          });
        }
      };
    }
  ]);
});
