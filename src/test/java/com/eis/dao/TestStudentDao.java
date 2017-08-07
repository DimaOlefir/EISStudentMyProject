package com.eis.dao;

import com.eis.dao.impl.CityDaoImpl;
import com.eis.dao.impl.DistrictDaoImpl;
import com.eis.dao.impl.StudentDaoImpl;
import com.eis.model.City;
import com.eis.model.Student;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import com.eis.model.District;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.List;


public class TestStudentDao {

    private DataSource dataSource;

    @Before
    public void initialize() {
        dataSource = DbTestUtil.getInstance().getEmptyDataSource();
    }

    @Test
    public void testEmptyStudents() {
        StudentDaoImpl studentDaoImpl = new StudentDaoImpl();
        studentDaoImpl.setDataSource(dataSource);

        Assert.assertEquals(0, studentDaoImpl.findAllStudents().size());
    }

    @Test
    public void testNewStudent() throws SQLException {
        StudentDaoImpl studentDaoImpl = new StudentDaoImpl();
        studentDaoImpl.setDataSource(dataSource);

        Student student = new Student();
        student.setFirstname("Oleg");
        student.setLastname("Blohin");

        CityDaoImpl cityDaoImpl = new CityDaoImpl();
        cityDaoImpl.setDataSource(dataSource);
        City city = cityDaoImpl.findCity(1L);

        DistrictDaoImpl districtDaoImpl = new DistrictDaoImpl();
        districtDaoImpl.setDataSource(dataSource);
        final List<District> districtsForCity = districtDaoImpl.findDistrictsForCity(city);

        student.setCity(city);
        student.setDistrict(districtsForCity.get(0));

        studentDaoImpl.createStudent(student);
        Assert.assertNotNull(student.getId());

        Long id = student.getId();
        Student studentFromDb = studentDaoImpl.findStudent(id);

        Assert.assertEquals(student.getFirstname(), studentFromDb.getFirstname());
        Assert.assertEquals(student.getCity(), studentFromDb.getCity());
        Assert.assertEquals(student.getDistrict(), studentFromDb.getDistrict());

        Assert.assertNull(student.getDescription());

        final List<Student> allStudents = studentDaoImpl.findAllStudents();
        Assert.assertEquals(1, allStudents.size());

        studentDaoImpl.deleteStudent(student);
        Assert.assertEquals(0, studentDaoImpl.findAllStudents().size());
    }
}
