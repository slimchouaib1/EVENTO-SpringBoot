package com.tekup.evento.controllers;

import com.tekup.evento.models.Utilisateur;
import com.tekup.evento.dto.UtilisateurDto;
import com.tekup.evento.services.UtilisateurService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/utilisateur")
public class UtilisateurController {

    @Autowired
    private UtilisateurService utilisateurService;

    // Display all users
    @GetMapping({"", "/"})
    public String showUtilisateurList(Model model) {
        List<Utilisateur> utilisateurs = utilisateurService.getAllUtilisateurs();
        model.addAttribute("utilisateurs", utilisateurs);
        model.addAttribute("utilisateurDto", new UtilisateurDto());
        return "utilisateur/index";
    }

    // Create a new user with role
    @PostMapping("/create")
    public String createUtilisateur(
            @Valid @ModelAttribute UtilisateurDto utilisateurDto,
            @RequestParam("role") String role, // Role parameter
            BindingResult result) {

        if (result.hasErrors()) {
            return "utilisateur/index";
        }

        // Validate role input
        if (!role.equalsIgnoreCase("USER") && !role.equalsIgnoreCase("ADMIN")) {
            result.rejectValue("role", "InvalidRole", "Role must be either USER or ADMIN.");
            return "utilisateur/index";
        }

        utilisateurService.createUtilisateur(utilisateurDto, role.toUpperCase());
        return "redirect:/utilisateur";
    }

    // Update an existing user with role
    @PostMapping("/update")
    public String updateUtilisateur(
            @Valid @ModelAttribute UtilisateurDto utilisateurDto,
            @RequestParam("role") String role, // Role parameter
            BindingResult result) {

        if (result.hasErrors()) {
            return "utilisateur/index";
        }

        // Validate role input
        if (!role.equalsIgnoreCase("USER") && !role.equalsIgnoreCase("ADMIN")) {
            result.rejectValue("role", "InvalidRole", "Role must be either USER or ADMIN.");
            return "utilisateur/index";
        }

        utilisateurService.updateUtilisateur(utilisateurDto, role.toUpperCase());
        return "redirect:/utilisateur";
    }

    // Delete a user by ID
    @GetMapping("/delete/{id}")
    public String deleteUtilisateur(@PathVariable int id) {
        utilisateurService.deleteUtilisateur(id);
        return "redirect:/utilisateur";
    }



}
