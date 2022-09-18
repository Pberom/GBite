package com.example.GBite.models;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.Collection;

@Entity
public class adress {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @NotEmpty(message = "Поле не может быть пустым!")
    @Size(message = "Строка не может быть меньше", min = 2, max = 1000)
    String title, fuadress;

    @OneToOne(optional = true, mappedBy = "adress")
    public filials filials;

    @OneToOne(optional = true, cascade = CascadeType.ALL)
    public Oborud Oborud;

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

    public String getFuadress() {
        return fuadress;
    }

    public void setFuadress(String fuadress) {
        this.fuadress = fuadress;
    }

    public adress(String title, String fuadress) {
        this.title = title;
        this.fuadress = fuadress;
    }

    public adress() {
    }
}
