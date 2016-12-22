package br.com.unicamp.sade.fixtures;

import br.com.unicamp.sade.domain.Address;
import br.com.unicamp.sade.domain.Technology;
import br.com.unicamp.sade.service.dto.DeveloperDTO;
import br.com.unicamp.sade.web.rest.vm.ManagedUserVM;
import com.google.common.collect.ImmutableSet;

public class DeveloperFixture {

    public static DeveloperDTO developer() {
        DeveloperDTO developer = new DeveloperDTO();
        developer.setPhoneNumber("1932323232");
        developer.setMobileNumber("19999200020");
        developer.setDocument("00000000000");
        developer.setLinkedIn("http://linkedin.com");
        developer.setGitHub("http://github.com");
        developer.setAvailability(20);
        developer.setProspectedBy("Unicamp");
        developer.setAddress(address());

        ImmutableSet<Technology> technologies = ImmutableSet.of(technology("Java", 10), technology("Javascript", 5));
        developer.setTechnologies(technologies);

        developer.setUser(UserFixture.managedVMUser());
        return developer;
    }

    private static Address address() {
        Address address = new Address();
        address.setStreet("Rua dos bobos");
        address.setNumber("100");
        address.setNeighborhood("Bairro Louco");
        address.setCity("Campinas");
        address.setState("São Paulo");
        address.setComplement("Unicamp");
        address.setPostalCode("00000000");
        return address;
    }

    private static Technology technology(String name, Integer score) {
        Technology technology = new Technology();
        technology.setName(name);
        technology.setScore(score);
        return technology;
    }

}
