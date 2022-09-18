package com.example.GBite.controllers;

import com.example.GBite.models.post;
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
@RequestMapping("/post")
public class PostController {
    @Autowired
    private com.example.GBite.repositories.postRepository postRepository;
    @GetMapping("/")
    public String index(Model model)
    {
        Iterable<post> post = postRepository.findAll();
        model.addAttribute("post",post);
        return "post/post";
    }
    @GetMapping("/add")
    public String AddGet(Model model)
    {
        model.addAttribute("post", new post());
        return "post/add";
    }
    @GetMapping("/{id}")
    public String read (@PathVariable("id") Long id, Model model) {
        Optional<post> post = postRepository.findById(id);
        ArrayList<post> arrayList = new ArrayList<>();
        post.ifPresent(arrayList::add);
        model.addAttribute("post", arrayList);
        return "post/details";
    }
    @GetMapping("/edit/{id}")
    public String edit (@PathVariable("id") Long id,
                        Model model) {
        if (!postRepository.existsById(id)) {
            return "redirect:/post/";
        }
        Optional<post> post = postRepository.findById(id);
        ArrayList<post> arrayList = new ArrayList<>();
        post.ifPresent(arrayList::add);
        model.addAttribute("post", arrayList.get(0));
        return "post/edit";
    }
    @GetMapping("/delete/{id}")
    public String delete (@PathVariable("id") Long id, Model model) {
        postRepository.deleteById(id);
        return "redirect:/post/";
    }
    @PostMapping("/add")
    public String AddPost(
            @ModelAttribute("periods") @Valid post newsNews,
            BindingResult bindingResult,
            Model model)
    {
        if(bindingResult.hasErrors())
            return "post/add";

        postRepository.save(newsNews);
        return "redirect:/post/";
    }

    @PostMapping("/edit/{id}")
    public String edit (@PathVariable("id") Long id,
                        @ModelAttribute("periods") @Valid post newOne, BindingResult bindingResult, Model model) {

        if (!postRepository.existsById(id)) {
            return "redirect:/post/";
        }
        if (bindingResult.hasErrors()) {
            return "redirect:/post/edit/{id}";
        }
        postRepository.save(newOne);
        return "redirect:/post/";
    }

    @GetMapping("/search")
    public String AddPost(
            @RequestParam("title") String title,
            Model model)
    {
        List<post> newsList = postRepository.findByTitle(title);
        model.addAttribute("post", newsList);
        return "post/post";
    }
}

