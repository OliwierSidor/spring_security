package pl.sda.arppl4.spring.arppl4_spring_security.config.jwt;

public interface SecurityConstans {
    String APPLICATION_NAME = "sPRINGsECURITY";
    String APPLICATION_KEY = "ArPpL4SeCrEt";
    String HEADER_AUTH = "Authorization"; // Standard
    String BEARER_SEPARATOR = ":";
    String HEADER_AUTH_BEARER = "Bearer" + BEARER_SEPARATOR; // Standard
    String HEADER_EXPIRATION = "Expires_at";
    String HEADER_ROLES = "App_roles";
    String ROLES_SEPARATOR = ",";
}
