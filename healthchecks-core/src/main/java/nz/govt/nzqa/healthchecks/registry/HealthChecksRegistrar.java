/*
 * Copyright (c) 2015 New Zealand Qualifications Authority.
 * All rights reserved.
 */

package nz.govt.nzqa.healthchecks.registry;

import java.util.Map;

import com.codahale.metrics.health.HealthCheck;
import com.codahale.metrics.health.HealthCheckRegistry;


/**
 * Add class description here
 *
 * @author $Author$
 * @version $Revision$
 */
public class HealthChecksRegistrar extends HealthCheckRegistry {


    public void setHealthCheck(Map<String, HealthCheck> healthChecks) {
        for (Map.Entry<String, HealthCheck> entry : healthChecks.entrySet()) {
            super.register(entry.getKey(), entry.getValue());
        }
    }

}
