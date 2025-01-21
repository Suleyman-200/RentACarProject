package com.example.rentacar.repositories;

import com.example.rentacar.models.Brand;

import com.example.rentacar.models.Model;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ModelRepository extends JpaRepository<Model, Integer> {
    boolean existsByNameAndBrand(String name, Brand brand);
    Optional<Model> findById(Long id);
    List<Model> findAllByBrand(Brand brand);
    List<Model> findByBrand(Brand brand);
}
