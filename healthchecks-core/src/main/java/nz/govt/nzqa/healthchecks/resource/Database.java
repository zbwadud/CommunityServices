/*
 * Copyright (c) 2015 New Zealand Qualifications Authority.
 * All rights reserved.
 */

package nz.govt.nzqa.healthchecks.resource;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;


/**
 * Add class description here
 *
 * @author $Author$
 * @version $Revision$
 */
public class Database {

    private static final Logger LOG = LoggerFactory.getLogger(Database.class);


    @Autowired
    private DataSource eqaDataSource;


//    public Database(SimpleDriverDataSource simpleDriverDataSource) {
//        this.simpleDriverDataSource = simpleDriverDataSource;
//    }


    public void setEqaDataSource(DataSource eqaDataSource) {
        this.eqaDataSource = eqaDataSource;
    }


    public boolean ping() throws SQLException {
        if (LOG.isDebugEnabled()) {
            LOG.debug("[Database.ping] ");
        }
        try (Connection connection = eqaDataSource.getConnection();
                Statement statement = connection.createStatement()) {
            return statement.execute("SELECT 1");
        }
        catch (SQLException e) {
            throw e;
        }
    }


    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Database{");
        sb.append("eqaDataSource=").append(eqaDataSource);
//        sb.append("url=").append(eqaDataSource.getUrl());
//        sb.append("driver=").append(eqaDataSource.getDriver().getClass().getName());
//        sb.append("username=").append(eqaDataSource.getUsername());
        sb.append('}');
        return sb.toString();
    }
}
