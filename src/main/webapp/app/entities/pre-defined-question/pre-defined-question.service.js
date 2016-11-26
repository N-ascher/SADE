(function() {
    'use strict';
    angular
        .module('sadeApp')
        .factory('PreDefinedQuestion', PreDefinedQuestion);

    PreDefinedQuestion.$inject = ['$resource'];

    function PreDefinedQuestion ($resource) {
        var resourceUrl =  'api/pre-defined-questions/:id';

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
            'update': { method:'PUT' }
        });
    }
})();
