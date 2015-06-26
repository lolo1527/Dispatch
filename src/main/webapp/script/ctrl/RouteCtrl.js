angular.module('dispatch').controller('RouteCtrl', ['$scope', '$log', '$modal', 'RouteService', function ($scope, $log, $modal, RouteService) {
  
  $log.info('Before service call...');
  //$scope.endpoints = Endpoint.getAllEndpoints();
  $log.info('service url = ' + RouteService);
  $scope.routes = RouteService.query();  
  $log.info('After service call - routes.length :', $scope.routes.length );
  
  $scope.new_endpoint = {'id': null, 'application': '', 'url': ''};        
  var route = new RouteService();
  //newCard.name = "Mike Smith";
  //newCard.$save();
  
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
        //reset();
        //feed();
      },
      function (log) {}
    );
  };
}])
        
    .controller('routeEditModalController', [
        '$rootScope',
        '$scope',
        '$filter',
        '$log',
        '$modalInstance',
        'route',
        function ($rootScope, $scope, $filter, $log, $modalInstance, route) {
            $scope.route = route;
            $scope['new'] = route.id === null;          
            $scope.route.source = null;
            $scope.route.destination = null;
            
            $scope.cancel = function () {
                $modalInstance.dismiss('cancel');
            };
            
            $scope.save = function () {
                $modalInstance.close(endpoint);
                                
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