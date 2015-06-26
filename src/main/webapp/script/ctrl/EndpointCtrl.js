angular.module('dispatch').controller('EndpointCtrl', ['$scope', '$log', '$modal', 'Endpoint', function ($scope, $log, $modal, Endpoint) {
  
  $log.info('Before service call...');
  //$scope.endpoints = Endpoint.getAllEndpoints();
  $log.info('service url = ' + Endpoint);
  $scope.endpoints = Endpoint.query();  
  $log.info('After service call - endpoints.length :', $scope.endpoints.length );
  
  $scope.new_endpoint = {'id': null, 'application': '', 'url': ''};        
  var endpoint = new Endpoint();
  //newCard.name = "Mike Smith";
  //newCard.$save();
  
  $scope.create = function () {
    var modalInstance = $modal.open({
      templateUrl: 'views/endpoint/endpoint-edit-modal.html',
      controller: 'endpointEditModalController',
      resolve: {
        endpoint: function () {
           return endpoint;
        }
      }
    });
                   
    modalInstance.result.then(
      function (endpoint) {
        endpoint.$save;
        $scope.endpoints.push(endpoint);
        //reset();
        //feed();
      },
      function (log) {}
    );
  };
}])
        
    .controller('endpointEditModalController', [
        '$rootScope',
        '$scope',
        '$filter',
        '$log',
        '$modalInstance',
        'endpoint',
        function ($rootScope, $scope, $filter, $log, $modalInstance, endpoint) {
            $scope.endpoint = endpoint;
            $scope['new'] = endpoint.id === null;          
            $scope.endpoint.application = null;
            $scope.endpoint.url = null;
            
            $scope.cancel = function () {
                $modalInstance.dismiss('cancel');
            };
            
            $scope.save = function () {
                $modalInstance.close(endpoint);
                                
                $scope.endpoint.$save(
                    function (endpoint, postResponseHeaders) {
                        $modalInstance.close(endpoint);
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