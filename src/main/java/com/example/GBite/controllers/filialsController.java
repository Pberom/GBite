package com.example.GBite.controllers;


import com.example.GBite.models.filials;
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
@RequestMapping("/filials")
public class filialsController {
    @Autowired
    private com.example.GBite.repositories.filialsRepository filialsRepository;
    @GetMapping("/")
    public String index(Model model)
    {
        Iterable<filials> filials = filialsRepository.findAll();
        model.addAttribute("filials",filials);
        return "filials/filials";
    }
    @GetMapping("/add")
    public String AddGet(Model model)
    {
        model.addAttribute("filials", new filials());
        return "filials/add";
    }
    @GetMapping("/{id}")
    public String read (@PathVariable("id") Long id, Model model) {
        Optional<filials> filials = filialsRepository.findById(id);
        ArrayList<filials> arrayList = new ArrayList<>();
        filials.ifPresent(arrayList::add);
        model.addAttribute("filials", arrayList);
        return "filials/details";
    }
    @GetMapping("/edit/{id}")
    public String edit (@PathVariable("id") Long id,
                        Model model) {
        if (!filialsRepository.existsById(id)) {
            return "redirect:/filials/";
        }
        Optional<filials> filials = filialsRepository.findById(id);
        ArrayList<filials> arrayList = new ArrayList<>();
        filials.ifPresent(arrayList::add);
        model.addAttribute("filials", arrayList.get(0));
        return "filials/edit";
    }
    @GetMapping("/delete/{id}")
    public String delete (@PathVariable("id") Long id, Model model) {
        filialsRepository.deleteById(id);
        return "redirect:/filials/";
    }
    @PostMapping("/add")
    public String AddPost(
            @ModelAttribute("filials") @Valid filials newsNews,
            BindingResult bindingResult,
            Model model)
    {
        if(bindingResult.hasErrors())
            return "filials/add";

        filialsRepository.save(newsNews);
        return "redirect:/filials/";
    }

    @PostMapping("/edit/{id}")
    public String edit (@PathVariable("id") Long id,
                        @ModelAttribute("filials") @Valid filials newOne, BindingResult bindingResult, Model model) {

        if (!filialsRepository.existsById(id)) {
            return "redirect:/filials/";
        }
        if (bindingResult.hasErrors()) {
            return "redirect:/filials/edit/{id}";
        }
        filialsRepository.save(newOne);
        return "redirect:/filials/";
    }

    @GetMapping("/search")
    public String AddPost(
            @RequestParam("title") String title,
            Model model)
    {
        List<filials> newsList = filialsRepository.findByTitle(title);
        model.addAttribute("filials", newsList);
        return "filials/filials";
    }
}