package pl.sda.arppl4.spring.arppl4_spring_security.respositorium;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.sda.arppl4.spring.arppl4_spring_security.model.ApplicationUser;

import java.util.Optional;

public interface ApplicationUserRepository extends JpaRepository<ApplicationUser, Long> {

    Optional<ApplicationUser> findByUsername(String username);
    boolean existsByUsername(String username);
}
