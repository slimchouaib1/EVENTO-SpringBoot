package com.tekup.evento.controllers;

import com.tekup.evento.dto.UtilisateurDto;
import com.tekup.evento.models.Utilisateur;
import com.tekup.evento.services.UtilisateurService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class RegisterController {

    private final UtilisateurService utilisateurService;

    public RegisterController(UtilisateurService utilisateurService) {
        this.utilisateurService = utilisateurService;
    }

    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        model.addAttribute("utilisateurDto", new UtilisateurDto());
        return "utilisateur/register";
    }

    @PostMapping("/register")
    public String registerUser(@ModelAttribute UtilisateurDto utilisateurDto) {
        utilisateurService.createUtilisateur(utilisateurDto);
        return "redirect:/login";
    }
}
