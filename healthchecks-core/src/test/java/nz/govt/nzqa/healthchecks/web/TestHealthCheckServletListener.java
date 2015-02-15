/*
 * Copyright (c) 2015 New Zealand Qualifications Authority.
 * All rights reserved.
 */

package nz.govt.nzqa.healthchecks.web;

import java.util.SortedSet;
import javax.servlet.ServletContextEvent;

import nz.govt.nzqa.healthchecks.registry.HealthChecksRegistrar;

import com.codahale.metrics.health.HealthCheckRegistry;
import com.google.common.collect.Sets;
import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.theories.suppliers.TestedOn;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockServletContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.nullValue;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;


/**
 * Add class description here
 *
 * @author $Author$
 * @version $Revision$
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:test-application-config.xml")
@WebAppConfiguration
public class TestHealthCheckServletListener {

    @Autowired
    WebApplicationContext webApplicationContext;

    @Autowired
    MockServletContext mockServletContext;

    @Autowired
    HealthCheckRegistry registry;

    @Mock
    HealthCheckServletListener listenerMock;


    @Before
    public void before() {
        initMocks(this);
        when(listenerMock.getHealthCheckRegistry()).thenReturn(registry);
    }

    @Test
    public void servletContextListnerHasValidHealthCheckRegistry() throws Exception {
        assertThat(listenerMock.getHealthCheckRegistry(), is(not(nullValue())));
    }

    @Test
    public void servletContextListenerHasHealthCheckRegistryWithAtLeastOneHealthCheck() throws Exception {
        HealthCheckRegistry healthCheckRegistry = listenerMock.getHealthCheckRegistry();
        SortedSet<String> names = healthCheckRegistry.getNames();
        assertThat(names, hasSize(greaterThan(0)));
    }
}
