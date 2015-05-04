'use strict';

angular.module('memorygameApp')
    .factory('GameData', function GameData($q, Game) {
        var factory = {};

        factory.clearAll = function() {
            factory.gameInitialized = false;
            factory.cardBack = {};
            factory.firstPick = undefined;
            factory.secondPick = undefined;
            factory.guessCount = 0;
            factory.cardPairs = 0;
            factory.cards = {};
        }


        factory.initializeGame = function(data) {
            factory.gameInitialized = true;
            factory.cardBack = data.cardBack;
            factory.firstPick = data.firstPick === null ? undefined : data.firstPick;
            factory.secondPick = data.secondPick === null ? undefined : data.secondPick;
            factory.guessCount = data.guessCount;
            factory.cards = data.cards;

            var count = 0;
            for(var prop in factory.cards) {
                if (factory.cards[prop].guessed === false) {
                    count++;
                }
            }
            factory.cardPairs = count / 2;
        };

        return factory;
    });
