package com.example.GBite.repositories;

import com.example.GBite.models.psforoborud;
import org.springframework.data.repository.CrudRepository;
import java.util.List;

public interface psforoborudRepository extends CrudRepository<psforoborud, Long> {
    public List<psforoborud> findByTitle(String title);
}
