/*
 * Copyright (c) 2015 New Zealand Qualifications Authority.
 * All rights reserved.
 */

package nz.govt.nzqa.healthchecks.library;

import nz.govt.nzqa.healthchecks.AbstractHealthCheckTest;
import nz.govt.nzqa.healthchecks.resource.Database;

import com.codahale.metrics.health.HealthCheck;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;


/**
 * Add class description here
 *
 * @author $Author$
 * @version $Revision$
 */
public class TestDatabaseHealthCheck extends AbstractHealthCheckTest {

    private static final Logger LOG = LoggerFactory.getLogger(TestDatabaseHealthCheck.class);

    @Autowired
    @Qualifier("webEqaDatabaseHealthCheck")
    private NZQAHealthCheck webEqaDatabaseHealthCheck;

    @Autowired
    @Qualifier("webReadWriteDatabaseHealthCheck")
    private NZQAHealthCheck webReadWriteDatabaseHealthCheck;

    @Autowired
    @Qualifier("webEqaDatabase")
    private Database webEqaDatabase;

    @Autowired
    @Qualifier("webReadWriteDatabase")
    private Database webReadWriteDatabase;

    @Value("${database.unhealthy.message}")
    private String databaseUnhealthyMessage;

    @Before
    public void before() {
        initMocks(this);
    }


    @Test
    public void testDatabaseIsHealthy() throws Exception {

        when(webEqaDatabase.ping()).thenReturn(true);
        assertThat(webEqaDatabaseHealthCheck.check().isHealthy(), is(true));
    }


    @Test
    public void testDatabaseIsNotHealthy() throws Exception {

        when(webEqaDatabase.ping()).thenReturn(false);
        assertThat(webEqaDatabaseHealthCheck.check().isHealthy(), is(false));
    }


    @Test
    public void testMultipleDatabasesAreHealthy() throws Exception {

        HealthCheck.Result expected = HealthCheck.Result.healthy(webEqaDatabase.toString());
        when(webEqaDatabase.ping()).thenReturn(true);
        assertThat(webEqaDatabaseHealthCheck.check().isHealthy(), is(true));
        assertThat(webEqaDatabaseHealthCheck.check(), is(expected));
        expected = HealthCheck.Result.healthy(webReadWriteDatabase.toString());
        when(webReadWriteDatabase.ping()).thenReturn(true);
        assertThat(webReadWriteDatabaseHealthCheck.check().isHealthy(), is(true));
        assertThat(webReadWriteDatabaseHealthCheck.check(), is(expected));
    }


    @Test
    public void testMultipleDatabasesAreNotHealthy() throws Exception {

        HealthCheck.Result expected = HealthCheck.Result.unhealthy(
                databaseUnhealthyMessage + ": " + webEqaDatabase.toString());
        when(webEqaDatabase.ping()).thenReturn(false);
        assertThat(webEqaDatabaseHealthCheck.check().isHealthy(), is(false));
        assertThat(webEqaDatabaseHealthCheck.check(), is(expected));

        expected = HealthCheck.Result.unhealthy(databaseUnhealthyMessage + ": " + webReadWriteDatabase.toString());
        when(webReadWriteDatabase.ping()).thenReturn(false);
        assertThat(webReadWriteDatabaseHealthCheck.check().isHealthy(), is(false));
        assertThat(webReadWriteDatabaseHealthCheck.check(), is(expected));
    }


    static final class HealthCheckTypes {

        public static final HealthCheck.Result HEALTHY = HealthCheck.Result.healthy();
        public static final HealthCheck.Result UNHEALTHY = HealthCheck.Result.unhealthy("Cannot connect to database");
    }
}
