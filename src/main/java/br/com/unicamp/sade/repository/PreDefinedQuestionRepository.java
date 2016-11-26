package br.com.unicamp.sade.repository;

import br.com.unicamp.sade.domain.PreDefinedQuestion;

import org.springframework.data.jpa.repository.*;

import java.util.List;

/**
 * Spring Data JPA repository for the PreDefinedQuestion entity.
 */
@SuppressWarnings("unused")
public interface PreDefinedQuestionRepository extends JpaRepository<PreDefinedQuestion,Long> {

}
