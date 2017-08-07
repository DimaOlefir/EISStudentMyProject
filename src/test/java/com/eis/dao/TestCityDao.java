package com.eis.dao;

import com.eis.dao.impl.CityDaoImpl;
import com.eis.model.City;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import javax.sql.DataSource;

/**
 * City table is read-only
 */
public class TestCityDao {

    private DataSource dataSource;

    @Before
    public void initialize() {
        dataSource = DbTestUtil.getInstance().getEmptyDataSource();
    }

    @Test
    public void testFindAllCities() {
        CityDaoImpl cityDaoImpl = new CityDaoImpl();
        cityDaoImpl.setDataSource(dataSource);
        Assert.assertEquals(2, cityDaoImpl.findAllCities().size());
    }

    @Test
    public void testFindCityById() {
        CityDaoImpl cityDaoImpl = new CityDaoImpl();
        cityDaoImpl.setDataSource(dataSource);

        final City city = cityDaoImpl.findCity(1L);
        Assert.assertEquals("Kiev", city.getName());
    }
}
