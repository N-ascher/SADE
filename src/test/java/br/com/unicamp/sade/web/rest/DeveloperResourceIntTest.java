package br.com.unicamp.sade.web.rest;

import br.com.unicamp.sade.SadeApp;
import br.com.unicamp.sade.domain.Developer;
import br.com.unicamp.sade.repository.DeveloperRepository;
import br.com.unicamp.sade.service.DeveloperService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Teste class for the DeveloperResource REST controller.
 *
 * @see DeveloperResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = SadeApp.class)
public class DeveloperResourceIntTest {

    private static final String DEFAULT_PHONE_NUMBER = "AAAAA";
    private static final String UPDATED_PHONE_NUMBER = "BBBBB";

    private static final String DEFAULT_MOBILE_NUMBER = "AAAAA";
    private static final String UPDATED_MOBILE_NUMBER = "BBBBB";

    private static final String DEFAULT_DOCUMENT = "AAAAA";
    private static final String UPDATED_DOCUMENT = "BBBBB";

    private static final String DEFAULT_LINKED_IN = "https://www.linkedin.com/";
    private static final String UPDATED_LINKED_IN = "https://www.linkedin.com/name";

    private static final String DEFAULT_GIT_HUB = "https://github.com/";
    private static final String UPDATED_GIT_HUB = "https://github.com/name";

    private static final Integer DEFAULT_AVAILABILITY = 1;
    private static final Integer UPDATED_AVAILABILITY = 2;

    private static final String DEFAULT_PROSPECTED_BY = "AAAAA";
    private static final String UPDATED_PROSPECTED_BY = "BBBBB";

    @Inject
    private DeveloperRepository developerRepository;

    @Inject
    private DeveloperService developerService;

    @Inject
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Inject
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Inject
    private EntityManager em;

    private MockMvc restDeveloperMockMvc;

    private Developer developer;

    @PostConstruct
    public void setup() {
        MockitoAnnotations.initMocks(this);
        DeveloperResource developerResource = new DeveloperResource();
        ReflectionTestUtils.setField(developerResource, "developerRepository", developerRepository);
        ReflectionTestUtils.setField(developerResource, "developerService", developerService);
        this.restDeveloperMockMvc = MockMvcBuilders.standaloneSetup(developerResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setMessageConverters(jacksonMessageConverter).build();
    }

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Developer createEntity(EntityManager em) {
        Developer developer = new Developer()
                .phoneNumber(DEFAULT_PHONE_NUMBER)
                .mobileNumber(DEFAULT_MOBILE_NUMBER)
                .document(DEFAULT_DOCUMENT)
                .linkedIn(DEFAULT_LINKED_IN)
                .gitHub(DEFAULT_GIT_HUB)
                .availability(DEFAULT_AVAILABILITY)
                .prospectedBy(DEFAULT_PROSPECTED_BY);
        return developer;
    }

    @Before
    public void initTest() {
        developer = createEntity(em);
    }

    @Test
    @Transactional
    public void createDeveloper() throws Exception {
        int databaseSizeBeforeCreate = developerRepository.findAll().size();

        // Create the Developer

        restDeveloperMockMvc.perform(post("/api/developers")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(developer)))
                .andExpect(status().isCreated());

        // Validate the Developer in the database
        List<Developer> developers = developerRepository.findAll();
        assertThat(developers).hasSize(databaseSizeBeforeCreate + 1);
        Developer testDeveloper = developers.get(developers.size() - 1);
        assertThat(testDeveloper.getPhoneNumber()).isEqualTo(DEFAULT_PHONE_NUMBER);
        assertThat(testDeveloper.getMobileNumber()).isEqualTo(DEFAULT_MOBILE_NUMBER);
        assertThat(testDeveloper.getDocument()).isEqualTo(DEFAULT_DOCUMENT);
        assertThat(testDeveloper.getLinkedIn()).isEqualTo(DEFAULT_LINKED_IN);
        assertThat(testDeveloper.getGitHub()).isEqualTo(DEFAULT_GIT_HUB);
        assertThat(testDeveloper.getAvailability()).isEqualTo(DEFAULT_AVAILABILITY);
        assertThat(testDeveloper.getProspectedBy()).isEqualTo(DEFAULT_PROSPECTED_BY);
    }

