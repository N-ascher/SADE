(function() {
    'use strict';

    angular
        .module('sadeApp')
        .config(stateConfig);

    stateConfig.$inject = ['$stateProvider'];

    function stateConfig($stateProvider) {
        $stateProvider
        .state('developer', {
            parent: 'entity',
            url: '/developer?page&sort&search',
            data: {
                authorities: ['REGISTERED_USER'],
                pageTitle: 'sadeApp.developer.home.title'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/developer/developers.html',
                    controller: 'DeveloperController',
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
                    $translatePartialLoader.addPart('developer');
                    $translatePartialLoader.addPart('global');
                    return $translate.refresh();
                }]
            }
        })
        .state('developer-detail', {
            parent: 'entity',
            url: '/developer/{id}',
            data: {
                authorities: ['REGISTERED_USER'],
                pageTitle: 'sadeApp.developer.detail.title'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/developer/developer-detail.html',
                    controller: 'DeveloperDetailController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                    $translatePartialLoader.addPart('developer');
                    return $translate.refresh();
                }],
                entity: ['$stateParams', 'Developer', function($stateParams, Developer) {
                    return Developer.get({id : $stateParams.id}).$promise;
                }],
                previousState: ["$state", function ($state) {
                    var currentStateData = {
                        name: $state.current.name || 'developer',
                        params: $state.params,
                        url: $state.href($state.current.name, $state.params)
                    };
                    return currentStateData;
                }]
            }
        })
        .state('developer-detail.edit', {
            parent: 'developer-detail',
            url: '/detail/edit',
            data: {
                authorities: ['REGISTERED_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/developer/developer-dialog.html',
                    controller: 'DeveloperDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['Developer', function(Developer) {
                            return Developer.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('^', {}, { reload: false });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('developer-detail.interview-new', {
            parent: 'developer-detail',
            url: '/detail/interview/new',
            data: {
                authorities: ['CONPEC_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/developer/interview/interview-dialog.html',
                    controller: 'InterviewDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: function () {
                            return {
                                hourValue: null,
                                id: null
                            };
                        }
                    }
                }).result.then(function() {
                    $state.go('^', null, { reload: 'interview' });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('developer-detail.interview-edit', {
                    parent: 'developer-detail',
                    url: '/detail/interview/edit',
                    data: {
                        authorities: ['CONPEC_USER']
                    },
                    params: {interviewId: null},
                    onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                        $uibModal.open({
                            templateUrl: 'app/entities/developer/interview/interview-dialog.html',
                            controller: 'InterviewDialogController',
                            controllerAs: 'vm',
                            backdrop: 'static',
                            size: 'lg',
                            resolve: {
                                entity: ['Interview', function(Interview) {
                                    return Interview.get({id : $stateParams.interviewId}).$promise;
                                }]
                            }
                        }).result.then(function() {
                            $state.go('^', {}, { reload: false });
                        }, function() {
                            $state.go('^');
                        });
                    }]
                })
        .state('developer.new', {
            parent: 'developer',
            url: '/new',
            data: {
                authorities: ['REGISTERED_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/developer/developer-dialog.html',
                    controller: 'DeveloperDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: function () {
                            return {
                                phoneNumber: null,
                                mobileNumber: null,
                                document: null,
                                linkedIn: null,
                                gitHub: null,
                                availability: null,
                                prospectedBy: null,
                                id: null
                            };
                        }
                    }
                }).result.then(function() {
                    $state.go('developer', null, { reload: 'developer' });
                }, function() {
                    $state.go('developer');
                });
            }]
        })
        .state('developer.edit', {
            parent: 'developer',
            url: '/{id}/edit',
            data: {
                authorities: ['REGISTERED_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/developer/developer-dialog.html',
                    controller: 'DeveloperDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['Developer', function(Developer) {
                            return Developer.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('developer', null, { reload: 'developer' });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('developer.delete', {
            parent: 'developer',
            url: '/{id}/delete',
            data: {
                authorities: ['CONPEC_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/developer/developer-delete-dialog.html',
                    controller: 'DeveloperDeleteController',
                    controllerAs: 'vm',
                    size: 'md',
                    resolve: {
                        entity: ['Developer', function(Developer) {
                            return Developer.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('developer', null, { reload: 'developer' });
                }, function() {
                    $state.go('^');
                });
            }]
        });
    }

})();
