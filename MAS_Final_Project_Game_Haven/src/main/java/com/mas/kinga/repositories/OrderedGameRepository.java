package com.mas.kinga.repositories;

import com.mas.kinga.models.OrderedGame;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderedGameRepository extends JpaRepository<OrderedGame, Long> {
    /**
     * Method finding all OrderedGames assigned to an order
     * @param orderId
     * @return
     */
    List<OrderedGame> findAllByOrder_Id(Long orderId);

}
