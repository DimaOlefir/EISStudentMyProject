package com.eis.service.exception;

import com.eis.model.Student;

public class StudentNotRegisteredException extends RuntimeException {
    public StudentNotRegisteredException(Student student) {
    }
}
