package com.example.GBite.repositories;

import com.example.GBite.models.periods;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface periodsRepository extends CrudRepository<periods, Long> {
    public List<periods> findByTitle(String title);
}
