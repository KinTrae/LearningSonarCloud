package com.mas.kinga.services.implementations;

import com.mas.kinga.dtos.OrderDTO;
import com.mas.kinga.dtos.OrderedGameDTO;
import com.mas.kinga.dtos.OrderedGamesDTO;
import com.mas.kinga.exceptions.ResourceNotFoundException;
import com.mas.kinga.models.*;
import com.mas.kinga.repositories.GameRepository;
import com.mas.kinga.repositories.OrderRepository;
import com.mas.kinga.repositories.OrderedGameRepository;
import com.mas.kinga.services.GameService;
import com.mas.kinga.services.OrderService;
import com.mas.kinga.services.OrderedGameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;
    private final OrderedGameRepository orderedGameRepository;
    private final GameService gameService;
    private final OrderedGameService orderedGameService;

    private final GameRepository gameRepository;

    @Autowired
    public OrderServiceImpl(OrderRepository orderRepository, OrderedGameRepository orderedGameRepository, GameService gameService, OrderedGameService orderedGameService, GameRepository gameRepository) {
        this.orderRepository = orderRepository;
        this.orderedGameRepository = orderedGameRepository;
        this.gameService = gameService;
        this.orderedGameService = orderedGameService;
        this.gameRepository = gameRepository;
    }

    public OrderedGamesDTO getOrderedGamesByOrderId(Long orderId) {
        if (!orderExists(orderId)) throw new ResourceNotFoundException(Order.class.getName(), "orderId", orderId);
        List<OrderedGame> orderedGames = orderedGameRepository.findAllByOrder_Id(orderId);
        if (orderedGames.isEmpty())
            throw new ResourceNotFoundException(OrderedGameDTO.class.getName(), "For given orderId, there are no ordered games", orderId);

        return new OrderedGamesDTO(orderedGames);
    }

    public Boolean orderExists(Long orderId) {
        return orderRepository.existsById(orderId);
    }

    public OrderDTO cancelOrder(Long orderId) {
        if (!orderExists(orderId)) throw new ResourceNotFoundException(Order.class.getName(), "orderId", orderId);
        Order order = orderRepository.findById(orderId).orElse(null);

        assert order != null;
        if (order.getStatus().equals(ORDERS_STATUS.CANCELLED))
            throw new ResourceNotFoundException(Order.class.getName(), "ORDER ALREADY CANCELLED", orderId);//TODO;
        order.setCancelDate(new Date(String.valueOf(LocalDate.now())));
        order.setStatus(ORDERS_STATUS.CANCELLED);

        Order cancelledOrder = orderRepository.save(order);

        return new OrderDTO(cancelledOrder);
    }

    public OrderDTO finalizeOrder(Long id) {
        if (!orderExists(id)) throw new ResourceNotFoundException(Order.class.getName(), "orderId", id);
        Order order = orderRepository.findById(id).orElse(null);

        assert order != null;
        if (order.getStatus().equals(ORDERS_STATUS.CANCELLED) || order.getStatus().equals(ORDERS_STATUS.DONE))
            throw new ResourceNotFoundException(Order.class.getName(), "ORDER ALREADY CANCELLED OR FINALIZED", id);
        order.setSendDate(new Date());
        order.setStatus(ORDERS_STATUS.DONE);

        Order sentOrder = orderRepository.save(order);

        return new OrderDTO(sentOrder);
    }

    public Order createOrAddToOrder(Long gameId, int quantity, String orderComment, Long userId) {
        User user = new User();
        user.setId(userId);

        Order order = findInProgressOrderForUser(userId).orElseGet(() -> {
            Order newOrder = Order.builder()
                    .user(user)
                    .orderDate(LocalDate.now())
                    .status(ORDERS_STATUS.IN_PROGRESS)
                    .build();

            return orderRepository.save(newOrder);
        });

        Game game = gameService.findById(gameId).orElseThrow(() -> new IllegalArgumentException("Invalid game ID"));

        if (quantity > game.getQuantity()) {
            throw new IllegalArgumentException("Ordered quantity exceeds available quantity for the game");
        }

        game.setQuantity(game.getQuantity() - quantity);
        gameRepository.save(game); // Save the updated game entity

        OrderedGame orderedGame = new OrderedGame();
        orderedGame.setOrder(order);
        orderedGame.setGame(game);
        orderedGame.setQuantity(quantity);
        orderedGame.setPriceOfGame(game.getPrice());
        orderedGame.setOrderComment(orderComment);

        orderedGameService.save(orderedGame);

        order.setSum(orderedGameRepository.findAllByOrder_Id(order.getId()).stream()
                .map(og -> og.getQuantity() * og.getPriceOfGame())
                .mapToDouble(Double::doubleValue).sum());
        orderRepository.save(order);

        return order;
    }



    @Override
    public Optional<Order> findInProgressOrderForUser(Long userId) {
        return orderRepository.findAllByStatusEqualsAndUser_Id(ORDERS_STATUS.IN_PROGRESS, userId);
    }

    @Override
    public Order deleteOrderedGameFromOrder(Long orderId, Long orderedGameId) {
        OrderedGame orderedGame = orderedGameRepository.findById(orderedGameId).orElseThrow();
        Order order = orderRepository.findById(orderId).orElseThrow();
        order.setSum(orderedGameRepository.findAllByOrder_Id(order.getId()).stream().map(og -> og.getQuantity()*og.getPriceOfGame()).mapToDouble(Double::doubleValue).sum());

        orderedGameRepository.delete(orderedGame);
        orderRepository.save(order);

        if(orderedGameRepository.findAllByOrder_Id(orderId) == null) {
            orderRepository.delete(order);
            return null;
        }

        return orderedGame.getOrder();
    }

    @Override
    public List<OrderDTO> getAllUsersOrders(Long userId) {
        List<Order> orders = orderRepository.findAllByUser_Id(userId);
        List<OrderDTO> ordersDto = orders.stream().map(o -> new OrderDTO(o)).toList();

        return ordersDto;
    }
}
