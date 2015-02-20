/*
 * Copyright (c) 2015 New Zealand Qualifications Authority.
 * All rights reserved.
 */

package nz.govt.nzqa.healthchecks.web;

import java.util.SortedMap;
import java.util.SortedSet;

import nz.govt.nzqa.healthchecks.AbstractHealthCheckTest;

import com.codahale.metrics.health.HealthCheck;
import com.codahale.metrics.health.HealthCheckRegistry;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.nullValue;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;


/**
 * Add class description here
 *
 * @author $Author$
 * @version $Revision$
 */
public class TestHealthCheckServletListener extends AbstractHealthCheckTest {

    @Mock
    HealthCheckServletListener listenerMock;


    @Before
    public void before() {
        initMocks(this);
    }


    @Test
    public void servletContextListnerHasValidHealthCheckRegistry() throws Exception {
        when(listenerMock.getHealthCheckRegistry()).thenReturn(healthCheckRegistry);
        assertThat(listenerMock.getHealthCheckRegistry(), is(not(nullValue())));
    }


    @Test
    public void servletContextListenerHasHealthCheckRegistryWithAtLeastOneHealthCheck() throws Exception {
        when(listenerMock.getHealthCheckRegistry()).thenReturn(healthCheckRegistry);
        HealthCheckRegistry healthCheckRegistry = listenerMock.getHealthCheckRegistry();
        SortedSet<String> names = healthCheckRegistry.getNames();
        assertThat(names, hasSize(greaterThan(0)));
    }


    @Test
    public void test() throws Exception {
        SortedMap<String, HealthCheck.Result> stringResultSortedMap = healthCheckRegistry.runHealthChecks();
    }


}
