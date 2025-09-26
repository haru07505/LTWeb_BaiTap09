package haru.kieu.repository;

import haru.kieu.Entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {
	List<Product> findAllByOrderByPriceAsc();

    @Query("select p from Product p join p.categories c where c.id = :catId")
    List<Product> findAllByCategoryId(@Param("catId") Long catId);
}
