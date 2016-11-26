(function() {
    'use strict';

    angular
        .module('sadeApp')
        .controller('PreDefinedQuestionDetailController', PreDefinedQuestionDetailController);

    PreDefinedQuestionDetailController.$inject = ['$scope', '$rootScope', '$stateParams', 'previousState', 'entity', 'PreDefinedQuestion'];

    function PreDefinedQuestionDetailController($scope, $rootScope, $stateParams, previousState, entity, PreDefinedQuestion) {
        var vm = this;

        vm.preDefinedQuestion = entity;
        vm.previousState = previousState.name;

        var unsubscribe = $rootScope.$on('sadeApp:preDefinedQuestionUpdate', function(event, result) {
            vm.preDefinedQuestion = result;
        });
        $scope.$on('$destroy', unsubscribe);
    }
})();
