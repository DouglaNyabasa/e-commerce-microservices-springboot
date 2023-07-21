package com.example.productservice.repository;

import com.example.productservice.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product,Long> {
    @Query("select p from Product p where p.is_deleted = false and p.is_activated = true")
    List<Product> getAllProduct();

    @Query(value = "select " +
            "p.product_id, p.name, p.description, p.current_quantity, p.cost_price, p.category_id, p.sale_price, p.image, p.is_activated, p.is_deleted " +
            "from products p where p.is_activated = true and p.is_deleted = false order by rand() limit 9", nativeQuery = true)
    List<Product> randomProduct();
    @Query("select p from Product p where p.productName like %?1% or p.description like %?1%")
    List<Product> findAllByNameOrDescription(String keyword);


//    @Query("select p from Product p inner join Category c ON c.categoryId = p.category.categoryId" +
//            " where p.category.name = ?1 and p.is_activated = true and p.is_deleted = false")
//    List<Product> findAllByCategory(String category);
    List<Product> findTop5ByOrderByCreatedOnDesc();
//    @Query(value = "select p from Product p inner join Category c on c.categoryId = ?1 and p.category.categoryId = ?1 where p.is_activated = true and p.is_deleted = false")
//    List<Product> getProductByCategoryId(Long id);
}
