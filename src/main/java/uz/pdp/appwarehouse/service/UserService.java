package uz.pdp.appwarehouse.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import uz.pdp.appwarehouse.entity.Client;
import uz.pdp.appwarehouse.entity.User;
import uz.pdp.appwarehouse.entity.Warehouse;
import uz.pdp.appwarehouse.payload.Result;
import uz.pdp.appwarehouse.payload.UserDto;
import uz.pdp.appwarehouse.repository.ClientRepository;
import uz.pdp.appwarehouse.repository.UserRepository;
import uz.pdp.appwarehouse.repository.WarehouseRepository;

import java.nio.file.attribute.UserDefinedFileAttributeView;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    WarehouseRepository warehouseRepository;

    public Result addUser(UserDto userDto){
        if (userRepository.existsByPhoneNumber(userDto.getPhoneNumber())){
            return new Result("Such phone number already exist!!! ",false);
        }
        User user=new User();
        user.setFirstName(userDto.getFirstName());
        user.setLastName(userDto.getLastName());
        user.setPhoneNumber(userDto.getPhoneNumber());
        user.setPassword(userDto.getPassword());
        Set<Warehouse> warehouseSet=new HashSet<>();
        for (Integer warehouse : userDto.getWarehousesId()) {
            Optional<Warehouse> optionalWarehouse = warehouseRepository.findById(warehouse);
            if (optionalWarehouse.isEmpty()){
                return new Result("Such warehouse doesn't exist!!! ",false);
            }
            warehouseSet.add(optionalWarehouse.get());
        }
        Integer code=userRepository.findAll().size()+1;
        user.setCode("User"+code);
        user.setWarehouses(warehouseSet);
        userRepository.save(user);
        return new Result("User added!!!",true);
    }

    public List<User> getUsers(){
        List<User> userList = userRepository.findAll();
        return userList;
    }

    public User getUserById(Integer id){
        Optional<User> optionalUser = userRepository.findById(id);
        return optionalUser.orElse(null);
    }

    public Result deleteUser(Integer id){
        userRepository.deleteById(id);
        return new Result("User deleted",true);
    }

    public Result editUser(Integer id,UserDto userDto){
        Optional<User> optionalUser = userRepository.findById(id);
        if (optionalUser.isEmpty()){
            return new Result("Such user doesn't exist",false);
        }
        User user = optionalUser.get();
        if (userRepository.existsByPhoneNumber(userDto.getPhoneNumber())){
            return new Result("Such phone number already exist!!! ",false);
        }
        user.setFirstName(userDto.getFirstName());
        user.setLastName(userDto.getLastName());
        user.setPhoneNumber(userDto.getPhoneNumber());
        user.setPassword(userDto.getPassword());
        Set<Warehouse> warehouseSet=new HashSet<>();
        for (Integer warehouse : userDto.getWarehousesId()) {
            Optional<Warehouse> optionalWarehouse = warehouseRepository.findById(warehouse);
            if (optionalWarehouse.isEmpty()){
                return new Result("Such warehouse doesn't exist!!! ",false);
            }
            warehouseSet.add(optionalWarehouse.get());
        }
        user.setWarehouses(warehouseSet);
        userRepository.save(user);
        return new Result("User edited!!!",true);
    }

}
