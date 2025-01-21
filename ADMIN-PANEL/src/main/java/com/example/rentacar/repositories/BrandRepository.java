package com.example.rentacar.repositories;
import com.example.rentacar.models.Brand;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface BrandRepository extends JpaRepository<Brand,Integer> {
    boolean existsByName(String name);
    Optional<Brand> findById(int id);



}