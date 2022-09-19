package com.example.GBite.repositories;

import com.example.GBite.models.Brone;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface broneRepository extends CrudRepository<Brone, Long> {
    public List<Brone> findByTitle(String title);
}