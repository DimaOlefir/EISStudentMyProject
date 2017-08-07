package com.eis.beans;

import com.eis.model.City;
import com.eis.model.District;
import com.eis.model.Student;
import com.eis.service.CityService;
import com.eis.service.StudentService;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import java.util.ArrayList;
import java.util.List;

@ManagedBean
@ViewScoped
public class StudentBean {

    private Student student;

    @ManagedProperty(value = "#{studentService}")
    private StudentService studentService;

    @ManagedProperty(value = "#{cityService}")
    private CityService cityService;

    public List<Student> getStudents() {
        return studentService.findAll();
    }

    private List<City> cityList;

    public List<City> getCityList() {
        return cityList;
    }

    @PostConstruct
    public void setup() {
        cityList = cityService.findAll();
    }

    public void saveOrUpdate() {
        if (student != null) {
            if (cityId != null) {
                final City city = cityService.findCity(cityId);
                student.setCity(city);
            }
            if (districtId != null) {
                final District district = cityService.findDistrict(districtId);
                student.setDistrict(district);
            }
            if (student.getId() != null) {
                studentService.update(student);
            } else {
                studentService.create(student);
            }
        }
    }

    public void create() {
        student = new Student();
        districtId = cityId = null;
    }

    public void editStudent(Student student) {
        this.student = student;
        if (student == null) {
            cityId = districtId = null;
        } else {
            if (student.getCity() != null) {
                cityId = student.getCity().getId();
                City city = new City();
                city.setId(cityId);
                districtList = cityService.findDistrictsForCity(city);
            } else {
                cityId = null;
            }
            if (student.getDistrict() != null) {
                districtId = student.getDistrict().getId();
            } else {
                districtId = null;
            }
        }
    }

    public void onCityChange() {
        if (cityId != null) {
            City city = new City();
            city.setId(cityId);
            districtList = cityService.findDistrictsForCity(city);
        } else {
            districtList = new ArrayList<District>();
        }
    }

    public StudentService getStudentService() {
        return studentService;
    }

    public void setStudentService(final StudentService studentService) {
        this.studentService = studentService;
    }

    public CityService getCityService() {
        return this.cityService;
    }

    public void setCityService(final CityService cityService) {
        this.cityService = cityService;
    }

    public void delete(Student student) {
        studentService.delete(student);
    }

    public Student find(Long id) {
        return studentService.find(id);
    }

    public List<Student> findAll() {
        return studentService.findAll();
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Student getStudent() {
        return student;
    }

    private Long cityId;

    public Long getCityId() {
        return cityId;
    }

    public void setCityId(final Long cityId) {
        this.cityId = cityId;
    }

    private Long districtId;

    public Long getDistrictId() {
        return districtId;
    }

    public void setDistrictId(final Long districtId) {
        this.districtId = districtId;
    }

    private List<District> districtList;

    public List<District> getDistrictList() {
        return districtList;
    }

    public void setDistrictList(final List<District> districtList) {
        this.districtList = districtList;
    }
}
