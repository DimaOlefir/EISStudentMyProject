package com.eis.dao.impl;

import com.eis.dao.DistrictDao;
import com.eis.dao.mappers.DistrictRowMapper;
import com.eis.model.City;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import com.eis.model.District;

import javax.sql.DataSource;
import java.util.List;

@Repository
public class DistrictDaoImpl implements DistrictDao {

    public List<District> findDistrictsForCity(final City city) {
        return this.jdbcTemplate.query("SELECT * FROM District where city_id = ? ",
                                            new Long[]{city.getId()},
                                            new DistrictRowMapper());
    }

    public District findDistrict(final Long id) {
        String sql = "SELECT * FROM District WHERE id = ?";

        return (District) jdbcTemplate.queryForObject(
                sql, new Object[]{id},
                new DistrictRowMapper());
    }

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setDataSource(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }
}
