(function() {
    'use strict';

    angular
        .module('sadeApp')
        .controller('InterviewController', InterviewController);

    InterviewController.$inject = ['$scope', '$state', 'Interview'];

    function InterviewController ($scope, $state, Interview) {
        var vm = this;
        
        vm.interviews = [];

        loadAll();

        function loadAll() {
            Interview.query(function(result) {
                vm.interviews = result;
            });
        }
    }
})();
