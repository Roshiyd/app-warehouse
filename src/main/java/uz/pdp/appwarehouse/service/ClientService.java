package uz.pdp.appwarehouse.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import uz.pdp.appwarehouse.entity.Client;
import uz.pdp.appwarehouse.entity.Currency;
import uz.pdp.appwarehouse.payload.Result;
import uz.pdp.appwarehouse.repository.ClientRepository;
import uz.pdp.appwarehouse.repository.CurrencyRepository;

import java.util.List;
import java.util.Optional;

@Service
public class ClientService {

    @Autowired
    ClientRepository clientRepository;

    public Result addClient(Client client){
        if (clientRepository.existsByPhoneNumber(client.getPhoneNumber())) {
            return new Result("Such phone number already exist",false);
        }
        clientRepository.save(client);
        return new Result("Client added!!!",true);
    }

    public List<Client> getClients(){
        List<Client> clientList = clientRepository.findAll();
        return clientList;
    }

    public Client getClientById(Integer id){
        Optional<Client> optionalClient = clientRepository.findById(id);
        return optionalClient.orElse(null);
    }

    public Result deleteClient(Integer id){
        clientRepository.deleteById(id);
        return new Result("Client deleted",true);
    }

    public Result editClient(Integer id,Client client){
        Optional<Client> optionalClient = clientRepository.findById(id);
        if (!optionalClient.isPresent()){
            return new Result("Such client doesn't exist",false);
        }
        Client current = optionalClient.get();
        current.setName(client.getName());
        current.setPhoneNumber(client.getPhoneNumber());
        clientRepository.save(client);
        return new Result("Client edited!",true);
    }

}
