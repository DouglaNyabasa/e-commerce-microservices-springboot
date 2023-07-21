package com.example.orderservice.service;

import com.example.orderservice.dto.OrderRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public interface OrderService {

    public void placeOrder(OrderRequest orderRequest);
}
