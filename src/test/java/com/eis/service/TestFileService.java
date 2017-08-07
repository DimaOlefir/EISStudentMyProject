package com.eis.service;

import com.eis.model.Student;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import com.eis.model.City;
import com.eis.model.District;
import com.eis.model.StudentFile;
import com.eis.dao.CityDao;
import com.eis.dao.DistrictDao;

import java.io.*;
import java.nio.file.Files;
import java.util.List;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:testApplicationContext.xml")
public class TestFileService {

    @Autowired
    private StudentService studentService;

    @Autowired
    private FileService fileService;

    @Autowired
    private CityDao cityDao;

    @Autowired
    private DistrictDao districtDao;

    @Test
    public void test() throws IOException {
        final Student student = createStudent();
        final List<StudentFile> studentFiles = fileService.findStudentFiles(student);
        Assert.assertEquals(0, studentFiles.size());

        // read test file to insert database
        ClassLoader classLoader = getClass().getClassLoader();
        File file = new File(classLoader.getResource("test.txt").getFile());

        StudentFile studentFile = new StudentFile();
        studentFile.setName("test.txt");
        studentFile.setContentType("application/text");
        studentFile.setContent(Files.readAllBytes(file.toPath()));

        fileService.addFileToStudent(student, studentFile);
        Assert.assertNotNull(studentFile.getId());

        Assert.assertEquals(1, fileService.findStudentFiles(student).size());

        fileService.deleteFile(studentFile);
        Assert.assertEquals(0, fileService.findStudentFiles(student).size());

        studentService.delete(student);
    }

    private Student createStudent() {
        Student student = new Student();
        student.setFirstname("Name");
        student.setLastname("Lastname-file");

        City city = cityDao.findCity(1L);
        final List<District> districtsForCity = districtDao.findDistrictsForCity(city);

        student.setCity(city);
        student.setDistrict(districtsForCity.get(0));

        studentService.create(student);
        return student;
    }
}
