package it.epicode.EpicEnergyService.auth;



import org.apache.catalina.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
public interface AppUserRepository extends JpaRepository<AppUser, Long> {


    Optional<AppUser> findByUsername(String username);
    boolean existsByUsername(String username);
    boolean existsByEmail(String email);
}

