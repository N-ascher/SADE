package br.com.unicamp.sade.web.rest;

import br.com.unicamp.sade.SadeApp;

import br.com.unicamp.sade.domain.InterviewQuestion;
import br.com.unicamp.sade.repository.InterviewQuestionRepository;
import br.com.unicamp.sade.service.InterviewQuestionService;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import static org.hamcrest.Matchers.hasItem;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
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
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Test class for the InterviewQuestionResource REST controller.
 *
 * @see InterviewQuestionResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = SadeApp.class)
public class InterviewQuestionResourceIntTest {

    private static final String DEFAULT_QUESTION_TITLE = "AAAAA";
    private static final String UPDATED_QUESTION_TITLE = "BBBBB";

    private static final String DEFAULT_RESPONSE = "AAAAA";
    private static final String UPDATED_RESPONSE = "BBBBB";

    @Inject
    private InterviewQuestionRepository interviewQuestionRepository;

    @Inject
    private InterviewQuestionService interviewQuestionService;

    @Inject
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Inject
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Inject
    private EntityManager em;

    private MockMvc restInterviewQuestionMockMvc;

    private InterviewQuestion interviewQuestion;

    @PostConstruct
    public void setup() {
        MockitoAnnotations.initMocks(this);
        InterviewQuestionResource interviewQuestionResource = new InterviewQuestionResource();
        ReflectionTestUtils.setField(interviewQuestionResource, "interviewQuestionService", interviewQuestionService);
        this.restInterviewQuestionMockMvc = MockMvcBuilders.standaloneSetup(interviewQuestionResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setMessageConverters(jacksonMessageConverter).build();
    }

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static InterviewQuestion createEntity(EntityManager em) {
        InterviewQuestion interviewQuestion = new InterviewQuestion()
                .questionTitle(DEFAULT_QUESTION_TITLE)
                .response(DEFAULT_RESPONSE);
        return interviewQuestion;
    }

    @Before
    public void initTest() {
        interviewQuestion = createEntity(em);
    }

    @Test
    @Transactional
    public void createInterviewQuestion() throws Exception {
        int databaseSizeBeforeCreate = interviewQuestionRepository.findAll().size();

        // Create the InterviewQuestion

        restInterviewQuestionMockMvc.perform(post("/api/interview-questions")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(interviewQuestion)))
                .andExpect(status().isCreated());

        // Validate the InterviewQuestion in the database
        List<InterviewQuestion> interviewQuestions = interviewQuestionRepository.findAll();
        assertThat(interviewQuestions).hasSize(databaseSizeBeforeCreate + 1);
        InterviewQuestion testInterviewQuestion = interviewQuestions.get(interviewQuestions.size() - 1);
        assertThat(testInterviewQuestion.getQuestionTitle()).isEqualTo(DEFAULT_QUESTION_TITLE);
        assertThat(testInterviewQuestion.getResponse()).isEqualTo(DEFAULT_RESPONSE);
    }

    @Test
    @Transactional
    public void getAllInterviewQuestions() throws Exception {
        // Initialize the database
        interviewQuestionRepository.saveAndFlush(interviewQuestion);

        // Get all the interviewQuestions
        restInterviewQuestionMockMvc.perform(get("/api/interview-questions?sort=id,desc"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(jsonPath("$.[*].id").value(hasItem(interviewQuestion.getId().intValue())))
                .andExpect(jsonPath("$.[*].questionTitle").value(hasItem(DEFAULT_QUESTION_TITLE.toString())))
                .andExpect(jsonPath("$.[*].response").value(hasItem(DEFAULT_RESPONSE.toString())));
    }

    @Test
    @Transactional
    public void getInterviewQuestion() throws Exception {
        // Initialize the database
        interviewQuestionRepository.saveAndFlush(interviewQuestion);

        // Get the interviewQuestion
        restInterviewQuestionMockMvc.perform(get("/api/interview-questions/{id}", interviewQuestion.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(interviewQuestion.getId().intValue()))
            .andExpect(jsonPath("$.questionTitle").value(DEFAULT_QUESTION_TITLE.toString()))
            .andExpect(jsonPath("$.response").value(DEFAULT_RESPONSE.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingInterviewQuestion() throws Exception {
        // Get the interviewQuestion
        restInterviewQuestionMockMvc.perform(get("/api/interview-questions/{id}", Long.MAX_VALUE))
                .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateInterviewQuestion() throws Exception {
        // Initialize the database
        interviewQuestionService.save(interviewQuestion);

        int databaseSizeBeforeUpdate = interviewQuestionRepository.findAll().size();

        // Update the interviewQuestion
        InterviewQuestion updatedInterviewQuestion = interviewQuestionRepository.findOne(interviewQuestion.getId());
        updatedInterviewQuestion
                .questionTitle(UPDATED_QUESTION_TITLE)
                .response(UPDATED_RESPONSE);

        restInterviewQuestionMockMvc.perform(put("/api/interview-questions")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(updatedInterviewQuestion)))
                .andExpect(status().isOk());

        // Validate the InterviewQuestion in the database
        List<InterviewQuestion> interviewQuestions = interviewQuestionRepository.findAll();
        assertThat(interviewQuestions).hasSize(databaseSizeBeforeUpdate);
        InterviewQuestion testInterviewQuestion = interviewQuestions.get(interviewQuestions.size() - 1);
        assertThat(testInterviewQuestion.getQuestionTitle()).isEqualTo(UPDATED_QUESTION_TITLE);
        assertThat(testInterviewQuestion.getResponse()).isEqualTo(UPDATED_RESPONSE);
    }

    @Test
    @Transactional
    public void deleteInterviewQuestion() throws Exception {
        // Initialize the database
        interviewQuestionService.save(interviewQuestion);

        int databaseSizeBeforeDelete = interviewQuestionRepository.findAll().size();

        // Get the interviewQuestion
        restInterviewQuestionMockMvc.perform(delete("/api/interview-questions/{id}", interviewQuestion.getId())
                .accept(TestUtil.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk());

        // Validate the database is empty
        List<InterviewQuestion> interviewQuestions = interviewQuestionRepository.findAll();
        assertThat(interviewQuestions).hasSize(databaseSizeBeforeDelete - 1);
    }
}
