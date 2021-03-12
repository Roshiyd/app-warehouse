package uz.pdp.appwarehouse.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import uz.pdp.appwarehouse.entity.Client;
import uz.pdp.appwarehouse.entity.Supplier;
import uz.pdp.appwarehouse.payload.Result;
import uz.pdp.appwarehouse.repository.ClientRepository;
import uz.pdp.appwarehouse.repository.SupplierRepository;

import java.util.List;
import java.util.Optional;

@Service
public class SupplierService {

    @Autowired
    SupplierRepository supplierRepository;

    public Result addSupplier(Supplier supplier){
        if (supplierRepository.existsByPhoneNumber(supplier.getPhoneNumber())) {
            return new Result("Such phone number already exist",false);
        }
        supplierRepository.save(supplier);
        return new Result("Supplier added!!!",true);
    }

    public List<Supplier> getSupplier(){
        List<Supplier> supplierList = supplierRepository.findAll();
        return supplierList;
    }


    public Supplier getSupplierById(Integer id){
        Optional<Supplier> optionalSupplier = supplierRepository.findById(id);
        return optionalSupplier.orElse(null);
    }

    public Result deleteSupplier(Integer id){
        supplierRepository.deleteById(id);
        return new Result("Supplier deleted",true);
    }

    public Result editSupplier(Integer id,Supplier supplier){
        Optional<Supplier> optionalSupplier = supplierRepository.findById(id);
        if (!optionalSupplier.isPresent()){
            return new Result("Such supplier doesn't exist",false);
        }
        Supplier current = optionalSupplier.get();
        current.setName(supplier.getName());
        current.setPhoneNumber(supplier.getPhoneNumber());
        supplierRepository.save(current);
        return new Result("Supplier edited!",true);
    }

}
