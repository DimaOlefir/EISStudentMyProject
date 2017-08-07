package com.eis.service.exception;

import com.eis.model.StudentFile;

public class FileNotSavedException extends RuntimeException {
    public FileNotSavedException(StudentFile file) {
    }
}
