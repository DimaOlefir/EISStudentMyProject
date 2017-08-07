package com.eis.service;

import com.eis.model.Student;

import java.util.List;

public interface StudentService {

    void create(Student student);

    void update(Student student);

    void delete(Student student);

    Student find(Long id);

    List<Student> findAll();
}
