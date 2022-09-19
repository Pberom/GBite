package com.example.GBite.repositories;

import com.example.GBite.models.Oborud;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface oborudRepository extends CrudRepository<Oborud, Long> {
    public List<Oborud> findByTitle(String title);
}