(function() {
    'use strict';

    angular
        .module('sadeApp')
        .controller('RegisterController', RegisterController);


    RegisterController.$inject = ['$translate', '$timeout', 'DeveloperService', 'LoginService'];

    function RegisterController ($translate, $timeout, DeveloperService, LoginService) {
        var vm = this;

        vm.doNotMatch = null;
        vm.error = null;
        vm.errorUserExists = null;
        vm.login = LoginService.open;
        vm.register = register;
        vm.success = null;
        vm.developer = {
            user: {},
            technologies: []
        };

        vm.technology = {};
        vm.addTech = addTech;
        vm.deleteTech = deleteTech;

        vm.currentTab = "basic";
        vm.tabClass = tabClass;
        vm.changeTab = changeTab;
        vm.showPrevious = showPrevious;
        vm.showNext = showNext;
        vm.next = next;
        vm.previous = previous;
        vm.showSubmit = showSubmit;

        $timeout(function (){angular.element('#login').focus();});

        function tabClass(id) {
            if(id == vm.currentTab) {
                return "active";
            }

            return "";
        }

        function changeTab(id) {
            vm.currentTab = id;
        }

        function showPrevious() {
            return vm.currentTab != "basic";
        }

        function showNext() {
            return vm.currentTab != "experience";
        }

        function showSubmit() {
            return vm.currentTab == "experience";
        }

        function next() {
            if(vm.currentTab == "basic") {
                vm.currentTab = "user";
            } else if(vm.currentTab == "user") {
                vm.currentTab = "experience";
            }
        }

        function previous() {
            if(vm.currentTab == "user") {
                vm.currentTab = "basic";
            } else if(vm.currentTab == "experience") {
                vm.currentTab = "user";
            }
        }

        function addTech() {
            vm.developer.technologies.push(vm.technology);
            vm.technology = {};
        }

        function deleteTech(tech) {
            var technologies = vm.developer.technologies;
            technologies.splice(technologies.indexOf(tech), 1);
        }

        function register () {
            if (vm.developer.user.password !== vm.confirmPassword) {
                vm.doNotMatch = 'ERROR';
            } else {
                vm.developer.user.langKey = $translate.use();
                vm.developer.user.firstName = vm.developer.user.name.split(/ (.+)?/)[0];
                vm.developer.user.lastName = vm.developer.user.name.split(/ (.+)?/)[1];
                vm.doNotMatch = null;
                vm.error = null;
                vm.errorUserExists = null;
                vm.errorEmailExists = null;

                DeveloperService.create(vm.developer).then(function () {
                    vm.success = 'OK';
                }).catch(function (response) {
                    vm.success = null;
                    if (response.status === 400 && response.data === 'login already in use') {
                        vm.errorUserExists = 'ERROR';
                    } else if (response.status === 400 && response.data === 'e-mail address already in use') {
                        vm.errorEmailExists = 'ERROR';
                    } else {
                        vm.error = 'ERROR';
                    }
                });
            }
        }
    }
})();
