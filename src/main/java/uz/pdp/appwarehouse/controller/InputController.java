package uz.pdp.appwarehouse.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uz.pdp.appwarehouse.entity.Input;
import uz.pdp.appwarehouse.entity.Product;
import uz.pdp.appwarehouse.payload.InputDto;
import uz.pdp.appwarehouse.payload.ProductDto;
import uz.pdp.appwarehouse.payload.Result;
import uz.pdp.appwarehouse.service.InputService;
import uz.pdp.appwarehouse.service.ProductService;

import java.util.List;

@RestController
@RequestMapping("/input")
public class InputController {
    @Autowired
    InputService inputService;

    @PostMapping
    public Result addInput(@RequestBody InputDto inputDto) {
        return inputService.addInput(inputDto);
    }

    @GetMapping
    public List<Input> getInputs() {
        return inputService.getInputs();
    }

    @GetMapping("/{id}")
    public Input getInputById(@PathVariable Integer id) {
        return inputService.getInputById(id);
    }

    @DeleteMapping("/{id}")
    public Result deleteInput(@PathVariable Integer id) {
        return inputService.deleteInput(id);
    }

    @PutMapping("/{id}")
    public Result editInput(@PathVariable Integer id, @RequestBody InputDto inputDto) {
        return inputService.editInput(id,inputDto);
    }

}
