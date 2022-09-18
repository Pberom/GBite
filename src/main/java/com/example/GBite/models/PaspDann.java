package com.example.GBite.models;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Entity
public class PaspDann {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @NotEmpty(message = "Поле не может быть пустым!")
    @Size(message = "Строка не может быть меньше 4 и больше 4", min = 6, max = 6)
    String title;

    @NotEmpty(message = "Поле не может быть пустым!")
    @Size(message = "Строка не может быть меньше", min = 4, max = 4)
    String NomPasp;

    @OneToOne(optional = true, mappedBy = "PaspDann")
    public persdan persdan;

    public PaspDann(String title, String nomPasp) {
        this.title = title;
        NomPasp = nomPasp;
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

    public String getNomPasp() {
        return NomPasp;
    }

    public void setNomPasp(String nomPasp) {
        NomPasp = nomPasp;
    }

    public PaspDann() {
    }
}
