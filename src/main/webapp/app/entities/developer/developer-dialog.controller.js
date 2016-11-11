(function() {
    'use strict';

    angular
        .module('sadeApp')
        .controller('DeveloperDialogController', DeveloperDialogController);

    DeveloperDialogController.$inject = ['$timeout', '$scope', '$stateParams', '$uibModalInstance', '$q', 'entity', 'Developer', 'Address', 'Technology'];

    function DeveloperDialogController ($timeout, $scope, $stateParams, $uibModalInstance, $q, entity, Developer, Address, Technology) {
        var vm = this;

        vm.developer = entity;
        vm.clear = clear;
        vm.save = save;
        vm.addresses = Address.query({filter: 'developer-is-null'});
        $q.all([vm.developer.$promise, vm.addresses.$promise]).then(function() {
            if (!vm.developer.address || !vm.developer.address.id) {
                return $q.reject();
            }
            return Address.get({id : vm.developer.address.id}).$promise;
        }).then(function(address) {
            vm.addresses.push(address);
        });
        vm.technologies = Technology.query();

        $timeout(function (){
            angular.element('.form-group:eq(1)>input').focus();
        });

        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function save () {
            vm.isSaving = true;
            if (vm.developer.id !== null) {
                Developer.update(vm.developer, onSaveSuccess, onSaveError);
            } else {
                Developer.save(vm.developer, onSaveSuccess, onSaveError);
            }
        }

        function onSaveSuccess (result) {
            $scope.$emit('sadeApp:developerUpdate', result);
            $uibModalInstance.close(result);
            vm.isSaving = false;
        }

        function onSaveError () {
            vm.isSaving = false;
        }


    }
})();
