package com.cooksys.lemonadestand.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cooksys.lemonadestand.entities.LemonadeStand;

@Repository
public interface LemonadeStandRepository extends JpaRepository<LemonadeStand, Long> {

    Optional<LemonadeStand> findByIdAndDeletedFalse(Long id);

    Optional<LemonadeStand> findByNameAndDeletedFalse(String name);

    List<LemonadeStand> findAllByDeletedFalse();
}
