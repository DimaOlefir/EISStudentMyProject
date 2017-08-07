package com.eis.service.impl;

import com.eis.dao.StudentDao;
import com.eis.model.Student;
import com.eis.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("studentService")
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentDao studentDao;

    @Transactional
    public void create(final Student student) {
        studentDao.createStudent(student);
    }

    @Transactional
    public void update(final Student student) {
        studentDao.updateStudent(student);
    }

    @Transactional
    public void delete(final Student student) {
        studentDao.deleteStudent(student);
    }

    @Transactional(readOnly = true)
    public Student find(final Long id) {
        return studentDao.findStudent(id);
    }

    @Transactional(readOnly = true)
    public List<Student> findAll() {
        return studentDao.findAllStudents();
    }
}
