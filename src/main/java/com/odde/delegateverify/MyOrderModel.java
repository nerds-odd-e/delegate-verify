package com.odde.delegateverify;

import java.time.Clock;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.function.Consumer;
import java.util.function.Predicate;

public class MyOrderModel implements OrderModel {
    private final Repository repository;
    private final Clock clock;

    public MyOrderModel(Repository repository) {
        this.repository = repository;
        clock = null;
    }

    public MyOrderModel(Repository repository, Clock clock) {
        this.repository = repository;
        this.clock = clock;
    }

    @Override
    public void save(Order order, Consumer<Order> insertCallback, Consumer<Order> updateCallback) {
        if (!repository.isExist(order)) {
            if (LocalDate.now(clock).getDayOfWeek().equals(DayOfWeek.SUNDAY)) {
                order.setAmount(order.getAmount() + 100);
            }
            repository.insert(order);
            insertCallback.accept(order);
        } else {
            repository.update(order);
            updateCallback.accept(order);
        }
    }

    @Override
    public void delete(Predicate<Order> predicate) {
        throw new UnsupportedOperationException();
    }
}
