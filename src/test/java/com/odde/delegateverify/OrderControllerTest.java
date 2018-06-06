package com.odde.delegateverify;

import org.junit.Test;
import org.mockito.ArgumentCaptor;

import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.stream.Stream;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentCaptor.forClass;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

public class OrderControllerTest {

    OrderModel model = mock(OrderModel.class);
    OrderController orderController = new OrderController(model);
    private Order order = new Order() {{
        setId(91);
        setAmount(100);
    }};

    @Test
    public void should_save_order() {
        orderController.save(order);

        verify(model).save(eq(order), any(Consumer.class), any(Consumer.class));
    }

    @Test
    public void should_set_message_when_insert() {
        givenInsertWithOrder(new Order() {{
            setId(91);
            setAmount(100);
        }});

        orderController.save(order);

        assertEquals("insert order id:91 with 100 successfully!", orderController.getMessage());
    }

    @Test
    public void should_set_message_when_update() {
        givenUpdateWithOrder(new Order() {{
            setId(91);
            setAmount(100);
        }});

        orderController.save(order);

        assertEquals("update order id:91 with 100 successfully!", orderController.getMessage());
    }

    @Test
    public void verify_lambda_function_of_delete() {
        orderController.deleteAmountMoreThan100();

        assertEquals(1, Stream.of(new Order() {{
            setAmount(101);
        }}).filter(deleteCondition()).count());
    }

    private Predicate deleteCondition() {
        ArgumentCaptor<Predicate> captor = forClass(Predicate.class);
        verify(model).delete(captor.capture());
        return captor.getValue();
    }

    private void givenUpdateWithOrder(Order order) {
        callbackWithArgumentIndexAndOrder(order, 2);
    }

    private void givenInsertWithOrder(Order order) {
        callbackWithArgumentIndexAndOrder(order, 1);
    }

    private void callbackWithArgumentIndexAndOrder(Order order, int i) {
        doAnswer(invocationOnMock -> {
            Consumer<Order> updateCallback = invocationOnMock.getArgument(i);
            updateCallback.accept(order);
            return null;
        }).when(model).save(any(Order.class), any(Consumer.class), any(Consumer.class));
    }

}
