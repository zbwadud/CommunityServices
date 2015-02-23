/*
 * Copyright (c) 2015 New Zealand Qualifications Authority.
 * All rights reserved.
 */

package nz.govt.nzqa.healthchecks.web.servlets;

import javax.servlet.ServletContextEvent;

import com.codahale.metrics.MetricRegistry;
import com.codahale.metrics.health.HealthCheckRegistry;
import com.codahale.metrics.servlets.HealthCheckServlet;
import com.codahale.metrics.servlets.MetricsServlet;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;


/**
 * Add class description here
 *
 * @author $Author$
 * @version $Revision$
 */
public class MetricsServletListener extends MetricsServlet.ContextListener {


    private WebApplicationContext context;


    public MetricsServletListener() {
    }


    public MetricsServletListener(WebApplicationContext context) {
        this.context = context;
    }


    @Override
    protected MetricRegistry getMetricRegistry() {
        return context.getBean(MetricRegistry.class);
    }


    @Override
    public void contextInitialized(ServletContextEvent event) {
        this.context = WebApplicationContextUtils.getRequiredWebApplicationContext(event.getServletContext());
        this.context.getServletContext()
                .setAttribute(MetricsServlet.METRICS_REGISTRY, context.getBean(MetricRegistry.class));
    }
}
