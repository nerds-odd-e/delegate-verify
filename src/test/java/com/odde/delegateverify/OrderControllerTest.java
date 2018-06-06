package com.odde.delegateverify;

import org.junit.Test;

import static org.mockito.Mockito.mock;

public class OrderControllerTest {
    
    @Test
    public void exist_order_should_update() {
        //TODO
        OrderModel model = mock(OrderModel.class);
        OrderController orderController = new OrderController(model);

        orderController.save(new Order(){{
           setId(91);
           setAmount(100);
        }});
    }
    
    @Test
    public void no_exist_order_should_insert() {
        //TODO
        OrderModel model = mock(OrderModel.class);
        OrderController orderController = new OrderController(model);

        orderController.save(new Order(){{
            setId(91);
            setAmount(100);
        }});
    }

    @Test
    public void verify_lambda_function_of_delete() {
        //TODO
        OrderModel model = mock(OrderModel.class);
        OrderController orderController = new OrderController(model);

        orderController.deleteAmountMoreThan100();
    }
}
