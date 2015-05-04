describe("E2E: Highscore Controller", function() {
  var constants = require('../test-constants.js');

  it('checking if page opens', function() {
    browser.get('#/highscore');
    expect(browser.getLocationAbsUrl()).toBe('/highscore');
    expect(element(by.css('.page-title')).getText()).toBe('Highscore TOP10');
  });


});
