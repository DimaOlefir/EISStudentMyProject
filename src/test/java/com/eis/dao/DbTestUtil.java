package com.eis.dao;

import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;

import javax.sql.DataSource;

public class DbTestUtil {

    private static DbTestUtil instance = null;

    private DbTestUtil() {
    }

    public static DbTestUtil getInstance() {
        if (instance == null) {
            instance = new DbTestUtil();
        }
        return instance;
    }

    DataSource getMockedDataSource() {
        EmbeddedDatabaseBuilder builder = new EmbeddedDatabaseBuilder();
        return builder
                .setType(EmbeddedDatabaseType.H2)
                .addScript("db/createDB.sql")
                .addScript("db/insertData.sql")
                .build();
    }

    DataSource getEmptyDataSource() {
        EmbeddedDatabaseBuilder builder = new EmbeddedDatabaseBuilder();
        return builder
                .setType(EmbeddedDatabaseType.H2)
                .addScript("db/createDB.sql")
                .addScript("db/insertBaseData.sql")
                .build();
    }
}
