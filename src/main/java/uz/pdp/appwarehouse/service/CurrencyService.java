package uz.pdp.appwarehouse.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.appwarehouse.entity.Currency;
import uz.pdp.appwarehouse.entity.Measurement;
import uz.pdp.appwarehouse.payload.Result;
import uz.pdp.appwarehouse.repository.CurrencyRepository;
import uz.pdp.appwarehouse.repository.MeasurementRepository;

import java.util.List;
import java.util.Optional;

@Service
public class CurrencyService {

    @Autowired
    CurrencyRepository currencyRepository;

    public Result addCurrency(Currency currency){
        boolean existByName = currencyRepository.existsByName(currency.getName());
        if (existByName){
            return new Result("Such currency exist!!!",false);
        }
        currencyRepository.save(currency);
        return new Result("Currency added!!!",true);
    }

    public List<Currency> getCurrencies(){
        return currencyRepository.findAll();
    }

    public Currency getCurrencyById(Integer id){
        Optional<Currency> optionalCurrency = currencyRepository.findById(id);
        return optionalCurrency.orElse(null);
    }

    public Result deleteCurrency(Integer id){
        currencyRepository.deleteById(id);
        return new Result("Currency deleted",true);
    }

    public Result editCurrency(Integer id,Currency currency){
        Optional<Currency> optionalCurrency = currencyRepository.findById(id);
        if (!optionalCurrency.isPresent()){
            return new Result("Such currency doesn't exist",false);
        }
        Currency current = optionalCurrency.get();
        current.setName(currency.getName());
        currencyRepository.save(current);
        return new Result("Currency edited!",true);
    }





}
