/*
 * Copyright (c) 2015 New Zealand Qualifications Authority.
 * All rights reserved.
 */

package nz.govt.nzqa.healthchecks.library;

import nz.govt.nzqa.healthchecks.resource.Database;

import com.codahale.metrics.health.HealthCheck;


/**
 * Add class description here
 *
 * @author $Author$
 * @version $Revision$
 */
public class DatabaseHealthCheck extends HealthCheck {


    // Time in seconds to wait for the database operation to complete
    public static final int TIMEOUT = 60;


    private final Database database;


    public DatabaseHealthCheck(Database database) {
        this.database = database;
    }


    @Override
    protected Result check() throws Exception {
        if (database.ping()) {
            return Result.healthy();
        }
        else {
            return Result.unhealthy("Cannot connect to " + database.toString());
        }
    }
}
