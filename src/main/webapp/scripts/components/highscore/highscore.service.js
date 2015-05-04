'use strict';

angular.module('memorygameApp')
    .factory('Highscore', function ($resource) {
        return $resource('api/highscore', {}, {
                'get': { method: 'GET', params: {}, isArray: true},            
                'post': {
                    method: 'POST',
                    transformResponse: function (data) {
                        data = angular.fromJson(data);
                        return data;
                    }
                }
            });
        });
