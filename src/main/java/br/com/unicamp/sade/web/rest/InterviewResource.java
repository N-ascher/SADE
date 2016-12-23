package br.com.unicamp.sade.web.rest;

import br.com.unicamp.sade.domain.Interview;
import br.com.unicamp.sade.domain.User;
import br.com.unicamp.sade.repository.InterviewRepository;
import br.com.unicamp.sade.repository.UserRepository;
import br.com.unicamp.sade.security.AuthoritiesConstants;
import br.com.unicamp.sade.security.SecurityUtils;
import br.com.unicamp.sade.service.InterviewService;
import br.com.unicamp.sade.service.dto.InterviewDTO;
import br.com.unicamp.sade.web.rest.util.HeaderUtil;
import com.codahale.metrics.annotation.Timed;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.inject.Inject;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing Interview.
 */
@RestController
@RequestMapping("/api")
public class InterviewResource {

    private final Logger log = LoggerFactory.getLogger(InterviewResource.class);
        
    @Inject
    private InterviewRepository interviewRepository;

    @Inject
    private UserRepository userRepository;

    @Inject
    private InterviewService interviewService;

    /**
     * POST  /interviews : Create a new interview.
     *
     * @param interview the interview to create
     * @return the ResponseEntity with status 201 (Created) and with body the new interview, or with status 400 (Bad Request) if the interview has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/interviews")
    @Timed
    @PreAuthorize("hasAuthority('" + AuthoritiesConstants.CONPEC_USER + "')")
    public ResponseEntity<Interview> createInterview(@RequestBody InterviewDTO interview) throws URISyntaxException {
        log.debug("REST request to save Interview : {}", interview);
        if (interview.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert("interview", "idexists", "A new interview cannot already have an ID")).body(null);
        }
        Optional<User> loggedUser = userRepository.findOneByLogin(SecurityUtils.getCurrentUserLogin());
        if(!loggedUser.isPresent()) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(null);
        }
        interview.setInterviewer(loggedUser.get());

        Interview result = interviewService.create(interview);
        return ResponseEntity.created(new URI("/api/interviews/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert("interview", result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /interviews : Updates an existing interview.
     *
     * @param interview the interview to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated interview,
     * or with status 400 (Bad Request) if the interview is not valid,
     * or with status 500 (Internal Server Error) if the interview couldnt be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/interviews")
    @Timed
    @PreAuthorize("hasAuthority('" + AuthoritiesConstants.CONPEC_USER + "')")
    public ResponseEntity<Interview> updateInterview(@RequestBody Interview interview) throws URISyntaxException {
        log.debug("REST request to update Interview : {}", interview);
        Optional<User> loggedUser = userRepository.findOneByLogin(SecurityUtils.getCurrentUserLogin());
        if(!loggedUser.isPresent()) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(null);
        }
        interview.setInterviewer(loggedUser.get());

        Interview result = interviewService.update(interview);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert("interview", interview.getId().toString()))
            .body(result);
    }

    /**
     * GET  /interviews : get all the interviews.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of interviews in body
     */
    @GetMapping("/interviews")
    @Timed
    @PreAuthorize("hasAuthority('" + AuthoritiesConstants.CONPEC_USER + "')")
    public List<Interview> getAllInterviews() {
        log.debug("REST request to get all Interviews");
        List<Interview> interviews = interviewRepository.findAll();
        return interviews;
    }

    /**
     * GET  /interviews/:id : get the "id" interview.
     *
     * @param id the id of the interview to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the interview, or with status 404 (Not Found)
     */
    @GetMapping("/interviews/{id}")
    @Timed
    @PreAuthorize("hasAuthority('" + AuthoritiesConstants.CONPEC_USER + "')")
    public ResponseEntity<Interview> getInterview(@PathVariable Long id) {
        log.debug("REST request to get Interview : {}", id);
        Interview interview = interviewRepository.findOne(id);
        return Optional.ofNullable(interview)
            .map(result -> new ResponseEntity<>(
                result,
                HttpStatus.OK))
            .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    /**
     * DELETE  /interviews/:id : delete the "id" interview.
     *
     * @param id the id of the interview to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/interviews/{id}")
    @Timed
    @PreAuthorize("hasAuthority('" + AuthoritiesConstants.CONPEC_USER + "')")
    public ResponseEntity<Void> deleteInterview(@PathVariable Long id) {
        log.debug("REST request to delete Interview : {}", id);
        interviewRepository.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert("interview", id.toString())).build();
    }

}
