package com.mas.kinga.repositories;

import com.mas.kinga.models.GameElement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GameElementRepository extends JpaRepository<GameElement, Long> {
}
