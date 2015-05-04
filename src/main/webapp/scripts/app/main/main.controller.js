'use strict';

angular.module('memorygameApp')
    .controller('MainController', function ($state, GameManager, GameData, HighscoreManager, Principal) {
            var self = this;            
            this.nameForHighscore;
            this.imageUrl = "/assets/images/cards";

            this.GameManager = GameManager;                
            this.GameData = GameData; 

            this.initializeGame = function() {
                self.GameManager.initializeGame();
            }            

            this.flipCard = function(card) {
                this.GameManager.flipCard(card);
            }

            this.newGame = function() {
                $cookieStore.remove('currentGame');
                this.initializeGame();    
            }

            this.closeOverlay = function() {
                this.GameManager.closeOverlay();
            } 

            this.addHighscore = function(formInstance) {

                Principal.identity().then(function(account) {
                    self.nameForHighscore = (Principal.isAuthenticated()) ? account.firstName : (self.highscoreObj !== undefined) ? self.highscoreObj.name : '';
                }).then(function () {
                    HighscoreManager.addHighscore({
                        name: self.nameForHighscore,
                        guesses: self.GameData.guessCount,
                    }).then(function () {
                        GameManager.closeOverlay();                    
                        $state.go('highscore');
                    }).catch(function (data) {
                        console.log(data);
                        self.serverResponse = data.data;
                    });
                });   
            }          


            if (!GameData.gameInitialized) {
                this.initializeGame();
            }

    });
