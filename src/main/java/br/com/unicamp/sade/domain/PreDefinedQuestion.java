package br.com.unicamp.sade.domain;


import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A PreDefinedQuestion.
 */
@Entity
@Table(name = "pre_defined_question")
public class PreDefinedQuestion implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "title")
    private String title;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public PreDefinedQuestion title(String title) {
        this.title = title;
        return this;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        PreDefinedQuestion preDefinedQuestion = (PreDefinedQuestion) o;
        if(preDefinedQuestion.id == null || id == null) {
            return false;
        }
        return Objects.equals(id, preDefinedQuestion.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "PreDefinedQuestion{" +
            "id=" + id +
            ", title='" + title + "'" +
            '}';
    }
}
