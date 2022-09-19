package com.example.GBite.repositories;

import com.example.GBite.models.Members;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface membersRepository extends CrudRepository<Members, Long> {
    public List<Members> findByTitle(String title);
}