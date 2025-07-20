package com.tekup.evento.dto;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;

import java.util.Date;
import java.util.List;

public class EvenementDto {

    private Long idEvenement;
    private String titre;
    private Date date;
    private String description;
    private String lieu;
    private int placesDisponibles;
    private double prix;

    private String photoUrl;
    private List<EvenementDto> evenements;


    // Constructeur public sans argument
    public EvenementDto() {}

    // Constructeur privé pour forcer l'utilisation du Builder
    private EvenementDto(Builder builder) {
        this.idEvenement = builder.idEvenement;
        this.titre = builder.titre;
        this.date = builder.date;
        this.description = builder.description;
        this.lieu = builder.lieu;
        this.placesDisponibles = builder.placesDisponibles;
        this.prix = builder.prix;
        this.photoUrl = builder.photoUrl;
    }

    // Méthode static pour retourner un Builder
    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private Long idEvenement;
        private String titre;
        private Date date;
        private String description;
        private String lieu;
        private int placesDisponibles;
        private double prix;
        private String photoUrl;

        public Builder idEvenement(Long idEvenement) {
            this.idEvenement = idEvenement;
            return this;
        }

        public Builder titre(String titre) {
            this.titre = titre;
            return this;
        }

        public Builder date(Date date) {
            this.date = date;
            return this;
        }

        public Builder description(String description) {
            this.description = description;
            return this;
        }

        public Builder lieu(String lieu) {
            this.lieu = lieu;
            return this;
        }

        public Builder placesDisponibles(int placesDisponibles) {
            this.placesDisponibles = placesDisponibles;
            return this;
        }

        public Builder prix(double prix) {
            this.prix = prix;
            return this;
        }

        public Builder photoUrl(String photoUrl) {
            this.photoUrl = photoUrl;
            return this;
        }

        // Méthode de construction finale
        public EvenementDto build() {
            return new EvenementDto(this);
        }
    }

    // Getters pour chaque champ
    public Long getIdEvenement() {
        return idEvenement;
    }

    public String getTitre() {
        return titre;
    }

    public Date getDate() {
        return date;
    }

    public String getDescription() {
        return description;
    }

    public String getLieu() {
        return lieu;
    }

    public int getPlacesDisponibles() {
        return placesDisponibles;
    }

    public double getPrix() {
        return prix;
    }

    public String getPhotoUrl() {
        return photoUrl;
    }
}
