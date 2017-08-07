package com.eis.dao.mappers;

import org.springframework.jdbc.core.RowMapper;
import com.eis.model.StudentFile;

import java.sql.ResultSet;
import java.sql.SQLException;

public class FileRowMapper implements RowMapper<StudentFile> {

    public StudentFile mapRow(final ResultSet resultSet, final int rowIndex) throws SQLException {

        StudentFile file = new StudentFile();
        file.setId(resultSet.getLong("id"));
        file.setName(resultSet.getString("file_name"));
        file.setDescription(resultSet.getString("description"));
        file.setContentType(resultSet.getString("content_type"));
        file.setContent(resultSet.getBytes("content"));
        return file;
    }
}
