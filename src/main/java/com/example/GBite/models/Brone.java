package com.example.GBite.models;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Entity
public class Brone {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @NotEmpty(message = "Поле не может быть пустым!")
    @Size(message = "Строка не может быть меньше 4 и больше 4", min = 6, max = 6)
    String title;

    @ManyToOne(fetch = FetchType.EAGER)
    periods periods;

    @OneToOne(optional = true, cascade = CascadeType.ALL)
    public User User;

    public Brone(com.example.GBite.models.periods periods) {
        this.periods = periods;
    }

    public com.example.GBite.models.periods getPeriods() {
        return periods;
    }

    public void setPeriods(com.example.GBite.models.periods periods) {
        this.periods = periods;
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

    public Brone(String title) {
        this.title = title;
    }

    public Brone() {
    }
}
