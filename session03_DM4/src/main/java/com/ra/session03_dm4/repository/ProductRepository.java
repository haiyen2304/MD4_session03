package com.ra.session03_dm4.repository;

import com.ra.session03_dm4.model.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ProductRepository extends JpaRepository<Product, Long> {

    @Query("SELECT p FROM Product p WHERE " +
            "(:search IS NULL OR LOWER(p.nameProduct) LIKE LOWER(CONCAT('%', :search, '%'))) AND " +
            "(:minPrice IS NULL OR p.price >= :minPrice) AND " +
            "(:maxPrice IS NULL OR p.price <= :maxPrice) " +
            "ORDER BY " +
            "CASE WHEN :sortOption = 'lowToHigh' THEN p.price END ASC, " +
            "CASE WHEN :sortOption = 'highToLow' THEN p.price END DESC")
    Page<Product> findProductsWithFilters(Pageable pageable,
                                          @Param("search") String search,
                                          @Param("minPrice") Double minPrice,
                                          @Param("maxPrice") Double maxPrice,
                                          @Param("sortOption") String sortOption);

}
