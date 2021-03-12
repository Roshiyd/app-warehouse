package uz.pdp.appwarehouse.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.appwarehouse.entity.*;
import uz.pdp.appwarehouse.payload.InputDto;
import uz.pdp.appwarehouse.payload.Result;
import uz.pdp.appwarehouse.repository.*;

import java.util.List;
import java.util.Optional;

@Service
public class InputService {

    @Autowired
    InputRepository inputRepository;
    @Autowired
    WarehouseRepository warehouseRepository;
    @Autowired
    SupplierRepository supplierRepository;
    @Autowired
    CurrencyRepository currencyRepository;


    public Result addInput(InputDto inputDto){
        Optional<Warehouse> optionalWarehouse = warehouseRepository.findById(inputDto.getWarehouseId());
        if (optionalWarehouse.isEmpty()){
            return new Result("Such warehouse doesn't exist",false);
        }
        Optional<Supplier> optionalSupplier = supplierRepository.findById(inputDto.getSupplierId());
        if (optionalSupplier.isEmpty()){
            return new Result("Such supplier doesn't exist",false);
        }
        Optional<Currency> optionalCurrency = currencyRepository.findById(inputDto.getCurrencyId());
        if (optionalCurrency.isEmpty()){
            return new Result("Such currency doesn't exist",false);
        }
        Input input=new Input();
        input.setDate(inputDto.getDate());
        input.setFactureNumber(inputDto.getFactureNumber());
        int code=inputRepository.findAll().size()+1;
        input.setCode("I"+code);
        input.setWarehouse(optionalWarehouse.get());
        input.setSupplier(optionalSupplier.get());
        input.setCurrency(optionalCurrency.get());
        inputRepository.save(input);
        return new Result("Input added!!!",true);
    }

    public List<Input> getInputs(){
        return inputRepository.findAll();
    }

    public Input getInputById(Integer id){
        Optional<Input> optionalInput = inputRepository.findById(id);
        return optionalInput.orElse(null);
    }


    public Result deleteInput(Integer id){
        inputRepository.deleteById(id);
        return new Result("Input deleted",true);
    }

    public Result editInput(Integer id,InputDto inputDto){
        Optional<Input> optionalInput = inputRepository.findById(id);
        if (optionalInput.isEmpty()){
            return new Result("Such input doesn't exist",false);
        }
        Optional<Warehouse> optionalWarehouse = warehouseRepository.findById(inputDto.getWarehouseId());
        if (optionalWarehouse.isEmpty()){
            return new Result("Such warehouse doesn't exist",false);
        }
        Optional<Supplier> optionalSupplier = supplierRepository.findById(inputDto.getSupplierId());
        if (optionalSupplier.isEmpty()){
            return new Result("Such supplier doesn't exist",false);
        }
        Optional<Currency> optionalCurrency = currencyRepository.findById(inputDto.getCurrencyId());
        if (optionalCurrency.isEmpty()){
            return new Result("Such currency doesn't exist",false);
        }
        Input input = optionalInput.get();
        input.setDate(inputDto.getDate());
        input.setFactureNumber(inputDto.getFactureNumber());
        int code=inputRepository.findAll().size()+1;
        input.setCode("I"+code);
        input.setWarehouse(optionalWarehouse.get());
        input.setSupplier(optionalSupplier.get());
        input.setCurrency(optionalCurrency.get());
        inputRepository.save(input);
        return new Result("Input edited!!!",true);
    }

}
