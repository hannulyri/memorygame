'use strict';

angular.module('memorygameApp')
    .factory('Game', function ($resource) {
        return $resource('api/game', {}, {
                'post': {
                    method: 'POST',
                    transformResponse: function (data) {
                        data = angular.fromJson(data);
                        return data;
                    }
                }
            });
        });
