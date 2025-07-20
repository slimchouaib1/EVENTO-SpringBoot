package com.tekup.evento.controllers;

import com.tekup.evento.models.Category;
import com.tekup.evento.services.CategoryService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class CategoryController {
    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    // Afficher la liste des catégories
    @GetMapping("/utilisateur/Category/categories")
    public String categories(Model model) {
        model.addAttribute("title", "Category");
        model.addAttribute("categories", categoryService.findAll());  // Liste des catégories
        model.addAttribute("category", new Category()); // Ajouter un objet vide pour le formulaire d'ajout
        return "categories";
    }

    // Ajouter une nouvelle catégorie
    @PostMapping("/utilisateur/Category/categories/add-category")
    public String addCategory(@ModelAttribute Category category, RedirectAttributes attributes) {
        try {
            categoryService.save(category); // Enregistrer la nouvelle catégorie
            attributes.addFlashAttribute("success", "Category added successfully");
        } catch (Exception e) {
            e.printStackTrace();
            attributes.addFlashAttribute("failed", "Error occurred while adding category");
        }
        return "redirect:/utilisateur/Category/categories";  // Redirection vers la page des catégories
    }

    // Mettre à jour une catégorie
    @PostMapping("/utilisateur/Category/categories/update-category")
    public String updateCategory(@RequestParam Long id, @RequestParam String name, RedirectAttributes attributes) {
        try {
            Category category = categoryService.findById(id);
            category.setName(name); // Mettre à jour le nom de la catégorie
            categoryService.update(category); // Sauvegarder les modifications
            attributes.addFlashAttribute("success", "Category updated successfully");
        } catch (Exception e) {
            e.printStackTrace();
            attributes.addFlashAttribute("failed", "Error occurred while updating category");
        }
        return "redirect:/utilisateur/Category/categories";  // Redirection vers la page des catégories
    }
}
