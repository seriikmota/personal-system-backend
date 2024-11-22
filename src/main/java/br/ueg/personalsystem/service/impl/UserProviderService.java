package br.ueg.personalsystem.service.impl;

import br.ueg.genericarchitecture.dto.CredentialDTO;
import br.ueg.genericarchitecture.enums.ApiErrorEnum;
import br.ueg.genericarchitecture.exception.BusinessException;
import br.ueg.genericarchitecture.service.IUserProviderService;
import br.ueg.personalsystem.entities.Role;
import br.ueg.personalsystem.entities.User;
import br.ueg.personalsystem.entities.UserGroup;
import br.ueg.personalsystem.repository.UserGroupRepository;
import br.ueg.personalsystem.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserProviderService implements IUserProviderService {

    @Autowired
    private UserRepository repository;

    @Autowired
    private UserGroupRepository userGroupRepository;

    @Override
    public CredentialDTO getCredentialByLogin(String login) {
        User user = repository.findByLogin(login);
        if (user == null) throw new BusinessException(ApiErrorEnum.LOGIN_INVALID);
        return getCredential(user);
    }

    @Override
    public CredentialDTO getCredentialByEmail(String email) {
        User user = repository.findByEmail(email);
        if (user == null) throw new BusinessException(ApiErrorEnum.LOGIN_INVALID);
        return getCredential(user);
    }

    public CredentialDTO getCredential(User user) {
        List<String> roles = this.getRolesOfUserGroup(user.getUserGroup().getId());

        return CredentialDTO.builder()
                .id(user.getId())
                .name(user.getName())
                .login(user.getLogin())
                .password(user.getPassword())
                .email(user.getEmail())
                .roles(roles)
                .activeState(user.getEnabled())
                .build();
    }

    private List<String> getRolesOfUserGroup(Long groupUserId) {
        UserGroup userGroup = userGroupRepository.findById(groupUserId).orElse(null);
        List<String> roles = new ArrayList<>();
        if (userGroup != null && userGroup.getRoles() != null) {
            for (Role userGroupRole : userGroup.getRoles()) {
                roles.add(userGroupRole.getRole());
            }
        }
        return roles;
    }

    @Override
    public void recordLog(CredentialDTO credentialDTO, String action) {

    }
}
