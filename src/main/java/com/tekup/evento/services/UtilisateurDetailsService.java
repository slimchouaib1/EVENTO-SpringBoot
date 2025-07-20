package com.tekup.evento.services;

import com.tekup.evento.models.Utilisateur;
import com.tekup.evento.repository.UtilisateurInterface;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UtilisateurDetailsService implements UserDetailsService {

    private final UtilisateurInterface utilisateurInterface;

    public UtilisateurDetailsService(UtilisateurInterface utilisateurInterface) {
        this.utilisateurInterface = utilisateurInterface;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        // Fetch the user from the database
        Utilisateur utilisateur = utilisateurInterface.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("Utilisateur non trouvé avec l'email : " + email));

        // Dynamically assign the role
        String role = String.valueOf(utilisateur.getRole()); // Assuming 'role' contains values like 'ADMIN' or 'USER'
        if (role == null || role.isEmpty()) {
            throw new UsernameNotFoundException("Role not assigned to user: " + email);
        }

        // Return the UserDetails object
        return org.springframework.security.core.userdetails.User.builder()
                .username(utilisateur.getEmail())
                .password(utilisateur.getPassword())
                .roles(role) // Dynamically set role
                .build();
    }
}
