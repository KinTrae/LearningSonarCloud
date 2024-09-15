package com.mas.kinga.repositories;

import com.mas.kinga.models.ORDERS_STATUS;
import com.mas.kinga.models.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long>
{
    /**
     * Method searching for all users Orders
     * @param userId
     * @return List of users orders
     */
    List<Order> findAllByUser_Id(Long userId);

    /**
     * Method searching for orders with specific status where given user is owner
     * @param status
     * @param userId
     * @return
     */
    Optional<Order> findAllByStatusEqualsAndUser_Id(ORDERS_STATUS status, Long userId);

}
