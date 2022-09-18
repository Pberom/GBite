package com.example.GBite.models;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.Collection;

@Entity
public class Oborud {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @NotEmpty(message = "Поле не может быть пустым!")
    @Size(message = "Строка не может быть меньше", min = 2, max = 1000)
    String title;

    @Min(message = "Число не может быть отрицательным", value = 0)
    @Max(message = "Число не может быть слишком большим!", value = 1000)
    @NotNull(message = "Поле не может быть пустым!")
    Integer KolVo;

    @OneToOne(optional = true, cascade = CascadeType.ALL)
    public psforoborud psforoborud;

    @OneToOne(optional = true, mappedBy = "Oborud")
    public adress adress;

    @OneToOne(optional = true, mappedBy = "Oborud")
    public persdan persdan;

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

    public Integer getKolVo() {
        return KolVo;
    }

    public void setKolVo(Integer kolVo) {
        KolVo = kolVo;
    }

    public Oborud() {
    }

    public Oborud(String title, Integer kolVo) {
        this.title = title;
        KolVo = kolVo;
    }
}
