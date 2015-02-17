/*
 * Copyright (c) 2015 New Zealand Qualifications Authority.
 * All rights reserved.
 */

package nz.govt.nzqa.healthchecks;

import nz.govt.nzqa.healthchecks.config.RootConfig;

import com.codahale.metrics.MetricRegistry;
import com.codahale.metrics.health.HealthCheckRegistry;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.AnnotationConfigWebContextLoader;
import org.springframework.test.context.web.WebAppConfiguration;


/**
 * Add class description here
 *
 * @author $Author$
 * @version $Revision$
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(
        loader = AnnotationConfigWebContextLoader.class,
        classes = {RootConfig.class})
@WebAppConfiguration
public abstract class AbstractHealthCheckTest {

    @Autowired
    public HealthCheckRegistry healthCheckRegistry;

    @Autowired
    public MetricRegistry metricsRegistry;
}
