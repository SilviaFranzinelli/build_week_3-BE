package it.epicode.EpicEnergyService.auth;

import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.Set;

@Component
public class AuthRunner implements ApplicationRunner {

    @Autowired
    private AppUserService appUserService;

    @Autowired
    private RoleRepository roleRepository;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        Optional<User> adminUser = appUserService.findByUsername("admin");
        if (adminUser.isEmpty()) {
            Role adminRole = roleRepository.findByName(RoleType.ROLE_ADMIN)
                    .orElseThrow(() -> new RuntimeException("Ruolo ROLE_ADMIN non trovato"));

            appUserService.registerUser(
                    "admin",
                    "adminpwd",
                    Set.of(adminRole),
                    "Admin",
                    "User",
                    "admin@example.com"
            );
        }

        Optional<User> normalUser = appUserService.findByUsername("user");
        if (normalUser.isEmpty()) {
            Role userRole = roleRepository.findByName(RoleType.ROLE_USER)
                    .orElseThrow(() -> new RuntimeException("Ruolo ROLE_USER non trovato"));

            appUserService.registerUser(
                    "user",
                    "userpwd",
                    Set.of(userRole),
                    "Normal",
                    "User",
                    "user@example.com"
            );
        }
    }
}
