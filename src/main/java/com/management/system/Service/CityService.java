package com.management.system.Service;

import com.management.system.DAO.CityRepository;
import com.management.system.Entity.City;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.naming.NameAlreadyBoundException;
import java.util.List;

@Service
public class CityService {

    @Autowired
    private CityRepository cityRepository;

    public Integer getNumberOfEventsInCity(long cityId){
        return cityRepository.getNumberOfEventsInCity(cityId);
    }

    public City saveCity(City city) throws Exception {
        if(!checkIfAlreadyExist(city.getName()))
            return cityRepository.save(city);
        else
            throw new Exception("City already existing in database!");
    }

    public String getCityNameById(long cityId){
        return cityRepository.getCityNameById(cityId);
    }

    public boolean checkIfAlreadyExist(String cityName){
        if(cityRepository.getCityByName(cityName) == null)
            return false;
        else
            return true;
    }

    public List<City> getAllCities(){
        return cityRepository.findAll();
    }

    public City getCityById(long cityId){
        return cityRepository.getCityById(cityId);
    }

    public City getCityByName(String cityName){
        return cityRepository.getCityByName(cityName);
    }
}
