package com.eis.service.impl;

import com.eis.dao.CityDao;
import com.eis.dao.DistrictDao;
import com.eis.model.City;
import com.eis.model.District;
import com.eis.service.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("cityService")
@Transactional(readOnly = true)
public class CityServiceImpl implements CityService {

    @Autowired
    private CityDao cityDao;

    @Autowired
    private DistrictDao districtDao;

    public List<City> findAll() {
        return cityDao.findAllCities();
    }

    public List<District> findDistrictsForCity(final City city) {
        return districtDao.findDistrictsForCity(city);
    }

    public City findCity(final Long cityId) {
        return cityDao.findCity(cityId);
    }

    public District findDistrict(final Long districtId) {
        return districtDao.findDistrict(districtId);
    }
}
