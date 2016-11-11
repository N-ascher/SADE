(function() {
    'use strict';

    angular
        .module('sadeApp')
        .controller('DeveloperDetailController', DeveloperDetailController);

    DeveloperDetailController.$inject = ['$scope', '$rootScope', '$stateParams', 'previousState', 'entity', 'Developer', 'Address', 'Technology'];

    function DeveloperDetailController($scope, $rootScope, $stateParams, previousState, entity, Developer, Address, Technology) {
        var vm = this;

        vm.developer = entity;
        vm.previousState = previousState.name;

        var unsubscribe = $rootScope.$on('sadeApp:developerUpdate', function(event, result) {
            vm.developer = result;
        });
        $scope.$on('$destroy', unsubscribe);
    }
})();
