package br.com.unicamp.sade.web.rest;

import com.codahale.metrics.annotation.Timed;
import br.com.unicamp.sade.domain.Technology;

import br.com.unicamp.sade.repository.TechnologyRepository;
import br.com.unicamp.sade.web.rest.util.HeaderUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing Technology.
 */
@RestController
@RequestMapping("/api")
public class TechnologyResource {

    private final Logger log = LoggerFactory.getLogger(TechnologyResource.class);
        
    @Inject
    private TechnologyRepository technologyRepository;

    /**
     * POST  /technologies : Create a new technology.
     *
     * @param technology the technology to create
     * @return the ResponseEntity with status 201 (Created) and with body the new technology, or with status 400 (Bad Request) if the technology has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/technologies")
    @Timed
    public ResponseEntity<Technology> createTechnology(@RequestBody Technology technology) throws URISyntaxException {
        log.debug("REST request to save Technology : {}", technology);
        if (technology.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert("technology", "idexists", "A new technology cannot already have an ID")).body(null);
        }
        Technology result = technologyRepository.save(technology);
        return ResponseEntity.created(new URI("/api/technologies/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert("technology", result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /technologies : Updates an existing technology.
     *
     * @param technology the technology to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated technology,
     * or with status 400 (Bad Request) if the technology is not valid,
     * or with status 500 (Internal Server Error) if the technology couldnt be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/technologies")
    @Timed
    public ResponseEntity<Technology> updateTechnology(@RequestBody Technology technology) throws URISyntaxException {
        log.debug("REST request to update Technology : {}", technology);
        if (technology.getId() == null) {
            return createTechnology(technology);
        }
        Technology result = technologyRepository.save(technology);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert("technology", technology.getId().toString()))
            .body(result);
    }

    /**
     * GET  /technologies : get all the technologies.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of technologies in body
     */
    @GetMapping("/technologies")
    @Timed
    public List<Technology> getAllTechnologies() {
        log.debug("REST request to get all Technologies");
        List<Technology> technologies = technologyRepository.findAll();
        return technologies;
    }

    /**
     * GET  /technologies/:id : get the "id" technology.
     *
     * @param id the id of the technology to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the technology, or with status 404 (Not Found)
     */
    @GetMapping("/technologies/{id}")
    @Timed
    public ResponseEntity<Technology> getTechnology(@PathVariable Long id) {
        log.debug("REST request to get Technology : {}", id);
        Technology technology = technologyRepository.findOne(id);
        return Optional.ofNullable(technology)
            .map(result -> new ResponseEntity<>(
                result,
                HttpStatus.OK))
            .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    /**
     * DELETE  /technologies/:id : delete the "id" technology.
     *
     * @param id the id of the technology to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/technologies/{id}")
    @Timed
    public ResponseEntity<Void> deleteTechnology(@PathVariable Long id) {
        log.debug("REST request to delete Technology : {}", id);
        technologyRepository.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert("technology", id.toString())).build();
    }

}
