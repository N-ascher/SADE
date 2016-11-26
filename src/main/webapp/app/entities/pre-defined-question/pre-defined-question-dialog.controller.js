(function() {
    'use strict';

    angular
        .module('sadeApp')
        .controller('PreDefinedQuestionDialogController', PreDefinedQuestionDialogController);

    PreDefinedQuestionDialogController.$inject = ['$timeout', '$scope', '$stateParams', '$uibModalInstance', 'entity', 'PreDefinedQuestion'];

    function PreDefinedQuestionDialogController ($timeout, $scope, $stateParams, $uibModalInstance, entity, PreDefinedQuestion) {
        var vm = this;

        vm.preDefinedQuestion = entity;
        vm.clear = clear;
        vm.save = save;

        $timeout(function (){
            angular.element('.form-group:eq(1)>input').focus();
        });

        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function save () {
            vm.isSaving = true;
            if (vm.preDefinedQuestion.id !== null) {
                PreDefinedQuestion.update(vm.preDefinedQuestion, onSaveSuccess, onSaveError);
            } else {
                PreDefinedQuestion.save(vm.preDefinedQuestion, onSaveSuccess, onSaveError);
            }
        }

        function onSaveSuccess (result) {
            $scope.$emit('sadeApp:preDefinedQuestionUpdate', result);
            $uibModalInstance.close(result);
            vm.isSaving = false;
        }

        function onSaveError () {
            vm.isSaving = false;
        }


    }
})();
