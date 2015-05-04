'use strict';

angular.module('memorygameApp')
    .factory('Register', function ($resource) {
        return $resource('api/register', {}, {
        });
    });