    @Test
    @Transactional
    public void getAllDevelopers() throws Exception {
        // Initialize the database
        developerRepository.saveAndFlush(developer);

        // Get all the developers
        restDeveloperMockMvc.perform(get("/api/developers?sort=id,desc"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(jsonPath("$.[*].id").value(hasItem(developer.getId().intValue())))
                .andExpect(jsonPath("$.[*].phoneNumber").value(hasItem(DEFAULT_PHONE_NUMBER.toString())))
                .andExpect(jsonPath("$.[*].mobileNumber").value(hasItem(DEFAULT_MOBILE_NUMBER.toString())))
                .andExpect(jsonPath("$.[*].document").value(hasItem(DEFAULT_DOCUMENT.toString())))
                .andExpect(jsonPath("$.[*].linkedIn").value(hasItem(DEFAULT_LINKED_IN.toString())))
                .andExpect(jsonPath("$.[*].gitHub").value(hasItem(DEFAULT_GIT_HUB.toString())))
                .andExpect(jsonPath("$.[*].availability").value(hasItem(DEFAULT_AVAILABILITY)))
                .andExpect(jsonPath("$.[*].prospectedBy").value(hasItem(DEFAULT_PROSPECTED_BY.toString())));
    }

    @Test
    @Transactional
    public void getDeveloper() throws Exception {
        // Initialize the database
        developerRepository.saveAndFlush(developer);

        // Get the developer
        restDeveloperMockMvc.perform(get("/api/developers/{id}", developer.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(developer.getId().intValue()))
            .andExpect(jsonPath("$.phoneNumber").value(DEFAULT_PHONE_NUMBER.toString()))
            .andExpect(jsonPath("$.mobileNumber").value(DEFAULT_MOBILE_NUMBER.toString()))
            .andExpect(jsonPath("$.document").value(DEFAULT_DOCUMENT.toString()))
            .andExpect(jsonPath("$.linkedIn").value(DEFAULT_LINKED_IN.toString()))
            .andExpect(jsonPath("$.gitHub").value(DEFAULT_GIT_HUB.toString()))
            .andExpect(jsonPath("$.availability").value(DEFAULT_AVAILABILITY))
            .andExpect(jsonPath("$.prospectedBy").value(DEFAULT_PROSPECTED_BY.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingDeveloper() throws Exception {
        // Get the developer
        restDeveloperMockMvc.perform(get("/api/developers/{id}", Long.MAX_VALUE))
                .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateDeveloper() throws Exception {
        // Initialize the database
        developerRepository.saveAndFlush(developer);
        int databaseSizeBeforeUpdate = developerRepository.findAll().size();

        // Update the developer
        Developer updatedDeveloper = developerRepository.findOne(developer.getId());
        updatedDeveloper
                .phoneNumber(UPDATED_PHONE_NUMBER)
                .mobileNumber(UPDATED_MOBILE_NUMBER)
                .document(UPDATED_DOCUMENT)
                .linkedIn(UPDATED_LINKED_IN)
                .gitHub(UPDATED_GIT_HUB)
                .availability(UPDATED_AVAILABILITY)
                .prospectedBy(UPDATED_PROSPECTED_BY);

        restDeveloperMockMvc.perform(put("/api/developers")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(updatedDeveloper)))
                .andExpect(status().isOk());

        // Validate the Developer in the database
        List<Developer> developers = developerRepository.findAll();
        assertThat(developers).hasSize(databaseSizeBeforeUpdate);
        Developer testDeveloper = developers.get(developers.size() - 1);
        assertThat(testDeveloper.getPhoneNumber()).isEqualTo(UPDATED_PHONE_NUMBER);
        assertThat(testDeveloper.getMobileNumber()).isEqualTo(UPDATED_MOBILE_NUMBER);
        assertThat(testDeveloper.getDocument()).isEqualTo(UPDATED_DOCUMENT);
        assertThat(testDeveloper.getLinkedIn()).isEqualTo(UPDATED_LINKED_IN);
        assertThat(testDeveloper.getGitHub()).isEqualTo(UPDATED_GIT_HUB);
        assertThat(testDeveloper.getAvailability()).isEqualTo(UPDATED_AVAILABILITY);
        assertThat(testDeveloper.getProspectedBy()).isEqualTo(UPDATED_PROSPECTED_BY);
    }

    @Test
    @Transactional
    public void deleteDeveloper() throws Exception {
        // Initialize the database
        developerRepository.saveAndFlush(developer);
        int databaseSizeBeforeDelete = developerRepository.findAll().size();

        // Get the developer
        restDeveloperMockMvc.perform(delete("/api/developers/{id}", developer.getId())
                .accept(TestUtil.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk());

        // Validate the database is empty
        List<Developer> developers = developerRepository.findAll();
        assertThat(developers).hasSize(databaseSizeBeforeDelete - 1);
    }
}
