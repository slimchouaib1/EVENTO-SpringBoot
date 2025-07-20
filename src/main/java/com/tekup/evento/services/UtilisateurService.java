package com.tekup.evento.services;

import com.tekup.evento.models.Utilisateur;
import com.tekup.evento.dto.UtilisateurDto;
import com.tekup.evento.repository.UtilisateurInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UtilisateurService {

    @Autowired
    private UtilisateurInterface utilisateurInterface;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    // Fetch all users
    public List<Utilisateur> getAllUtilisateurs() {
        return utilisateurInterface.findAll();
    }

    // Fetch a user by ID
    public Optional<Utilisateur> getUtilisateurById(int id) {
        return utilisateurInterface.findById(id);
    }

    // Create a new user with a specified role
    public Utilisateur createUtilisateur(UtilisateurDto utilisateurDto, String role) {
        Utilisateur utilisateur = new Utilisateur();
        utilisateur.setNom(utilisateurDto.getNom());
        utilisateur.setPrenom(utilisateurDto.getPrenom());
        utilisateur.setEmail(utilisateurDto.getEmail());

        // Hash the password before saving
        utilisateur.setPassword(passwordEncoder.encode(utilisateurDto.getPassword()));

        // Assign role with default to USER
        utilisateur.setRole(Utilisateur.Role.valueOf(resolveRole(role)));

        return utilisateurInterface.save(utilisateur);
    }

    // Create a new user with default role USER
    public Utilisateur createUtilisateur(UtilisateurDto utilisateurDto) {
        return createUtilisateur(utilisateurDto, "USER");
    }

    // Update an existing user
    public Utilisateur updateUtilisateur(UtilisateurDto utilisateurDto, String role) {
        Optional<Utilisateur> utilisateurOptional = utilisateurInterface.findById(utilisateurDto.getId());
        if (utilisateurOptional.isEmpty()) {
            throw new IllegalArgumentException("Utilisateur not found");
        }

        Utilisateur utilisateur = utilisateurOptional.get();
        utilisateur.setNom(utilisateurDto.getNom());
        utilisateur.setPrenom(utilisateurDto.getPrenom());
        utilisateur.setEmail(utilisateurDto.getEmail());

        // Hash the password only if it is updated
        if (utilisateurDto.getPassword() != null && !utilisateurDto.getPassword().isEmpty()) {
            utilisateur.setPassword(passwordEncoder.encode(utilisateurDto.getPassword()));
        }

        // Update role
        utilisateur.setRole(Utilisateur.Role.valueOf(resolveRole(role)));

        return utilisateurInterface.save(utilisateur);
    }

    // Delete a user by ID
    public void deleteUtilisateur(int id) {
        if (!utilisateurInterface.existsById(id)) {
            throw new IllegalArgumentException("Utilisateur not found");
        }
        utilisateurInterface.deleteById(id);
    }

    public Optional<Utilisateur> getUtilisateurByEmail(String email) {
        return utilisateurInterface.findByEmail(email);
    }


    private String resolveRole(String role) {
        if ("ADMIN".equalsIgnoreCase(role)) {
            return "ADMIN";
        }
        return "USER";
    }
}
