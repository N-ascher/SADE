(function() {
    'use strict';

    angular
        .module('sadeApp')
        .controller('InterviewQuestionDetailController', InterviewQuestionDetailController);

    InterviewQuestionDetailController.$inject = ['$scope', '$rootScope', '$stateParams', 'previousState', 'entity', 'InterviewQuestion', 'PreDefinedQuestion'];

    function InterviewQuestionDetailController($scope, $rootScope, $stateParams, previousState, entity, InterviewQuestion, PreDefinedQuestion) {
        var vm = this;

        vm.interviewQuestion = entity;
        vm.previousState = previousState.name;

        var unsubscribe = $rootScope.$on('sadeApp:interviewQuestionUpdate', function(event, result) {
            vm.interviewQuestion = result;
        });
        $scope.$on('$destroy', unsubscribe);
    }
})();
