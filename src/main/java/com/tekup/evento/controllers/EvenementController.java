package com.tekup.evento.controllers;

import com.tekup.evento.dto.EvenementDto;
import com.tekup.evento.models.Evenement;
import com.tekup.evento.services.EvenementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("utilisateur/evenements")  // Base path
public class EvenementController {

    private final EvenementService evenementService;



    @Autowired
    public EvenementController(EvenementService evenementService) {
        this.evenementService = evenementService;
    }

    @GetMapping("/evenements")
    public String evenement(Model model) {
        List<EvenementDto> evenements = evenementService.findAll();
        model.addAttribute("evenements", evenements);
        return "utilisateur/evenements/evenements";  // Chemin vers la vue
    }

    @GetMapping("/evenements/new")
    public String createEvenementForm(Model model) {
        // On crée un EvenementDto vide pour l'afficher dans le formulaire
        EvenementDto evenementDto = new EvenementDto();
        model.addAttribute("evenement", evenementDto);  // On passe l'objet EvenementDto au modèle
        return "utilisateur/evenements/evenement-create"; // Chemin vers la vue du formulaire
    }

    @PostMapping("/evenements")
    public String saveEvenement(@ModelAttribute("evenement") EvenementDto evenementDto) {
        evenementService.saveEvenement(evenementDto);
        return "redirect:/utilisateur/evenements/evenements";
    }




}
