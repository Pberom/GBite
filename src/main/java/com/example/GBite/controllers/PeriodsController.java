package com.example.GBite.controllers;

import com.example.GBite.models.periods;
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
@RequestMapping("/periods")
public class PeriodsController {
    @Autowired
    private com.example.GBite.repositories.periodsRepository periodsRepository;
    @GetMapping("/")
    public String index(Model model)
    {
        Iterable<periods> periods = periodsRepository.findAll();
        model.addAttribute("periods",periods);
        return "periods/periods";
    }
    @GetMapping("/add")
    public String AddGet(Model model)
    {
        model.addAttribute("periods", new periods());
        return "periods/add";
    }
    @GetMapping("/{id}")
    public String read (@PathVariable("id") Long id, Model model) {
        Optional<periods> periods = periodsRepository.findById(id);
        ArrayList<periods> arrayList = new ArrayList<>();
        periods.ifPresent(arrayList::add);
        model.addAttribute("psforoborud", arrayList);
        return "psforoborud/details";
    }
    @GetMapping("/edit/{id}")
    public String edit (@PathVariable("id") Long id,
                        Model model) {
        if (!periodsRepository.existsById(id)) {
            return "redirect:/periods/";
        }
        Optional<periods> periods = periodsRepository.findById(id);
        ArrayList<periods> arrayList = new ArrayList<>();
        periods.ifPresent(arrayList::add);
        model.addAttribute("periods", arrayList.get(0));
        return "periods/edit";
    }
    @GetMapping("/delete/{id}")
    public String delete (@PathVariable("id") Long id, Model model) {
        periodsRepository.deleteById(id);
        return "redirect:/periods/";
    }
    @PostMapping("/add")
    public String AddPost(
            @ModelAttribute("periods") @Valid periods newsNews,
            BindingResult bindingResult,
            Model model)
    {
        if(bindingResult.hasErrors())
            return "periods/add";

        periodsRepository.save(newsNews);
        return "redirect:/periods/";
    }

    @PostMapping("/edit/{id}")
    public String edit (@PathVariable("id") Long id,
                        @ModelAttribute("periods") @Valid periods newOne, BindingResult bindingResult, Model model) {

        if (!periodsRepository.existsById(id)) {
            return "redirect:/periods/";
        }
        if (bindingResult.hasErrors()) {
            return "redirect:/periods/edit/{id}";
        }
        periodsRepository.save(newOne);
        return "redirect:/periods/";
    }

    @GetMapping("/search")
    public String AddPost(
            @RequestParam("title") String title,
            Model model)
    {
        List<periods> newsList = periodsRepository.findByTitle(title);
        model.addAttribute("periods", newsList);
        return "periods/periods";
    }
}
