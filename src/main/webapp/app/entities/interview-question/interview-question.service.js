(function() {
    'use strict';
    angular
        .module('sadeApp')
        .factory('InterviewQuestion', InterviewQuestion);

    InterviewQuestion.$inject = ['$resource'];

    function InterviewQuestion ($resource) {
        var resourceUrl =  'api/interview-questions/:id';

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
