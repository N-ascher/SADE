package br.com.unicamp.sade.web.rest;

import br.com.unicamp.sade.security.AuthoritiesConstants;
import com.codahale.metrics.annotation.Timed;
import br.com.unicamp.sade.domain.PreDefinedQuestion;

import br.com.unicamp.sade.repository.PreDefinedQuestionRepository;
import br.com.unicamp.sade.web.rest.util.HeaderUtil;
import br.com.unicamp.sade.web.rest.util.PaginationUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing PreDefinedQuestion.
 */
@RestController
@RequestMapping("/api")
public class PreDefinedQuestionResource {

    private final Logger log = LoggerFactory.getLogger(PreDefinedQuestionResource.class);
        
    @Inject
    private PreDefinedQuestionRepository preDefinedQuestionRepository;

    /**
     * POST  /pre-defined-questions : Create a new preDefinedQuestion.
     *
     * @param preDefinedQuestion the preDefinedQuestion to create
     * @return the ResponseEntity with status 201 (Created) and with body the new preDefinedQuestion, or with status 400 (Bad Request) if the preDefinedQuestion has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/pre-defined-questions")
    @Timed
    @PreAuthorize("hasAuthority('" + AuthoritiesConstants.CONPEC_USER + "')")
    public ResponseEntity<PreDefinedQuestion> createPreDefinedQuestion(@RequestBody PreDefinedQuestion preDefinedQuestion) throws URISyntaxException {
        log.debug("REST request to save PreDefinedQuestion : {}", preDefinedQuestion);
        if (preDefinedQuestion.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert("preDefinedQuestion", "idexists", "A new preDefinedQuestion cannot already have an ID")).body(null);
        }
        PreDefinedQuestion result = preDefinedQuestionRepository.save(preDefinedQuestion);
        return ResponseEntity.created(new URI("/api/pre-defined-questions/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert("preDefinedQuestion", result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /pre-defined-questions : Updates an existing preDefinedQuestion.
     *
     * @param preDefinedQuestion the preDefinedQuestion to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated preDefinedQuestion,
     * or with status 400 (Bad Request) if the preDefinedQuestion is not valid,
     * or with status 500 (Internal Server Error) if the preDefinedQuestion couldnt be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/pre-defined-questions")
    @Timed
    @PreAuthorize("hasAuthority('" + AuthoritiesConstants.CONPEC_USER + "')")
    public ResponseEntity<PreDefinedQuestion> updatePreDefinedQuestion(@RequestBody PreDefinedQuestion preDefinedQuestion) throws URISyntaxException {
        log.debug("REST request to update PreDefinedQuestion : {}", preDefinedQuestion);
        if (preDefinedQuestion.getId() == null) {
            return createPreDefinedQuestion(preDefinedQuestion);
        }
        PreDefinedQuestion result = preDefinedQuestionRepository.save(preDefinedQuestion);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert("preDefinedQuestion", preDefinedQuestion.getId().toString()))
            .body(result);
    }

    /**
     * GET  /pre-defined-questions : get all the preDefinedQuestions.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of preDefinedQuestions in body
     * @throws URISyntaxException if there is an error to generate the pagination HTTP headers
     */
    @GetMapping("/pre-defined-questions")
    @Timed
    @PreAuthorize("hasAuthority('" + AuthoritiesConstants.CONPEC_USER + "')")
    public ResponseEntity<List<PreDefinedQuestion>> getAllPreDefinedQuestions(Pageable pageable)
        throws URISyntaxException {
        log.debug("REST request to get a page of PreDefinedQuestions");
        Page<PreDefinedQuestion> page = preDefinedQuestionRepository.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/pre-defined-questions");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /pre-defined-questions/:id : get the "id" preDefinedQuestion.
     *
     * @param id the id of the preDefinedQuestion to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the preDefinedQuestion, or with status 404 (Not Found)
     */
    @GetMapping("/pre-defined-questions/{id}")
    @Timed
    @PreAuthorize("hasAuthority('" + AuthoritiesConstants.CONPEC_USER + "')")
    public ResponseEntity<PreDefinedQuestion> getPreDefinedQuestion(@PathVariable Long id) {
        log.debug("REST request to get PreDefinedQuestion : {}", id);
        PreDefinedQuestion preDefinedQuestion = preDefinedQuestionRepository.findOne(id);
        return Optional.ofNullable(preDefinedQuestion)
            .map(result -> new ResponseEntity<>(
                result,
                HttpStatus.OK))
            .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    /**
     * DELETE  /pre-defined-questions/:id : delete the "id" preDefinedQuestion.
     *
     * @param id the id of the preDefinedQuestion to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/pre-defined-questions/{id}")
    @Timed
    @PreAuthorize("hasAuthority('" + AuthoritiesConstants.CONPEC_USER + "')")
    public ResponseEntity<Void> deletePreDefinedQuestion(@PathVariable Long id) {
        log.debug("REST request to delete PreDefinedQuestion : {}", id);
        preDefinedQuestionRepository.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert("preDefinedQuestion", id.toString())).build();
    }

}
