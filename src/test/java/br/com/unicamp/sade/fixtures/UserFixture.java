package br.com.unicamp.sade.fixtures;

import br.com.unicamp.sade.security.AuthoritiesConstants;
import br.com.unicamp.sade.service.dto.UserDTO;
import com.google.common.collect.ImmutableSet;

public class UserFixture {

    public static UserDTO admin() {
        return new UserDTO("charmander", "Charmander", "da Silva Sauro", "charmander@sauro.com", true, "pt-br",
                ImmutableSet.of(AuthoritiesConstants.CONPEC_USER, AuthoritiesConstants.REGISTERED_USER));
    }

}
