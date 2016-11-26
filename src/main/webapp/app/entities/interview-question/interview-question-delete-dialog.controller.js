(function() {
    'use strict';

    angular
        .module('sadeApp')
        .controller('InterviewQuestionDeleteController',InterviewQuestionDeleteController);

    InterviewQuestionDeleteController.$inject = ['$uibModalInstance', 'entity', 'InterviewQuestion'];

    function InterviewQuestionDeleteController($uibModalInstance, entity, InterviewQuestion) {
        var vm = this;

        vm.interviewQuestion = entity;
        vm.clear = clear;
        vm.confirmDelete = confirmDelete;
        
        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function confirmDelete (id) {
            InterviewQuestion.delete({id: id},
                function () {
                    $uibModalInstance.close(true);
                });
        }
    }
})();
