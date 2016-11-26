package br.com.unicamp.sade.service;

import br.com.unicamp.sade.domain.InterviewQuestion;

import java.util.List;

/**
 * Service Interface for managing InterviewQuestion.
 */
public interface InterviewQuestionService {

    /**
     * Save a interviewQuestion.
     *
     * @param interviewQuestion the entity to save
     * @return the persisted entity
     */
    InterviewQuestion save(InterviewQuestion interviewQuestion);

    /**
     *  Get all the interviewQuestions.
     *  
     *  @return the list of entities
     */
    List<InterviewQuestion> findAll();

    /**
     *  Get the "id" interviewQuestion.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    InterviewQuestion findOne(Long id);

    /**
     *  Delete the "id" interviewQuestion.
     *
     *  @param id the id of the entity
     */
    void delete(Long id);
}
