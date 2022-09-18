package com.example.GBite.models;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Entity
public class Members {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @NotEmpty(message = "Поле не может быть пустым!")
    @Size(message = "Строка не может быть меньше", min = 2, max = 1000)
    String title;


    @ManyToOne(fetch = FetchType.EAGER)
    filials filials;

    public Members(com.example.GBite.models.filials filials) {
        this.filials = filials;
    }

    public com.example.GBite.models.filials getFilials() {
        return filials;
    }

    public void setFilials(com.example.GBite.models.filials filials) {
        this.filials = filials;
    }

    @ManyToOne(fetch = FetchType.EAGER)
    post post;

    public com.example.GBite.models.post getPost() {
        return post;
    }

    public void setPost(com.example.GBite.models.post post) {
        this.post = post;
    }

    public Members(com.example.GBite.models.post post) {
        this.post = post;
    }

    public Members() {
    }

    public Members(String title) {
        this.title = title;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
