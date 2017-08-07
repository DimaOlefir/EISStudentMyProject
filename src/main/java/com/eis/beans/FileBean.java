package com.eis.beans;

import com.eis.model.Student;
import com.eis.model.StudentFile;
import com.eis.service.FileService;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.DefaultStreamedContent;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import java.io.ByteArrayInputStream;
import java.util.List;

@ManagedBean
@ViewScoped
public class FileBean {

    @ManagedProperty(value = "#{fileService}")
    private FileService fileService;

    private Student student;

    private List<StudentFile> fileList;

    public void upload(final FileUploadEvent event) {
        StudentFile file = new StudentFile();
        file.setName(event.getFile().getFileName());
        file.setContentType(event.getFile().getContentType());
        file.setContent(event.getFile().getContents());
        fileService.addFileToStudent(student, file);

        getStudentFiles();
    }

    public DefaultStreamedContent download(final StudentFile file) {
        ByteArrayInputStream bis = new ByteArrayInputStream(file.getContent());
        return new DefaultStreamedContent(bis, file.getContentType(), file.getName());
    }

    private void getStudentFiles() {
        fileList = fileService.findStudentFiles(student);
    }

    public void deleteFile(final StudentFile file) {
        fileService.deleteFile(file);
        getStudentFiles();
    }

    public void controlFilesStudent(Student student) {
        this.student = student;
        getStudentFiles();
    }


    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public List<StudentFile> getFileList() {
        return fileList;
    }

    public void setFileList(List<StudentFile> fileList) {
        this.fileList = fileList;
    }

    public FileService getFileService() {
        return fileService;
    }

    public void setFileService(FileService fileService) {
        this.fileService = fileService;
    }
}
