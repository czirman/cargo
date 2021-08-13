package com.cargo.repositories;

import com.cargo.entity.Watches;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WatchRepository extends JpaRepository<Watches, Integer> {
}
