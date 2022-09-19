package com.example.GBite.repositories;

import com.example.GBite.models.PaspDann;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface paspdannRepository extends CrudRepository<PaspDann, Long> {
    public List<PaspDann> findByTitle(String title);
}