package pl.sda.arppl4.spring.arppl4_spring_security.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/api/test")
public class TestController {

    @GetMapping("")
    public String test() {
        return "test";
    }

    @GetMapping("/authorized")
    public String testAuthorized(Authentication principal) {
        log.info("after filtred: " + principal);
        return "testAuthorized";
    }
}
