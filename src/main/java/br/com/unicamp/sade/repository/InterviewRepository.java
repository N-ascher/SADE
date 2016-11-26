package br.com.unicamp.sade.repository;

import br.com.unicamp.sade.domain.Interview;

import org.springframework.data.jpa.repository.*;

import java.util.List;

/**
 * Spring Data JPA repository for the Interview entity.
 */
@SuppressWarnings("unused")
public interface InterviewRepository extends JpaRepository<Interview,Long> {

}
