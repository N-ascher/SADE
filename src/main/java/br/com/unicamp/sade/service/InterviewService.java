package br.com.unicamp.sade.service;

import br.com.unicamp.sade.domain.Developer;
import br.com.unicamp.sade.domain.Interview;
import br.com.unicamp.sade.domain.PreDefinedQuestion;
import br.com.unicamp.sade.repository.CommentRepository;
import br.com.unicamp.sade.repository.DeveloperRepository;
import br.com.unicamp.sade.repository.InterviewRepository;
import br.com.unicamp.sade.repository.PreDefinedQuestionRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;

/**
 * Service class for managing interviews.
 */
@Service
@Transactional
public class InterviewService {

    @Inject
    DeveloperRepository developerRepository;

    @Inject
    PreDefinedQuestionRepository preDefinedQuestionRepository;

    @Inject
    CommentRepository commentRepository;

    @Inject
    InterviewRepository interviewRepository;

    public Interview create(Interview interview) {
        Developer developer = developerRepository.findOne(interview.getDeveloper().getId());
        interview.setDeveloper(developer);

        interview.getQuestions().forEach(interviewQuestion -> {
            interviewQuestion.setInterview(interview);
            PreDefinedQuestion preDefinedQuestion = preDefinedQuestionRepository.findOne(interviewQuestion.getQuestion().getId());
            interviewQuestion.setQuestion(preDefinedQuestion);
        });

        interview.getComments().forEach(comment -> {
            comment.setInterview(interview);
        });

        return interviewRepository.save(interview);
    }

    public Interview update(Interview interview) {
        Interview attachedInterview = interviewRepository.findOne(interview.getId());
        commentRepository.delete(attachedInterview.getComments());
        attachedInterview.getComments().clear();
        attachedInterview.setComments(interview.getComments());

        attachedInterview.getQuestions().clear();
        attachedInterview.setQuestions(interview.getQuestions());

        attachedInterview.setHourValue(interview.getHourValue());

        attachedInterview.getQuestions().forEach(interviewQuestion -> {
            interviewQuestion.setInterview(interview);
            PreDefinedQuestion preDefinedQuestion = preDefinedQuestionRepository.findOne(interviewQuestion.getQuestion().getId());
            interviewQuestion.setQuestion(preDefinedQuestion);
        });

        attachedInterview.getComments().forEach(comment -> {
            comment.setInterview(interview);
        });

        return interviewRepository.save(interview);
    }
}
