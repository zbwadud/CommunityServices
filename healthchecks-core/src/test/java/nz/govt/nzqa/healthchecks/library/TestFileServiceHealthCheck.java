/*
 * Copyright (c) 2015 New Zealand Qualifications Authority.
 * All rights reserved.
 */

package nz.govt.nzqa.healthchecks.library;

import java.net.URI;
import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.nio.file.Path;

import nz.govt.nzqa.healthchecks.AbstractHealthCheckTest;
import nz.govt.nzqa.healthchecks.resource.FileService;

import com.codahale.metrics.health.HealthCheck;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mock.jndi.SimpleNamingContextBuilder;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.isEmptyOrNullString;
import static org.hamcrest.Matchers.not;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;


/**
 * Add class description here
 *
 * @author $Author$
 * @version $Revision$
 */
public class TestFileServiceHealthCheck extends AbstractHealthCheckTest {

    @Autowired
    FileServiceHealthCheck fileServiceHealthCheck;

    @Autowired
    FileService fileService;


    Path path;

    @Value("${secure.cifs.cmsAssetsClient.user}")
    String cifsCMSAssetsUserId;

    @Value("${secure.cifs.cmsAssetsClient.password}")
    String cifsCMSAssetsPassword;

    @Value("${secure.cifs.cmsAssetsClient.domain}")
    String cifsCMSAssetsDomain;

    @Value("${secure.cifs.cmsAssetsClient.url}")
    String cifsCMSAssetsBaseUrl;


    @Before
    public void setUp() throws Exception {
        initMocks(this);
        path = FileSystems.getDefault().getPath("version/VersionDetails.xml");
        SimpleNamingContextBuilder context = SimpleNamingContextBuilder.emptyActivatedContextBuilder();
        context.bind("java:comp/env/cifsCMSAssetsUserId", cifsCMSAssetsUserId);
        context.bind("java:comp/env/cifsCMSAssetsPassword", cifsCMSAssetsPassword);
        context.bind("java:comp/env/cifsCMSAssetsDomain", cifsCMSAssetsDomain);
        context.bind("java:comp/env/cifsCMSAssetsBaseUrl", cifsCMSAssetsBaseUrl);
    }


    @Before


    @Test
    public void fileServiceIsHealthy() throws Exception {

        when(fileService.ping()).thenReturn(true);
        assertThat(fileServiceHealthCheck.check().isHealthy(), is(true));
    }


    @Test
    public void fileServiceIsUnHealthy() throws Exception {

        when(fileService.ping()).thenReturn(false);
        HealthCheck.Result check = fileServiceHealthCheck.check();
        assertThat(check.isHealthy(), is(false));
        assertThat(check.getMessage(), is(not(isEmptyOrNullString())));
    }


    @Test
    public void fileServiceIsHealthyAccessingAFile() throws Exception {
        fileService = new FileService();
        fileService.setFilePath(path);
        fileServiceHealthCheck = new FileServiceHealthCheck(fileService);
        HealthCheck.Result check = fileServiceHealthCheck.check();
        assertThat("", check.isHealthy(), is(true));
    }


    @Test
    public void fileServiceIsUnHealthyAccessingAFile() throws Exception {
        fileService = new FileService();
        fileService.setFilePath(FileSystems.getDefault().getPath("VersionDetails.xml"));
        fileServiceHealthCheck = new FileServiceHealthCheck(fileService);
        HealthCheck.Result check = fileServiceHealthCheck.check();
        assertThat("File Service is unavailable: " + fileService, check.isHealthy(), is(false));
    }



    @Test
    public void networkFileServiceIsHealthy() throws Exception {
        // smb://[[[domain;]username[:password]@]server[:port]/[[share/[dir/]file]]][?[param=value[param2=value2[...]]]
        fileService = new FileService()
                .setDomain(cifsCMSAssetsDomain)
                .setBaseUrl(cifsCMSAssetsBaseUrl)
                .setUsername(cifsCMSAssetsUserId)
                .setPassword(cifsCMSAssetsPassword);
        fileServiceHealthCheck = new FileServiceHealthCheck(fileService);
        HealthCheck.Result check = fileServiceHealthCheck.check();
        assertThat(check.isHealthy(), is(true));

    }
}
