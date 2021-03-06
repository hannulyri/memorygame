'use strict';

angular.module('memorygameApp')
    .controller('LanguageController', function ($scope, $translate, Language) {
        $scope.changeLanguage = function (languageKey) {
            $translate.use(languageKey);
            $scope.languagesCurrent = languageKey;
        };

        Language.getCurrent().then(function (languagesCurrent) {
            $scope.languagesCurrent = languagesCurrent;
        });
      
        Language.getAll().then(function (languages) {
            $scope.languages = languages;
        });
    });
