'use strict';

angular.module('dispatch')

    .factory('RouteService', ['$resource', function ($resource) {
        	var RouteService = $resource('/mytestapplication/service/route/:id', {}, {
        		query: {
        			method: 'GET', 
        			isArray: true,
        			transformResponse: function(data, headersGetter) {
                        try {
                            return JSON.parse(data);
                        }
                        catch (e) {
                            //$log.warn('catched:', e.message);
                            return {
                                'headers': headersGetter(),
                                'message': data,
                                'catched': e.message
                            };
                        }
                    }
        		},
                create: {
                    method: 'POST'
                },
                startRoute:{
        			method: 'GET',
        			id:'@id',
        			params:{action:"start"}
                	
                }
        	});
        	return RouteService;
                    
            /*return $resource(
                //App.getResourceUrl('endpoint'),
            	'/service/endpoint',	
                {},
                {
                    query: {
                        method: 'GET',
                        isArray: true,
                        transformResponse: function(data, headersGetter) {
                            try {
                                return JSON.parse(data);
                            }
                            catch (e) {
                                $log.warn('catched:', e.message);
                                return {
                                    'headers': headersGetter(),
                                    'message': data,
                                    'catched': e.message
                                };
                            }
                        }
                    },
                    create: {
                        method: 'POST'
                    }
                }
            );*/
            /*
            return {
              getAllEndpoints : function() {
                return [{"id": 1,"application": "Mosaic","url":"seda://mosaic"},
                {"id": 2,"application": "OpenLayer","url":"seda://openlayer"},
                {"id": 3,"application": "JCDStream","url":"seda://jcdstream"}];                
              },
              create : function(endpoint){
                return endpoint;
              } 
            };*/
        }
    ]);