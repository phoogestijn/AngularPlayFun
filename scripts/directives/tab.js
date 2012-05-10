// Generated by CoffeeScript 1.3.1
/*global define
*/

define(['directives/directives', 'text!templates/tab.html', 'directives/tabs'], function(directives, template) {
  'use strict';
  return directives.directive('ngTab', [
    function() {
      return {
        require: '^ngTabs',
        restrict: 'E',
        transclude: true,
        scope: {
          caption: 'bind'
        },
        link: function(scope, element, attrs, controller) {
          return controller.addTab(scope);
        },
        template: template,
        replace: true
      };
    }
  ]);
});
