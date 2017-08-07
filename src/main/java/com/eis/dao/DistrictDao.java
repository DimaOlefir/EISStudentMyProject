package com.eis.dao;

import com.eis.model.City;
import com.eis.model.District;

import java.util.List;

public interface DistrictDao {
    District findDistrict(final Long id);

    List<District> findDistrictsForCity(final City city);
}
