(function() {
    'use strict';

    angular
        .module('sadeApp')
        .controller('InterviewQuestionDialogController', InterviewQuestionDialogController);

    InterviewQuestionDialogController.$inject = ['$timeout', '$scope', '$stateParams', '$uibModalInstance', 'entity', 'InterviewQuestion', 'PreDefinedQuestion'];

    function InterviewQuestionDialogController ($timeout, $scope, $stateParams, $uibModalInstance, entity, InterviewQuestion, PreDefinedQuestion) {
        var vm = this;

        vm.interviewQuestion = entity;
        vm.clear = clear;
        vm.save = save;
        vm.predefinedquestions = PreDefinedQuestion.query();

        $timeout(function (){
            angular.element('.form-group:eq(1)>input').focus();
        });

        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function save () {
            vm.isSaving = true;
            if (vm.interviewQuestion.id !== null) {
                InterviewQuestion.update(vm.interviewQuestion, onSaveSuccess, onSaveError);
            } else {
                InterviewQuestion.save(vm.interviewQuestion, onSaveSuccess, onSaveError);
            }
        }

        function onSaveSuccess (result) {
            $scope.$emit('sadeApp:interviewQuestionUpdate', result);
            $uibModalInstance.close(result);
            vm.isSaving = false;
        }

        function onSaveError () {
            vm.isSaving = false;
        }


    }
})();
