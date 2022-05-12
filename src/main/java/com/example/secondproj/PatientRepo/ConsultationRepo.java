package com.example.secondproj.PatientRepo;

import com.example.secondproj.entities.Consultation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ConsultationRepo extends JpaRepository<Consultation,Long> {
}
