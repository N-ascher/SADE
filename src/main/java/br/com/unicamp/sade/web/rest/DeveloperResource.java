package br.com.unicamp.sade.web.rest;

import br.com.unicamp.sade.domain.Developer;
import br.com.unicamp.sade.domain.User;
import br.com.unicamp.sade.repository.DeveloperRepository;
import br.com.unicamp.sade.repository.UserRepository;
import br.com.unicamp.sade.security.AuthoritiesConstants;
import br.com.unicamp.sade.service.DeveloperService;
import br.com.unicamp.sade.service.MailService;
import br.com.unicamp.sade.service.UserService;
import br.com.unicamp.sade.service.dto.DeveloperDTO;
import br.com.unicamp.sade.web.rest.util.HeaderUtil;
import br.com.unicamp.sade.web.rest.util.PaginationUtil;
import br.com.unicamp.sade.web.rest.vm.ManagedUserVM;
import com.codahale.metrics.annotation.Timed;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing Developer.
 */
@RestController
@RequestMapping("/api/developers")
public class DeveloperResource {

    private final Logger log = LoggerFactory.getLogger(DeveloperResource.class);

    @Inject
    private DeveloperRepository developerRepository;

    @Inject
    private DeveloperService developerService;

    @Inject
    private UserRepository userRepository;

    @Inject
    private UserService userService;

    @Inject
    private MailService mailService;

    /**
     * POST  /developers/register : register the user.
     *
     * @param developerDTO the new developer
     * @param request the HTTP request
     * @return the ResponseEntity with status 201 (Created) if the user is registered or 400 (Bad Request) if the login or e-mail is already in use
     */
    @PostMapping(path = "/register",
            produces={MediaType.APPLICATION_JSON_VALUE, MediaType.TEXT_PLAIN_VALUE})
    @Timed
    public ResponseEntity<?> register(@Valid @RequestBody DeveloperDTO developerDTO, HttpServletRequest request) {

        HttpHeaders textPlainHeaders = new HttpHeaders();
        textPlainHeaders.setContentType(MediaType.TEXT_PLAIN);

        ManagedUserVM managedUserVM = developerDTO.getUser();
        return userRepository.findOneByLogin(managedUserVM.getLogin().toLowerCase())
                .map(user -> new ResponseEntity<>("login already in use", textPlainHeaders, HttpStatus.BAD_REQUEST))
                .orElseGet(() -> userRepository.findOneByEmail(managedUserVM.getEmail())
                        .map(user -> new ResponseEntity<>("e-mail address already in use", textPlainHeaders, HttpStatus.BAD_REQUEST))
                        .orElseGet(() -> {
                            User user = userService.createUser(managedUserVM.getLogin(), managedUserVM.getPassword(),
                                    managedUserVM.getFirstName(), managedUserVM.getLastName(), managedUserVM.getEmail().toLowerCase(),
                                    managedUserVM.getLangKey());
                            developerService.createDeveloper(developerDTO.getPhoneNumber(), developerDTO.getMobileNumber(),
                                    developerDTO.getDocument(), developerDTO.getLinkedIn(), developerDTO.getGitHub(),
                                    developerDTO.getAvailability(), developerDTO.getProspectedBy(), developerDTO.getAddress(),
                                    developerDTO.getTechnologies(), user);

                            String baseUrl = request.getScheme() + // "http"
                                    "://" +                                // "://"
                                    request.getServerName() +              // "myhost"
                                    ":" +                                  // ":"
                                    request.getServerPort() +              // "80"
                                    request.getContextPath();              // "/myContextPath" or "" if deployed in root context

                            mailService.sendActivationEmail(user, baseUrl);
                            return new ResponseEntity<>(HttpStatus.CREATED);
                        })
                );
    }
    /**
     * POST  /developers : Create a new developer.
     *
     * @param developer the developer to create
     * @return the ResponseEntity with status 201 (Created) and with body the new developer, or with status 400 (Bad Request) if the developer has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("")
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
    @PutMapping("")
    @Timed
    @Secured(AuthoritiesConstants.USER)
    public ResponseEntity<Developer> updateDeveloper(@RequestBody Developer developer) throws URISyntaxException {
        log.debug("REST request to update Developer : {}", developer);
        if (developer.getId() == null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert("developerManagement", "noID", "The developer does not exist.")).body(null);
        }
        developerService.updateDeveloper(developer.getId(),
            developer.getPhoneNumber(),
            developer.getMobileNumber(),
            developer.getDocument(),
            developer.getLinkedIn(),
            developer.getGitHub(),
            developer.getAvailability(),
            developer.getProspectedBy(),
            developer.getAddress(),
            developer.getTechnologies()
        );
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert("Developer has been updated", developer.getId().toString()))
            .body(developer);
    }

    /**
     * GET  /developers : get all the developers.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of developers in body
     * @throws URISyntaxException if there is an error to generate the pagination HTTP headers
     */
    @GetMapping("")
    @Timed
    @Secured(AuthoritiesConstants.ADMIN)
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
    @GetMapping("/{id}")
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
    @DeleteMapping("/{id}")
    @Timed
    @Secured(AuthoritiesConstants.ADMIN)
    public ResponseEntity<Void> deleteDeveloper(@PathVariable Long id) {
        log.debug("REST request to delete Developer : {}", id);
        developerRepository.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert("developer", id.toString())).build();
    }

}
