package com.cargo.repositories;

import com.cargo.entity.Watches;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public interface WatchRepository extends JpaRepository<Watches, Integer> {

    Collection<Watches> findByIdIn(Collection<Integer> ids);

}
