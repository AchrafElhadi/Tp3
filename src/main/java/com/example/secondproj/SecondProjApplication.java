package com.example.secondproj;

import com.example.secondproj.PatientRepo.PatientRepo;
import com.example.secondproj.entities.Patient;
import com.example.secondproj.security.Repositories.AppRoleRepository;
import com.example.secondproj.security.entities.AppRole;
import com.example.secondproj.security.service.SecurityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Date;

@SpringBootApplication
public class SecondProjApplication  {

    public static void main(String[] args) {
        SpringApplication.run(SecondProjApplication.class, args);
    }


//    private SecurityService securityService;
//    @Override
//    public void run(String... args) throws Exception {

//        Page<Patient> p=patientEntity.findAll(PageRequest.of(1,5));
//        System.out.println(p.getContent());
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




//    }
@Bean
PasswordEncoder passwordEncoder()
{
    return new BCryptPasswordEncoder();
}
    @Bean
     CommandLineRunner saveUsers(SecurityService secService, AppRoleRepository rolerep)
    {
        return args -> {
//            secService.saveNewUser("achraf","1234","1234");
//            secService.saveNewUser("adam","1234","1234");
//            secService.saveNewUser("admin","admin","admin");
//
//            secService.saveNewRole("USER","");
//            secService.saveNewRole("ADMIN","");
//
//
//
//            secService.addRoleToUser("achraf","USER");
//            secService.addRoleToUser("adam","USER");
//            secService.addRoleToUser("admin","ADMIN");
            secService.addRoleToUser("admin","USER");

        };
    }
}
