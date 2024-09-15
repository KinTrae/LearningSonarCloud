package com.mas.kinga.services;

import com.mas.kinga.dtos.GameDTO;
import com.mas.kinga.models.Game;
import com.mas.kinga.models.Order;
import com.mas.kinga.models.StationaryStore;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;


public interface GameService {
    Optional<Game> findById(Long id);
    GameDTO getGame(long id);
    Boolean gameExists(long id);
    Page<GameDTO> getAllGames(Pageable pageable);
    List<Game> getGamesByCategoryId(Long id);

    String getNextNotUsedLicenseKey(Long id, Order order);

    void restoreLicenseKeeyAfterPurchaseAnullment(Order order, Game game);
}