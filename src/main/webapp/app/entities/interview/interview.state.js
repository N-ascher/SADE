(function() {
    'use strict';

    angular
        .module('sadeApp')
        .config(stateConfig);

    stateConfig.$inject = ['$stateProvider'];

    function stateConfig($stateProvider) {
        $stateProvider
        .state('interview', {
            parent: 'entity',
            url: '/interview',
            data: {
                authorities: ['CONPEC_USER'],
                pageTitle: 'sadeApp.interview.home.title'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/interview/interviews.html',
                    controller: 'InterviewController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                    $translatePartialLoader.addPart('interview');
                    $translatePartialLoader.addPart('global');
                    return $translate.refresh();
                }]
            }
        })
        .state('interview-detail', {
            parent: 'entity',
            url: '/interview/{id}',
            data: {
                authorities: ['CONPEC_USER'],
                pageTitle: 'sadeApp.interview.detail.title'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/interview/interview-detail.html',
                    controller: 'InterviewDetailController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                    $translatePartialLoader.addPart('interview');
                    return $translate.refresh();
                }],
                entity: ['$stateParams', 'Interview', function($stateParams, Interview) {
                    return Interview.get({id : $stateParams.id}).$promise;
                }],
                previousState: ["$state", function ($state) {
                    var currentStateData = {
                        name: $state.current.name || 'interview',
                        params: $state.params,
                        url: $state.href($state.current.name, $state.params)
                    };
                    return currentStateData;
                }]
            }
        })
        .state('interview-detail.edit', {
            parent: 'interview-detail',
            url: '/detail/edit',
            data: {
                authorities: ['CONPEC_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/interview/interview-dialog.html',
                    controller: 'InterviewDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['Interview', function(Interview) {
                            return Interview.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('^', {}, { reload: false });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('interview.new', {
            parent: 'interview',
            url: '/new',
            data: {
                authorities: ['CONPEC_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/interview/interview-dialog.html',
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
                    $state.go('interview', null, { reload: 'interview' });
                }, function() {
                    $state.go('interview');
                });
            }]
        })
        .state('interview.edit', {
            parent: 'interview',
            url: '/{id}/edit',
            data: {
                authorities: ['CONPEC_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/interview/interview-dialog.html',
                    controller: 'InterviewDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['Interview', function(Interview) {
                            return Interview.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('interview', null, { reload: 'interview' });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('interview.delete', {
            parent: 'interview',
            url: '/{id}/delete',
            data: {
                authorities: ['CONPEC_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/interview/interview-delete-dialog.html',
                    controller: 'InterviewDeleteController',
                    controllerAs: 'vm',
                    size: 'md',
                    resolve: {
                        entity: ['Interview', function(Interview) {
                            return Interview.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('interview', null, { reload: 'interview' });
                }, function() {
                    $state.go('^');
                });
            }]
        });
    }

})();
