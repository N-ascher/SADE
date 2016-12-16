package br.com.unicamp.sade.domain;

import org.hibernate.validator.constraints.URL;

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
 * A Developer.
 */
@Entity
@Table(name = "developer")
public class Developer implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "mobile_number")
    private String mobileNumber;

    @Column(name = "document")
    private String document;

    @URL
    @Column(name = "linked_in")
    private String linkedIn;

    @URL
    @Column(name = "git_hub")
    private String gitHub;

    @Column(name = "availability")
    private Integer availability;

    @Column(name = "prospected_by")
    private String prospectedBy;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(unique = true)
    private Address address;

    @OneToMany(mappedBy = "developer", cascade = CascadeType.ALL, fetch=FetchType.EAGER)
    private Set<Technology> technologies = new HashSet<>();

    @OneToOne(cascade = {CascadeType.REMOVE, CascadeType.MERGE, CascadeType.REFRESH})
    @JoinColumn(unique = true)
    private User user;

    @OneToOne(mappedBy = "developer", fetch = FetchType.EAGER)
    private Interview interview;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public Developer phoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
        return this;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public Developer mobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
        return this;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public String getDocument() {
        return document;
    }

    public Developer document(String document) {
        this.document = document;
        return this;
    }

    public void setDocument(String document) {
        this.document = document;
    }

    public String getLinkedIn() {
        return linkedIn;
    }

    public Developer linkedIn(String linkedIn) {
        this.linkedIn = linkedIn;
        return this;
    }

    public void setLinkedIn(String linkedIn) {
        this.linkedIn = linkedIn;
    }

    public String getGitHub() {
        return gitHub;
    }

    public Developer gitHub(String gitHub) {
        this.gitHub = gitHub;
        return this;
    }

    public void setGitHub(String gitHub) {
        this.gitHub = gitHub;
    }

    public Integer getAvailability() {
        return availability;
    }

    public Developer availability(Integer availability) {
        this.availability = availability;
        return this;
    }

    public void setAvailability(Integer availability) {
        this.availability = availability;
    }

    public String getProspectedBy() {
        return prospectedBy;
    }

    public Developer prospectedBy(String prospectedBy) {
        this.prospectedBy = prospectedBy;
        return this;
    }

    public void setProspectedBy(String prospectedBy) {
        this.prospectedBy = prospectedBy;
    }

    public Address getAddress() {
        return address;
    }

    public Developer address(Address address) {
        this.address = address;
        return this;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public Set<Technology> getTechnologies() {
        return technologies;
    }

    public Developer technologies(Set<Technology> technologies) {
        this.technologies = technologies;
        return this;
    }

    public Developer addTechnologies(Technology technology) {
        technologies.add(technology);
        technology.setDeveloper(this);
        return this;
    }

    public Developer removeTechnologies(Technology technology) {
        technologies.remove(technology);
        technology.setDeveloper(null);
        return this;
    }

    public void setTechnologies(Set<Technology> technologies) {
        this.technologies = technologies;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Interview getInterview() {
        return interview;
    }

    public Developer interview(Interview interview) {
        this.interview = interview;
        return this;
    }

    public void setInterview(Interview interview) {
        this.interview = interview;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Developer developer = (Developer) o;
        if(developer.id == null || id == null) {
            return false;
        }
        return Objects.equals(id, developer.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "Developer{" +
            "id=" + id +
            ", phoneNumber='" + phoneNumber + "'" +
            ", mobileNumber='" + mobileNumber + "'" +
            ", document='" + document + "'" +
            ", linkedIn='" + linkedIn + "'" +
            ", gitHub='" + gitHub + "'" +
            ", availability='" + availability + "'" +
            ", prospectedBy='" + prospectedBy + "'" +
            '}';
    }
}
