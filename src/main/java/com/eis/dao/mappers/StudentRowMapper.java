
package com.eis.dao.mappers;

import com.eis.model.City;
import com.eis.model.Student;
import org.springframework.jdbc.core.RowMapper;
import com.eis.model.District;

import java.sql.ResultSet;
import java.sql.SQLException;

public class StudentRowMapper implements RowMapper<Student> {

    public Student mapRow(final ResultSet resultSet, final int rowIndex) throws SQLException {
        Student student = new Student();
        student.setId(resultSet.getLong("id"));
        student.setFirstname(resultSet.getString("first_name"));
        student.setLastname(resultSet.getString("last_name"));
        student.setPhone(resultSet.getString("phone"));
        student.setDescription(resultSet.getString("description"));

        City city = new City();
        city.setId(resultSet.getLong("c_id"));
        city.setName(resultSet.getString("c_name"));
        student.setCity(city);
        District district = new District();
        district.setId(resultSet.getLong("d_id"));
        district.setName(resultSet.getString("d_name"));
        student.setDistrict(district);
        return student;
    }
}
