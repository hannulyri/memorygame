'use strict';

angular.module('memorygameApp')
    .service('GameManager', function GameManager($q, $timeout, $cookieStore, Game, GameData, Principal) {
    var self = this;

    this.initializeGame = function() {        

        var gameFromCookie = $cookieStore.get('currentGame');
        if (gameFromCookie !== undefined) {
            GameData.initializeGame(gameFromCookie);
        } else {
            Game.post().$promise
                .then(function (data) {
                    GameData.initializeGame(data);                 
                });                
        }        
             
        this.gameEnd = false;   
        this.queryHighscore = false;           
        
    }

    this.newGame = function() {
        $cookieStore.remove('currentGame');
        GameData.clearAll();                 
        this.initializeGame();    
    }    

    this.closeOverlay = function() {
        this.gameEnd = false;
    }      
    this.flip = function(cardKey) {
        GameData.cards[cardKey].flipped = !GameData.cards[cardKey].flipped;
    }
    this.flipGuessed = function(cardKey) {
        GameData.cards[cardKey].guessed = !GameData.cards[cardKey].guessed;
    }    

    this.okToFlip = function(cardKey) {
        if (GameData.cards[cardKey].flipped === false) {
            return true;
        }
        return false;
    }    

    this.flipCard = function(clickedCard) {

        var updateCount = false;

        if (!this.okToFlip(clickedCard)) {
            return;    
        }

        this.flip(clickedCard);

        if (GameData.firstPick !== undefined && GameData.secondPick !== undefined) {            
            this.flip(GameData.firstPick);
            this.flip(GameData.secondPick);
            GameData.firstPick = GameData.secondPick = undefined;
            GameData.guessCount++;
            updateCount = true;
        }        

        if (GameData.firstPick === undefined) {
            GameData.firstPick = clickedCard;        
        } else if (GameData.secondPick === undefined) {
            GameData.secondPick = clickedCard;      

            if (GameData.firstPick !== undefined && GameData.secondPick !== undefined && GameData.cards[GameData.firstPick].id === GameData.cards[GameData.secondPick].id) { 
                this.flipGuessed(GameData.firstPick);
                this.flipGuessed(GameData.secondPick);                
                GameData.firstPick = GameData.secondPick = undefined; 
                GameData.guessCount++;
                GameData.cardPairs--;                
                updateCount = true;

                if (GameData.cardPairs == 0) {
                    $timeout(function () {
                        if (Principal.isAuthenticated()) {
                            self.gameEnd = true    
                            self.queryHighscore = false;        
                        } else {
                            self.gameEnd = true    
                            self.queryHighscore = true;
                        }
                        
                    }, 1000);
                }
            }

        }

        $cookieStore.put('currentGame', {
            cards: GameData.cards,
            cardBack: GameData.cardBack,
            guessCount: GameData.guessCount,
            firstPick: GameData.firstPick,
            secondPick: GameData.secondPick,
            cardPairs: GameData.cardPairs
        });

     




    };  

    });
