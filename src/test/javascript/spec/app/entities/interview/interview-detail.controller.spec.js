'use strict';

describe('Controller Tests', function() {

    describe('Interview Management Detail Controller', function() {
        var $scope, $rootScope;
        var MockEntity, MockPreviousState, MockInterview, MockUser, MockComment, MockInterviewQuestion;
        var createController;

        beforeEach(inject(function($injector) {
            $rootScope = $injector.get('$rootScope');
            $scope = $rootScope.$new();
            MockEntity = jasmine.createSpy('MockEntity');
            MockPreviousState = jasmine.createSpy('MockPreviousState');
            MockInterview = jasmine.createSpy('MockInterview');
            MockUser = jasmine.createSpy('MockUser');
            MockComment = jasmine.createSpy('MockComment');
            MockInterviewQuestion = jasmine.createSpy('MockInterviewQuestion');
            

            var locals = {
                '$scope': $scope,
                '$rootScope': $rootScope,
                'entity': MockEntity,
                'previousState': MockPreviousState,
                'Interview': MockInterview,
                'User': MockUser,
                'Comment': MockComment,
                'InterviewQuestion': MockInterviewQuestion
            };
            createController = function() {
                $injector.get('$controller')("InterviewDetailController", locals);
            };
        }));


        describe('Root Scope Listening', function() {
            it('Unregisters root scope listener upon scope destruction', function() {
                var eventType = 'sadeApp:interviewUpdate';

                createController();
                expect($rootScope.$$listenerCount[eventType]).toEqual(1);

                $scope.$destroy();
                expect($rootScope.$$listenerCount[eventType]).toBeUndefined();
            });
        });
    });

});
