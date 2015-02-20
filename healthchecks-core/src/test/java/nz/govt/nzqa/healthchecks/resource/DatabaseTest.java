/*
 * Copyright (c) 2015 New Zealand Qualifications Authority.
 * All rights reserved.
 */

package nz.govt.nzqa.healthchecks.resource;

import java.sql.SQLException;
import javax.sql.DataSource;

import nz.govt.nzqa.healthchecks.AbstractHealthCheckTest;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;


public class DatabaseTest extends AbstractHealthCheckTest {

    @Autowired
    @Qualifier("webEqaDatabase")
    Database database;

    @Mock
    @Qualifier("webEqaDataSource")
    DataSource webEqaDataSource;


    @Before
    public void setUp() throws Exception {
        initMocks(this);
        database.setDataSource(webEqaDataSource);

    }


    @After
    public void tearDown() throws Exception {

    }


    @Test
    public void testSuccessfulPing() throws Exception {
        doReturn(true).when(database).ping();
        assertThat(database.ping(), is(true));
    }


    @Test
    public void testUnsuccessfulPing() throws Exception {
        doReturn(false).when(database).ping();
        assertThat(database.ping(), is(false));
    }


    @Test(expected = ResourceNotAvailableException.class)
    public void testPingThrowsException() throws Exception {
        SQLException sqle = new SQLException("Connection failed.", new Throwable().fillInStackTrace());
        when(database.ping()).thenThrow(new ResourceNotAvailableException(sqle.getMessage(), sqle));
        database.ping();
    }
}