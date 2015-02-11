/*
 * Copyright (c) 2015 New Zealand Qualifications Authority.
 * All rights reserved.
 */

package nz.govt.nzqa.healthchecks.library;

import nz.govt.nzqa.healthchecks.resource.Database;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
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
public class TestDatabaseHealthCheck  {

    private static final Logger LOG = LoggerFactory.getLogger(TestDatabaseHealthCheck.class);

    @Autowired
    private DatabaseHealthCheck databaseHealthCheck;

    @Autowired
    private Database database;

    @Before
    public void before() {
        initMocks(this);
        if (LOG.isDebugEnabled()){
            LOG.debug("[TestDatabaseHealthCheck.before] database = " + database.toString());
        }
    }


    @Test
    public void testDatabaseIsHealthy() throws Exception {

        when(database.ping()).thenReturn(true);
        assertThat(databaseHealthCheck.check().isHealthy(), is(true));
    }


    @Test
    public void testDatabaseIsNotHealthy() throws Exception {

        when(database.ping()).thenReturn(false);
        assertThat(databaseHealthCheck.check().isHealthy(), is(false));
    }
}
