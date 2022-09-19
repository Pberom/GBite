package com.example.GBite.controllers;

import com.example.GBite.models.Members;
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
@RequestMapping("/Members")
public class membersController {
    @Autowired
    private com.example.GBite.repositories.membersRepository membersRepository;
    @GetMapping("/")
    public String index(Model model)
    {
        Iterable<Members> Members = membersRepository.findAll();
        model.addAttribute("Members",Members);
        return "Members/Members";
    }
    @GetMapping("/add")
    public String AddGet(Model model)
    {
        model.addAttribute("Members", new Members());
        return "Members/add";
    }
    @GetMapping("/{id}")
    public String read (@PathVariable("id") Long id, Model model) {
        Optional<Members> Members = membersRepository.findById(id);
        ArrayList<Members> arrayList = new ArrayList<>();
        Members.ifPresent(arrayList::add);
        model.addAttribute("Members", arrayList);
        return "Members/details";
    }
    @GetMapping("/edit/{id}")
    public String edit (@PathVariable("id") Long id,
                        Model model) {
        if (!membersRepository.existsById(id)) {
            return "redirect:/Members/";
        }
        Optional<Members> Members = membersRepository.findById(id);
        ArrayList<Members> arrayList = new ArrayList<>();
        Members.ifPresent(arrayList::add);
        model.addAttribute("Members", arrayList.get(0));
        return "Members/edit";
    }
    @GetMapping("/delete/{id}")
    public String delete (@PathVariable("id") Long id, Model model) {
        membersRepository.deleteById(id);
        return "redirect:/Members/";
    }
    @PostMapping("/add")
    public String AddPost(
            @ModelAttribute("Members") @Valid Members newsNews,
            BindingResult bindingResult,
            Model model)
    {
        if(bindingResult.hasErrors())
            return "Members/add";

        membersRepository.save(newsNews);
        return "redirect:/Members/";
    }

    @PostMapping("/edit/{id}")
    public String edit (@PathVariable("id") Long id,
                        @ModelAttribute("Members") @Valid Members newOne, BindingResult bindingResult, Model model) {

        if (!membersRepository.existsById(id)) {
            return "redirect:/Members/";
        }
        if (bindingResult.hasErrors()) {
            return "redirect:/Members/edit/{id}";
        }
        membersRepository.save(newOne);
        return "redirect:/Members/";
    }

    @GetMapping("/search")
    public String AddPost(
            @RequestParam("title") String title,
            Model model)
    {
        List<Members> newsList = membersRepository.findByTitle(title);
        model.addAttribute("Members", newsList);
        return "Members/Members";
    }
}
