package uz.pdp.appwarehouse.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import uz.pdp.appwarehouse.entity.Input;
import uz.pdp.appwarehouse.entity.InputProduct;

import java.util.Optional;

public interface InputProductRepository extends JpaRepository<InputProduct,Integer> {
    @Query(value = "select sum(amount) from input_product where product_id=:productId",nativeQuery = true)
    Double getAmountByProductId(@Param("productId") Integer productId);

    InputProduct findByProduct_Id(Integer product_id);
}
