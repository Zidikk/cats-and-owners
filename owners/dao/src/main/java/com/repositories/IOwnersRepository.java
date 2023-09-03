package com.repositories;

import com.models.Owner;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IOwnersRepository extends JpaRepository<Owner, Integer> {
}