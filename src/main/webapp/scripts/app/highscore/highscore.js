'use strict';

angular.module('memorygameApp')
    .config(function ($stateProvider) {
        $stateProvider
            .state('highscore', {
                parent: 'site',
                url: '/highscore',
                data: {
                    roles: [],
                    pageTitle: 'highscore.title'
                },
                views: {
                    'content@': {
                        templateUrl: 'scripts/app/highscore/highscore.html',
                        controller: 'HighscoreController'
                    }
                },
                resolve: {
                    highscoreData: ['HighscoreManager',
                        function (HighscoreManager) {
                            return HighscoreManager.getHighscore();
                        }
                    ],                    
                    mainTranslatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate,$translatePartialLoader) {
                        $translatePartialLoader.addPart('highscore');
                        return $translate.refresh();
                    }]
                }
            });
    });