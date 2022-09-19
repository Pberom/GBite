package com.example.GBite.controllers;

import com.example.GBite.models.Brone;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/Brone")
public class BroneController {
    @Autowired
    private com.example.GBite.repositories.broneRepository broneRepository;
    @GetMapping("/")
    public String index(Model model)
    {
        Iterable<Brone> Brone = broneRepository.findAll();
        model.addAttribute("Brone",Brone);
        return "Brone/Brone";
    }
    @GetMapping("/add")
    public String AddGet(Model model)
    {
        model.addAttribute("Brone", new Brone());
        return "Brone/add";
    }
    @GetMapping("/{id}")
    public String read (@PathVariable("id") Long id, Model model) {
        Optional<Brone> Brone = broneRepository.findById(id);
        ArrayList<Brone> arrayList = new ArrayList<>();
        Brone.ifPresent(arrayList::add);
        model.addAttribute("Brone", arrayList);
        return "Brone/details";
    }
    @GetMapping("/edit/{id}")
    public String edit (@PathVariable("id") Long id,
                        Model model) {
        if (!broneRepository.existsById(id)) {
            return "redirect:/Brone/";
        }
        Optional<Brone> Brone = broneRepository.findById(id);
        ArrayList<Brone> arrayList = new ArrayList<>();
        Brone.ifPresent(arrayList::add);
        model.addAttribute("Brone", arrayList.get(0));
        return "Brone/edit";
    }
    @GetMapping("/delete/{id}")
    public String delete (@PathVariable("id") Long id, Model model) {
        broneRepository.deleteById(id);
        return "redirect:/Brone/";
    }
    @PostMapping("/add")
    public String AddPost(
            @ModelAttribute("Brone") @Valid Brone newsNews,
            BindingResult bindingResult,
            Model model)
    {
        if(bindingResult.hasErrors())
            return "Brone/add";

        broneRepository.save(newsNews);
        return "redirect:/Brone/";
    }

    @PostMapping("/edit/{id}")
    public String edit (@PathVariable("id") Long id,
                        @ModelAttribute("Brone") @Valid Brone newOne, BindingResult bindingResult, Model model) {

        if (!broneRepository.existsById(id)) {
            return "redirect:/Brone/";
        }
        if (bindingResult.hasErrors()) {
            return "redirect:/Brone/edit/{id}";
        }
        broneRepository.save(newOne);
        return "redirect:/Brone/";
    }

    @GetMapping("/search")
    public String AddPost(
            @RequestParam("title") String title,
            Model model)
    {
        List<Brone> newsList = broneRepository.findByTitle(title);
        model.addAttribute("Brone", newsList);
        return "Brone/Brone";
    }
}

