(function() {
    'use strict';

    angular
        .module('sadeApp')
        .controller('InterviewDialogController', InterviewDialogController);

    InterviewDialogController.$inject = ['$timeout', '$scope', '$stateParams', '$uibModalInstance', '$q', 'entity', 'Interview', 'User', 'Comment', 'InterviewQuestion'];

    function InterviewDialogController ($timeout, $scope, $stateParams, $uibModalInstance, $q, entity, Interview, User, Comment, InterviewQuestion) {
        var vm = this;

        vm.interview = entity;
        vm.clear = clear;
        vm.save = save;
        vm.users = User.query();
        vm.comments = Comment.query();
        vm.interviewquestions = InterviewQuestion.query();

        $timeout(function (){
            angular.element('.form-group:eq(1)>input').focus();
        });

        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function save () {
            vm.isSaving = true;
            if (vm.interview.id !== null) {
                Interview.update(vm.interview, onSaveSuccess, onSaveError);
            } else {
                Interview.save(vm.interview, onSaveSuccess, onSaveError);
            }
        }

        function onSaveSuccess (result) {
            $scope.$emit('sadeApp:interviewUpdate', result);
            $uibModalInstance.close(result);
            vm.isSaving = false;
        }

        function onSaveError () {
            vm.isSaving = false;
        }


    }
})();
