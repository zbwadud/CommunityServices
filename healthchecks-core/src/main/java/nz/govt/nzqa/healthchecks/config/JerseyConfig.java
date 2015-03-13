/*
 * Copyright (c) 2015 New Zealand Qualifications Authority.
 * All rights reserved.
 */

package nz.govt.nzqa.healthchecks.config;

import javax.xml.bind.Marshaller;

import nz.govt.nzqa.healthchecks.web.controllers.HealthCheckController;

import org.eclipse.persistence.jaxb.JAXBContextProperties;
import org.eclipse.persistence.jaxb.MarshallerProperties;
import org.glassfish.jersey.CommonProperties;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.server.spring.scope.RequestContextFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * Add class description here
 *
 * @author $Author$
 * @version $Revision$
 */
public class JerseyConfig extends ResourceConfig {

    private static final transient Logger log = LoggerFactory.getLogger(JerseyConfig.class);


    /**
     * Registers JAX-RS application components.
     */
    public JerseyConfig() {
        if (log.isDebugEnabled()) {
            if (log.isDebugEnabled()) {
                log.debug("[JerseyConfig.JerseyConfig] CONSTRUCTOR");
            }
        }
        // JAX-RS package scan
        register(HealthCheckController.class);
        register(RequestContextFilter.class);
        Object property = getProperty(CommonProperties.FEATURE_AUTO_DISCOVERY_DISABLE);
        property(JAXBContextProperties.JSON_INCLUDE_ROOT, true);
        property(MarshallerProperties.JSON_INCLUDE_ROOT, true);
        property(Marshaller.JAXB_FORMATTED_OUTPUT, true);
    }



}
