package uz.pdp.appwarehouse.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;
import uz.pdp.appwarehouse.entity.*;
import uz.pdp.appwarehouse.payload.ProductDto;
import uz.pdp.appwarehouse.payload.Result;
import uz.pdp.appwarehouse.repository.*;

import java.util.List;
import java.util.Optional;

import static org.apache.logging.log4j.ThreadContext.isEmpty;

@Service
public class ProductService {

    @Autowired
    ProductRepository productRepository;
    @Autowired
    CategoryRepository categoryRepository;
    @Autowired
    MeasurementRepository measurementRepository;
    @Autowired
    AttachmentRepository attachmentRepository;

    public Result addProduct(ProductDto productDto){
        if (productRepository.existsByNameAndCategoryId(productDto.getName(),productDto.getCategoryId())){
            return new Result("Such product already exist in this category",false);
        }
        Optional<Category> optionalCategory = categoryRepository.findById(productDto.getCategoryId());
        if (optionalCategory.isEmpty()){
            return new Result("Such category doesn't exist",false);
        }
        Optional<Measurement> optionalMeasurement = measurementRepository.findById(productDto.getMeasurementId());
        if (optionalMeasurement.isEmpty()){
            return new Result("Such measurement doesn't exist",false);
        }
        Optional<Attachment> optionalAttachment = attachmentRepository.findById(productDto.getAttachmentId());
        if (optionalAttachment.isEmpty()){
            return new Result("Such attachment doesn't exist",false);
        }
        Product product=new Product();
        product.setName(productDto.getName());
        product.setCategory(optionalCategory.get());
        product.setMeasurement(optionalMeasurement.get());
        product.setPhoto(optionalAttachment.get());
        int code=productRepository.findAll().size()+1;
        product.setCode("Pr"+code);
        productRepository.save(product);
        return new Result("Product added",true);
    }

    public List<Product> getProducts(){
        List<Product> productList = productRepository.findAll();
        return productList;
    }

    public List<Product> getProductByCategoryId(Integer categoryId){
        return productRepository.findAllByCategory_Id(categoryId);
    }

    public Page<Product> getProductPage(Integer page){
        Pageable pageable= PageRequest.of(page,10);
        return productRepository.findAll(pageable);
    }

    public Product getProductById(Integer id){
        Optional<Product> optionalProduct = productRepository.findById(id);
        return optionalProduct.orElse(null);
    }

    public Result deleteProduct(Integer id){
        productRepository.deleteById(id);
        return new Result("Product deleted",true);
    }

    public Result editProduct(Integer id,ProductDto productDto){
        Optional<Product> optionalProduct = productRepository.findById(id);
        if (optionalProduct.isEmpty()){
            return new Result("Such product doesn't exist",false);
        }
        if (productRepository.existsByNameAndCategoryId(productDto.getName(),productDto.getCategoryId())){
            return new Result("Such product already exist in this category",false);
        }
        Optional<Category> optionalCategory = categoryRepository.findById(productDto.getCategoryId());
        if (optionalCategory.isEmpty()){
            return new Result("Such category doesn't exist",false);
        }
        Optional<Measurement> optionalMeasurement = measurementRepository.findById(productDto.getMeasurementId());
        if (optionalMeasurement.isEmpty()){
            return new Result("Such measurement doesn't exist",false);
        }
        Optional<Attachment> optionalAttachment = attachmentRepository.findById(productDto.getAttachmentId());
        if (optionalAttachment.isEmpty()){
            return new Result("Such attachment doesn't exist",false);
        }
        Product product = optionalProduct.get();
        product.setName(productDto.getName());
        product.setCategory(optionalCategory.get());
        product.setMeasurement(optionalMeasurement.get());
        product.setPhoto(optionalAttachment.get());
        int code=productRepository.findAll().size()+1;
        product.setCode("Pr"+code);
        productRepository.save(product);
        return new Result("Product edited",true);
    }

}
