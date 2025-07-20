package com.tekup.evento.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.util.Date;

@Entity
@Table(name = "evenement")
public class Evenement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long idEvenement;



    @ManyToOne(fetch =FetchType.EAGER , cascade =CascadeType.ALL)

    @JoinColumn(name="categorie_id" , referencedColumnName = "categorie_id")
    private Category category;
    private boolean is_deleted ;
    private boolean is_activated ;
    @Column(name = "titre", nullable = false, length = 100)
    private String titre;

    @Column(name = "description", length = 500)
    private String description;

    @Temporal(TemporalType.DATE)
    @Column(nullable = false)
    private Date date;

    @Column(nullable = false, length = 100)
    private String lieu;

    @Column(nullable = false)
    private int placesDisponibles;

    @Column(nullable = false)
    private double prix;
    @Lob

    @Column(name = "photo_url", length = 255 , columnDefinition ="MEDIUMLOB")
    private String photoUrl;



    // Constructeurs

    public Evenement() {
        // Constructeur par défaut
    }

    public Evenement(String titre, String description, Date date, String lieu, int placesDisponibles, double prix, String photoUrl) {
        this.titre = titre;
        this.description = description;
        this.date = date;
        this.lieu = lieu;
        this.placesDisponibles = placesDisponibles;
        this.prix = prix;
        this.photoUrl = photoUrl;
    }

    public Evenement(Long idEvenement, String titre, String description, Date date, String lieu, int placesDisponibles, double prix, String photoUrl) {
        this.idEvenement = idEvenement;
        this.titre = titre;
        this.description = description;
        this.date = date;
        this.lieu = lieu;
        this.placesDisponibles = placesDisponibles;
        this.prix = prix;
        this.photoUrl = photoUrl;
    }

    public Long getIdEvenement() {
        return idEvenement;
    }

    public void setIdEvenement(Long idEvenement) {
        this.idEvenement = idEvenement;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getLieu() {
        return lieu;
    }

    public void setLieu(String lieu) {
        this.lieu = lieu;
    }

    public int getPlacesDisponibles() {
        return placesDisponibles;
    }

    public void setPlacesDisponibles(int placesDisponibles) {
        this.placesDisponibles = placesDisponibles;
    }

    public double getPrix() {
        return prix;
    }

    public void setPrix(double prix) {
        this.prix = prix;
    }

    public String getPhotoUrl() {
        return photoUrl;
    }

    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
    }


    // Méthode pour afficher les détails de l'événement
    public String afficherEvenement() {
        return String.format(
                "Événement : %s\nDescription : %s\nDate : %s\nLieu : %s\nPlaces disponibles : %d\nPrix : %.2f DT\nPhoto : %s",
                titre, description, date, lieu, placesDisponibles, prix, photoUrl
        );
    }
}
