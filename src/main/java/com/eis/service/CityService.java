package com.eis.service;

import com.eis.model.City;
import com.eis.model.District;

import java.util.List;

public interface CityService {

    List<City> findAll();

    List<District> findDistrictsForCity(final City city);

    City findCity(Long cityId);

    District findDistrict(Long districtId);
}
