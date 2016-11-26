(function() {
    'use strict';

    angular
        .module('sadeApp')
        .config(stateConfig);

    stateConfig.$inject = ['$stateProvider'];

    function stateConfig($stateProvider) {
        $stateProvider
        .state('pre-defined-question', {
            parent: 'entity',
            url: '/pre-defined-question?page&sort&search',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'sadeApp.preDefinedQuestion.home.title'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/pre-defined-question/pre-defined-questions.html',
                    controller: 'PreDefinedQuestionController',
                    controllerAs: 'vm'
                }
            },
            params: {
                page: {
                    value: '1',
                    squash: true
                },
                sort: {
                    value: 'id,asc',
                    squash: true
                },
                search: null
            },
            resolve: {
                pagingParams: ['$stateParams', 'PaginationUtil', function ($stateParams, PaginationUtil) {
                    return {
                        page: PaginationUtil.parsePage($stateParams.page),
                        sort: $stateParams.sort,
                        predicate: PaginationUtil.parsePredicate($stateParams.sort),
                        ascending: PaginationUtil.parseAscending($stateParams.sort),
                        search: $stateParams.search
                    };
                }],
                translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                    $translatePartialLoader.addPart('preDefinedQuestion');
                    $translatePartialLoader.addPart('global');
                    return $translate.refresh();
                }]
            }
        })
        .state('pre-defined-question-detail', {
            parent: 'entity',
            url: '/pre-defined-question/{id}',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'sadeApp.preDefinedQuestion.detail.title'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/pre-defined-question/pre-defined-question-detail.html',
                    controller: 'PreDefinedQuestionDetailController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                    $translatePartialLoader.addPart('preDefinedQuestion');
                    return $translate.refresh();
                }],
                entity: ['$stateParams', 'PreDefinedQuestion', function($stateParams, PreDefinedQuestion) {
                    return PreDefinedQuestion.get({id : $stateParams.id}).$promise;
                }],
                previousState: ["$state", function ($state) {
                    var currentStateData = {
                        name: $state.current.name || 'pre-defined-question',
                        params: $state.params,
                        url: $state.href($state.current.name, $state.params)
                    };
                    return currentStateData;
                }]
            }
        })
        .state('pre-defined-question-detail.edit', {
            parent: 'pre-defined-question-detail',
            url: '/detail/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/pre-defined-question/pre-defined-question-dialog.html',
                    controller: 'PreDefinedQuestionDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['PreDefinedQuestion', function(PreDefinedQuestion) {
                            return PreDefinedQuestion.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('^', {}, { reload: false });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('pre-defined-question.new', {
            parent: 'pre-defined-question',
            url: '/new',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/pre-defined-question/pre-defined-question-dialog.html',
                    controller: 'PreDefinedQuestionDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: function () {
                            return {
                                title: null,
                                id: null
                            };
                        }
                    }
                }).result.then(function() {
                    $state.go('pre-defined-question', null, { reload: 'pre-defined-question' });
                }, function() {
                    $state.go('pre-defined-question');
                });
            }]
        })
        .state('pre-defined-question.edit', {
            parent: 'pre-defined-question',
            url: '/{id}/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/pre-defined-question/pre-defined-question-dialog.html',
                    controller: 'PreDefinedQuestionDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['PreDefinedQuestion', function(PreDefinedQuestion) {
                            return PreDefinedQuestion.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('pre-defined-question', null, { reload: 'pre-defined-question' });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('pre-defined-question.delete', {
            parent: 'pre-defined-question',
            url: '/{id}/delete',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/pre-defined-question/pre-defined-question-delete-dialog.html',
                    controller: 'PreDefinedQuestionDeleteController',
                    controllerAs: 'vm',
                    size: 'md',
                    resolve: {
                        entity: ['PreDefinedQuestion', function(PreDefinedQuestion) {
                            return PreDefinedQuestion.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('pre-defined-question', null, { reload: 'pre-defined-question' });
                }, function() {
                    $state.go('^');
                });
            }]
        });
    }

})();
