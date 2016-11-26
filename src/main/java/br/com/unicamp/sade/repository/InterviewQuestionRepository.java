package br.com.unicamp.sade.repository;

import br.com.unicamp.sade.domain.InterviewQuestion;

import org.springframework.data.jpa.repository.*;

import java.util.List;

/**
 * Spring Data JPA repository for the InterviewQuestion entity.
 */
@SuppressWarnings("unused")
public interface InterviewQuestionRepository extends JpaRepository<InterviewQuestion,Long> {

}
