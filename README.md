# MemoryGame

Simple Java Spring-Boot AngularJS Single-page application built on [JHipster Yeoman generator.](https://jhipster.github.io/)

Demo page can be found here: [memorygame-hannulyri.rhcloud.com](http://memorygame-hannulyri.rhcloud.com)

### Installation

```shell
# Clone your GitHub repository
git clone https://github.com/hannulyri/memorygame.git

# Go to the memorygame directory
cd memorygame

# Install npm packages
npm install
```

Create MySQL-database called `memorygame` and check the database configuration:
* `/src/main/resources/config/application-dev.yml` (developer-profile)
* `/src/main/resources/config/application-prod.yml` (production-profile)

```shell
# Run application with maven in developer mode
mvn spring-boot:run
```

You can run the application in production mode with `mvn -P prod spring-boot:run`

### Testing

Project has java Springframework tests, javascript Karma tests and Protractor javavscript E2E-tests.

#### Karma
```shell
# karma-tests are run in the basedirectory of the project
grunt test
```

#### Protractor E2E-tests
```shell
# update webdriver-manager
node node_modules\protractor\bin\webdriver-manager update --standalone
```

**Then run the tests:**  
1. Open a console and run the project `mvn spring-boot:run`
2. Open another console and start webdriver-manager `node node_modules\protractor\bin\webdriver-manager start`
3. Open a third console and run the tests `protractor src\test\javascript\protractor\protractor.conf.js`

