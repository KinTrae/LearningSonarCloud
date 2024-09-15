package com.mas.kinga.dtos;

import com.mas.kinga.models.ORDERS_STATUS;
import com.mas.kinga.models.Order;
import com.mas.kinga.models.User;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Date;

@Data
@NoArgsConstructor
public class OrderDTO {
    private Long id;
    private LocalDate orderDate;
    private Date sendDate;
    private long userId;
    private String userMail;
    private String userLogin;
    private double sum;
    private Date cancelDate;
    private ORDERS_STATUS orderStatus;

    public OrderDTO(Order order) {
        User user = order.getUser();

        this.id = order.getId();
        this.orderDate = order.getOrderDate();
        this.sendDate = order.getSendDate();
        //userInfo
        this.userId = user == null ? -1 : user.getId();
        this.userMail = user == null ? "Null" : user.getEmail();
        this.userLogin = user == null ? "Null" : user.getLogin();
        //userInfo
        this.sum = order.getSum();
        this.cancelDate = order.getCancelDate();
        this.orderStatus = order.getStatus();
    }

    public Order createNewOrderBasedOnDTO(User user, double sum){
        Order order = new Order();

        order.setOrderDate(LocalDate.now());
        order.setStatus(ORDERS_STATUS.IN_PROGRESS);
        order.setUser(user);

        return  order;
    }

}
