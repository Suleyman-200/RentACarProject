package com.example.rentacar.business.concretes;

import com.example.rentacar.business.abstracts.RentService;
import com.example.rentacar.dto.request.RentCarRequest;
import com.example.rentacar.dto.request.UpdateRentRequest;
import com.example.rentacar.exceptions.BusinessException;
import com.example.rentacar.exceptions.NotFoundException;
import com.example.rentacar.repositories.CarRepository;
import com.example.rentacar.repositories.RentRepository;
import com.example.rentacar.repositories.UserRepository;
import com.example.rentacar.models.Car;
import com.example.rentacar.models.Rent;
import com.example.rentacar.models.User;
import com.example.rentacar.mappers.ModelMapperService;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class RentManager implements RentService {
    private final RentRepository rentRepository;
    private final CarRepository carRepository;
    private final UserRepository userRepository;
    private final ModelMapperService modelMapperService;
    private final KafkaTemplate<Object, Rent> kafkaTemplate;
    Logger logger= LoggerFactory.getLogger(RentManager.class);

    public RentManager(RentRepository rentRepository, CarRepository carRepository, UserRepository userRepository, ModelMapperService modelMapperService, KafkaTemplate<Object, Rent> kafkaTemplate) {
        this.rentRepository = rentRepository;
        this.carRepository = carRepository;
        this.userRepository = userRepository;
        this.modelMapperService = modelMapperService;
        this.kafkaTemplate = kafkaTemplate;
    }

    @Override
    public ResponseEntity<String> rentCar(RentCarRequest rentCarRequest) {
        Car car = carRepository.findByPlate(rentCarRequest.getCarPlate());
        Optional<User> userOptional = userRepository.findByuserName(rentCarRequest.getUserName());

        if (car == null) {
            logger.error("Can't find car with plate: "+rentCarRequest.getCarPlate());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Can't find car with plate: " + rentCarRequest.getCarPlate());

        }

        if (userOptional.isEmpty()) {
            logger.error("Cant find user with username: "+ rentCarRequest.getUserName());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Can't find user with username: " + rentCarRequest.getUserName());
        }




        boolean isRentAvailable = isRentAvailable(car, rentCarRequest.getRentStartDate(),rentCarRequest.getRentEndDate());
        if (!isRentAvailable) {
            logger.error("Car is not available for the specified dates");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Car is not available for the specified dates");
        }
        User user=userOptional.get();
        car.setAvailable(false);
        car.setRentEndDate(rentCarRequest.getRentEndDate());
        carRepository.save(car);

        Rent rent=this.modelMapperService.forRequest().map(rentCarRequest,Rent.class);
        rent.setUser(user);
        rent.setCar(car);

        rentRepository.save(rent);
        logger.info("Car rented successfully,Rent ID: " + rent.getId()+ " username: "+ rentCarRequest.getUserName());
        //kafkaTemplate.send("rent-notification",rent);
        return ResponseEntity.ok("Car rented successfully.Please don't forget this Rent ID: " + rent.getId());
    }

    private boolean isRentAvailable(Car car, Date rentStartDate, Date rentEndDate) {

        List<Rent> rents = rentRepository.findByCarAndRentEndDateGreaterThanEqual(car, rentStartDate);

        for (Rent rent : rents) {

            if (rent.getRentStartDate().before(rentEndDate) && rent.getRentEndDate().after(rentStartDate)) {
                logger.error("Car is already rented for the specified dates");


            }
        }


        if (!car.getAvailable()) {
            logger.error("Car is currently rented");
            return false;
        }

        return true;
    }
    @Override
    public List<Rent> getAllRents() {
        return rentRepository.findAll();
    }

    @Override
    @Transactional
    public void delete(Long id) {
        Rent rent = rentRepository.findById(id).orElse(null);
        if (rent == null) {
            logger.error("Can't find rent with id: "+id);
            throw new NotFoundException("Can't find rent with id: " + id);

        }

        Car car = rent.getCar();
        if (car != null) {
            car.setAvailable(true);
            car.setRentEndDate(null);
            carRepository.save(car);
        }
        this.rentRepository.deleteById(id);
    }

    @Override
    public void update(UpdateRentRequest updateRentRequest) {
        Rent rent=this.modelMapperService.forRequest().map(updateRentRequest,Rent.class);
        Rent rent1=this.rentRepository.findById(updateRentRequest.getId()).orElseThrow(()->new NotFoundException("Can't find this rent id"+rent.getId()));
        rent1.setRentStartDate(rent.getRentStartDate());
        rent1.setRentEndDate(rent.getRentEndDate());
        this.rentRepository.save(rent1);
    }
}

