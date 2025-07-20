package com.tekup.evento.services.impl;

import com.tekup.evento.models.Category;
import com.tekup.evento.repository.CategoryRepository;
import com.tekup.evento.services.CategoryService;
import com.tekup.evento.exceptions.CategoryNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public List<Category> findAll() {
        return categoryRepository.findAll();
    }

    @Override
    public Category save(Category category) {
        // Vérification de l'existence d'une catégorie avec ce nom
        if (existsByName(category.getName())) {
            throw new IllegalArgumentException("Une catégorie avec ce nom existe déjà.");
        }

        // Création d'une nouvelle catégorie
        Category categorySave = new Category(category.getName());
        return categoryRepository.save(categorySave);
    }

    @Override
    public Category findById(Long id) {
        // Utilisation de findById pour éviter les exceptions de type NoSuchElementException
        Optional<Category> category = categoryRepository.findById(id);
        return category.orElseThrow(() -> new CategoryNotFoundException("Catégorie non trouvée"));
    }

    @Override
    public Category update(Category category) {
        // Vérification si la catégorie existe avant de la mettre à jour
        Category categoryUpdate = categoryRepository.findById(category.getId())
                .orElseThrow(() -> new CategoryNotFoundException("Catégorie non trouvée pour mise à jour"));

        categoryUpdate.setName(category.getName());
        categoryUpdate.set_activated(category.is_activated());
        categoryUpdate.set_deleted(category.is_deleted());

        return categoryRepository.save(categoryUpdate);
    }

    @Override
    public Category getById(Long id) {
        // Utilisation de findById avec gestion d'exception
        return findById(id);
    }

    @Override
    public void deleteById(Long id) {
        // Vérification si la catégorie existe avant de la marquer comme supprimée
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new CategoryNotFoundException("Catégorie non trouvée pour suppression"));

        category.set_deleted(true);
        category.set_activated(false);
        categoryRepository.save(category);
    }

    @Override
    public void enabledById(Long id) {
        // Vérification si la catégorie existe avant de la marquer comme activée
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new CategoryNotFoundException("Catégorie non trouvée pour activation"));

        category.set_activated(true);
        category.set_deleted(false);
        categoryRepository.save(category);
    }

    @Override
    public boolean existsByName(String name) {
        return existsByName(name);
    }
}
