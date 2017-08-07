package com.eis.dao.impl;

import com.eis.dao.StudentDao;
import com.eis.dao.mappers.StudentRowMapper;
import com.eis.model.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class StudentDaoImpl implements StudentDao {

    public void createStudent(Student student) {
        Map<String, Object> parameters = new HashMap<String, Object>(2);
        System.err.println("Creating: " + student.getFirstname() + " " + student.getLastname());
        parameters.put("first_name", student.getFirstname());
        parameters.put("last_name", student.getLastname());
        parameters.put("phone", student.getPhone());
        parameters.put("description", student.getDescription());
        parameters.put("city_id", student.getCity() == null ? null : student.getCity().getId());
        parameters.put("district_id", student.getCity() == null ? null : student.getDistrict().getId());
        parameters.put("deleted", false);
        Number newId = studentCreator.executeAndReturnKey(parameters);
        student.setId(newId.longValue());
    }

    public void updateStudent(Student student) {
        this.jdbcTemplate.update(
                "UPDATE Student " +
                        "SET first_name = ?,last_name = ? , phone = ?, city_id =?, district_id=?, description = ? " +
                        "WHERE id = ?",
                student.getFirstname(),
                student.getLastname(),
                student.getPhone(),
                student.getCity() == null ? 1 : student.getCity().getId(),
                student.getDistrict() == null ? 1 : student.getDistrict().getId(),
                student.getDescription(),
                student.getId());
    }

    public void deleteStudent(Student student) {
        this.jdbcTemplate.update("UPDATE Student set deleted = true WHERE id = ? ", student.getId());
    }

    public List<Student> findAllStudents() {
        return this.jdbcTemplate.query("SELECT s.id as id, s.first_name as first_name, " +
                "s.last_name as last_name, s.phone as phone, s.description as description, " +
                "c.id as c_id, c.city_name as c_name, d.id as d_id, d.district_name as d_name " +
                "FROM Student s " +
                " LEFT JOIN City c on s.city_id = c.id " +
                " LEFT JOIN District d on s.district_id = d.id " +
                " where s.deleted = false",
                new StudentRowMapper());
    }

    public Student findStudent(final Long id) {
        String sql = "SELECT s.id as id, s.first_name as first_name, " +
                "s.last_name as last_name, s.phone as phone, s.description as description, " +
                "c.id as c_id, c.city_name as c_name, d.id as d_id, d.district_name as d_name " +
                "FROM Student s " +
                " LEFT JOIN City c on s.city_id = c.id " +
                " LEFT JOIN District d on s.district_id = d.id " +
                " where s.id = ?";

        return jdbcTemplate.queryForObject(
                sql, new StudentRowMapper(), id);
    }

    private JdbcTemplate jdbcTemplate;
    private SimpleJdbcInsert studentCreator;

    @Autowired
    public void setDataSource(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
        this.studentCreator =
                new SimpleJdbcInsert(dataSource)
                        .withTableName("Student")
                        .usingGeneratedKeyColumns("id");
    }
}
