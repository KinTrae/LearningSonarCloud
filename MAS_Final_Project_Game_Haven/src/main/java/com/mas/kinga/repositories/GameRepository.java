package com.mas.kinga.repositories;

import com.mas.kinga.models.Game;
import com.mas.kinga.models.GameElement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GameRepository extends JpaRepository<Game, Long> {
    List<Game> findByCategories_IdOrderByName(Long id);

}
