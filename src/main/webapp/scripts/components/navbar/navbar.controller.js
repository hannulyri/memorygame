'use strict';

angular.module('memorygameApp')
    .controller('NavbarController', function ($scope, $location, $state, Auth, Principal, GameManager, GameData) {
        $scope.isAuthenticated = Principal.isAuthenticated;
        $scope.isInRole = Principal.isInRole;
        $scope.$state = $state;
        $scope.GameData = GameData;

        $scope.newGame = function () {
            GameManager.newGame();
            $state.go('home');
        };        

        $scope.logout = function () {
            Auth.logout();
            $state.go('home');
        };
    });
