package pl.sda.arppl4.spring.arppl4_spring_security.respositorium;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.sda.arppl4.spring.arppl4_spring_security.model.ApplicationUser;
import pl.sda.arppl4.spring.arppl4_spring_security.model.ApplicationUserRole;

import java.util.Optional;

public interface ApplicationUserRoleRepository extends JpaRepository<ApplicationUserRole, Long> {
    Optional<ApplicationUserRole> findByName(String name);

    boolean existsByName(String name);
}
