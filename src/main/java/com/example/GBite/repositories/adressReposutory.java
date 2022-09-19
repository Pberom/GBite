package com.example.GBite.repositories;

import com.example.GBite.models.adress;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface adressReposutory extends CrudRepository<adress, Long> {
    public List<adress> findByTitle(String title);
}