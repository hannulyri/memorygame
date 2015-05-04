describe('E2E: registerLoginControllerSpec.js -', function() {
	var constants = require('../test-constants.js');

	var d = new Date();
	var currentTime = d.toLocaleTimeString("hhmmss").replace(':','').replace(':',''); 	

	describe('register -', function() {

		it('checking if register page opens', function() {
			browser.get('#/register');
			browser.waitForAngular();
			expect(browser.getLocationAbsUrl()).toBe('/register');		
			expect(element(by.buttonText('Register')).isEnabled()).toBe(false);
		});			

		it('login alraedy in use', function() {
			expect(element(by.buttonText('Register')).isEnabled()).toBe(false);

			element(by.model('registerAccount.login')).clear().sendKeys('user');
			element(by.model('registerAccount.email')).clear().sendKeys('test' + currentTime + '@gmail.com');
			element(by.model('registerAccount.password')).clear().sendKeys('omenarahka123');
			element(by.model('confirmPassword')).clear().sendKeys('omenarahka123');
			expect(element(by.buttonText('Register')).isEnabled()).toBe(true);

			element(by.buttonText('Register')).click();
			browser.waitForAngular();
			expect(element(by.css('[ng-show="errorUserExists"]')).isDisplayed()).toBe(true); 
		});		

		it('wrong email', function() {

			element(by.model('registerAccount.login')).clear().sendKeys('user' + currentTime);
			element(by.model('registerAccount.email')).clear().sendKeys('test@');
			element(by.model('registerAccount.password')).clear().sendKeys('omenarahka123');
			element(by.model('confirmPassword')).clear().sendKeys('omenarahka123');
			expect(element(by.buttonText('Register')).isEnabled()).toBe(false);
		});	

		it('different password', function() {

			element(by.model('registerAccount.login')).clear().sendKeys('user' + currentTime);
			element(by.model('registerAccount.email')).clear().sendKeys('test' + currentTime + '@gmail.com');
			element(by.model('registerAccount.password')).clear().sendKeys('bug');
			element(by.model('confirmPassword')).clear().sendKeys('omenarahka123');
			expect(element(by.buttonText('Register')).isEnabled()).toBe(false);
		});	

		it('short password', function() {

			element(by.model('registerAccount.login')).clear().sendKeys('user' + currentTime);
			element(by.model('registerAccount.email')).clear().sendKeys('test' + currentTime + '@gmail.com');
			element(by.model('registerAccount.password')).clear().sendKeys('bug');
			element(by.model('confirmPassword')).clear().sendKeys('bug');
			expect(element(by.buttonText('Register')).isEnabled()).toBe(false);
		});		

		it('registrate user account', function() {

			element(by.model('registerAccount.login')).clear().sendKeys('user' + currentTime);
			element(by.model('registerAccount.email')).clear().sendKeys('test' + currentTime + '@gmail.com');
			element(by.model('registerAccount.password')).clear().sendKeys('omenarahka123');
			element(by.model('confirmPassword')).clear().sendKeys('omenarahka123');
			expect(element(by.buttonText('Register')).isEnabled()).toBe(true);

			element(by.buttonText('Register')).click();
			browser.waitForAngular();
			expect(element(by.css('[ng-show="success"]')).isDisplayed()).toBe(true); 
		});						
	});

	describe('login -', function() {

		it('check if login page opens', function() {

			browser.get('#/login');
			browser.waitForAngular();
			expect(browser.getLocationAbsUrl()).toBe('/login');	

			expect(element(by.model('rememberMe')).isSelected()).toBe(true);
		});	

		it('empty login', function() {
			element(by.buttonText('Authenticate')).click();
			browser.waitForAngular();
			expect(element(by.css('[ng-show="authenticationError"]')).isDisplayed()).toBe(true); 
		});		

		it('wrong password', function() {
			element(by.model('username')).clear().sendKeys('user' + currentTime);
			element(by.model('password')).clear().sendKeys('bug');
		});		

		it('successful login', function() {
			element(by.model('username')).clear().sendKeys('user' + currentTime);
			element(by.model('password')).clear().sendKeys('omenarahka123');
			element(by.buttonText('Authenticate')).click();
			browser.waitForAngular();			
			expect(browser.getLocationAbsUrl()).toBe('/');	
			browser.get('#/settings');
			browser.waitForAngular();			
			expect(browser.getLocationAbsUrl()).toBe('/settings');	
		});	
	});

});
