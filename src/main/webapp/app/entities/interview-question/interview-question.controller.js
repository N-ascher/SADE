(function() {
    'use strict';

    angular
        .module('sadeApp')
        .controller('InterviewQuestionController', InterviewQuestionController);

    InterviewQuestionController.$inject = ['$scope', '$state', 'InterviewQuestion'];

    function InterviewQuestionController ($scope, $state, InterviewQuestion) {
        var vm = this;
        
        vm.interviewQuestions = [];

        loadAll();

        function loadAll() {
            InterviewQuestion.query(function(result) {
                vm.interviewQuestions = result;
            });
        }
    }
})();
