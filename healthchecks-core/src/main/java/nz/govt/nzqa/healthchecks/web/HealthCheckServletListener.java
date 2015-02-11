/*
 * Copyright (c) 2015 New Zealand Qualifications Authority.
 * All rights reserved.
 */

package nz.govt.nzqa.healthchecks.web;

import javax.servlet.ServletContextEvent;
import javax.servlet.annotation.WebListener;

import com.codahale.metrics.health.HealthCheckRegistry;
import com.codahale.metrics.servlets.HealthCheckServlet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.WebApplicationContextUtils;


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


    @Override
    public void contextInitialized(ServletContextEvent event) {
        WebApplicationContextUtils
                .getRequiredWebApplicationContext(event.getServletContext())
                .getAutowireCapableBeanFactory()
                .autowireBean(this);
        super.contextInitialized(event);
    }
}
