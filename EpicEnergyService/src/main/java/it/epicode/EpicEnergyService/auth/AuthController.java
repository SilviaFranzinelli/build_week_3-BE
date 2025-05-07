package it.epicode.EpicEnergyService.auth;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AppUserService appUserService;
    private final RoleRepository roleRepository;

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody RegisterRequest registerRequest) {
        // Recupera il ruolo ROLE_USER dal database
        Role userRole = roleRepository.findByName(RoleType.ROLE_USER)
                .orElseThrow(() -> new RuntimeException("Ruolo ROLE_USER non trovato nel database"));

        Set<Role> roles = Set.of(userRole);

        appUserService.registerUser(
                registerRequest.getUsername(),
                registerRequest.getPassword(),
                roles,
                registerRequest.getNome(),
                registerRequest.getCognome(),
                registerRequest.getEmail()
        );

        return ResponseEntity.ok("Registrazione avvenuta con successo");
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody LoginRequest loginRequest) {
        AuthResponse authResponse = appUserService.authenticateUser(
                loginRequest.getUsername(),
                loginRequest.getPassword()
        );
        return ResponseEntity.ok(authResponse);
    }
}
