package uz.pdp.appwarehouse.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.appwarehouse.entity.*;
import uz.pdp.appwarehouse.payload.InputProductDto;
import uz.pdp.appwarehouse.payload.OutputProductDto;
import uz.pdp.appwarehouse.payload.Result;
import uz.pdp.appwarehouse.repository.*;

import java.util.List;
import java.util.Optional;

@Service
public class OutputProductService {

    @Autowired
    OutputProductRepository outputProductRepository;
    @Autowired
    OutputRepository outputRepository;
    @Autowired
    ProductRepository productRepository;

    public Result addOutputProduct(OutputProductDto outputProductDto){
        Optional<Output> optionalOutput = outputRepository.findById(outputProductDto.getOutputId());
        if (optionalOutput.isEmpty()){
            return new Result("Such output doesn't exist",false);
        }
        Optional<Product> optionalProduct = productRepository.findById(outputProductDto.getProductId());
        if (optionalProduct.isEmpty()){
            return new Result("Such product doesn't exist",false);
        }
        OutputProduct outputProduct=new OutputProduct();
        outputProduct.setAmount(outputProductDto.getAmount());
        outputProduct.setPrice(outputProductDto.getPrice());
        outputProduct.setOutput(optionalOutput.get());
        outputProduct.setProduct(optionalProduct.get());
        outputProductRepository.save(outputProduct);
        return new Result("Output Product added",true);
    }

    public List<OutputProduct> getOutputProducts(){
        List<OutputProduct> outputProductList = outputProductRepository.findAll();
        return outputProductList;
    }

    public List<OutputProduct> getOutputProductByProductId(Integer productId){
        List<OutputProduct> byProductId = outputProductRepository.findAllByProduct_Id(productId);
        return byProductId;
    }

    public OutputProduct getOutputProductById(Integer id){
        Optional<OutputProduct> optionalOutputProduct = outputProductRepository.findById(id);
        return optionalOutputProduct.orElse(null);
    }

    public Result deleteOutputProduct(Integer id){
        outputProductRepository.deleteById(id);
        return new Result("Output-Product deleted",true);
    }

    public Result editOutputProduct(Integer id,OutputProductDto outputProductDto){
        Optional<OutputProduct> optionalOutputProduct = outputProductRepository.findById(id);
        if (optionalOutputProduct.isEmpty()){
            return new Result("Such Output-Product doesn't exist",false);
        }
        Optional<Output> optionalOutput = outputRepository.findById(outputProductDto.getOutputId());
        if (optionalOutput.isEmpty()){
            return new Result("Such output doesn't exist",false);
        }
        Optional<Product> optionalProduct = productRepository.findById(outputProductDto.getProductId());
        if (optionalProduct.isEmpty()){
            return new Result("Such product doesn't exist",false);
        }
        OutputProduct outputProduct = optionalOutputProduct.get();
        outputProduct.setAmount(outputProductDto.getAmount());
        outputProduct.setPrice(outputProductDto.getPrice());
        outputProduct.setOutput(optionalOutput.get());
        outputProduct.setProduct(optionalProduct.get());
        outputProductRepository.save(outputProduct);
        return new Result("Output Product edited",true);
    }

}
