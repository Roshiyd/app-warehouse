package uz.pdp.appwarehouse.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import uz.pdp.appwarehouse.entity.Measurement;
import uz.pdp.appwarehouse.payload.Result;
import uz.pdp.appwarehouse.repository.MeasurementRepository;

import java.util.List;
import java.util.Optional;

@Service
public class MeasurementService {

    @Autowired
    MeasurementRepository measurementRepository;

    public Result addMeasurement(Measurement measurement){
        boolean existByName = measurementRepository.existsByName(measurement.getName());
        if (existByName){
            return new Result("Such measurement exist!!!",false);
        }
        measurementRepository.save(measurement);
        return new Result("Measurement added!!!",true);
    }

    public List<Measurement> getMeasurements(){
        return measurementRepository.findAll();
    }

    public Measurement getMeasurementById(Integer id){
        Optional<Measurement> optionalMeasurement = measurementRepository.findById(id);
        return optionalMeasurement.orElse(null);
    }

    public Result deleteMeasurement(Integer id){
        measurementRepository.deleteById(id);
        return new Result("Measurement deleted",true);
    }

    public Result editMeasurement(Integer id,Measurement measurement){
        Optional<Measurement> optionalMeasurement = measurementRepository.findById(id);
        if (!optionalMeasurement.isPresent()){
            return new Result("Such measurement doesn't exist",false);
        }
        Measurement current = optionalMeasurement.get();
        current.setName(measurement.getName());
        measurementRepository.save(current);
        return new Result("Measurement edited!",true);
    }





}
