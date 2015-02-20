/*
 * Copyright (c) 2015 New Zealand Qualifications Authority.
 * All rights reserved.
 */

package nz.govt.nzqa.healthchecks.library;

import nz.govt.nzqa.healthchecks.resource.Resource;

import com.codahale.metrics.health.HealthCheck;


/**
 * Add class description here
 *
 * @author $Author$
 * @version $Revision$
 */
public class DatabaseHealthCheck extends HealthCheck {


    private final Resource resource;


    public DatabaseHealthCheck(Resource resource) {
        this.resource = resource;
    }


    @Override
    protected Result check() throws Exception {
        if (resource.ping()) {
            return Result.healthy();
        }
        else {
            return Result.unhealthy("Cannot connect to " + resource.toString());
        }
    }
}
