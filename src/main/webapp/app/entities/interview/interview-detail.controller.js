(function() {
    'use strict';

    angular
        .module('sadeApp')
        .controller('InterviewDetailController', InterviewDetailController);

    InterviewDetailController.$inject = ['$scope', '$rootScope', '$stateParams', 'previousState', 'entity', 'Interview', 'User', 'Comment', 'InterviewQuestion'];

    function InterviewDetailController($scope, $rootScope, $stateParams, previousState, entity, Interview, User, Comment, InterviewQuestion) {
        var vm = this;

        vm.interview = entity;
        vm.previousState = previousState.name;

        var unsubscribe = $rootScope.$on('sadeApp:interviewUpdate', function(event, result) {
            vm.interview = result;
        });
        $scope.$on('$destroy', unsubscribe);
    }
})();
