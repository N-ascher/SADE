package br.com.unicamp.sade.web.rest;

import com.codahale.metrics.annotation.Timed;
import br.com.unicamp.sade.domain.Developer;

import br.com.unicamp.sade.repository.DeveloperRepository;
import br.com.unicamp.sade.web.rest.util.HeaderUtil;
import br.com.unicamp.sade.web.rest.util.PaginationUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
 * REST controller for managing Developer.
 */
@RestController
@RequestMapping("/api")
public class DeveloperResource {

    private final Logger log = LoggerFactory.getLogger(DeveloperResource.class);
        
    @Inject
    private DeveloperRepository developerRepository;

    /**
     * POST  /developers : Create a new developer.
     *
     * @param developer the developer to create
     * @return the ResponseEntity with status 201 (Created) and with body the new developer, or with status 400 (Bad Request) if the developer has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/developers")
    @Timed
    public ResponseEntity<Developer> createDeveloper(@RequestBody Developer developer) throws URISyntaxException {
        log.debug("REST request to save Developer : {}", developer);
        if (developer.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert("developer", "idexists", "A new developer cannot already have an ID")).body(null);
        }
        Developer result = developerRepository.save(developer);
        return ResponseEntity.created(new URI("/api/developers/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert("developer", result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /developers : Updates an existing developer.
     *
     * @param developer the developer to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated developer,
     * or with status 400 (Bad Request) if the developer is not valid,
     * or with status 500 (Internal Server Error) if the developer couldnt be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/developers")
    @Timed
    public ResponseEntity<Developer> updateDeveloper(@RequestBody Developer developer) throws URISyntaxException {
        log.debug("REST request to update Developer : {}", developer);
        if (developer.getId() == null) {
            return createDeveloper(developer);
        }
        Developer result = developerRepository.save(developer);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert("developer", developer.getId().toString()))
            .body(result);
    }

    /**
     * GET  /developers : get all the developers.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of developers in body
     * @throws URISyntaxException if there is an error to generate the pagination HTTP headers
     */
    @GetMapping("/developers")
    @Timed
    public ResponseEntity<List<Developer>> getAllDevelopers(Pageable pageable)
        throws URISyntaxException {
        log.debug("REST request to get a page of Developers");
        Page<Developer> page = developerRepository.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/developers");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /developers/:id : get the "id" developer.
     *
     * @param id the id of the developer to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the developer, or with status 404 (Not Found)
     */
    @GetMapping("/developers/{id}")
    @Timed
    public ResponseEntity<Developer> getDeveloper(@PathVariable Long id) {
        log.debug("REST request to get Developer : {}", id);
        Developer developer = developerRepository.findOne(id);
        return Optional.ofNullable(developer)
            .map(result -> new ResponseEntity<>(
                result,
                HttpStatus.OK))
            .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    /**
     * DELETE  /developers/:id : delete the "id" developer.
     *
     * @param id the id of the developer to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/developers/{id}")
    @Timed
    public ResponseEntity<Void> deleteDeveloper(@PathVariable Long id) {
        log.debug("REST request to delete Developer : {}", id);
        developerRepository.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert("developer", id.toString())).build();
    }

}
