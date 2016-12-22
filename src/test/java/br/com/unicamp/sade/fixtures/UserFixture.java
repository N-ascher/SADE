package br.com.unicamp.sade.fixtures;

import br.com.unicamp.sade.security.AuthoritiesConstants;
import br.com.unicamp.sade.service.dto.UserDTO;
import br.com.unicamp.sade.web.rest.vm.ManagedUserVM;
import com.google.common.collect.ImmutableSet;

public class UserFixture {

    public static UserDTO admin() {
        return new UserDTO("charmander", "Charmander", "da Silva Sauro", "charmander@sauro.com",
                true, "pt-br",
                ImmutableSet.of(AuthoritiesConstants.CONPEC_USER, AuthoritiesConstants.REGISTERED_USER));
    }

    public static UserDTO normalUser() {
        return new UserDTO("squirtle", "Squirtle", "Water", "squirtle@water.com",
                true, "pt-br", ImmutableSet.of(AuthoritiesConstants.REGISTERED_USER));
    }

    public static ManagedUserVM managedVMUser() {
        UserDTO user = normalUser();

        return new ManagedUserVM(null, user.getLogin(), "hello123", user.getFirstName(), user.getLastName(),
                user.getEmail(), true, user.getLangKey(), user.getAuthorities(),
                null, null, null, null);
    }
}
