(function() {
    'use strict';

    angular
        .module('sadeApp')
        .controller('DeveloperDetailController', DeveloperDetailController);

    DeveloperDetailController.$inject = ['$scope', '$rootScope', '$stateParams', 'previousState', 'entity', 'Developer', 'Technology'];

    function DeveloperDetailController($scope, $rootScope, $stateParams, previousState, entity, Developer, Technology) {
        var vm = this;

        vm.developer = entity;
        vm.previousState = previousState.name;

        vm.currentTab = 'experience';
        vm.tabClass = tabClass;
        vm.changeTab = changeTab;

        var unsubscribe = $rootScope.$on('sadeApp:developerUpdate', function(event, result) {
            vm.developer = result;
        });
        $scope.$on('$destroy', unsubscribe);

        function tabClass(tabName) {
            if(tabName == vm.currentTab) {
                return 'active';
            }
            return '';
        }

        function changeTab(tabName) {
            vm.currentTab = tabName;
        }
    }
})();
