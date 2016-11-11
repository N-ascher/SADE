(function() {
    'use strict';

    angular
        .module('sadeApp')
        .controller('TechnologyDetailController', TechnologyDetailController);

    TechnologyDetailController.$inject = ['$scope', '$rootScope', '$stateParams', 'previousState', 'entity', 'Technology', 'Developer'];

    function TechnologyDetailController($scope, $rootScope, $stateParams, previousState, entity, Technology, Developer) {
        var vm = this;

        vm.technology = entity;
        vm.previousState = previousState.name;

        var unsubscribe = $rootScope.$on('sadeApp:technologyUpdate', function(event, result) {
            vm.technology = result;
        });
        $scope.$on('$destroy', unsubscribe);
    }
})();
