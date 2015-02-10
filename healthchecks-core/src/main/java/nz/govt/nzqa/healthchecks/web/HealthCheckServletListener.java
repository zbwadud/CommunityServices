/*
 * Copyright (c) 2015 New Zealand Qualifications Authority.
 * All rights reserved.
 */

package nz.govt.nzqa.healthchecks.web;

import javax.servlet.annotation.WebListener;

import com.codahale.metrics.health.HealthCheckRegistry;
import com.codahale.metrics.servlets.HealthCheckServlet;
import org.springframework.beans.factory.annotation.Autowired;


/**
 * Add class description here
 *
 * @author $Author$
 * @version $Revision$
 */
@WebListener
public class HealthCheckServletListener extends HealthCheckServlet.ContextListener {

    @Autowired
    HealthCheckRegistry healthCheckRegistry;

    @Override
    protected HealthCheckRegistry getHealthCheckRegistry() {
        return healthCheckRegistry;
    }
}
