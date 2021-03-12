package uz.pdp.appwarehouse.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.appwarehouse.entity.*;
import uz.pdp.appwarehouse.payload.InputDto;
import uz.pdp.appwarehouse.payload.OutputDto;
import uz.pdp.appwarehouse.payload.Result;
import uz.pdp.appwarehouse.repository.*;

import java.util.List;
import java.util.Optional;

@Service
public class OutputService {

    @Autowired
    OutputRepository outputRepository;
    @Autowired
    WarehouseRepository warehouseRepository;
    @Autowired
    ClientRepository clientRepository;
    @Autowired
    CurrencyRepository currencyRepository;


    public Result addOutput(OutputDto outputDto){
        Optional<Warehouse> optionalWarehouse = warehouseRepository.findById(outputDto.getWarehouseId());
        if (optionalWarehouse.isEmpty()){
            return new Result("Such warehouse doesn't exist",false);
        }
        Optional<Client> optionalClient = clientRepository.findById(outputDto.getClientId());
        if (optionalClient.isEmpty()){
            return new Result("Such client doesn't exist",false);
        }
        Optional<Currency> optionalCurrency = currencyRepository.findById(outputDto.getCurrencyId());
        if (optionalCurrency.isEmpty()){
            return new Result("Such currency doesn't exist",false);
        }
        Output output=new Output();
        output.setDate(outputDto.getDate());
        output.setFactureNumber(outputDto.getFactureNumber());
        output.setWarehouse(optionalWarehouse.get());
        output.setCurrency(optionalCurrency.get());
        output.setClient(optionalClient.get());
        int code=outputRepository.findAll().size()+1;
        output.setCode("I"+code);
        outputRepository.save(output);
        return new Result("Output added!!!",true);
    }

    public List<Output> getOutputs(){
        return outputRepository.findAll();
    }

    public Output getOutputById(Integer id){
        Optional<Output> optionalOutput = outputRepository.findById(id);
        return optionalOutput.orElse(null);
    }

    public Result deleteOutput(Integer id){
        outputRepository.deleteById(id);
        return new Result("Output deleted",true);
    }

    public Result editOutput(Integer id,OutputDto outputDto){
        Optional<Output> optionalOutput = outputRepository.findById(id);
        if (optionalOutput.isEmpty()){
            return new Result("Such output doesn't exist",false);
        }
        Optional<Warehouse> optionalWarehouse = warehouseRepository.findById(outputDto.getWarehouseId());
        if (optionalWarehouse.isEmpty()){
            return new Result("Such warehouse doesn't exist",false);
        }
        Optional<Client> optionalClient = clientRepository.findById(outputDto.getClientId());
        if (optionalClient.isEmpty()){
            return new Result("Such client doesn't exist",false);
        }
        Optional<Currency> optionalCurrency = currencyRepository.findById(outputDto.getCurrencyId());
        if (optionalCurrency.isEmpty()){
            return new Result("Such currency doesn't exist",false);
        }
        Output output = optionalOutput.get();
        output.setDate(outputDto.getDate());
        output.setFactureNumber(outputDto.getFactureNumber());
        output.setWarehouse(optionalWarehouse.get());
        output.setCurrency(optionalCurrency.get());
        output.setClient(optionalClient.get());
        int code=outputRepository.findAll().size()+1;
        output.setCode("I"+code);
        outputRepository.save(output);
        return new Result("Output edited!!!",true);
    }

}
