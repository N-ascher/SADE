package br.com.unicamp.sade.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * A Interview.
 */
@Entity
@Table(name = "interview")
public class Interview implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "hour_value")
    private Integer hourValue;

    @OneToOne
    @JoinColumn(unique = true)
    private User interviewerId;

    @OneToMany(mappedBy = "interview")
    @JsonIgnore
    private Set<Comment> comments = new HashSet<>();

    @ManyToOne
    private InterviewQuestion questions;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getHourValue() {
        return hourValue;
    }

    public Interview hourValue(Integer hourValue) {
        this.hourValue = hourValue;
        return this;
    }

    public void setHourValue(Integer hourValue) {
        this.hourValue = hourValue;
    }

    public User getInterviewerId() {
        return interviewerId;
    }

    public Interview interviewerId(User user) {
        this.interviewerId = user;
        return this;
    }

    public void setInterviewerId(User user) {
        this.interviewerId = user;
    }

    public Set<Comment> getComments() {
        return comments;
    }

    public Interview comments(Set<Comment> comments) {
        this.comments = comments;
        return this;
    }

    public Interview addComments(Comment comment) {
        comments.add(comment);
        comment.setInterview(this);
        return this;
    }

    public Interview removeComments(Comment comment) {
        comments.remove(comment);
        comment.setInterview(null);
        return this;
    }

    public void setComments(Set<Comment> comments) {
        this.comments = comments;
    }

    public InterviewQuestion getQuestions() {
        return questions;
    }

    public Interview questions(InterviewQuestion interviewQuestion) {
        this.questions = interviewQuestion;
        return this;
    }

    public void setQuestions(InterviewQuestion interviewQuestion) {
        this.questions = interviewQuestion;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Interview interview = (Interview) o;
        if(interview.id == null || id == null) {
            return false;
        }
        return Objects.equals(id, interview.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "Interview{" +
            "id=" + id +
            ", hourValue='" + hourValue + "'" +
            '}';
    }
}
