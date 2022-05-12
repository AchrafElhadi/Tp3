package com.example.secondproj.PatientRepo;

import com.example.secondproj.entities.Medecin;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MedecinRepo extends JpaRepository<Medecin,Long> {

    Page<Medecin> findByNomContains(String val, Pageable pageable);
}
