package com.example.productservice.controller;

import com.example.productservice.dto.ProductRequestDTO;
import com.example.productservice.model.Product;
import com.example.productservice.service.ProductService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/product/")
public class ProductController {
    private final ProductService productService;


    public ProductController(ProductService productService) {
        this.productService = productService;

    }

    @PostMapping(value = "/create", headers = {"content-type=multipart/form-data"})
    public ResponseEntity<Object> createProduct(@RequestParam(name = "productImage")MultipartFile multipartFile, ProductRequestDTO productRequestDTO){
        return new ResponseEntity<>(productService.createProduct(productRequestDTO,multipartFile), HttpStatus.CREATED);
    }
    @GetMapping(value = "/all")
    public ResponseEntity<Page<Product>> getAllProducts(@RequestParam(value = "page",defaultValue = "0")int page,@RequestParam(value = "size",defaultValue = "10")int size){
        Pageable pageable = PageRequest.of(page, size);
        return new ResponseEntity(productService.getAllProducts(pageable),HttpStatus.OK);

    }

    @GetMapping(value = "/recent")
    public ResponseEntity<Object> getRecentlyAdded(){
        return new ResponseEntity<>(productService.getRecentlyAddedProducts(),HttpStatus.OK);
    }
//    @GetMapping("/findByCategory/{categoryId}")
//    public List<ProductRequestDTO>findByCategory(@PathVariable("categoryId") Long categoryId){
//        return productService.findByCategory(categoryId);
//    }

    @PostMapping("/update/{id}")
    public ResponseEntity<String> updateProduct(@PathVariable("id") Long id,@RequestBody ProductRequestDTO product,MultipartFile productImage){
        return new ResponseEntity(productService.updateProduct(product, productImage, id),HttpStatus.OK);
    }
    @GetMapping("/getById/{id}")
    public Product findProductById(@PathVariable("id") Long id){
        return productService.findById(id);

    }
    // make search api
//    @GetMapping("/search")
//    public String searchProducts(@RequestParam("keyword")String keyword){
//        List<CategoryDTO> categoryDTOS = categoryService.getCategoriesAndSize();
//        List<ProductRequestDTO> productRequestDTOList = productService.searchProduct(keyword);
//    }
}
