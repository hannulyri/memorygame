'use strict';

angular.module('memorygameApp')
    .factory('HighscoreManager', function GameManager(GameData, Principal, Highscore) {

     return {
            getHighscore: function () {
                return Highscore.get();
            },        
            addHighscore: function (highscoreData, callback) {
                var cb = callback || angular.noop;

                return Highscore.save(highscoreData,
                    function () {
                        return cb(highscoreData);
                    },
                    function (err) {
                        return cb(err);
                    }.bind(this)).$promise;
            }
        };

    /*
    var factory = {};


    factory.getHighscore = function(success, error) {
        return $http.get('/api/highscore').success(function(data){
                success(data);                     
        }).error(function(data){
                error(data);
        });
    };     

    factory.addHighscore = function(object, success, error) {
        return $http.post('/api/highscore', object).success(function(data){
                success(data);                     
        }).error(function(data){
                error(data);
        });
    }; 

    return factory;
    */ 

    });
