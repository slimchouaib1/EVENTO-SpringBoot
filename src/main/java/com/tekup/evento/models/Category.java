package com.tekup.evento.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "categorie", uniqueConstraints = @UniqueConstraint(columnNames = "name"))
public class Category {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "categorie_id")
    private Long id;

    @Column(name = "name", nullable = false, length = 100)
    private String name;

    private boolean is_deleted;

    private boolean is_activated;

    // Constructeur avec un argument
    public Category(String name) {
        this.name = name;
        this.is_deleted = false;
        this.is_activated = true;
    }

    // Getters et setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isIs_deleted() {
        return is_deleted;
    }

    public void setIs_deleted(boolean is_deleted) {
        this.is_deleted = is_deleted;
    }

    public boolean isIs_activated() {
        return is_activated;
    }

    public void setIs_activated(boolean is_activated) {
        this.is_activated = is_activated;
    }

    // Pour l'activation et suppression
    public boolean is_deleted() {
        return is_deleted;
    }

    public void set_deleted(boolean is_deleted) {
        this.is_deleted = is_deleted;
    }

    public boolean is_activated() {
        return is_activated;
    }

    public void set_activated(boolean is_activated) {
        this.is_activated = is_activated;
    }

    public Category get() { return get();
    }
}
