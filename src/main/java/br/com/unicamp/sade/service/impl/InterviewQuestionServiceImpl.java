package br.com.unicamp.sade.service.impl;

import br.com.unicamp.sade.service.InterviewQuestionService;
import br.com.unicamp.sade.domain.InterviewQuestion;
import br.com.unicamp.sade.repository.InterviewQuestionRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.List;

/**
 * Service Implementation for managing InterviewQuestion.
 */
@Service
@Transactional
public class InterviewQuestionServiceImpl implements InterviewQuestionService{

    private final Logger log = LoggerFactory.getLogger(InterviewQuestionServiceImpl.class);
    
    @Inject
    private InterviewQuestionRepository interviewQuestionRepository;

    /**
     * Save a interviewQuestion.
     *
     * @param interviewQuestion the entity to save
     * @return the persisted entity
     */
    public InterviewQuestion save(InterviewQuestion interviewQuestion) {
        log.debug("Request to save InterviewQuestion : {}", interviewQuestion);
        InterviewQuestion result = interviewQuestionRepository.save(interviewQuestion);
        return result;
    }

    /**
     *  Get all the interviewQuestions.
     *  
     *  @return the list of entities
     */
    @Transactional(readOnly = true) 
    public List<InterviewQuestion> findAll() {
        log.debug("Request to get all InterviewQuestions");
        List<InterviewQuestion> result = interviewQuestionRepository.findAll();

        return result;
    }

    /**
     *  Get one interviewQuestion by id.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    @Transactional(readOnly = true) 
    public InterviewQuestion findOne(Long id) {
        log.debug("Request to get InterviewQuestion : {}", id);
        InterviewQuestion interviewQuestion = interviewQuestionRepository.findOne(id);
        return interviewQuestion;
    }

    /**
     *  Delete the  interviewQuestion by id.
     *
     *  @param id the id of the entity
     */
    public void delete(Long id) {
        log.debug("Request to delete InterviewQuestion : {}", id);
        interviewQuestionRepository.delete(id);
    }
}
