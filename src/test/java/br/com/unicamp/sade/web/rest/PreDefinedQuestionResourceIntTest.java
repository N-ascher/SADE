package br.com.unicamp.sade.web.rest;

import br.com.unicamp.sade.SadeApp;

import br.com.unicamp.sade.domain.PreDefinedQuestion;
import br.com.unicamp.sade.repository.PreDefinedQuestionRepository;

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
 * Teste class for the PreDefinedQuestionResource REST controller.
 *
 * @see PreDefinedQuestionResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = SadeApp.class)
public class PreDefinedQuestionResourceIntTest {

    private static final String DEFAULT_TITLE = "AAAAA";
    private static final String UPDATED_TITLE = "BBBBB";

    @Inject
    private PreDefinedQuestionRepository preDefinedQuestionRepository;

    @Inject
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Inject
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Inject
    private EntityManager em;

    private MockMvc restPreDefinedQuestionMockMvc;

    private PreDefinedQuestion preDefinedQuestion;

    @PostConstruct
    public void setup() {
        MockitoAnnotations.initMocks(this);
        PreDefinedQuestionResource preDefinedQuestionResource = new PreDefinedQuestionResource();
        ReflectionTestUtils.setField(preDefinedQuestionResource, "preDefinedQuestionRepository", preDefinedQuestionRepository);
        this.restPreDefinedQuestionMockMvc = MockMvcBuilders.standaloneSetup(preDefinedQuestionResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setMessageConverters(jacksonMessageConverter).build();
    }

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static PreDefinedQuestion createEntity(EntityManager em) {
        PreDefinedQuestion preDefinedQuestion = new PreDefinedQuestion()
                .title(DEFAULT_TITLE);
        return preDefinedQuestion;
    }

    @Before
    public void initTest() {
        preDefinedQuestion = createEntity(em);
    }

    @Test
    @Transactional
    public void createPreDefinedQuestion() throws Exception {
        int databaseSizeBeforeCreate = preDefinedQuestionRepository.findAll().size();

        // Create the PreDefinedQuestion

        restPreDefinedQuestionMockMvc.perform(post("/api/pre-defined-questions")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(preDefinedQuestion)))
                .andExpect(status().isCreated());

        // Validate the PreDefinedQuestion in the database
        List<PreDefinedQuestion> preDefinedQuestions = preDefinedQuestionRepository.findAll();
        assertThat(preDefinedQuestions).hasSize(databaseSizeBeforeCreate + 1);
        PreDefinedQuestion testPreDefinedQuestion = preDefinedQuestions.get(preDefinedQuestions.size() - 1);
        assertThat(testPreDefinedQuestion.getTitle()).isEqualTo(DEFAULT_TITLE);
    }

    @Test
    @Transactional
    public void getAllPreDefinedQuestions() throws Exception {
        // Initialize the database
        preDefinedQuestionRepository.saveAndFlush(preDefinedQuestion);

        // Get all the preDefinedQuestions
        restPreDefinedQuestionMockMvc.perform(get("/api/pre-defined-questions?sort=id,desc"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(jsonPath("$.[*].id").value(hasItem(preDefinedQuestion.getId().intValue())))
                .andExpect(jsonPath("$.[*].title").value(hasItem(DEFAULT_TITLE.toString())));
    }

    @Test
    @Transactional
    public void getPreDefinedQuestion() throws Exception {
        // Initialize the database
        preDefinedQuestionRepository.saveAndFlush(preDefinedQuestion);

        // Get the preDefinedQuestion
        restPreDefinedQuestionMockMvc.perform(get("/api/pre-defined-questions/{id}", preDefinedQuestion.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(preDefinedQuestion.getId().intValue()))
            .andExpect(jsonPath("$.title").value(DEFAULT_TITLE.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingPreDefinedQuestion() throws Exception {
        // Get the preDefinedQuestion
        restPreDefinedQuestionMockMvc.perform(get("/api/pre-defined-questions/{id}", Long.MAX_VALUE))
                .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updatePreDefinedQuestion() throws Exception {
        // Initialize the database
        preDefinedQuestionRepository.saveAndFlush(preDefinedQuestion);
        int databaseSizeBeforeUpdate = preDefinedQuestionRepository.findAll().size();

        // Update the preDefinedQuestion
        PreDefinedQuestion updatedPreDefinedQuestion = preDefinedQuestionRepository.findOne(preDefinedQuestion.getId());
        updatedPreDefinedQuestion
                .title(UPDATED_TITLE);

        restPreDefinedQuestionMockMvc.perform(put("/api/pre-defined-questions")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(updatedPreDefinedQuestion)))
                .andExpect(status().isOk());

        // Validate the PreDefinedQuestion in the database
        List<PreDefinedQuestion> preDefinedQuestions = preDefinedQuestionRepository.findAll();
        assertThat(preDefinedQuestions).hasSize(databaseSizeBeforeUpdate);
        PreDefinedQuestion testPreDefinedQuestion = preDefinedQuestions.get(preDefinedQuestions.size() - 1);
        assertThat(testPreDefinedQuestion.getTitle()).isEqualTo(UPDATED_TITLE);
    }

    @Test
    @Transactional
    public void deletePreDefinedQuestion() throws Exception {
        // Initialize the database
        preDefinedQuestionRepository.saveAndFlush(preDefinedQuestion);
        int databaseSizeBeforeDelete = preDefinedQuestionRepository.findAll().size();

        // Get the preDefinedQuestion
        restPreDefinedQuestionMockMvc.perform(delete("/api/pre-defined-questions/{id}", preDefinedQuestion.getId())
                .accept(TestUtil.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk());

        // Validate the database is empty
        List<PreDefinedQuestion> preDefinedQuestions = preDefinedQuestionRepository.findAll();
        assertThat(preDefinedQuestions).hasSize(databaseSizeBeforeDelete - 1);
    }
}
