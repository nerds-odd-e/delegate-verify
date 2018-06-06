package com.odde.delegateverify;

import java.util.function.Consumer;
import java.util.function.Predicate;

public interface OrderModel {

    void save(Order order, Consumer<Order> insertCallback, Consumer<Order> updateCallback);
    void delete(Predicate<Order> predicate);

}
