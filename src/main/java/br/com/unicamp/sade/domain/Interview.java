package br.com.unicamp.sade.domain;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

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
    private User interviewer;

    @OneToMany(mappedBy = "interview", cascade = CascadeType.ALL, fetch=FetchType.EAGER)
    private Set<Comment> comments = new HashSet<>();

    @OneToMany(mappedBy = "interview", cascade = CascadeType.ALL, fetch= FetchType.EAGER)
    private Set<InterviewQuestion> questions;

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

    public User getInterviewer() {
        return interviewer;
    }

    public Interview interviewerId(User user) {
        this.interviewer = user;
        return this;
    }

    public void setInterviewer(User user) {
        this.interviewer = user;
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

    public Set<InterviewQuestion> getQuestions() {
        return questions;
    }

    public Interview questions(Set<InterviewQuestion> interviewQuestion) {
        this.questions = interviewQuestion;
        return this;
    }

    public void setQuestions(Set<InterviewQuestion> interviewQuestion) {
        this.questions = interviewQuestion;
    }

    public Interview addInterviewQuestion(InterviewQuestion interviewQuestion) {
        questions.add(interviewQuestion);
        interviewQuestion.setInterview(this);
        return this;
    }

    public Interview removeInterviewQuestion(InterviewQuestion interviewQuestion) {
        questions.remove(interviewQuestion);
        interviewQuestion.setInterview(null);
        return this;
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
