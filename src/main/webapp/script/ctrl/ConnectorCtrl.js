angular.module('dispatch').controller('ConnectorCtrl', ['$scope', '$log', '$modal', 'EndpointService', 'ConnectorService', function ($scope, $log, $modal, EndpointService, ConnectorService) {
  
  $scope.connectors = ConnectorService.query();  
  
  var connector = new ConnectorService();
  $log.info('service url = ' + ConnectorService.$url);
  
  $scope.create = function () {
    var modalInstance = $modal.open({
      templateUrl: 'views/connector/connector-edit-modal.html',
      controller: 'connectorEditModalController',
      resolve: {
    	  connector: function () {
           return connector;
        }
      }
    });
                   
    modalInstance.result.then(
      function (connector) {
    	connector.$save;
        $scope.connectors.push(connector);
        //reset();
        //feed();
      },
      function (log) {}
    );
  };

  $scope.prepareMessage = function (c) {
	    var modalInstance = $modal.open({
	      templateUrl: 'views/connector/connector-post-modal.html',
	      controller: 'connectorPostModalController',
	      resolve: {
	    	  connector: function () {
	           return c;
	        }
	      }
	    });
	                   
	    modalInstance.result.then(
	      function (connector) {
	    	//connector.$save;
	        //$scope.connectors.push(connector);
	        //reset();
	        //feed();
	      },
	      function (log) {}
	    );
	  };

}])
        
    .controller('connectorEditModalController', [
        '$rootScope',
        '$scope',
        '$filter',
        '$log',
        '$modalInstance',
        'EndpointService',
        'connector',
        function ($rootScope, $scope, $filter, $log, $modalInstance, EndpointService, connector) {
       	  	$scope.endpoints = EndpointService.query();  
       	  	$log.info('After service call...$scope.endpoints.size = ' + $scope.endpoints.length);
            $scope.connector = connector;
            $scope['new'] = connector.id === null;          
            $scope.connector.endPoint = "";
            $scope.connector.consumeQueue=""; 
            $scope.connector.produceQueue=""; 
            $scope.connector.status = "STARTED";
            
            $scope.setEndPoint = function () {
            	$log.info('Log...$scope.connector.endPoint = ' + $scope.connector.endPoint);
            	$scope.connector.consumeQueue="seda://" + $scope.connector.endPoint + "/consume";
                $scope.connector.produceQueue="seda://" + $scope.connector.endPoint + "/produce"; 
            };
            
            $scope.cancel = function () {
                $modalInstance.dismiss('cancel');
            };
            
            $scope.log = function () {
           	  	$log.info('Log...$scope.endpoints.size = ' + $scope.endpoints.length);
           	  	$log.info('Log...$scope.endpoints.0 = ' + $scope.endpoints[0].application);
            };

            $scope.save = function () {
                $modalInstance.close(connector);
                                
                $scope.connector.$save(
                    function (connector, postResponseHeaders) {
                        $modalInstance.close(connector);
                    },
                    function (error) {
                        $scope.notifs = Notifs.set(error);
                        $scope.loading = false;
                    }
                );
            };

        }
    ])
    
        .controller('connectorPostModalController', [
        '$rootScope',
        '$scope',
        '$filter',
        '$log',
        '$modalInstance',
        'PostService',
        'connector',
        function ($rootScope, $scope, $filter, $log, $modalInstance, PostService, connector) {
            $scope.connector = connector;
            $scope.post = new PostService();
            $scope.post.message='write your message here';
            $scope.post.queue=connector.produceQueue;
            
            $scope.cancel = function () {
                $modalInstance.dismiss('cancel');
            };
            
            $scope.log = function () {
           	  	$log.info('Log...$connector.endPoint = ' + $scope.connector.endPoint);
           	  	$log.info('Log...$scope.message = ' + $scope.post.message);
            };

            $scope.postMessage = function () {
                $modalInstance.close(connector);
            	$scope.post.$save();
                /*$scope.postconnector.$save(
                    function (connector, postResponseHeaders) {
                        $modalInstance.close(connector);
                    },
                    function (error) {
                        $scope.notifs = Notifs.set(error);
                        $scope.loading = false;
                    }
                );*/
            };

        }
    ])
    ;