package com.example.GBite.controllers;


import com.example.GBite.models.Oborud;
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
@RequestMapping("/Oborud")
public class OborudController {
    @Autowired
    private com.example.GBite.repositories.oborudRepository oborudRepository;
    @GetMapping("/")
    public String index(Model model)
    {
        Iterable<Oborud> Oborud = oborudRepository.findAll();
        model.addAttribute("Oborud",Oborud);
        return "Oborud/Oborud";
    }
    @GetMapping("/add")
    public String AddGet(Model model)
    {
        model.addAttribute("Oborud", new Oborud());
        return "Oborud/add";
    }
    @GetMapping("/{id}")
    public String read (@PathVariable("id") Long id, Model model) {
        Optional<Oborud> Oborud = oborudRepository.findById(id);
        ArrayList<Oborud> arrayList = new ArrayList<>();
        Oborud.ifPresent(arrayList::add);
        model.addAttribute("Oborud", arrayList);
        return "Oborud/details";
    }
    @GetMapping("/edit/{id}")
    public String edit (@PathVariable("id") Long id,
                        Model model) {
        if (!oborudRepository.existsById(id)) {
            return "redirect:/Oborud/";
        }
        Optional<Oborud> Oborud = oborudRepository.findById(id);
        ArrayList<Oborud> arrayList = new ArrayList<>();
        Oborud.ifPresent(arrayList::add);
        model.addAttribute("Oborud", arrayList.get(0));
        return "Oborud/edit";
    }
    @GetMapping("/delete/{id}")
    public String delete (@PathVariable("id") Long id, Model model) {
        oborudRepository.deleteById(id);
        return "redirect:/Oborud/";
    }
    @PostMapping("/add")
    public String AddPost(
            @ModelAttribute("Oborud") @Valid Oborud newsNews,
            BindingResult bindingResult,
            Model model)
    {
        if(bindingResult.hasErrors())
            return "Oborud/add";

        oborudRepository.save(newsNews);
        return "redirect:/Oborud/";
    }

    @PostMapping("/edit/{id}")
    public String edit (@PathVariable("id") Long id,
                        @ModelAttribute("Oborud") @Valid Oborud newOne, BindingResult bindingResult, Model model) {

        if (!oborudRepository.existsById(id)) {
            return "redirect:/Oborud/";
        }
        if (bindingResult.hasErrors()) {
            return "redirect:/Oborud/edit/{id}";
        }
        oborudRepository.save(newOne);
        return "redirect:/Oborud/";
    }

    @GetMapping("/search")
    public String AddPost(
            @RequestParam("title") String title,
            Model model)
    {
        List<Oborud> newsList = oborudRepository.findByTitle(title);
        model.addAttribute("Oborud", newsList);
        return "Oborud/Oborud";
    }
}
