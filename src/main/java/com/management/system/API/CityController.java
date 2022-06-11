package com.management.system.API;

import com.management.system.Entity.City;
import com.management.system.Entity.DTO.CityDTO;
import com.management.system.Service.CityService;
import com.management.system.Utility.DTOConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/city")
@CrossOrigin
public class CityController {

    @Autowired
    private CityService cityService;

    @Autowired
    private DTOConverter dtoConverter;

    @GetMapping
    public ResponseEntity<List<City>> getAllCities(){
        try{
            return new ResponseEntity<>(cityService.getAllCities(), HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
    }

    @PostMapping("/saveCity")
    public ResponseEntity<City> saveCity(@RequestBody CityDTO city){
        try {
           return new ResponseEntity<City>(cityService.saveCity(dtoConverter.cityDtoToEntityConverter(city)), HttpStatus.CREATED) ;
        }catch(Exception e){
            e.printStackTrace();
        }

        return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
    }
}
