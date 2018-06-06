package com.odde.delegateverify;

public interface Repository {

    boolean isExist(Order order);
    void insert(Order order);
    void update(Order order);
}
