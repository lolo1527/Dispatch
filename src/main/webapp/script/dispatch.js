'use strict';

// Declare app level module which depends on views, and components
angular.module('dispatch', ['ngRoute','ngResource','dispatch.version','ui.bootstrap'])

    .provider('App', function () {
        var App = function () {
            var self = this;
            
            self.version = '0.1.0';
            
            self.urls = {
                'rest': {
                    'mock': "mocks",
                    'local': window.location.origin + "/service",
                    'xdomain': ""
                }
            };
            
            self.rest = 'local';
            //$log.info('service url = ' + self.urls.rest[self.rest]);
            self.getResourceUrl = function (service) {
                return self.urls.rest[self.rest]
                    + '/' + service
                    + (self.rest == 'mock' ? '.json' : '')
                    + (service.indexOf('?') > -1 ? '&' : '?') + 'v=' + self.version;
            };
        };
        
        this.$get = [
            '$log',
            function ($log) {
                return new App();
        }];
    })

    .config([
        '$routeProvider',
        '$resourceProvider',
        '$logProvider',
        'AppProvider',
        function ($routeProvider, $resourceProvider, $logProvider, AppProvider) {
            $routeProvider
                .when('/', {
                    templateUrl: 'views/index.html',
                    controller: 'indexController'
                })
                .when('/endpoint', {
                    templateUrl: 'views/endpoint/endpoint-list.html',
                    controller: 'EndpointCtrl'
                })
                .when('/route', {
                    templateUrl: 'views/route/route-list.html',
                    controller: 'RouteCtrl'
                })
                .otherwise({
                    redirectTo: '/'
                });

            $resourceProvider.defaults.stripTrailingSlashes = true;

            $logProvider.debugEnabled(true);
            
        }
    ]);
  
