'use strict';

angular.module('dispatch')

    .factory('PostService', ['$resource', function ($resource) {
        	var PostService = $resource('/mytestapplication/service/post',{},{
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
        		}
        	});
        	return PostService;
        }
    ]);