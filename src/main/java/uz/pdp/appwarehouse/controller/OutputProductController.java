package uz.pdp.appwarehouse.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uz.pdp.appwarehouse.entity.InputProduct;
import uz.pdp.appwarehouse.entity.OutputProduct;
import uz.pdp.appwarehouse.payload.InputProductDto;
import uz.pdp.appwarehouse.payload.OutputProductDto;
import uz.pdp.appwarehouse.payload.Result;
import uz.pdp.appwarehouse.service.InputProductService;
import uz.pdp.appwarehouse.service.OutputProductService;

import java.util.List;

@RestController
@RequestMapping("/outputProduct")
public class OutputProductController {
    @Autowired
    OutputProductService outputProductService;
    @Autowired
    InputProductService inputProductService;

    @PostMapping
    public Result addOutputProduct(@RequestBody OutputProductDto outputProductDto) {
        if (outputProductDto.getAmount()>inputProductService.getAmountByProductId(outputProductDto.getProductId())){
            return new Result("Not enough product!!!",true);
        }
        double leftOver=inputProductService.getAmountByProductId(outputProductDto.getProductId())- outputProductDto.getAmount();
        InputProduct inputProductById = inputProductService.getInputProductByProductId(outputProductDto.getProductId());
        inputProductService.leftOver(inputProductById.getId(),leftOver);
        return outputProductService.addOutputProduct(outputProductDto);
    }

    @GetMapping
    public List<OutputProduct> getOutputProducts() {
        return outputProductService.getOutputProducts();
    }

    @GetMapping("/{productId}")
    public List<OutputProduct> getOutputProductByProductId(@PathVariable Integer productId){
        return outputProductService.getOutputProductByProductId(productId);
    }

    @GetMapping("/{id}")
    public OutputProduct getOutputProductById(@PathVariable Integer id) {
        return outputProductService.getOutputProductById(id);
    }

    @DeleteMapping("/{id}")
    public Result deleteOutputProduct(@PathVariable Integer id) {
        return outputProductService.deleteOutputProduct(id);
    }

    @PutMapping("/{id}")
    public Result editOutputProduct(@PathVariable Integer id, @RequestBody OutputProductDto outputProductDto) {
        return outputProductService.editOutputProduct(id,outputProductDto);
    }

}
