package com.example.GBite.repositories;

import com.example.GBite.models.post;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface postRepository extends CrudRepository<post, Long> {
    public List<post> findByTitle(String title);
}