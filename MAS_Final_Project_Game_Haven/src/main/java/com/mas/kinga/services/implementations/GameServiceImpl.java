package com.mas.kinga.services.implementations;

import com.mas.kinga.dtos.GameDTO;
import com.mas.kinga.exceptions.ResourceNotFoundException;
import com.mas.kinga.models.*;
import com.mas.kinga.repositories.GameRepository;
import com.mas.kinga.services.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class GameServiceImpl implements GameService {
    private final GameRepository gameRepository;

    @Autowired
    public GameServiceImpl(GameRepository gameRepository) {
        this.gameRepository = gameRepository;

    }

    public Optional<Game> findById(Long id) {
        return gameRepository.findById(id);
    }

    @Override
    public GameDTO getGame(long id) {
        if(!gameExists(id)) throw new ResourceNotFoundException(Game.class.getName(), "gameId", id);
        Game game = gameRepository.findById(id).orElse(null);
        assert game != null;
        return new GameDTO(game);
    }

    @Override
    public Boolean gameExists(long id) {
        return gameRepository.existsById(id);
    }

    @Override
    public Page<GameDTO> getAllGames(Pageable pageable) {
        Page<Game> games = gameRepository.findAll(pageable);

        return games.map(GameDTO::new);
    }

    @Override
    public String getNextNotUsedLicenseKey(Long id, Order order) {
        Game game = gameRepository.getReferenceById(id);
        if(game == null) throw new ResourceNotFoundException(Game.class.getName(),  "given game by id does not exist", id);
        if(order == null) throw new ResourceNotFoundException(Game.class.getName(),  "given order by id does not exist", order);
        if(game.getGameType().contains(GAME_TYPE.VIDEO)){
            Map.Entry<String, Long> licenseKey = game.getLicenseKeys().entrySet().stream()
                    .filter(entry -> entry.getValue() == -1L)
                    .findFirst().get();

            if(licenseKey == null) throw new ResourceNotFoundException(Game.class.getName(),  "given game does not have any available licenseKeys left", game);
            game.getLicenseKeys().put(licenseKey.getKey(), order.getId());
            gameRepository.save(game);

            return licenseKey.getKey();
        }

        throw new ResourceNotFoundException(Game.class.getName(),  "given game by id does not exist", id);
    }

    @Override
    public void restoreLicenseKeeyAfterPurchaseAnullment(Order order, Game game) {
        if (order == null) throw new IllegalArgumentException("Order cannot be null");
        if (game == null)
            throw new ResourceNotFoundException(Game.class.getName(), "given game by id does not exist", game);
        if (!game.getLicenseKeys().containsValue(order.getId()))
            throw new IllegalArgumentException("This game wasnt purchased in this order");
        if (game.getGameType().contains(GAME_TYPE.VIDEO)) {
            String licenseKey = game.getLicenseKeys().entrySet().stream().filter(entry -> entry.getValue().equals(order.getId())).findFirst().get().getKey();
            game.getLicenseKeys().replace(licenseKey, -1L);
        }
    }

    public List<Game> getGamesByCategoryId(Long id) {
        return gameRepository.findByCategories_IdOrderByName(id);
    }
}