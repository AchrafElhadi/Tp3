package com.example.secondproj;

import com.example.secondproj.PatientRepo.PatientRepo;
import com.example.secondproj.entities.Patient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.Date;

@SpringBootApplication
public class SecondProjApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(SecondProjApplication.class, args);
    }

    @Autowired
    private PatientRepo patientEntity;
    @Override
    public void run(String... args) throws Exception {

        Page<Patient> p=patientEntity.findAll(PageRequest.of(1,5));
        System.out.println(p.getContent());
//        patientEntity.save(new Patient(null,"Messi",new Date(),true,(int)(Math.random()*30)));
//        patientEntity.save(new Patient(null,"Ozil",new Date(),false,(int)(Math.random()*30)));
//        patientEntity.save(new Patient(null,"Baloteli",new Date(),false,(int)(Math.random()*30)));
//        patientEntity.save(new Patient(null,"Lampard",new Date(),false,(int)(Math.random()*30)));
//        patientEntity.save(new Patient(null,"Giroud",new Date(),false,(int)(Math.random()*30)));
//        patientEntity.save(new Patient(null,"Drogba",new Date(),false,(int)(Math.random()*30)));
//        patientEntity.save(new Patient(null,"iniesta",new Date(),true,(int)(Math.random()*30)));
//
//        patientEntity.findAll().forEach(x->
//        {
//            System.out.println(x.toString());
//        });

    }
}
