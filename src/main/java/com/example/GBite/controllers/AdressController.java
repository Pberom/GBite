package com.example.GBite.controllers;

import com.example.GBite.models.adress;
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
@RequestMapping("/adress")
public class AdressController {
    @Autowired
    private com.example.GBite.repositories.adressReposutory adressReposutory;
    @GetMapping("/")
    public String index(Model model)
    {
        Iterable<adress> adress = adressReposutory.findAll();
        model.addAttribute("adress",adress);
        return "adress/adress";
    }
    @GetMapping("/add")
    public String AddGet(Model model)
    {
        model.addAttribute("adress", new adress());
        return "adress/add";
    }
    @GetMapping("/{id}")
    public String read (@PathVariable("id") Long id, Model model) {
        Optional<adress> adress = adressReposutory.findById(id);
        ArrayList<adress> arrayList = new ArrayList<>();
        adress.ifPresent(arrayList::add);
        model.addAttribute("adress", arrayList);
        return "adress/details";
    }
    @GetMapping("/edit/{id}")
    public String edit (@PathVariable("id") Long id,
                        Model model) {
        if (!adressReposutory.existsById(id)) {
            return "redirect:/adress/";
        }
        Optional<adress> adress = adressReposutory.findById(id);
        ArrayList<adress> arrayList = new ArrayList<>();
        adress.ifPresent(arrayList::add);
        model.addAttribute("adress", arrayList.get(0));
        return "adress/edit";
    }
    @GetMapping("/delete/{id}")
    public String delete (@PathVariable("id") Long id, Model model) {
        adressReposutory.deleteById(id);
        return "redirect:/adress/";
    }
    @PostMapping("/add")
    public String AddPost(
            @ModelAttribute("adress") @Valid adress newsNews,
            BindingResult bindingResult,
            Model model)
    {
        if(bindingResult.hasErrors())
            return "adress/add";

        adressReposutory.save(newsNews);
        return "redirect:/adress/";
    }

    @PostMapping("/edit/{id}")
    public String edit (@PathVariable("id") Long id,
                        @ModelAttribute("adress") @Valid adress newOne, BindingResult bindingResult, Model model) {

        if (!adressReposutory.existsById(id)) {
            return "redirect:/adress/";
        }
        if (bindingResult.hasErrors()) {
            return "redirect:/adress/edit/{id}";
        }
        adressReposutory.save(newOne);
        return "redirect:/adress/";
    }

    @GetMapping("/search")
    public String AddPost(
            @RequestParam("title") String title,
            Model model)
    {
        List<adress> newsList = adressReposutory.findByTitle(title);
        model.addAttribute("adress", newsList);
        return "adress/adress";
    }
}

