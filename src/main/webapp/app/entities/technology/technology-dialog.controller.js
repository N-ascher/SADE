(function() {
    'use strict';

    angular
        .module('sadeApp')
        .controller('TechnologyDialogController', TechnologyDialogController);

    TechnologyDialogController.$inject = ['$timeout', '$scope', '$stateParams', '$uibModalInstance', 'entity', 'Technology', 'Developer'];

    function TechnologyDialogController ($timeout, $scope, $stateParams, $uibModalInstance, entity, Technology, Developer) {
        var vm = this;

        vm.technology = entity;
        vm.clear = clear;
        vm.save = save;
        vm.developers = Developer.query();

        $timeout(function (){
            angular.element('.form-group:eq(1)>input').focus();
        });

        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function save () {
            vm.isSaving = true;
            if (vm.technology.id !== null) {
                Technology.update(vm.technology, onSaveSuccess, onSaveError);
            } else {
                Technology.save(vm.technology, onSaveSuccess, onSaveError);
            }
        }

        function onSaveSuccess (result) {
            $scope.$emit('sadeApp:technologyUpdate', result);
            $uibModalInstance.close(result);
            vm.isSaving = false;
        }

        function onSaveError () {
            vm.isSaving = false;
        }


    }
})();
