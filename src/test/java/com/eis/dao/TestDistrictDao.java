package com.eis.dao;

import com.eis.dao.impl.CityDaoImpl;
import com.eis.dao.impl.DistrictDaoImpl;
import com.eis.model.City;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import com.eis.model.District;

import javax.sql.DataSource;
import java.util.List;

/**
 * District table is read-only
 */
public class TestDistrictDao {

    private DataSource dataSource;

    @Before
    public void initialize() {
        dataSource = DbTestUtil.getInstance().getMockedDataSource();
    }

    @Test
    public void testFindDistrictById() {
        DistrictDaoImpl districtDaoImpl = new DistrictDaoImpl();
        districtDaoImpl.setDataSource(dataSource);

        final District district = districtDaoImpl.findDistrict(1L);
        Assert.assertEquals("Pecherskiy", district.getName());
    }

    @Test
    public void testFindDistrictsByCityId() {
        CityDaoImpl cityDaoImpl = new CityDaoImpl();
        cityDaoImpl.setDataSource(dataSource);

        // city is Odessa
        final City city = cityDaoImpl.findCity(2L);

        DistrictDaoImpl districtDaoImpl = new DistrictDaoImpl();
        districtDaoImpl.setDataSource(dataSource);
        final List<District> districtList = districtDaoImpl.findDistrictsForCity(city);
        Assert.assertEquals(4, districtList.size());
    }
}
