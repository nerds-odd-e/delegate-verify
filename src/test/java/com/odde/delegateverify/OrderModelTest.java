package com.odde.delegateverify;

import org.junit.Test;

import java.time.Clock;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.function.Consumer;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class OrderModelTest {

    private Repository repository = mock(Repository.class);
    Clock stubClock = stubClock(LocalDate.of(2018, 6, 9));
    MyOrderModel myOrderModel = new MyOrderModel(repository, stubClock);
    Order order = new Order();
    Consumer mockInsertCallback = mock(Consumer.class);
    Consumer mockUpdateCallback = mock(Consumer.class);

    @Test
    public void insert_order() {
        givenOrderExists(false);

        myOrderModel.save(order, mockInsertCallback, mockUpdateCallback);

        verify(repository).insert(order);
        verify(mockInsertCallback).accept(order);
    }

    @Test
    public void update_order() {
        givenOrderExists(true);

        myOrderModel.save(order, mockInsertCallback, mockUpdateCallback);

        verify(repository).update(order);
        verify(mockUpdateCallback).accept(order);
    }

    @Test
    public void insert_order_on_sunday() {
        givenTodayIsSunday();
        givenOrderExists(false);

        order.setAmount(50);
        myOrderModel.save(order, mockInsertCallback, mockUpdateCallback);

        assertEquals(150, order.getAmount());
    }

    @Test
    public void insert_order_not_on_sunday() {
        givenTodayIsNotSunday();
        givenOrderExists(false);

        order.setAmount(50);
        myOrderModel.save(order, mockInsertCallback, mockUpdateCallback);

        assertEquals(50, order.getAmount());
    }

    private void givenTodayIsSunday() {
        myOrderModel = new MyOrderModel(repository, stubClock(LocalDate.of(2018, 6, 10)));
    }

    private void givenTodayIsNotSunday() {
        myOrderModel = new MyOrderModel(repository, stubClock(LocalDate.of(2018, 6, 9)));
    }

    private void givenOrderExists(boolean b) {
        when(repository.isExist(any(Order.class))).thenReturn(b);
    }

    private Clock stubClock(LocalDate sunday) {
        return Clock.fixed(sunday.atStartOfDay(ZoneId.systemDefault()).toInstant(), ZoneId.systemDefault());
    }

}
