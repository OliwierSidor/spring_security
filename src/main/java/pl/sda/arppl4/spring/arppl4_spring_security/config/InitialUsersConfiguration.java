package pl.sda.arppl4.spring.arppl4_spring_security.config;

import lombok.*;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Setter
@Getter
@Configuration
@ConfigurationProperties(prefix = "default")
public class InitialUsersConfiguration {

    private List<String> roles;
    private List<InitialUserInfo> users;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class InitialUserInfo {
        private String username;
        private String password;
        private List<String> roles;
    }
}
