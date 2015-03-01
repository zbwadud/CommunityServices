/*
 * Copyright (c) 2015 New Zealand Qualifications Authority.
 * All rights reserved.
 */

package nz.govt.nzqa.healthchecks;

import javax.servlet.ServletContext;
import javax.servlet.ServletRegistration;

import nz.govt.nzqa.healthchecks.config.JerseyConfig;
import nz.govt.nzqa.healthchecks.config.RootConfig;
import nz.govt.nzqa.healthchecks.web.servlets.HealthCheckServletListener;
import nz.govt.nzqa.healthchecks.web.servlets.MetricsServletListener;

import org.glassfish.jersey.servlet.ServletContainer;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
//import org.springframework.web.servlet.DispatcherServlet;


/**
 * Add class description here
 *
 * @author $Author$
 * @version $Revision$
 */
@Order(Ordered.HIGHEST_PRECEDENCE)
public class MyWebAppInitializer implements WebApplicationInitializer {

    @Override
    public void onStartup(ServletContext context) {

        context.addListener(ContextLoaderListener.class);
        context.addListener(HealthCheckServletListener.class);
        context.addListener(MetricsServletListener.class);

        context.setInitParameter(ContextLoader.CONTEXT_CLASS_PARAM, AnnotationConfigWebApplicationContext.class.getName());
        context.setInitParameter(ContextLoader.CONFIG_LOCATION_PARAM, RootConfig.class.getName());
        context.setInitParameter("spring.profiles.active", "default");

        // Register Jersey Servlet
        ServletRegistration.Dynamic dispatcher =
                context.addServlet("Health Checks - Test Service", new ServletContainer(new JerseyConfig()));
//        dispatcher.setInitParameter("javax.ws.rs.Application", JerseyConfig.class.getName());
        dispatcher.setLoadOnStartup(1);
        dispatcher.addMapping("/rest/*");
    }

}