(function() {
    'use strict';

    angular
        .module('sadeApp')
        .controller('PreDefinedQuestionDeleteController',PreDefinedQuestionDeleteController);

    PreDefinedQuestionDeleteController.$inject = ['$uibModalInstance', 'entity', 'PreDefinedQuestion'];

    function PreDefinedQuestionDeleteController($uibModalInstance, entity, PreDefinedQuestion) {
        var vm = this;

        vm.preDefinedQuestion = entity;
        vm.clear = clear;
        vm.confirmDelete = confirmDelete;
        
        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function confirmDelete (id) {
            PreDefinedQuestion.delete({id: id},
                function () {
                    $uibModalInstance.close(true);
                });
        }
    }
})();
