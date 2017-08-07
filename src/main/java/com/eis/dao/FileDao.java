package com.eis.dao;

        import com.eis.model.Student;
        import com.eis.model.StudentFile;

        import java.util.List;

public interface FileDao {
    List<StudentFile> findStudentFiles(Student student);

    void addFileToStudent(Student student, StudentFile file);

    void deleteFile(StudentFile file);
}
