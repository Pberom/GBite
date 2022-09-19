package com.example.GBite.controllers;

import com.example.GBite.models.persdan;
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
@RequestMapping("/persdan")
public class persdanController {
    @Autowired
    private com.example.GBite.repositories.persdannRepository persdannRepository;
    @GetMapping("/")
    public String index(Model model)
    {
        Iterable<persdan> persdan = persdannRepository.findAll();
        model.addAttribute("persdan",persdan);
        return "persdan/persdan";
    }
    @GetMapping("/add")
    public String AddGet(Model model)
    {
        model.addAttribute("persdan", new persdan());
        return "persdan/add";
    }
    @GetMapping("/{id}")
    public String read (@PathVariable("id") Long id, Model model) {
        Optional<persdan> persdan = persdannRepository.findById(id);
        ArrayList<persdan> arrayList = new ArrayList<>();
        persdan.ifPresent(arrayList::add);
        model.addAttribute("persdan", arrayList);
        return "persdan/details";
    }
    @GetMapping("/edit/{id}")
    public String edit (@PathVariable("id") Long id,
                        Model model) {
        if (!persdannRepository.existsById(id)) {
            return "redirect:/persdan/";
        }
        Optional<persdan> persdan = persdannRepository.findById(id);
        ArrayList<persdan> arrayList = new ArrayList<>();
        persdan.ifPresent(arrayList::add);
        model.addAttribute("persdan", arrayList.get(0));
        return "persdan/edit";
    }
    @GetMapping("/delete/{id}")
    public String delete (@PathVariable("id") Long id, Model model) {
        persdannRepository.deleteById(id);
        return "redirect:/persdan/";
    }
    @PostMapping("/add")
    public String AddPost(
            @ModelAttribute("persdan") @Valid persdan newsNews,
            BindingResult bindingResult,
            Model model)
    {
        if(bindingResult.hasErrors())
            return "persdan/add";

        persdannRepository.save(newsNews);
        return "redirect:/persdan/";
    }

    @PostMapping("/edit/{id}")
    public String edit (@PathVariable("id") Long id,
                        @ModelAttribute("persdan") @Valid persdan newOne, BindingResult bindingResult, Model model) {

        if (!persdannRepository.existsById(id)) {
            return "redirect:/persdan/";
        }
        if (bindingResult.hasErrors()) {
            return "redirect:/persdan/edit/{id}";
        }
        persdannRepository.save(newOne);
        return "redirect:/persdan/";
    }

    @GetMapping("/search")
    public String AddPost(
            @RequestParam("title") String title,
            Model model)
    {
        List<persdan> newsList = persdannRepository.findByTitle(title);
        model.addAttribute("persdan", newsList);
        return "persdan/persdan";
    }
}
