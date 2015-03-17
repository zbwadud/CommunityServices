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
public class FileServiceHealthCheck extends HealthCheck {


    private final Resource resource;
    private final String errorMessage;


    public FileServiceHealthCheck(Resource resource, String errorMessage) {
        this.resource = resource;
        this.errorMessage = errorMessage;
    }


    @Override
    protected Result check() throws Exception {

        if (resource.ping()) {
            return Result.healthy(resource.toString());
        }
        else {
            return Result.unhealthy(this.errorMessage + ": " + resource.toString());
        }
    }
}
