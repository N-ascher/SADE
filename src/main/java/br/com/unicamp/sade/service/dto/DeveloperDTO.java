package br.com.unicamp.sade.service.dto;

import br.com.unicamp.sade.domain.Address;
import br.com.unicamp.sade.domain.Technology;
import br.com.unicamp.sade.web.rest.vm.ManagedUserVM;
import org.hibernate.validator.constraints.URL;

import java.util.HashSet;
import java.util.Set;

/**
 * A DTO representing a developer
 */
public class DeveloperDTO {

    private String phoneNumber;

    private String mobileNumber;

    private String document;

    @URL
    private String linkedIn;

    @URL
    private String gitHub;

    private Integer availability;

    private String prospectedBy;

    private Address address;

    private Set<Technology> technologies = new HashSet<>();

    private ManagedUserVM user;

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public String getDocument() {
        return document;
    }

    public void setDocument(String document) {
        this.document = document;
    }

    public String getLinkedIn() {
        return linkedIn;
    }

    public void setLinkedIn(String linkedIn) {
        this.linkedIn = linkedIn;
    }

    public String getGitHub() {
        return gitHub;
    }

    public void setGitHub(String gitHub) {
        this.gitHub = gitHub;
    }

    public Integer getAvailability() {
        return availability;
    }

    public void setAvailability(Integer availability) {
        this.availability = availability;
    }

    public String getProspectedBy() {
        return prospectedBy;
    }

    public void setProspectedBy(String prospectedBy) {
        this.prospectedBy = prospectedBy;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public Set<Technology> getTechnologies() {
        return technologies;
    }

    public void setTechnologies(Set<Technology> technologies) {
        this.technologies = technologies;
    }

    public ManagedUserVM getUser() {
        return user;
    }

    public void setUser(ManagedUserVM user) {
        this.user = user;
    }
}
