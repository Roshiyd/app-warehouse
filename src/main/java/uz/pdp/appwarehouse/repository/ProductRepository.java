package uz.pdp.appwarehouse.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.appwarehouse.entity.Category;
import uz.pdp.appwarehouse.entity.Product;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product,Integer> {
    boolean existsByNameAndCategoryId(String name, Integer category_id);
    List<Product> findAllByCategory_Id(Integer category_id);
}
