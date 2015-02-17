/*
 * Copyright (c) 2015 New Zealand Qualifications Authority.
 * All rights reserved.
 */

package nz.govt.nzqa.healthchecks.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.ImportResource;


/**
* Add class description here
*
* @author $Author$
* @version $Revision$
*/
@Configuration
@ImportResource("classpath:test-application-config.xml")
@Import(MetricsConfig.class)
public class RootConfig { }
