/*
 * Copyright (c) 2015 New Zealand Qualifications Authority.
 * All rights reserved.
 */

package nz.govt.nzqa.healthchecks.web.servlets;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import com.codahale.metrics.health.HealthCheckRegistry;
import com.codahale.metrics.servlets.HealthCheckServlet;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;


/**
 * Add class description here
 *
 * @author $Author$
 * @version $Revision$
 */
public class HealthCheckServletListener extends HealthCheckServlet.ContextListener implements ServletContextListener {

    private WebApplicationContext context;


    public HealthCheckServletListener() {
    }


    public HealthCheckServletListener(WebApplicationContext context) {
        this.context = context;
    }


    @Override
    protected HealthCheckRegistry getHealthCheckRegistry() {
        return context.getBean(HealthCheckRegistry.class);
    }


    @Override
    public void contextInitialized(ServletContextEvent event) {
        this.context = WebApplicationContextUtils.getRequiredWebApplicationContext(event.getServletContext());
        this.context.getServletContext()
                .setAttribute(HealthCheckServlet.HEALTH_CHECK_REGISTRY, context.getBean(HealthCheckRegistry.class));
    }
}
