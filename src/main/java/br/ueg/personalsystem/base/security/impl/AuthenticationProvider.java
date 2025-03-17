package br.ueg.personalsystem.base.security.impl;

import br.ueg.personalsystem.base.dto.CredentialDTO;
import br.ueg.personalsystem.base.exception.SecurityException;
import br.ueg.personalsystem.base.security.Credential;
import br.ueg.personalsystem.base.security.IAuthenticationProvider;
import br.ueg.personalsystem.base.service.impl.AuthService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AuthenticationProvider implements IAuthenticationProvider {

    private final Log logger = LogFactory.getLog(getClass());

    @Autowired
    private AuthService authService;

    @Override
    public Credential getAuthentication(final String accessToken) {
        CredentialDTO credentialDTO;

        try {
            credentialDTO = authService.getInfoByToken(accessToken);
        } catch (SecurityException e) {
            logger.error("Acesso negado.", e);
            throw e;
        }
        return credentialDTO;
    }
}
