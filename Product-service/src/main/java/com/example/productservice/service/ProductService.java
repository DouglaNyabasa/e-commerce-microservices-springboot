package com.example.productservice.service;


import com.example.productservice.dto.ProductRequestDTO;
import com.example.productservice.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
@Service
public interface ProductService {

    ResponseEntity<Object> createProduct(ProductRequestDTO productRequestDTO, MultipartFile productImage);
    ResponseEntity<String> updateProduct(ProductRequestDTO productRequestDTO, MultipartFile productImage, Long id);
    ResponseEntity deleteProduct(Long id);
    Product findById(Long id);
    ResponseEntity<Page<Product>> getAllProducts(Pageable pageable);
    Page<ProductRequestDTO> searchProduct(String keyword, int pageNo);
    ResponseEntity<List<Product>> getRecentlyAddedProducts();

//    List<ProductRequestDTO> findByCategory(Long id);
    List<ProductRequestDTO> randomProduct();



}
