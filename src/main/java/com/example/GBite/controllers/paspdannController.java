package com.example.GBite.controllers;


import com.example.GBite.models.PaspDann;
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
@RequestMapping("/PaspDann")
public class paspdannController {

    @Autowired
    private com.example.GBite.repositories.paspdannRepository paspdannRepository;
    @GetMapping("/")
    public String index(Model model)
    {
        Iterable<PaspDann> PaspDann = paspdannRepository.findAll();
        model.addAttribute("PaspDann",PaspDann);
        return "PaspDann/PaspDann";
    }
    @GetMapping("/add")
    public String AddGet(Model model)
    {
        model.addAttribute("PaspDann", new PaspDann());
        return "PaspDann/add";
    }
    @GetMapping("/{id}")
    public String read (@PathVariable("id") Long id, Model model) {
        Optional<PaspDann> PaspDann = paspdannRepository.findById(id);
        ArrayList<PaspDann> arrayList = new ArrayList<>();
        PaspDann.ifPresent(arrayList::add);
        model.addAttribute("Oborud", arrayList);
        return "Oborud/details";
    }
    @GetMapping("/edit/{id}")
    public String edit (@PathVariable("id") Long id,
                        Model model) {
        if (!paspdannRepository.existsById(id)) {
            return "redirect:/PaspDann/";
        }
        Optional<PaspDann> PaspDann = paspdannRepository.findById(id);
        ArrayList<PaspDann> arrayList = new ArrayList<>();
        PaspDann.ifPresent(arrayList::add);
        model.addAttribute("PaspDann", arrayList.get(0));
        return "PaspDann/edit";
    }
    @GetMapping("/delete/{id}")
    public String delete (@PathVariable("id") Long id, Model model) {
        paspdannRepository.deleteById(id);
        return "redirect:/PaspDann/";
    }
    @PostMapping("/add")
    public String AddPost(
            @ModelAttribute("PaspDann") @Valid PaspDann newsNews,
            BindingResult bindingResult,
            Model model)
    {
        if(bindingResult.hasErrors())
            return "PaspDann/add";

        paspdannRepository.save(newsNews);
        return "redirect:/PaspDann/";
    }

    @PostMapping("/edit/{id}")
    public String edit (@PathVariable("id") Long id,
                        @ModelAttribute("PaspDann") @Valid PaspDann newOne, BindingResult bindingResult, Model model) {

        if (!paspdannRepository.existsById(id)) {
            return "redirect:/PaspDann/";
        }
        if (bindingResult.hasErrors()) {
            return "redirect:/PaspDann/edit/{id}";
        }
        paspdannRepository.save(newOne);
        return "redirect:/PaspDann/";
    }

    @GetMapping("/search")
    public String AddPost(
            @RequestParam("title") String title,
            Model model)
    {
        List<PaspDann> newsList = paspdannRepository.findByTitle(title);
        model.addAttribute("PaspDann", newsList);
        return "PaspDann/PaspDann";
    }
}

