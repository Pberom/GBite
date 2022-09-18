package com.example.GBite.models;

import javax.persistence.*;
import javax.validation.constraints.*;

@Entity
public class persdan {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @NotEmpty(message = "Поле не может быть пустым!")
    @Size(message = "Строка не может быть меньше", min = 2, max = 1000)
    String title, secondname, middlename;

    @Min(message = "Число не может быть меньше 14", value = 14)
    @Max(message = "Число не может быть слишком большим!", value = 100)
    @NotNull(message = "Поле не может быть пустым!")
    Integer age;

    @OneToOne(optional = true, cascade = CascadeType.ALL)
    public PaspDann PaspDann;

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

    public String getSecondname() {
        return secondname;
    }

    public void setSecondname(String secondname) {
        this.secondname = secondname;
    }

    public String getMiddlename() {
        return middlename;
    }

    public void setMiddlename(String middlename) {
        this.middlename = middlename;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public persdan(String title, String secondname, String middlename, Integer age) {
        this.title = title;
        this.secondname = secondname;
        this.middlename = middlename;
        this.age = age;
    }

    public persdan() {
    }
}
