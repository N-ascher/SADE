(function() {
    'use strict';
    angular
        .module('sadeApp')
        .factory('PreDefinedQuestion', PreDefinedQuestion);

    PreDefinedQuestion.$inject = ['$resource'];

    function PreDefinedQuestion ($resource) {
        var baseUrl = 'api/pre-defined-questions';
        var resourceUrl =  baseUrl + '/:id';

        return $resource(resourceUrl, {}, {
            'query': { method: 'GET', isArray: true},
            'get': {
                method: 'GET',
                transformResponse: function (data) {
                    if (data) {
                        data = angular.fromJson(data);
                    }
                    return data;
                }
            },
            'update': { method:'PUT' },
            'getAll': { method: 'GET', isArray: true, url: baseUrl + '/all' }
        });
    }
})();
