import React from "react"
import {Link} from "react-router-dom"

const Order = ({order}) => {
    const shortTime = new Intl.DateTimeFormat("US", {
        timeStyle: "short",
        dateStyle: "short",
      }); 

    const orderDate = order.orderDate == null ? null : shortTime.format(new Date(order.orderDate)); //(new Date(order.orderDate)).toString();
    const sendDate = order.sendDate == null ? null : shortTime.format(new Date(order.sendDate));
    const cancelDate = order.cancelDate == null ? null : shortTime.format(new Date(order.cancelDate));

    //dodaj warunkowe wyświetlanie danych zamówienia, jeśli zostało anulowane
    return (
    <li key={order.id}>
        <Link to={`/orders/${order.id}`}>Link to order details {`/orders/${order.id}`}</Link> <br />
        <strong>ID:</strong> {order.id} <br />
        <strong>User ID:</strong> {order.userId} <br />
        <strong>Login:</strong> {order.userLogin} <br />
        <strong>Email:</strong> {order.userMail} <br />
        <strong>Order Date:</strong> {orderDate} <br />
        <strong>Send Date:</strong> {sendDate} <br />
        <strong>Cancel Date:</strong> {cancelDate} <br />
        <strong>Sum:</strong> ${order.sum} <br />
        <hr />
    </li>);
}

export default Order;