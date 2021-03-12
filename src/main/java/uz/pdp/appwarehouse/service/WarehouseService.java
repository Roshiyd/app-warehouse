package uz.pdp.appwarehouse.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.appwarehouse.entity.Measurement;
import uz.pdp.appwarehouse.entity.Warehouse;
import uz.pdp.appwarehouse.payload.Result;
import uz.pdp.appwarehouse.repository.MeasurementRepository;
import uz.pdp.appwarehouse.repository.WarehouseRepository;

import java.util.List;
import java.util.Optional;

@Service
public class WarehouseService {

    @Autowired
    WarehouseRepository warehouseRepository;

    public Result addWarehouse(Warehouse warehouse){
        warehouseRepository.save(warehouse);
        return new Result("Warehouse added!!!",true);
    }

    public List<Warehouse> getWarehouses(){
        return warehouseRepository.findAll();
    }


    public Warehouse getWarehouseById(Integer id){
        Optional<Warehouse> optionalWarehouse = warehouseRepository.findById(id);
        return optionalWarehouse.orElse(null);
    }

    public Result deleteWarehouse(Integer id){
        warehouseRepository.deleteById(id);
        return new Result("Warehouse deleted",true);
    }

    public Result editWarehouse(Integer id,Warehouse warehouse){
        Optional<Warehouse> optionalWarehouse = warehouseRepository.findById(id);
        if (!optionalWarehouse.isPresent()){
            return new Result("Such warehouse doesn't exist",false);
        }
        Warehouse current = optionalWarehouse.get();
        current.setName(warehouse.getName());
        warehouseRepository.save(current);
        return new Result("Warehouse edited!",true);
    }





}
