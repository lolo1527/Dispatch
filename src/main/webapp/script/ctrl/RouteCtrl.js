angular.module('dispatch').controller('RouteCtrl', ['$scope', '$log', '$modal', 'EndpointService', 'RouteService', function ($scope, $log, $modal, EndpointService, RouteService) {
  
  $log.info('service url = ' + RouteService);
  $scope.routes = RouteService.query();  
  $log.info('After service call - routes.length :', $scope.routes.length );
  
  var route = new RouteService();
  
  $scope.create = function () {
    var modalInstance = $modal.open({
      templateUrl: 'views/route/route-edit-modal.html',
      controller: 'routeEditModalController',
      resolve: {
        route: function () {
           return route;
        }
      }
    });

    modalInstance.result.then(
      function (route) {
        route.$save;
        $scope.routes.push(route);
      },
      function (log) {}
    );
  };
  
  $scope.startRoute = function (r) {
	  $log.info('starting route : ' + r.id);
	  var route = new RouteService();
	  route.$get({id:r.id, action:'start'});
	  r.status='STARTED';
  };

  $scope.pauseRoute = function (r) {
	  $log.info('pausing route : ' + r.id);
	  var route = new RouteService();
	  route.$get({id:r.id, action:'pause'});
	  r.status='PAUSED';
  };

  $scope.stopRoute = function (r) {
	  $log.info('stopping route :' + r.id);
	  var route = new RouteService();
	  route.$get({id:r.id, action:'stop'});
	  r.status='STOPPED';
  };

}])
        
    .controller('routeEditModalController', [
        '$rootScope',
        '$scope',
        '$filter',
        '$log',
        '$modalInstance',
        'EndpointService',
        'route',
        function ($rootScope, $scope, $filter, $log, $modalInstance, EndpointService, route) {
       	  	$scope.endpoints = EndpointService.query();  
       	    /*$scope.endpoints = [{"id": 1,"application": "Mosaic","url":"seda://mosaic"},
       	                        {"id": 2,"application": "OpenLayer","url":"seda://openlayer"},
       	                        {"id": 3,"application": "JCDStream","url":"seda://jcdstream"}];*/
       	  	$log.info('After service call...$scope.endpoints.size = ' + $scope.endpoints.length);
            $scope.route = route;
            $scope['new'] = route.id === null;          
            $scope.route.source = $scope.endpoints[0];
            $scope.route.destination = $scope.endpoints[0];
            $scope.route.status = 'STOPPED';
            
            /*$scope.setSource = function() {
                $scope.route.source = $scope.timezone.region + '/' + $scope.timezone.city[$scope.timezone.region];
            };*/
            
            $scope.cancel = function () {
                $modalInstance.dismiss('cancel');
            };
            
            $scope.log = function () {
           	  	$log.info('Log...$scope.endpoints.size = ' + $scope.endpoints.length);
           	  	$log.info('Log...$scope.endpoints.0 = ' + $scope.endpoints[0].application);
            };

            $scope.save = function () {
                $modalInstance.close(route);
                                
                $scope.route.$save(
                    function (route, postResponseHeaders) {
                        $modalInstance.close(route);
                    },
                    function (error) {
                        $scope.notifs = Notifs.set(error);
                        $scope.loading = false;
                    }
                );
            };

        }
    ])
    ;