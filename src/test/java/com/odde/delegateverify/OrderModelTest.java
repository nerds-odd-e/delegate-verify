package com.odde.delegateverify;

import org.junit.Test;

import static org.mockito.Mockito.mock;

public class OrderModelTest {

    private Repository repository = mock(Repository.class);

    @Test
    public void insert_order() {
        //TODO
        MyOrderModel myOrderModel = new MyOrderModel(repository);
    }
    
    @Test
    public void update_order() {
        //TODO
        MyOrderModel myOrderModel = new MyOrderModel(repository);
    }
}
