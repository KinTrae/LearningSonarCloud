package com.mas.kinga.services;


import com.mas.kinga.dtos.OrderDTO;
import com.mas.kinga.dtos.OrderedGamesDTO;
import com.mas.kinga.models.ORDERS_STATUS;
import com.mas.kinga.models.Order;
import com.mas.kinga.models.OrderedGame;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface OrderService {
    OrderedGamesDTO getOrderedGamesByOrderId(Long orderId);

    Boolean orderExists(Long orderId);

    OrderDTO cancelOrder(Long id);

    OrderDTO finalizeOrder(Long id);

    Order createOrAddToOrder(Long gameId,  int quantity,  String orderComment,  Long userId);

    Optional<Order> findInProgressOrderForUser(Long userId);

    Order deleteOrderedGameFromOrder(Long orderId, Long orderedGameId);

    List<OrderDTO> getAllUsersOrders(Long userId);
}
