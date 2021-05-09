package com.springboot.security;

import com.springboot.security.entity.User;
import com.springboot.security.repository.UserRepository;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@SpringBootApplication
@OpenAPIDefinition(info = @Info(title = "Spring boot JWT Token application", description = "This is an rest api ui for understanding",
        termsOfService = "nothing", license = @License(name = "openapi"), contact = @Contact(email = "sample@test.com"), version = "1.0.0"))
public class SpringSecurityJwtApplication {
    @Autowired
    private UserRepository repository;

    @PostConstruct
    public void initUsers() {
        List<User> users = Stream.of(
                new User(101, "SSO_ADMIN_USER", "password", "password@gmail.com"),
                new User(102, "SSO_NON_ADMIN_USER", "password1", "user1@gmail.com")
        ).collect(Collectors.toList());
        repository.saveAll(users);
    }

    public static void main(String[] args) {
        SpringApplication.run(SpringSecurityJwtApplication.class, args);
    }

}
