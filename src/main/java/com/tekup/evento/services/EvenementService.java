package com.tekup.evento.services;

import com.tekup.evento.dto.EvenementDto;
import com.tekup.evento.models.Evenement;

import java.util.List;

public interface EvenementService {
    List<EvenementDto> findAll();

    void saveEvenement(EvenementDto evenementDto);

    EvenementDto findEvenementById(Long id);



}
