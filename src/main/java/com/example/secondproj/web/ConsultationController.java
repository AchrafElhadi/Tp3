package com.example.secondproj.web;

import com.example.secondproj.PatientRepo.ConsultationRepo;
import com.example.secondproj.entities.Consultation;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Controller
@AllArgsConstructor
public class ConsultationController {
    private ConsultationRepo consultation;


    @GetMapping("User/Consultation/Delete")
    public String consultationDelete(Model model,Long id)
    {
        consultation.deleteById(id);
        return "redirect:/User/Consultation";
    }
    @GetMapping("/User/Consultation")
    public String consultation(Model model)
    {
        model.addAttribute("consult",consultation.findAll());
        return "Consultation";
    }
    @GetMapping("/Admin/Consultation/Create")
    public String consultcreate()
    {
        return "ConsultForm";
    }
    @PostMapping("/Admin/Consultation/Create")
    public String postconsultcreate(@Valid Consultation cons, BindingResult bdr)
    {
        if(bdr.hasErrors())
            return "ConsultForm";

        consultation.save(cons);
        return "redirect:/User/Consultation";

    }
}
