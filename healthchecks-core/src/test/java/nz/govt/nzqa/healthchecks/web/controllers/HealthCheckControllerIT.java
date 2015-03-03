/*
 * Copyright (c) 2015 New Zealand Qualifications Authority.
 * All rights reserved.
 */

package nz.govt.nzqa.healthchecks.web.controllers;

import java.util.Map;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.WebTarget;

import nz.govt.nzqa.healthchecks.AbstractHealthCheckTest;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.nullValue;


/**
 * This Integration Test is not run during the verify phase of HealthChecks-Testservice.
 */
public class HealthCheckControllerIT extends AbstractHealthCheckTest {


    @Autowired
    @Qualifier("testClientBuilder")
    Client client;

    @Value("${healthcheck.testservice.rest.uri}")
    private String healthCheckTestServiceUri;


    WebTarget target;


    @Before
    public void setUp() throws Exception {

        target = client.target(healthCheckTestServiceUri);


    }


    @After
    public void tearDown() throws Exception {

    }


    @Test
    public void help() throws Exception {

        String response = target.path("help").request().get(String.class);
        System.out.println("response = " + response);

    }

    @Test
    public void list() throws Exception {

        String list = target.path("list").request().get(String.class);
        assertThat(list, is(not(nullValue())));

        ObjectMapper mapper = new ObjectMapper();
        Map<String, String> mapped = mapper.readValue(list, Map.class);
        System.out.println("mapped = " + mapped);

    }
}