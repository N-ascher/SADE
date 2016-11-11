(function() {
    'use strict';

    angular
        .module('sadeApp')
        .controller('TechnologyController', TechnologyController);

    TechnologyController.$inject = ['$scope', '$state', 'Technology'];

    function TechnologyController ($scope, $state, Technology) {
        var vm = this;
        
        vm.technologies = [];

        loadAll();

        function loadAll() {
            Technology.query(function(result) {
                vm.technologies = result;
            });
        }
    }
})();
