package br.com.unicamp.sade.web.rest;

import br.com.unicamp.sade.SadeApp;
import br.com.unicamp.sade.domain.Interview;
import br.com.unicamp.sade.repository.InterviewRepository;
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
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Teste class for the InterviewResource REST controller.
 *
 * @see InterviewResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = SadeApp.class)
public class InterviewResourceIntTest {

    private static final Integer DEFAULT_HOUR_VALUE = 1;
    private static final Integer UPDATED_HOUR_VALUE = 2;

    @Inject
    private InterviewRepository interviewRepository;

    @Inject
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Inject
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Inject
    private EntityManager em;

    private MockMvc restInterviewMockMvc;

    private Interview interview;

    @PostConstruct
    public void setup() {
        MockitoAnnotations.initMocks(this);
        InterviewResource interviewResource = new InterviewResource();
        ReflectionTestUtils.setField(interviewResource, "interviewRepository", interviewRepository);
        this.restInterviewMockMvc = MockMvcBuilders.standaloneSetup(interviewResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setMessageConverters(jacksonMessageConverter).build();
    }

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Interview createEntity(EntityManager em) {
        Interview interview = new Interview()
                .hourValue(DEFAULT_HOUR_VALUE);
        return interview;
    }

    @Before
    public void initTest() {
        interview = createEntity(em);
    }

    @Test
    @Transactional
    public void getAllInterviews() throws Exception {
        // Initialize the database
        interviewRepository.saveAndFlush(interview);

        // Get all the interviews
        restInterviewMockMvc.perform(get("/api/interviews?sort=id,desc"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(jsonPath("$.[*].id").value(hasItem(interview.getId().intValue())))
                .andExpect(jsonPath("$.[*].hourValue").value(hasItem(DEFAULT_HOUR_VALUE)));
    }

    @Test
    @Transactional
    public void getInterview() throws Exception {
        // Initialize the database
        interviewRepository.saveAndFlush(interview);

        // Get the interview
        restInterviewMockMvc.perform(get("/api/interviews/{id}", interview.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(interview.getId().intValue()))
            .andExpect(jsonPath("$.hourValue").value(DEFAULT_HOUR_VALUE));
    }

    @Test
    @Transactional
    public void getNonExistingInterview() throws Exception {
        // Get the interview
        restInterviewMockMvc.perform(get("/api/interviews/{id}", Long.MAX_VALUE))
                .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void deleteInterview() throws Exception {
        // Initialize the database
        interviewRepository.saveAndFlush(interview);
        int databaseSizeBeforeDelete = interviewRepository.findAll().size();

        // Get the interview
        restInterviewMockMvc.perform(delete("/api/interviews/{id}", interview.getId())
                .accept(TestUtil.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk());

        // Validate the database is empty
        List<Interview> interviews = interviewRepository.findAll();
        assertThat(interviews).hasSize(databaseSizeBeforeDelete - 1);
    }
}
