package com.eis.dao;

import com.eis.model.Student;

import java.util.List;

public interface StudentDao {

    void createStudent(Student student);

    void updateStudent(Student student);

    void deleteStudent(Student student);

    List<Student> findAllStudents();

    Student findStudent(Long id);
}
