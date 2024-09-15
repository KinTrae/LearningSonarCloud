package com.mas.kinga.services.implementations;

import com.mas.kinga.models.OrderedGame;
import com.mas.kinga.repositories.OrderedGameRepository;
import com.mas.kinga.services.OrderedGameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderedGameServiceImpl implements OrderedGameService {
    private final OrderedGameRepository orderedGameRepository;

    @Autowired
    public OrderedGameServiceImpl(OrderedGameRepository orderedGameRepository) {
        this.orderedGameRepository = orderedGameRepository;
    }

    public OrderedGame save(OrderedGame orderedGame) {
        return orderedGameRepository.save(orderedGame);
    }
}