package com.example.inventoryservice.service;


import com.example.inventoryservice.dto.InventoryResponse;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public interface InventoryService {

    public  List<InventoryResponse> isInStock(List<String> skuCode);


}
