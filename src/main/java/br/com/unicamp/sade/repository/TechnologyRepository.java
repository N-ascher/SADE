package br.com.unicamp.sade.repository;

import br.com.unicamp.sade.domain.Technology;

import org.springframework.data.jpa.repository.*;

import java.util.List;

/**
 * Spring Data JPA repository for the Technology entity.
 */
@SuppressWarnings("unused")
public interface TechnologyRepository extends JpaRepository<Technology,Long> {

}
