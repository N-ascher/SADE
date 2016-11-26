package br.com.unicamp.sade.web.rest;

import com.codahale.metrics.annotation.Timed;
import br.com.unicamp.sade.domain.InterviewQuestion;
import br.com.unicamp.sade.service.InterviewQuestionService;
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
 * REST controller for managing InterviewQuestion.
 */
@RestController
@RequestMapping("/api")
public class InterviewQuestionResource {

    private final Logger log = LoggerFactory.getLogger(InterviewQuestionResource.class);
        
    @Inject
    private InterviewQuestionService interviewQuestionService;

    /**
     * POST  /interview-questions : Create a new interviewQuestion.
     *
     * @param interviewQuestion the interviewQuestion to create
     * @return the ResponseEntity with status 201 (Created) and with body the new interviewQuestion, or with status 400 (Bad Request) if the interviewQuestion has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/interview-questions")
    @Timed
    public ResponseEntity<InterviewQuestion> createInterviewQuestion(@RequestBody InterviewQuestion interviewQuestion) throws URISyntaxException {
        log.debug("REST request to save InterviewQuestion : {}", interviewQuestion);
        if (interviewQuestion.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert("interviewQuestion", "idexists", "A new interviewQuestion cannot already have an ID")).body(null);
        }
        InterviewQuestion result = interviewQuestionService.save(interviewQuestion);
        return ResponseEntity.created(new URI("/api/interview-questions/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert("interviewQuestion", result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /interview-questions : Updates an existing interviewQuestion.
     *
     * @param interviewQuestion the interviewQuestion to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated interviewQuestion,
     * or with status 400 (Bad Request) if the interviewQuestion is not valid,
     * or with status 500 (Internal Server Error) if the interviewQuestion couldnt be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/interview-questions")
    @Timed
    public ResponseEntity<InterviewQuestion> updateInterviewQuestion(@RequestBody InterviewQuestion interviewQuestion) throws URISyntaxException {
        log.debug("REST request to update InterviewQuestion : {}", interviewQuestion);
        if (interviewQuestion.getId() == null) {
            return createInterviewQuestion(interviewQuestion);
        }
        InterviewQuestion result = interviewQuestionService.save(interviewQuestion);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert("interviewQuestion", interviewQuestion.getId().toString()))
            .body(result);
    }

    /**
     * GET  /interview-questions : get all the interviewQuestions.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of interviewQuestions in body
     */
    @GetMapping("/interview-questions")
    @Timed
    public List<InterviewQuestion> getAllInterviewQuestions() {
        log.debug("REST request to get all InterviewQuestions");
        return interviewQuestionService.findAll();
    }

    /**
     * GET  /interview-questions/:id : get the "id" interviewQuestion.
     *
     * @param id the id of the interviewQuestion to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the interviewQuestion, or with status 404 (Not Found)
     */
    @GetMapping("/interview-questions/{id}")
    @Timed
    public ResponseEntity<InterviewQuestion> getInterviewQuestion(@PathVariable Long id) {
        log.debug("REST request to get InterviewQuestion : {}", id);
        InterviewQuestion interviewQuestion = interviewQuestionService.findOne(id);
        return Optional.ofNullable(interviewQuestion)
            .map(result -> new ResponseEntity<>(
                result,
                HttpStatus.OK))
            .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    /**
     * DELETE  /interview-questions/:id : delete the "id" interviewQuestion.
     *
     * @param id the id of the interviewQuestion to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/interview-questions/{id}")
    @Timed
    public ResponseEntity<Void> deleteInterviewQuestion(@PathVariable Long id) {
        log.debug("REST request to delete InterviewQuestion : {}", id);
        interviewQuestionService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert("interviewQuestion", id.toString())).build();
    }

}
