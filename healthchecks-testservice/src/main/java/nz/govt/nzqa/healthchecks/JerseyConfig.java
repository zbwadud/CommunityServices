/*
 * Copyright (c) 2015 New Zealand Qualifications Authority.
 * All rights reserved.
 */

package nz.govt.nzqa.healthchecks;

import org.glassfish.jersey.server.ResourceConfig;
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
        packages("nz.govt.nzqa.healthchecks", "nz.govt.nzqa.healthchecks.web.controllers");
    }

}
