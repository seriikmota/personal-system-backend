package br.ueg.personalsystem.base.controller.impl;

import br.ueg.personalsystem.base.dto.CredentialDTO;
import br.ueg.personalsystem.base.security.impl.CredentialProvider;

public abstract class AbstractController {

    protected CredentialDTO getCredential() {
        return (CredentialDTO) CredentialProvider.newInstance().getCurrentInstance();
    }

    protected Long getIdFromLoggedUser() {
        CredentialDTO credential = getCredential();
        return credential != null ? credential.getId() : null;
    }

    protected String getUserNameFromLoggedUser() {
        CredentialDTO credential = getCredential();
        return credential != null ? credential.getLogin() : null;
    }
}
