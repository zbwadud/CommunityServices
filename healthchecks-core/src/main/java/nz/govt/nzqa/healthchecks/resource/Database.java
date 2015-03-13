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


/**
 * Add class description here
 *
 * @author $Author$
 * @version $Revision$
 */
public class Database implements Resource {

    private static final Logger LOG = LoggerFactory.getLogger(Database.class);


    private DataSource dataSource;


    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }


    @Override
    public boolean ping() throws ResourceNotAvailableException {
        if (LOG.isDebugEnabled()) {
            LOG.debug("[Database.ping] ");
        }
        try (Connection connection = dataSource.getConnection();
                Statement statement = connection.createStatement()) {
            return statement.execute("SELECT 1");
        }
        catch (SQLException e) {

            throw new ResourceNotAvailableException(
                    "There was a problem while accessing the database " + dataSource, e);
        }
    }


    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Database - ");
        sb.append("eqaDataSource=").append(dataSource);
        sb.append('}');
        return sb.toString();
    }
}
