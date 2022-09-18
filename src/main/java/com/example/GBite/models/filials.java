package com.example.GBite.models;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.Collection;

@Entity
public class filials {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @NotEmpty(message = "Поле не может быть пустым!")
    @Size(message = "Строка не может быть меньше", min = 2, max = 1000)
    String title;

    @OneToOne(optional = true, cascade = CascadeType.ALL)
    public adress adress;

    @OneToMany(mappedBy = "title", fetch = FetchType.EAGER)
    Collection<Members> members;

    public filials(Collection<Members> members) {
        this.members = members;
    }

    public Collection<Members> getMembers() {
        return members;
    }

    public void setMembers(Collection<Members> members) {
        this.members = members;
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

    public filials() {
    }

    public filials(String title) {
        this.title = title;
    }
}
