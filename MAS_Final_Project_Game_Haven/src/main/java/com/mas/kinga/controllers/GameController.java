package com.mas.kinga.controllers;

import com.mas.kinga.dtos.GameDTO;
import com.mas.kinga.models.Game;
import com.mas.kinga.services.GameService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "api/games")
public class GameController {
    private final GameService gameService;

    public GameController(GameService gameService){
        this.gameService = gameService;
    }

    @GetMapping("")
    public ResponseEntity<Page<GameDTO>> getAllGames(@RequestParam(defaultValue = "0") Integer page,
                                                     @RequestParam(defaultValue = "5") Integer size){
        Pageable pageable = PageRequest.of(page, size);
        Page<GameDTO> gamesPage = gameService.getAllGames(pageable);

        return new ResponseEntity<>(gamesPage, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<GameDTO> getGameById(@PathVariable Long id){
        GameDTO game = gameService.getGame(id);
        return new ResponseEntity<>(game, HttpStatus.OK);
    }

    @GetMapping("/category/{id}")
    public ResponseEntity<List<Game>> getGamesByCategory(@PathVariable Long id) {
        List<Game> games = gameService.getGamesByCategoryId(id);
        games.forEach(g -> g.toString());
        return ResponseEntity.ok(games);
    }
}
