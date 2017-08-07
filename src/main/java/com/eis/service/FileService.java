package com.eis.service;

import com.eis.model.Student;
import com.eis.model.StudentFile;

import java.util.List;

public interface FileService {

    void addFileToStudent(final Student student, final StudentFile file);

    void deleteFile(StudentFile file);

    List<StudentFile> findStudentFiles(Student student);
}
