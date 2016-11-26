'use strict';

describe('Controller Tests', function() {

    describe('InterviewQuestion Management Detail Controller', function() {
        var $scope, $rootScope;
        var MockEntity, MockPreviousState, MockInterviewQuestion, MockPreDefinedQuestion;
        var createController;

        beforeEach(inject(function($injector) {
            $rootScope = $injector.get('$rootScope');
            $scope = $rootScope.$new();
            MockEntity = jasmine.createSpy('MockEntity');
            MockPreviousState = jasmine.createSpy('MockPreviousState');
            MockInterviewQuestion = jasmine.createSpy('MockInterviewQuestion');
            MockPreDefinedQuestion = jasmine.createSpy('MockPreDefinedQuestion');
            

            var locals = {
                '$scope': $scope,
                '$rootScope': $rootScope,
                'entity': MockEntity,
                'previousState': MockPreviousState,
                'InterviewQuestion': MockInterviewQuestion,
                'PreDefinedQuestion': MockPreDefinedQuestion
            };
            createController = function() {
                $injector.get('$controller')("InterviewQuestionDetailController", locals);
            };
        }));


        describe('Root Scope Listening', function() {
            it('Unregisters root scope listener upon scope destruction', function() {
                var eventType = 'sadeApp:interviewQuestionUpdate';

                createController();
                expect($rootScope.$$listenerCount[eventType]).toEqual(1);

                $scope.$destroy();
                expect($rootScope.$$listenerCount[eventType]).toBeUndefined();
            });
        });
    });

});
