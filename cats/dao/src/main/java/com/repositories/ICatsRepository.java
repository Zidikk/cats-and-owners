package com.repositories;

import com.models.Cat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ICatsRepository extends JpaRepository<Cat, Integer> {
}