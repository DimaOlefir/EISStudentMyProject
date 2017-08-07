package com.eis.dao;

import com.eis.model.City;

import java.util.List;

public interface CityDao {
    City findCity(Long id);

    List<City> findAllCities();
}
