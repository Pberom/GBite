package com.example.GBite.repositories;

import com.example.GBite.models.filials;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface filialsRepository extends CrudRepository<filials, Long> {
    public List<filials> findByTitle(String title);
}