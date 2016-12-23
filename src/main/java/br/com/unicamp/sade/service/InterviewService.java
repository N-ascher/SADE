package br.com.unicamp.sade.service;

import br.com.unicamp.sade.domain.Developer;
import br.com.unicamp.sade.domain.Interview;
import br.com.unicamp.sade.domain.PreDefinedQuestion;
import br.com.unicamp.sade.repository.CommentRepository;
import br.com.unicamp.sade.repository.DeveloperRepository;
import br.com.unicamp.sade.repository.InterviewRepository;
import br.com.unicamp.sade.repository.PreDefinedQuestionRepository;
import br.com.unicamp.sade.service.dto.InterviewDTO;
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

    public Interview create(InterviewDTO interviewDTO) {
        Interview interview = new Interview();
        interview.setHourValue(interviewDTO.getHourValue());

        Developer developer = developerRepository.findOne(interviewDTO.getDeveloper().getId());
        interview.setDeveloper(developer);

        interviewDTO.getQuestions().forEach(interviewQuestion -> {
            interview.addInterviewQuestion(interviewQuestion);
            interviewQuestion.setInterview(interview);
            PreDefinedQuestion preDefinedQuestion = preDefinedQuestionRepository.findOne(interviewQuestion.getQuestion().getId());
            interviewQuestion.setQuestion(preDefinedQuestion);
        });

        interviewDTO.getComments().forEach(comment -> {
            interview.addComments(comment);
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
