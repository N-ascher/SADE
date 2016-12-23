package br.com.unicamp.sade.service.dto;

import br.com.unicamp.sade.domain.Comment;
import br.com.unicamp.sade.domain.InterviewQuestion;
import br.com.unicamp.sade.domain.User;

import java.util.HashSet;
import java.util.Set;

/**
 * A DTO representing an interview
 */
public class InterviewDTO {

    private Long id;

    private Integer hourValue;

    private User interviewer;

    private Developer developer;

    private Set<Comment> comments = new HashSet<>();

    private Set<InterviewQuestion> questions = new HashSet<>();

    public class Developer {
        private Long id;

        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getHourValue() {
        return hourValue;
    }

    public void setHourValue(Integer hourValue) {
        this.hourValue = hourValue;
    }

    public User getInterviewer() {
        return interviewer;
    }

    public void setInterviewer(User interviewer) {
        this.interviewer = interviewer;
    }

    public Developer getDeveloper() {
        return developer;
    }

    public void setDeveloper(Developer developer) {
        this.developer = developer;
    }

    public Set<Comment> getComments() {
        return comments;
    }

    public void setComments(Set<Comment> comments) {
        this.comments = comments;
    }

    public Set<InterviewQuestion> getQuestions() {
        return questions;
    }

    public void setQuestions(Set<InterviewQuestion> questions) {
        this.questions = questions;
    }
}
