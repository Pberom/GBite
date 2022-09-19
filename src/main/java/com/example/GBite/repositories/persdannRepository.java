package com.example.GBite.repositories;

import com.example.GBite.models.PaspDann;
import com.example.GBite.models.persdan;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface persdannRepository extends CrudRepository<persdan, Long> {
    public List<persdan> findByTitle(String title);
}