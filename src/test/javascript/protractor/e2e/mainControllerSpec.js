describe('E2E: mainController.js -', function() {
	var constants = require('../test-constants.js');

	var self = this;

	var fs = require('fs');
	var path = require('path');	

	this.backCards = [];
	this.cardCount = 0;
	this.cardUrls = [];

	/*
	beforeEach(function () {
		browser.get('/');
		// Wait for the AngularJS app to load before testing DOM elements.
		browser.waitForAngular();
	});	
	*/

	describe('check not logged in -', function() {
		self.backCards = [];
		self.cardCount = 0;
		self.cardUrls = [];		

		it('fetch page', function() {	
			browser.get('/');
			browser.waitForAngular();
		});	


		it('checking if page opens', function() {
			expect(browser.getLocationAbsUrl()).toBe('/');
			backCards = element.all(by.css('.back-side-card'));
		});	

		it('fetch image urls', function() {
			self.cardUrls = backCards.map(function(elm, index) {
				self.cardCount++;
				return {
					key: elm.getAttribute('id'),
					value: elm.getAttribute('src')
				}			
			});	
		});

		it('more cards than zero', function() {
			expect(self.cardCount).toBeGreaterThan(0);
		});		

		it('check if an even number of cards', function() {
			expect(self.cardCount % 2).toBe(0);
		});		

		it('solve game', function() {
			var cardUrlsSorted = self.cardUrls.value_.sort(function (a, b) {
	    		return a.value.localeCompare( b.value );
			});
			for (var j = 0; j < cardUrlsSorted.length; j++){
				//element.all(by.repeater('(cardKey, card) in ctrl.GameData.cards').row(cardUrlsSorted[j].key)).get(0).element(by.css('.front-side-card')).click();

				var k = cardUrlsSorted[j].key;
				k.replace('bimage', 'fimage');
				element(by.id(k)).click();
			}

			browser.waitForAngular();

			expect(element(by.css('.overlay-title')).isDisplayed()).toBe(true); 

			//browser.pause();
		});	

		it('check if can input highscore without name', function() {
			var addButton = element(by.css('.btn-lg'));

			addButton.click();		

			browser.waitForAngular();

			expect(element(by.css('.error')).isDisplayed()).toBe(true); 
		});	


		it('input highscore not logged in', function() {
			var d = new Date();
			var currentTime = d.toLocaleTimeString("hhmmss").replace(':','').replace(':',''); 				

			var addField = element(by.model('ctrl.highscoreObj.name'));
			var addButton = element(by.css('.btn-lg'));

			addField.sendKeys(constants.TESTUSER + currentTime);
			addButton.click();		

			browser.waitForAngular();

			expect(browser.getLocationAbsUrl()).toBe('/highscore');
		});	
	});


	describe('check logged in -', function() {
		self.backCards = [];
		self.cardCount = 0;
		self.cardUrls = [];

		it('fetch page', function() {	
			browser.get('/');
			browser.waitForAngular();
			element(by.css('.newgame')).click();
		});	


		it('go to login page', function() {
			browser.get('#/login');
			expect(browser.getLocationAbsUrl()).toBe('/login');
		});

		it('login with user', function() {
			element(by.model('username')).sendKeys(constants.USERLOGIN);
			element(by.model('password')).sendKeys(constants.USERPASSWORD);
			element(by.css('.btn-primary')).click();

			browser.waitForAngular();
			browser.get('/');
			browser.waitForAngular();

			expect(browser.getLocationAbsUrl()).toBe('/');
			self.backCards = element.all(by.css('.back-side-card'));

		});	  

		it('fetch image urls', function() {
			self.cardUrls = self.backCards.map(function(elm, index) {
				self.cardCount++;
				return {
					key: elm.getAttribute('id'),
					value: elm.getAttribute('src')
				}			
			});	
		});		

		it('solve game', function() {
			var cardUrlsSorted = self.cardUrls.value_.sort(function (a, b) {
	    		return a.value.localeCompare( b.value );
			});

			for (var j = 0; j < cardUrlsSorted.length; j++){
				//element.all(by.repeater('(cardKey, card) in ctrl.GameData.cards').row(cardUrlsSorted[j].key)).get(0).element(by.css('.front-side-card')).click();

				var k = cardUrlsSorted[j].key;
				k.replace('bimage', 'fimage');
				element(by.id(k)).click();
			}

			browser.waitForAngular();


			expect(element(by.css('.overlay-title')).isDisplayed()).toBe(true); 

			//browser.pause();
		});	

		it('input highscore logged in', function() {
			browser.waitForAngular();
			element(by.buttonText('Add Highscore')).click();

			browser.waitForAngular();

			expect(browser.getLocationAbsUrl()).toBe('/highscore');
		});	
		
	});	

});
