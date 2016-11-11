(function () {
    'use strict';

    angular
        .module('sadeApp')
        .factory('RegisterDeveloper', RegisterDeveloper);

    RegisterDeveloper.$inject = ['$resource'];

    function RegisterDeveloper ($resource) {
        var service = $resource('api/developers/register', {}, {
            'save': { method:'POST' },
        });

        return service;
    }
})();
