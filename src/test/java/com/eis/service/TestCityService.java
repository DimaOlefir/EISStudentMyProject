package com.eis.service;

import com.eis.model.City;
import com.eis.model.District;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:testApplicationContext.xml")
public class TestCityService {

    @Autowired
    private CityService cityService;

    @Test
    public void testMockCities() {
        final List<City> all = cityService.findAll();
        Assert.assertEquals(2, all.size());
    }

    @Test
    public void testMockKiev() {
        final City city = cityService.findCity(1L);
        Assert.assertEquals("Kiev", city.getName());
    }

    @Test
    public void testMockMalinovskiy() {
        final District district = cityService.findDistrict(6L);
        Assert.assertEquals("Malinovskiy", district.getName());
    }

    @Test
    public void testMockKievDistricts() {
        final City city = cityService.findCity(1L);
        final List<District> districtsForCity = cityService.findDistrictsForCity(city);
        Assert.assertEquals(4, districtsForCity.size());
        Assert.assertEquals("Pecherskiy", districtsForCity.get(0).getName());
        Assert.assertEquals("Obolonskiy", districtsForCity.get(1).getName());
        Assert.assertEquals("Svatoshinskiy", districtsForCity.get(2).getName());
        Assert.assertEquals("Desnyanskiy", districtsForCity.get(3).getName());
    }

}
