/*
 * Copyright (c) 2015 New Zealand Qualifications Authority.
 * All rights reserved.
 */

package nz.govt.nzqa.healthchecks;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;


/**
 * Add class description here
 *
 * @author $Author$
 * @version $Revision$
 */
/*public class HealthChecksAppInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

    private static final transient Logger LOG = LoggerFactory.getLogger(HealthChecksAppInitializer.class);


    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class<?>[] { RootConfig.class };
    }


    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class<?>[] { SpringMvcConfig.class };
    }


    @Override
    protected String[] getServletMappings() {
        return new String[] {"*//*"};
    }


    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {
        if (LOG.isDebugEnabled()) {
            LOG.debug("[HealthChecksAppInitializer.onStartup] ");
        }
        super.onStartup(servletContext);
    }
}*/
