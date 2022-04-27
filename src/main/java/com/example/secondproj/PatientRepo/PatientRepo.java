package com.example.secondproj.PatientRepo;

import com.example.secondproj.entities.Patient;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PatientRepo extends JpaRepository<Patient,Long> {

    Page<Patient> findByNomContains(String ky, Pageable pageable);
}
