package br.com.unicamp.sade.domain;


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
    private PreDefinedQuestion questionId;

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

    public PreDefinedQuestion getQuestionId() {
        return questionId;
    }

    public InterviewQuestion questionId(PreDefinedQuestion preDefinedQuestion) {
        this.questionId = preDefinedQuestion;
        return this;
    }

    public void setQuestionId(PreDefinedQuestion preDefinedQuestion) {
        this.questionId = preDefinedQuestion;
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
