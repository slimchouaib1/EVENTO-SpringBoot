package com.tekup.evento.services;

import com.tekup.evento.exceptions.CategoryNotFoundException;
import com.tekup.evento.models.Category;
import java.util.List;

public interface CategoryService {


    List<Category> findAll();

    Category save(Category category);


    Category findById(Long id);

    Category update(Category category);


    Category getById(Long id) throws CategoryNotFoundException;

    void deleteById(Long id);

    void enabledById(Long id);

    boolean existsByName(String name);
}
