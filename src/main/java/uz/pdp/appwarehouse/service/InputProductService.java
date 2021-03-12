package uz.pdp.appwarehouse.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.appwarehouse.entity.Client;
import uz.pdp.appwarehouse.entity.Input;
import uz.pdp.appwarehouse.entity.InputProduct;
import uz.pdp.appwarehouse.entity.Product;
import uz.pdp.appwarehouse.payload.InputProductDto;
import uz.pdp.appwarehouse.payload.Result;
import uz.pdp.appwarehouse.repository.ClientRepository;
import uz.pdp.appwarehouse.repository.InputProductRepository;
import uz.pdp.appwarehouse.repository.InputRepository;
import uz.pdp.appwarehouse.repository.ProductRepository;

import java.util.List;
import java.util.Optional;

@Service
public class InputProductService {

    @Autowired
    InputProductRepository inputProductRepository;
    @Autowired
    InputRepository inputRepository;
    @Autowired
    ProductRepository productRepository;

    public Result addInputProduct(InputProductDto inputProductDto){
        Optional<Input> optionalInput = inputRepository.findById(inputProductDto.getInputId());
        if (optionalInput.isEmpty()){
            return new Result("Such input doesn't exist",false);
        }
        Optional<Product> optionalProduct = productRepository.findById(inputProductDto.getProductId());
        if (optionalProduct.isEmpty()){
            return new Result("Such product doesn't exist",false);
        }
        InputProduct inputProduct=new InputProduct();
        inputProduct.setAmount(inputProductDto.getAmount());
        inputProduct.setPrice(inputProductDto.getPrice());
        inputProduct.setExpireDate(inputProductDto.getExpireDate());
        inputProduct.setProduct(optionalProduct.get());
        inputProduct.setInput(optionalInput.get());
        inputProductRepository.save(inputProduct);
        return new Result("Input Product added",true);
    }

    public List<InputProduct> getInputProducts(){
        List<InputProduct> inputProductList = inputProductRepository.findAll();
        return inputProductList;
    }

    public InputProduct getInputProductById(Integer id){
        Optional<InputProduct> optionalInputProduct = inputProductRepository.findById(id);
        return optionalInputProduct.orElse(null);
    }

    public Double getAmountByProductId(Integer productId){
        return inputProductRepository.getAmountByProductId(productId);
    }

    public Result deleteInputProduct(Integer id){
        inputProductRepository.deleteById(id);
        return new Result("Input-Product deleted",true);
    }

    public Result editInputProduct(Integer id,InputProductDto inputProductDto){
        Optional<InputProduct> optionalInputProduct = inputProductRepository.findById(id);
        if (optionalInputProduct.isEmpty()){
            return new Result("Such Input-Product doesn't exist",false);
        }
        Optional<Input> optionalInput = inputRepository.findById(inputProductDto.getInputId());
        if (optionalInput.isEmpty()){
            return new Result("Such input doesn't exist",false);
        }
        Optional<Product> optionalProduct = productRepository.findById(inputProductDto.getProductId());
        if (optionalProduct.isEmpty()){
            return new Result("Such product doesn't exist",false);
        }
        InputProduct inputProduct = optionalInputProduct.get();
        inputProduct.setAmount(inputProductDto.getAmount());
        inputProduct.setPrice(inputProductDto.getPrice());
        inputProduct.setExpireDate(inputProductDto.getExpireDate());
        inputProduct.setProduct(optionalProduct.get());
        inputProduct.setInput(optionalInput.get());
        inputProductRepository.save(inputProduct);
        return new Result("Input Product edited",true);
    }
    public void leftOver(Integer inputProductID,Double leftOver){
        Optional<InputProduct> optionalInputProduct = inputProductRepository.findById(inputProductID);
        if (optionalInputProduct.isPresent()){
            InputProduct inputProduct = optionalInputProduct.get();
            inputProduct.setAmount(leftOver);
            inputProductRepository.save(inputProduct);
        }
    }

    public InputProduct getInputProductByProductId(Integer productId){
        return inputProductRepository.findByProduct_Id(productId);
    }

}
