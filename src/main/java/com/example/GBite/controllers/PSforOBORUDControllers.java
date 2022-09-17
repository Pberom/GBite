package com.example.GBite.controllers;

import com.example.GBite.models.psforoborud;
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
@RequestMapping("/psforoborud")
public class PSforOBORUDControllers {
    @Autowired
    private com.example.GBite.repositories.psforoborudRepository psforoborudRepository;
    @GetMapping("/")
    public String index(Model model)
    {
        Iterable<psforoborud> psforoborud = psforoborudRepository.findAll();
        model.addAttribute("psforoborud",psforoborud);
        return "psforoborud/psforoborud";
    }
    @GetMapping("/add")
    public String AddGet(Model model)
    {
        model.addAttribute("psforoborud", new psforoborud());
        return "psforoborud/add";
    }
    @GetMapping("/{id}")
    public String read (@PathVariable("id") Long id, Model model) {
        Optional<psforoborud> psforoborud = psforoborudRepository.findById(id);
        ArrayList<psforoborud> arrayList = new ArrayList<>();
        psforoborud.ifPresent(arrayList::add);
        model.addAttribute("psforoborud", arrayList);
        return "psforoborud/details";
    }
    @GetMapping("/edit/{id}")
    public String edit (@PathVariable("id") Long id,
                        Model model) {
        if (!psforoborudRepository.existsById(id)) {
            return "redirect:/psforoborud/";
        }
        Optional<psforoborud> psforoborud = psforoborudRepository.findById(id);
        ArrayList<psforoborud> arrayList = new ArrayList<>();
        psforoborud.ifPresent(arrayList::add);
        model.addAttribute("psforoborud", arrayList.get(0));
        return "psforoborud/edit";
    }
    @GetMapping("/delete/{id}")
    public String delete (@PathVariable("id") Long id, Model model) {
        psforoborudRepository.deleteById(id);
        return "redirect:/psforoborud/";
    }
    @PostMapping("/add")
    public String AddPost(
            @ModelAttribute("psforoborud") @Valid psforoborud newsNews,
            BindingResult bindingResult,
            Model model)
    {
        if(bindingResult.hasErrors())
            return "psforoborud/add";

        psforoborudRepository.save(newsNews);
        return "redirect:/psforoborud/";
    }

    @PostMapping("/edit/{id}")
    public String edit (@PathVariable("id") Long id,
                        @ModelAttribute("psforoborud") @Valid psforoborud newOne, BindingResult bindingResult, Model model) {

        if (!psforoborudRepository.existsById(id)) {
            return "redirect:/psforoborud/";
        }
        if (bindingResult.hasErrors()) {
            return "redirect:/psforoborud/edit/{id}";
        }
        psforoborudRepository.save(newOne);
        return "redirect:/psforoborud/";
    }

    @GetMapping("/search")
    public String AddPost(
            @RequestParam("title") String title,
            Model model)
    {
        List<psforoborud> newsList = psforoborudRepository.findByTitle(title);
        model.addAttribute("psforoborud", newsList);
        return "psforoborud/psforoborud";
    }
}
