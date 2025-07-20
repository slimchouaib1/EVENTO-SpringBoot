package com.tekup.evento.repository;

import com.tekup.evento.models.Evenement;
import org.springframework.data.jpa.repository.JpaRepository;


import java.util.List;

public interface EvenementReposi extends JpaRepository<Evenement, Long> {


    List<Evenement> findByTitre(String titre);

}
