(function() {
    'use strict';

    angular
        .module('sadeApp')
        .config(stateConfig);

    stateConfig.$inject = ['$stateProvider'];

    function stateConfig($stateProvider) {
        $stateProvider
        .state('interview-question', {
            parent: 'entity',
            url: '/interview-question',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'sadeApp.interviewQuestion.home.title'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/interview-question/interview-questions.html',
                    controller: 'InterviewQuestionController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                    $translatePartialLoader.addPart('interviewQuestion');
                    $translatePartialLoader.addPart('global');
                    return $translate.refresh();
                }]
            }
        })
        .state('interview-question-detail', {
            parent: 'entity',
            url: '/interview-question/{id}',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'sadeApp.interviewQuestion.detail.title'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/interview-question/interview-question-detail.html',
                    controller: 'InterviewQuestionDetailController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                    $translatePartialLoader.addPart('interviewQuestion');
                    return $translate.refresh();
                }],
                entity: ['$stateParams', 'InterviewQuestion', function($stateParams, InterviewQuestion) {
                    return InterviewQuestion.get({id : $stateParams.id}).$promise;
                }],
                previousState: ["$state", function ($state) {
                    var currentStateData = {
                        name: $state.current.name || 'interview-question',
                        params: $state.params,
                        url: $state.href($state.current.name, $state.params)
                    };
                    return currentStateData;
                }]
            }
        })
        .state('interview-question-detail.edit', {
            parent: 'interview-question-detail',
            url: '/detail/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/interview-question/interview-question-dialog.html',
                    controller: 'InterviewQuestionDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['InterviewQuestion', function(InterviewQuestion) {
                            return InterviewQuestion.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('^', {}, { reload: false });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('interview-question.new', {
            parent: 'interview-question',
            url: '/new',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/interview-question/interview-question-dialog.html',
                    controller: 'InterviewQuestionDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: function () {
                            return {
                                questionTitle: null,
                                response: null,
                                id: null
                            };
                        }
                    }
                }).result.then(function() {
                    $state.go('interview-question', null, { reload: 'interview-question' });
                }, function() {
                    $state.go('interview-question');
                });
            }]
        })
        .state('interview-question.edit', {
            parent: 'interview-question',
            url: '/{id}/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/interview-question/interview-question-dialog.html',
                    controller: 'InterviewQuestionDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['InterviewQuestion', function(InterviewQuestion) {
                            return InterviewQuestion.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('interview-question', null, { reload: 'interview-question' });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('interview-question.delete', {
            parent: 'interview-question',
            url: '/{id}/delete',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/interview-question/interview-question-delete-dialog.html',
                    controller: 'InterviewQuestionDeleteController',
                    controllerAs: 'vm',
                    size: 'md',
                    resolve: {
                        entity: ['InterviewQuestion', function(InterviewQuestion) {
                            return InterviewQuestion.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('interview-question', null, { reload: 'interview-question' });
                }, function() {
                    $state.go('^');
                });
            }]
        });
    }

})();
