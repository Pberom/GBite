package com.example.GBite.models;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.Collection;

@Entity
public class periods {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @NotEmpty(message = "Поле не может быть пустым!")
    @Size(message = "Строка не может быть меньше", min = 4, max = 1000)
    String title;
    @NotEmpty(message = "Дата должна быть выбрана")
    @Size(message = "Дата должна соответствовать формату DD/MM/YY", min = 7, max = 1000)
    String end;

    @OneToMany(mappedBy = "title", fetch = FetchType.EAGER)
    Collection<Brone> brones;

    public Collection<Brone> getBrones() {
        return brones;
    }

    public void setBrones(Collection<Brone> brones) {
        this.brones = brones;
    }

    public periods(Collection<Brone> brones) {
        this.brones = brones;
    }

    public periods() {
    }

    public periods(String title, String end) {
        this.title = title;
        this.end = end;
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

    public String getEnd() {
        return end;
    }

    public void setEnd(String end) {
        this.end = end;
    }
}
