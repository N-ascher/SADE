(function() {
    'use strict';

    angular
        .module('sadeApp')
        .controller('InterviewDialogController', InterviewDialogController);

    InterviewDialogController.$inject = ['$timeout', '$scope', '$stateParams', '$uibModalInstance', '$q', 'entity',
    'Interview', 'User', 'Comment', 'InterviewQuestion', 'PreDefinedQuestion', 'AlertService'];

    function InterviewDialogController ($timeout, $scope, $stateParams, $uibModalInstance, $q, entity, Interview,
    User, Comment, InterviewQuestion, PreDefinedQuestion, AlertService) {
        var vm = this;

        vm.interview = entity;
        vm.interview.developer = {id: $stateParams.id};
        vm.clear = clear;
        vm.save = save;
        vm.users = User.query();
        vm.comments = Comment.query();
        vm.interviewquestions = InterviewQuestion.query();
        loadAllPreDefinedQuestions();

        vm.comment = {};

        vm.currentTab = 'basicData';
        vm.tabClass = tabClass;
        vm.changeTab = changeTab;

        vm.addComment = addComment;
        vm.deleteComment = deleteComment;

        if(!vm.interview.id) {
            vm.interview.comments = [];
        }

        $timeout(function (){
            angular.element('.form-group:eq(1)>input').focus();
        });

        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function save () {
            vm.isSaving = true;
            if (vm.interview.id !== null) {
                Interview.update(vm.interview, onSaveSuccess, onSaveError);
            } else {
                Interview.save(vm.interview, onSaveSuccess, onSaveError);
            }
        }

        function onSaveSuccess (result) {
            $scope.$emit('sadeApp:developerInterviewUpdate', result);
            $uibModalInstance.close(result);
            vm.isSaving = false;
        }

        function onSaveError () {
            vm.isSaving = false;
        }

        function tabClass(tabName) {
            if(tabName == vm.currentTab) {
                return 'active';
            }
            return '';
        }

        function changeTab(tabName) {
            vm.currentTab = tabName;
        }

        function addComment() {
            if(vm.comment.content) {
                vm.interview.comments.push(vm.comment);
                vm.comment = {};
            }
        }

        function deleteComment(comment) {
            var comments = vm.interview.comments;
            comments.splice(comments.indexOf(comment), 1);
        }

        function loadAllPreDefinedQuestions () {
            if(vm.interview.id == null) {
                PreDefinedQuestion.getAll({
                    sort: sort()
                }, onSuccess, onError);
            }
            function sort() {
                var result = [vm.predicate + ',' + (vm.reverse ? 'asc' : 'desc')];
                if (vm.predicate !== 'id') {
                    result.push('id');
                }
                return result;
            }
            function onSuccess(data, headers) {
                vm.interview.questions = [];
                data.forEach(function(question) {
                    vm.interview.questions.push({
                        questionTitle: question.title,
                        question: {
                            id: question.id
                        }
                    });
                });
            }
            function onError(error) {
                AlertService.error(error.data.message);
            }
        }
    }
})();
