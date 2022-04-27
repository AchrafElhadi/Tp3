package com.example.secondproj.web;

import com.example.secondproj.PatientRepo.PatientRepo;
import com.example.secondproj.entities.Patient;
import lombok.AllArgsConstructor;
import org.springframework.boot.context.properties.bind.BindResult;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import java.util.Date;
import java.util.List;

@Controller
@AllArgsConstructor
public class PatientController {
    private PatientRepo patientRepo;

    @GetMapping("/index")
    public String Patient(Model model, @RequestParam(name="page",defaultValue = "0") int page,@RequestParam(name="size",defaultValue = "5") int size,@RequestParam(name="keyword",defaultValue = "") String keyword)
    {
        Page<Patient> p=patientRepo.findByNomContains(keyword,PageRequest.of(page,size));

         model.addAttribute("mypatients",p.getContent());
         model.addAttribute("nbpage",new int [5]);
        model.addAttribute("currentpage",page);
        model.addAttribute("keyword",keyword);
        return "Patient";
    }

    @GetMapping("/delete")
    public String delete(Long id,@RequestParam(name="page",defaultValue = "0") int page,@RequestParam(name="keyword",defaultValue = "") String keyword)
    {
        patientRepo.deleteById(id);
        return "redirect:/index?page="+page+"&keyword="+keyword;
    }
    @GetMapping("/formPatients")
    public String formPatients(Model model)
    {
        model.addAttribute("patient",new Patient());
        return "formPatients";
    }
    @PostMapping(path = "/save")
    public String save(Model model, @Valid Patient p, BindingResult b,@RequestParam(name="page",defaultValue = "0") int page,@RequestParam(name="keyword",defaultValue = "") String keyword)
    {
        if(b.hasErrors())
            return "formPatients";
        patientRepo.save(p);
        return "redirect:/index?page="+page+"&keyword="+keyword;
    }
    @GetMapping("/edit")
    public String Edit(Model model,long id,@RequestParam(name="page",defaultValue = "0") int page,@RequestParam(name="keyword",defaultValue = "") String keyword)
    {
        Patient p=patientRepo.findById(id).get();
        model.addAttribute("patient",p);
        model.addAttribute("currentpage",page);
        model.addAttribute("keyword",keyword);
        return "Edit";
    }
    @GetMapping("/mv")
    public String showmv()
    {
        return "movie";
    }
    @GetMapping("/")
    public String home()
    {
        return "home";
    }
}
