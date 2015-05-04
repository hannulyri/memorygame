'use strict';

angular.module('memorygameApp')
    .controller('LogoutController', function (Auth) {
        Auth.logout();
    });
