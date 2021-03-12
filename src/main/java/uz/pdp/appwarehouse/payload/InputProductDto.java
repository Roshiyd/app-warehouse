package uz.pdp.appwarehouse.payload;

import lombok.Data;
import uz.pdp.appwarehouse.entity.Input;
import uz.pdp.appwarehouse.entity.Product;

import javax.persistence.Column;
import javax.persistence.ManyToOne;
import java.sql.Date;

@Data
public class InputProductDto {

    private Integer productId;

    private Double amount;

    private Double price;

    private Date expireDate;

    private Integer inputId;
}
