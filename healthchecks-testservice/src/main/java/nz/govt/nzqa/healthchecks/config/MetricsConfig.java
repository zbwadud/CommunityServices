/*
 * Copyright (c) 2015 New Zealand Qualifications Authority.
 * All rights reserved.
 */

package nz.govt.nzqa.healthchecks.config;

import com.codahale.metrics.MetricRegistry;
import com.codahale.metrics.jvm.ClassLoadingGaugeSet;
import com.codahale.metrics.jvm.FileDescriptorRatioGauge;
import com.codahale.metrics.jvm.GarbageCollectorMetricSet;
import com.codahale.metrics.jvm.MemoryUsageGaugeSet;
import com.codahale.metrics.jvm.ThreadStatesGaugeSet;
import com.ryantenney.metrics.spring.config.annotation.EnableMetrics;
import com.ryantenney.metrics.spring.config.annotation.MetricsConfigurerAdapter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;


/**
 * Add class description here
 *
 * @author $Author$
 * @version $Revision$
 */
@Configuration
@EnableMetrics
public class MetricsConfig extends MetricsConfigurerAdapter {

    private static final transient Logger LOG = LoggerFactory.getLogger(MetricsConfig.class);


    @Override
    public void configureReporters(MetricRegistry metricRegistry) {

        metricRegistry.register("jvm.gc.hc", new GarbageCollectorMetricSet());
        metricRegistry.register("jvm.memory.hc", new MemoryUsageGaugeSet());
        metricRegistry.register("jvm.thread-states.hc", new ThreadStatesGaugeSet());
        metricRegistry.register("jvm.fd.usage.hc", new FileDescriptorRatioGauge());
        metricRegistry.register("jvm.classloader.hc", new ClassLoadingGaugeSet());
    }
}
