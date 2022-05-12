package com.example.secondproj.web;

import com.example.secondproj.PatientRepo.MedecinRepo;
import com.example.secondproj.entities.Medecin;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import java.util.List;

@Controller
@AllArgsConstructor

public class MedecinController {
    private MedecinRepo medecinRepo;

    @GetMapping("/Admin/Medecin/Create")
    public String createMedecin(Model model)
    {
        model.addAttribute("med",new Medecin());
        return "MedecinForm";
    }
    @PostMapping("/Admin/Medecin/Create")
    public String createMedecinPost(Model model, @Valid  Medecin medecin, BindingResult bdr,@RequestParam(name = "page",defaultValue = "0") int page,@RequestParam(name = "chercher",defaultValue = "") String chercher )
    {
        if(bdr.hasErrors()) {
            return "MedecinForm";
        }
        medecinRepo.save(medecin);
        return "redirect:/User/Medecin?page="+page+"&chercher="+chercher;
    }

    @GetMapping("/User/Medecin/Edit")
    public String EditMedecin(Model model,Long id,@RequestParam(name = "page",defaultValue = "0") int page,@RequestParam(name = "chercher",defaultValue = "") String chercher)
    {
        Medecin med= medecinRepo.findById(id).orElse(null);
        model.addAttribute("currentpage",page);
        model.addAttribute("chercher",chercher);
        model.addAttribute("med",med);
        return "MedecinForm";
    }
    @GetMapping("/User/Medecin")
    public String ListMedecin(Model model,@RequestParam(name = "page",defaultValue = "0") int page,@RequestParam(name = "chercher",defaultValue = "") String chercher)
    {
        Page<Medecin> med= medecinRepo.findByNomContains(chercher,PageRequest.of(page,4));
        model.addAttribute("med",med);
        model.addAttribute("size",new int [med.getTotalPages()]);
        model.addAttribute("chercher",chercher);
        model.addAttribute("currentpage",page);
        return "Medecin";
    }

    @GetMapping("/User/Medecin/Delete")
    public String DeleteMedecin(Model model,Long id,@RequestParam(name = "page",defaultValue = "0") int page) {
        medecinRepo.deleteById(id);
        return "redirect:/User/Medecin?page="+page;
    }
}
