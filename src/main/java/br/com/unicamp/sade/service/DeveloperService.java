package br.com.unicamp.sade.service;

import br.com.unicamp.sade.domain.Address;
import br.com.unicamp.sade.domain.Developer;
import br.com.unicamp.sade.domain.Technology;
import br.com.unicamp.sade.domain.User;
import br.com.unicamp.sade.repository.DeveloperRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import java.util.Set;

/**
 * Service class for managing developers.
 */
@Service
@Transactional
public class DeveloperService {

    private final Logger log = LoggerFactory.getLogger(DeveloperService.class);

    @Inject
    private DeveloperRepository developerRepository;

    public Developer createDeveloper(String phoneNumber,
                                     String mobileNumber,
                                     String document,
                                     String linkedIn,
                                     String gitHub,
                                     Integer availability,
                                     String prospectedBy,
                                     Address address,
                                     Set<Technology> technologies,
                                     User user) {
        Developer developer = new Developer();
        developer.setPhoneNumber(phoneNumber);
        developer.setMobileNumber(mobileNumber);
        developer.setDocument(document);
        developer.setLinkedIn(linkedIn);
        developer.setGitHub(gitHub);
        developer.setAvailability(availability);
        developer.setProspectedBy(prospectedBy);
        developer.setAddress(address);
        developer.setTechnologies(technologies);
        developer.setUser(user);

        technologies.forEach(technology -> technology.setDeveloper(developer));

        Developer created = developerRepository.save(developer);
        log.debug("Created information: Developer={}", created);
        return created;
    }

}
