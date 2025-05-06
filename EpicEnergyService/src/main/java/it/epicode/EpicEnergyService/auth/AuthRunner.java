package it.epicode.EpicEnergyService.auth;

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
        // Inizializza i ruoli principali
        initializeRoles();

        // Verifica se esiste l'utente admin, altrimenti lo crea
        Optional<AppUser> adminUser = appUserService.findByUsername("admin");
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

        // Verifica se esiste l'utente normale, altrimenti lo crea
        Optional<AppUser> normalUser = appUserService.findByUsername("user");
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

    /**
     * Metodo per inizializzare i ruoli principali (ROLE_ADMIN, ROLE_USER) se non esistono
     */
    private void initializeRoles() {
        createRoleIfNotExists(RoleType.ROLE_ADMIN);
        createRoleIfNotExists(RoleType.ROLE_USER);
    }

    /**
     * Crea un ruolo nel database se non esiste
     *
     * @param roleType il tipo di ruolo da verificare/creare
     */
    private void createRoleIfNotExists(RoleType roleType) {
        Optional<Role> role = roleRepository.findByName(roleType);
        if (role.isEmpty()) {
            Role newRole = new Role();
            newRole.setName(roleType);
            roleRepository.save(newRole);
        }
    }
}
