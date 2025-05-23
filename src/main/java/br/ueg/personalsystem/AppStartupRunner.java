package br.ueg.personalsystem;

import br.ueg.personalsystem.entities.Role;
import br.ueg.personalsystem.entities.User;
import br.ueg.personalsystem.entities.UserGroup;
import br.ueg.personalsystem.repository.RoleRepository;
import br.ueg.personalsystem.repository.UserGroupRepository;
import br.ueg.personalsystem.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
public class AppStartupRunner implements ApplicationRunner {
    private static final Logger LOG = LoggerFactory.getLogger(AppStartupRunner.class);

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private UserGroupRepository userGroupRepository;

    public void initData() {
        if (userRepository.count() == 0 && roleRepository.count() == 0 && userGroupRepository.count() == 0) {
            List<String> roles = Arrays.asList(
                    "ROLE_USER_CREATE",
                    "ROLE_USER_READ",
                    "ROLE_USER_UPDATE",
                    "ROLE_USER_DELETE",
                    "ROLE_USER_LISTALL",
                    "ROLE_PATIENT_CREATE",
                    "ROLE_PATIENT_READ",
                    "ROLE_PATIENT_UPDATE",
                    "ROLE_PATIENT_DELETE",
                    "ROLE_PATIENT_LISTALL",
                    "ROLE_ANAMNESE_CREATE",
                    "ROLE_ANAMNESE_READ",
                    "ROLE_ANAMNESE_UPDATE",
                    "ROLE_ANAMNESE_DELETE",
                    "ROLE_ANAMNESE_LISTALL",
                    "ROLE_EVOLUTION_CREATE",
                    "ROLE_EVOLUTION_DELETE",
                    "ROLE_EVOLUTION_CONNECT",
                    "ROLE_EVOLUTION_LOGOUT",
                    "ROLE_EVOLUTION_STATUS",
                    "ROLE_EVOLUTION_SEND_MESSAGE");

            List<Role> roleList = new ArrayList<>();
            for (String role : roles) {
                Role userGroupRole = Role.builder().role(role).build();
                roleList.add(userGroupRole);
            }

            UserGroup userGroup = UserGroup.builder()
                    .name("admin")
                    .roles(roleList)
                    .build();

            User user = User.builder()
                    .name("Administrador")
                    .login("admin")
                    .password("$2y$10$1MgdNcIduZBhvlTym.PKje0nDX54UVS28jTa2U3lB3JvrqAj4fAdq") // Senha == admin
                    .email("teste@gmail.com")
                    .enabled(Boolean.TRUE)
                    .userGroup(userGroup)
//                    .apiKeyEvolution("3DBFB2F3-AD91-4ABC-9ED3-4072027EC101")
//                    .instanceNameEvolution("Instancia Erik")
                    .build();

            roleRepository.saveAll(roleList);
            userGroupRepository.save(userGroup);
            userRepository.save(user);
        }
    }

    public void run(ApplicationArguments args) {
        try {
            this.initData();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
