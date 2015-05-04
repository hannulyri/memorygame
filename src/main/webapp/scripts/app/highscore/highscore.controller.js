'use strict';

angular.module('memorygameApp')
    .controller('HighscoreController', function ($scope, GameManager, GameData, HighscoreManager, highscoreData) {
        $scope.highscoreList = highscoreData;
    });
