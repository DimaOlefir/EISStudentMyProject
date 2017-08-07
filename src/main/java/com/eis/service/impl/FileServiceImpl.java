package com.eis.service.impl;

import com.eis.dao.FileDao;
import com.eis.model.Student;
import com.eis.model.StudentFile;
import com.eis.service.FileService;
import com.eis.service.exception.FileNotSavedException;
import com.eis.service.exception.StudentNotRegisteredException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("fileService")
public class FileServiceImpl implements FileService {

    @Autowired
    private FileDao fileDao;

    public void addFileToStudent(final Student student, final StudentFile file) {
        if (student.getId() == null) {
            throw new StudentNotRegisteredException(student);
        }
        fileDao.addFileToStudent(student, file);
    }

    public void deleteFile(final StudentFile file) {
        if (file.getId() == null) {
            throw new FileNotSavedException(file);
        }
        fileDao.deleteFile(file);
    }

    public List<StudentFile> findStudentFiles(final Student student) {
        if (student.getId() == null) {
            throw new StudentNotRegisteredException(student);
        }
        return fileDao.findStudentFiles(student);
    }
}
