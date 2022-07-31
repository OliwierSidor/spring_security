package pl.sda.arppl4.spring.arppl4_spring_security.component;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import pl.sda.arppl4.spring.arppl4_spring_security.config.InitialUsersConfiguration;
import pl.sda.arppl4.spring.arppl4_spring_security.model.ApplicationUser;
import pl.sda.arppl4.spring.arppl4_spring_security.model.ApplicationUserRole;
import pl.sda.arppl4.spring.arppl4_spring_security.respositorium.ApplicationUserRepository;
import pl.sda.arppl4.spring.arppl4_spring_security.respositorium.ApplicationUserRoleRepository;

import java.util.*;
import java.util.stream.Collectors;

@Slf4j
@Component
@RequiredArgsConstructor
public class DatabaseInitializer {
    private final ApplicationUserRepository applicationUserRepository;
    private final ApplicationUserRoleRepository applicationUserRoleRepository;

    private final InitialUsersConfiguration initialUsersConfiguration;

    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @EventListener(ContextRefreshedEvent.class)
    public void initializeDatabase() {
        createRoles();
        createUsers();
    }

    private void createUsers() {
        for (InitialUsersConfiguration.InitialUserInfo userInfo : initialUsersConfiguration.getUsers()) {
            createUserIfNotExists(userInfo);
        }
    }

    private void createUserIfNotExists(InitialUsersConfiguration.InitialUserInfo userInfo) {
        if (!applicationUserRepository.existsByUsername(userInfo.getUsername())) {

            //  List<ApplicationUserRole> applicationUserRoleList = new ArrayList<>();
            //  for (String role : userInfo.getRoles()) {
            //     Optional<ApplicationUserRole> applicationUserRoleOptional = applicationUserRoleRepository.findByName(role);
            //    if(applicationUserRoleOptional.isPresent()){
            //         applicationUserRoleList.add(applicationUserRoleOptional.get());

            //      }
            //   }

            Set<ApplicationUserRole> applicationUserRoleSet = userInfo.getRoles().stream()
                    .map(applicationUserRoleRepository::findByName)
                    .filter(Optional::isPresent)
                    .map(Optional::get)
                    .collect(Collectors.toSet());


            ApplicationUser applicationUser = ApplicationUser.builder()
                    .username(userInfo.getUsername())
                    .password(bCryptPasswordEncoder.encode(userInfo.getPassword()))
                    .roles(applicationUserRoleSet)
                    .accountNonExpired(true)
                    .accountNonLocked(true)
                    .credentialsNonExpired(true)
                    .enabled(true)
                    .build();

            applicationUserRepository.save(applicationUser);
        }
    }

    private void createRoles() {
        for (String roleName : initialUsersConfiguration.getRoles()) {
            crateRoleIfNotExist(roleName);
        }
    }

    private void crateRoleIfNotExist(String roleName) {
        if (!applicationUserRoleRepository.existsByName(roleName)) {
            ApplicationUserRole role = new ApplicationUserRole();
            role.setName(roleName);
            applicationUserRoleRepository.save(role);
        }
    }
}
