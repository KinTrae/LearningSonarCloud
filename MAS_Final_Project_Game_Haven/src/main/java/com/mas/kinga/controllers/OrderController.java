package com.mas.kinga.controllers;

import com.mas.kinga.dtos.OrderDTO;
import com.mas.kinga.dtos.OrderedGameDTO;
import com.mas.kinga.dtos.OrderedGamesDTO;
import com.mas.kinga.models.*;
import com.mas.kinga.services.GameService;
import com.mas.kinga.services.OrderService;
import com.mas.kinga.services.OrderedGameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "api/orders")
public class OrderController {

    private final OrderService orderService;
    private final OrderedGameService orderedGameService;
    private final GameService gameService;

    @Autowired
    public OrderController(OrderService orderService, OrderedGameService orderedGameService, GameService gameService) {
        this.orderService = orderService;
        this.orderedGameService = orderedGameService;
        this.gameService = gameService;
    }

    @PostMapping("/add")
    public ResponseEntity<Order> addGameToOrder(@RequestParam Long gameId, @RequestParam int quantity, @RequestParam String orderComment, @RequestParam Long userId) {
        Order order = orderService.createOrAddToOrder(gameId, quantity, orderComment, 2L);

        return new ResponseEntity<>(order,  HttpStatus.OK);
    }

    @GetMapping("/{orderId}")
    public ResponseEntity<OrderedGamesDTO> getOrderById(@PathVariable Long orderId) {
        OrderedGamesDTO games = orderService.getOrderedGamesByOrderId(orderId);
        return new ResponseEntity<>(games, HttpStatus.OK);
    }

    @PutMapping("cancel/{orderId}")
    public ResponseEntity<OrderDTO> cancelOrder(@PathVariable Long orderId) {
        OrderDTO cancelledOrder = orderService.cancelOrder(orderId);
        return new ResponseEntity<>(cancelledOrder, HttpStatus.OK);
    }

    @PutMapping("send/{orderId}")
    public ResponseEntity<OrderDTO> finalizeOrder(@PathVariable Long orderId) {
        OrderDTO sentOrder = orderService.finalizeOrder(orderId);
        return new ResponseEntity<>(sentOrder, HttpStatus.OK);
    }

    @GetMapping("current/{userId}")
    public ResponseEntity<OrderedGamesDTO> getCurrentUserOrder(@PathVariable Long userId) {
        Order order = orderService.findInProgressOrderForUser(2L).orElseGet(null);
        OrderedGamesDTO games;
        if (order != null) {
            games = orderService.getOrderedGamesByOrderId(order.getId());
            System.out.println(games);
        } else {
            games = null;
        }

        return new ResponseEntity<>(games, HttpStatus.OK);
    }

    @DeleteMapping("{orderId}/{orderedGameId}")
    public ResponseEntity<Order> deleteOrderedGameFromOrder(@PathVariable Long orderId, @PathVariable Long orderedGameId) {
        Order order = orderService.deleteOrderedGameFromOrder(orderId, orderedGameId);
        return new ResponseEntity<>(order, HttpStatus.OK);
    }

    @GetMapping("user/{id}")
    public ResponseEntity<List<OrderDTO>> getUserOrders(@PathVariable Long id){
        List<OrderDTO> orders = orderService.getAllUsersOrders(id);
        return new ResponseEntity<>(orders, HttpStatus.OK);
    }
}
