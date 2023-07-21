package com.example.inventoryservice;

import com.example.inventoryservice.model.Inventory;
import com.example.inventoryservice.repository.InventoryRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.transaction.annotation.Transactional;

@SpringBootApplication
public class InventoryServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(InventoryServiceApplication.class, args);
	}

//	@Bean
//	public CommandLineRunner loadData(InventoryRepository inventoryRepository){
//		return args -> {
//			Inventory inventory = new Inventory();
//			inventory.setSkuCode("Lenovo_X250");
//			inventory.setQuantity(100);
//
//			Inventory inventory1 = new Inventory();
//			inventory.setSkuCode("samsung_S23");
//			inventory.setQuantity(50);
//
//			inventoryRepository.save(inventory1);
//			inventoryRepository.save(inventory);
//		};
//	}
}
