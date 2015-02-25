/*
 * Copyright (c) 2015 New Zealand Qualifications Authority.
 * All rights reserved.
 */

package nz.govt.nzqa.healthchecks.resource;

import java.net.URISyntaxException;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.sql.SQLException;

import nz.govt.nzqa.healthchecks.AbstractHealthCheckTest;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


public class FileServiceTest extends AbstractHealthCheckTest {

    @Autowired
    FileService fileService;

    @Before
    public void setUp() throws Exception {

    }


    @After
    public void tearDown() throws Exception {

    }


    @Test
    public void testSuccessfulPing() throws Exception {
        doReturn(true).when(fileService).ping();
        assertThat(fileService.ping(), is(true));
    }


    @Test
    public void testUnSuccessfulPing() throws Exception {
        doReturn(false).when(fileService).ping();
        assertThat(fileService.ping(), is(false));
    }


    @Test(expected = ResourceNotAvailableException.class)
    public void testPingThrowsException() throws Exception {
        // Given
        URISyntaxException ex = new URISyntaxException("version/VersionDetails.xml", "File Service unavailable.");
        ResourceNotAvailableException toBeThrown = new ResourceNotAvailableException(ex.getMessage(), ex);

        // When
        when(fileService.ping()).thenThrow(toBeThrown);
        fileService.ping();

        // Then exception expected
    }

    @Test
    public void testToString() throws Exception {
        // Given
        when(fileService.toString()).thenReturn("version/VersionDetails.xml");

        // When
        String fileServiceMessage = fileService.toString();

        // Then
        assertThat(fileServiceMessage, is(equalTo("version/VersionDetails.xml")));
    }
}