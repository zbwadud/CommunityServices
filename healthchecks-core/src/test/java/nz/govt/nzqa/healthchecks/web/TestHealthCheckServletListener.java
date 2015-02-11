/*
 * Copyright (c) 2015 New Zealand Qualifications Authority.
 * All rights reserved.
 */

package nz.govt.nzqa.healthchecks.web;

import javax.servlet.ServletContextEvent;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockServletContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.web.context.WebApplicationContext;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.nullValue;


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

    HealthCheckServletListener listener;


    @Before
    public void before() {

        mockServletContext.setAttribute(WebApplicationContext.ROOT_WEB_APPLICATION_CONTEXT_ATTRIBUTE, webApplicationContext);
        listener = new HealthCheckServletListener();
        listener.contextInitialized(new ServletContextEvent(mockServletContext));
    }

    @Test
    public void testHealthCheckServletListener() throws Exception {
        assertThat(listener.getHealthCheckRegistry(), is(not(nullValue())));
    }
}
