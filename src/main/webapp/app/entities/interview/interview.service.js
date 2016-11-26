(function() {
    'use strict';
    angular
        .module('sadeApp')
        .factory('Interview', Interview);

    Interview.$inject = ['$resource'];

    function Interview ($resource) {
        var resourceUrl =  'api/interviews/:id';

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
