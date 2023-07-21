package com.example.productservice.serviceImpl;

import com.example.productservice.dto.ProductRequestDTO;
import com.example.productservice.model.Product;
import com.example.productservice.repository.ProductRepository;
import com.example.productservice.service.ProductService;
import com.example.productservice.utils.ImageUpload;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

@Service
@Slf4j

public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;
    private final ImageUpload imageUpload;


    public ProductServiceImpl(ProductRepository productRepository, ImageUpload imageUpload) {
        this.productRepository = productRepository;
        this.imageUpload = imageUpload;
    }

    @Override
    public ResponseEntity<Object> createProduct(ProductRequestDTO productRequestDTO, MultipartFile productImage) {
        Product product = new Product();
        try {
            if (productImage == null){
                product.setImage(null);
                log.info("product not created because image is NULL !!");
            }
            imageUpload.uploadFile(productImage);
            product.setImage(Base64.getEncoder().encodeToString(productImage.getBytes()));
            product.setProductName(productRequestDTO.getProductName());
            product.setCreatedOn(productRequestDTO.getCreatedOn());
            product.setDescription(product.getDescription());
            product.setPrice(productRequestDTO.getPrice());
            product.setQuantity(productRequestDTO.getQuantity());
            product.set_activated(true);
            product.set_deleted(false);
            productRepository.save(product);
            log.info("Product successfully created");
            return  ResponseEntity.ok().body("Product successfully created");


        }

        catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public ResponseEntity<String> updateProduct(ProductRequestDTO productRequestDTO, MultipartFile productImage, Long id) {
      try {
          Product product1 = productRepository.getById(productRequestDTO.getId());
          if (productImage == null){
              product1.setImage(product1.getImage());
          } else if (imageUpload.checkExist(productImage)) {
              product1.setImage(product1.getImage());

          }else {
              imageUpload.uploadFile(productImage);
              product1.setImage(Base64.getEncoder().encodeToString(productImage.getBytes()));
          }
          product1.setUpdatedOn(productRequestDTO.getUpdatedOn());
          product1.setId(product1.getId());
          product1.setProductName(productRequestDTO.getProductName());
          product1.setQuantity(productRequestDTO.getQuantity());
          product1.setPrice(productRequestDTO.getPrice());
          product1.setDescription(productRequestDTO.getDescription());
          productRepository.save(product1);
          log.info("Product successfully updated");
          return  ResponseEntity.ok().body("Product successfully updated");

      } catch (IOException e) {
          throw new RuntimeException(e);
      }
    }

    @Override
    public ResponseEntity deleteProduct(Long id) {
        Product product = productRepository.getById(id);
        product.set_activated(false);
        product.set_deleted(true);
        productRepository.save(product);
        log.info("product is successfully deleted");
        return ResponseEntity.ok().body("Product has been successfully deleted");
    }

    @Override
    public Product findById(Long id) {
        return productRepository.findById(id).get();
    }



    @Override
    public ResponseEntity<Page<Product>> getAllProducts(Pageable pageable) {
        Page<Product> products = productRepository.findAll(pageable);

        return ResponseEntity.ok().body(products);
    }

    @Override
    public Page<ProductRequestDTO> searchProduct(String keyword, int pageNo) {
        List<Product> products = productRepository.findAllByNameOrDescription(keyword);
        List<ProductRequestDTO> productRequestDTOList = transferData(products);
        Pageable pageable = PageRequest.of(pageNo,5);
        Page<ProductRequestDTO> productRequestDTOPage = toPage(productRequestDTOList,pageable);
        return productRequestDTOPage;
    }

    @Override
    public ResponseEntity<List<Product>> getRecentlyAddedProducts() {
        List<Product> products =productRepository.findTop5ByOrderByCreatedOnDesc();
        List<Product> productsList = new ArrayList<>();
        for (Product product :products){
            product.setCreatedOn(product.getCreatedOn());
            productsList.add(product);
        }
        return ResponseEntity.ok().body(productsList);
    }

//    @Override
//    public List<ProductRequestDTO> findByCategory(Long id) {
//        return transferData(productRepository.getProductByCategoryId(id));
//    }

    @Override
    public List<ProductRequestDTO> randomProduct() {
        return transferData(productRepository.randomProduct());
    }

    private List<ProductRequestDTO> transferData(List<Product> products) {
        List<ProductRequestDTO> productDtos = new ArrayList<>();
        for (Product product : products) {
            ProductRequestDTO productDto = new ProductRequestDTO();
            productDto.setId(product.getId());
            productDto.setProductName(product.getProductName());
            productDto.setQuantity(product.getQuantity());
            productDto.setPrice(product.getPrice());
            productDto.setDescription(product.getDescription());
            productDto.setImage(product.getImage());
            productDto.setActivated(product.is_activated());
            productDto.setDeleted(product.is_deleted());
            productDtos.add(productDto);
        }
        return productDtos;
    }
    private  Page toPage(List list,Pageable pageable){
        if (pageable.getOffset()>= list.size()){
            return Page.empty();
        }
        int startIndex = (int) pageable.getOffset();
        int endIndex = ((pageable.getOffset() + pageable.getPageSize())> list.size())? list.size() : (int) (pageable.getOffset()+ pageable.getPageSize());
        List subList = list.subList(startIndex,endIndex);
        return new PageImpl(subList,pageable,list.size());
    }
}
