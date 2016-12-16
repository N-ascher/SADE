package br.com.unicamp.sade.domain;


import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A InterviewQuestion.
 */
@Entity
@Table(name = "interview_question")
public class InterviewQuestion implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "question_title")
    private String questionTitle;

    @Column(name = "response")
    private String response;

    @ManyToOne
    @JsonIgnore
    private PreDefinedQuestion question;

    @ManyToOne
    @JsonIgnore
    private Interview interview;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getQuestionTitle() {
        return questionTitle;
    }

    public InterviewQuestion questionTitle(String questionTitle) {
        this.questionTitle = questionTitle;
        return this;
    }

    public void setQuestionTitle(String questionTitle) {
        this.questionTitle = questionTitle;
    }

    public String getResponse() {
        return response;
    }

    public InterviewQuestion response(String response) {
        this.response = response;
        return this;
    }

    public void setResponse(String response) {
        this.response = response;
    }

    public PreDefinedQuestion getQuestion() {
        return question;
    }

    public InterviewQuestion question(PreDefinedQuestion preDefinedQuestion) {
        this.question = preDefinedQuestion;
        return this;
    }

    public void setInterview(Interview interview) {
        this.interview = interview;
    }

    public Interview getInterview() {
        return interview;
    }

    public InterviewQuestion interview(Interview interview) {
        this.interview = interview;
        return this;
    }

    public void setQuestion(PreDefinedQuestion preDefinedQuestion) {
        this.question = preDefinedQuestion;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        InterviewQuestion interviewQuestion = (InterviewQuestion) o;
        if(interviewQuestion.id == null || id == null) {
            return false;
        }
        return Objects.equals(id, interviewQuestion.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "InterviewQuestion{" +
            "id=" + id +
            ", questionTitle='" + questionTitle + "'" +
            ", response='" + response + "'" +
            '}';
    }
}
