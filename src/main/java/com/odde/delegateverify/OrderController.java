package com.odde.delegateverify;

public class OrderController {
    private final OrderModel orderModel;

    public OrderController(OrderModel orderModel) {
        this.orderModel = orderModel;
    }

    public void save(Order order) {
        orderModel.save(order, this::insertMessage, this::updateMessage);
    }

    private void updateMessage(Order order)
    {
        System.out.println(String.format("update order id:%d with %d successfully!", order.getId(), order.getAmount()));
    }

    private void insertMessage(Order order)
    {
        System.out.println(String.format("insert order id:%d with %d successfully!", order.getId(), order.getAmount()));
    }

    public void deleteAmountMoreThan100()
    {
        orderModel.delete(o -> o.getAmount() > 100);
    }
}
