package uz.pdp.appwarehouse.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uz.pdp.appwarehouse.entity.Input;
import uz.pdp.appwarehouse.entity.InputProduct;
import uz.pdp.appwarehouse.payload.InputDto;
import uz.pdp.appwarehouse.payload.InputProductDto;
import uz.pdp.appwarehouse.payload.Result;
import uz.pdp.appwarehouse.service.InputProductService;
import uz.pdp.appwarehouse.service.InputService;

import java.util.List;

@RestController
@RequestMapping("/inputProduct")
public class InputProductController {
    @Autowired
    InputProductService inputProductService;

    @PostMapping
    public Result addInputProduct(@RequestBody InputProductDto inputProductDto) {
        return inputProductService.addInputProduct(inputProductDto);
    }

    @GetMapping
    public List<InputProduct> getInputProducts() {
        return inputProductService.getInputProducts();
    }

    @GetMapping("/{id}")
    public InputProduct getInputProductById(@PathVariable Integer id) {
        return inputProductService.getInputProductById(id);
    }

    @GetMapping("/getAmount/{productId}")
    public Double getAmountByProductId(@PathVariable Integer productId){
        return inputProductService.getAmountByProductId(productId);
    }

    @DeleteMapping("/{id}")
    public Result deleteInputProduct(@PathVariable Integer id) {
        return inputProductService.deleteInputProduct(id);
    }

    @PutMapping("/{id}")
    public Result editInputProduct(@PathVariable Integer id, @RequestBody InputProductDto inputProductDto) {
        return inputProductService.editInputProduct(id,inputProductDto);
    }



}
