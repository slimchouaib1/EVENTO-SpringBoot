package com.tekup.evento.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.tekup.evento.dto.EvenementDto;
import com.tekup.evento.models.Evenement;
import com.tekup.evento.repository.EvenementReposi;
import com.tekup.evento.services.EvenementService;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EvenementServiceImpl implements EvenementService {

    private final EvenementReposi evenementReposi;

    @Autowired
    public EvenementServiceImpl(EvenementReposi evenementReposi) {
        this.evenementReposi = evenementReposi;
    }

    @Override
    public List<EvenementDto> findAll() {
        List<Evenement> evenements = evenementReposi.findAll();
        if (evenements.isEmpty()) {
            throw new IllegalArgumentException("No events found.");
        }
        System.out.println("Événements récupérés : " + evenements.size());  // Pour déboguer
        return evenements.stream().map(this::mapToEvenementDto).collect(Collectors.toList());
    }

    @Override
    public void saveEvenement(EvenementDto evenementDto) {
        // Conversion du DTO en modèle
        Evenement evenement = mapToEvenement(evenementDto);

        // Sauvegarde de l'événement dans la base de données
        evenementReposi.save(evenement);
    }

    @Override
    public EvenementDto findEvenementById(Long id) {
        Evenement evenement = evenementReposi.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Event not found with id: " + id));
        return mapToEvenementDto(evenement);
    }

    // Méthode privée pour convertir un EvenementDto en Evenement
    private Evenement mapToEvenement(EvenementDto evenementDto) {
        Evenement evenement = new Evenement();
        evenement.setIdEvenement(evenementDto.getIdEvenement());
        evenement.setTitre(evenementDto.getTitre());
        evenement.setDate(evenementDto.getDate());
        evenement.setDescription(evenementDto.getDescription());
        evenement.setLieu(evenementDto.getLieu());
        evenement.setPhotoUrl(evenementDto.getPhotoUrl());
        evenement.setPlacesDisponibles(evenementDto.getPlacesDisponibles());
        evenement.setPrix(evenementDto.getPrix());
        return evenement;
    }

    // Méthode privée pour convertir un Evenement en EvenementDto
    private EvenementDto mapToEvenementDto(Evenement evenement) {
        return EvenementDto.builder()
                .idEvenement(evenement.getIdEvenement())
                .titre(evenement.getTitre())
                .date(evenement.getDate())
                .description(evenement.getDescription())
                .lieu(evenement.getLieu())
                .placesDisponibles(evenement.getPlacesDisponibles())
                .prix(evenement.getPrix())
                .photoUrl(evenement.getPhotoUrl())
                .build();
    }
}
