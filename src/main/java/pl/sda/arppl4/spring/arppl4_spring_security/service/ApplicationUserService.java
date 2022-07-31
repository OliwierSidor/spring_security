package pl.sda.arppl4.spring.arppl4_spring_security.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import pl.sda.arppl4.spring.arppl4_spring_security.model.ApplicationUser;
import pl.sda.arppl4.spring.arppl4_spring_security.respositorium.ApplicationUserRepository;

import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class ApplicationUserService implements UserDetailsService {
    private final ApplicationUserRepository applicationUserRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<ApplicationUser> optionalApplicationUser = applicationUserRepository.findByUsername(username);
        if (optionalApplicationUser.isPresent()) {
            log.info("User found " + username);
            return optionalApplicationUser.get();
        }
        log.warn("User not found " + username);
        throw new UsernameNotFoundException("Can't find user with username: " + username);
    }
}
