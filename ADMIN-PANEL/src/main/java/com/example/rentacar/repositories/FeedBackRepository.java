package com.example.rentacar.repositories;

import com.example.rentacar.models.FeedBack;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FeedBackRepository extends JpaRepository<FeedBack,Long> {
}
