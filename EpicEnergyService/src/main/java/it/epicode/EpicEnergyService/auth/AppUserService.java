package it.epicode.EpicEnergyService.auth;

import it.epicode.EpicEnergyService.exceptions.ConflictException;
import it.epicode.EpicEnergyService.exceptions.UnauthorizedException;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class AppUserService {

    private final AppUserRepository appUserRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtTokenUtil jwtTokenUtil;

    public AppUser registerUser(String username, String password, Set<Role> roles,
                                String nome, String cognome, String email) {

        if (appUserRepository.existsByUsername(username)) {
            throw new ConflictException("Username già in uso");
        }

        if (appUserRepository.existsByEmail(email)) {
            throw new ConflictException("Email già in uso");
        }

        AppUser user = new AppUser();
        user.setUsername(username);
        user.setPassword(passwordEncoder.encode(password));
        user.setRoles(roles);
        user.setNome(nome);
        user.setCognome(cognome);
        user.setEmail(email);

        return save(user);
    }

    public Optional<AppUser> findByUsername(String username) {
        return appUserRepository.findByUsername(username);
    }

    public AppUser loadUserByUsername(String username) {
        return (AppUser) appUserRepository.findByUsername(username)
                .orElseThrow(() -> new EntityNotFoundException("Utente non trovato con username: " + username));
    }

    public AuthResponse authenticateUser(String username, String password) {
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(username, password)
            );

            UserDetails userDetails = (UserDetails) authentication.getPrincipal();
            String token = jwtTokenUtil.generateToken(userDetails);
            AppUser user = loadUserByUsername(username);

            return new AuthResponse(token, user);

        } catch (AuthenticationException e) {
            throw new UnauthorizedException("Errore nell'autenticazione: " + e.getMessage());
        }
    }

    public AppUser save(AppUser appUser) {
        try {
            return appUserRepository.save(appUser);
        } catch (DataIntegrityViolationException e) {
            System.err.println("Errore nel salvataggio utente: " + e.getMessage());
            throw new ConflictException("Dati duplicati o vincoli violati. Verifica che email e username siano unici.");
        } catch (Exception e) {
            throw new RuntimeException("Errore inatteso nel salvataggio utente: " + e.getMessage());
        }
    }
}
