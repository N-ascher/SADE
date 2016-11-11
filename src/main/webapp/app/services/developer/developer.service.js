(function() {
    'use strict';

    angular
        .module('sadeApp')
        .factory('DeveloperService', DeveloperService);

    DeveloperService.$inject = ['RegisterDeveloper', 'Auth'];

    function DeveloperService (RegisterDeveloper, Auth) {
        var service = {
            create: create,
        };

        return service;

        function create (developer, callback) {
            var cb = callback || angular.noop;

            return RegisterDeveloper.save(developer,
                function () {
                    return cb(developer);
                },
                function (err) {
                    Auth.logout();
                    return cb(err);
                }.bind(this)).$promise;
        }

    }
})();
