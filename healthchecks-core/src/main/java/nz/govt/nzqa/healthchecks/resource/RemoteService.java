/*
 * Copyright (c) 2015 New Zealand Qualifications Authority.
 * All rights reserved.
 */

package nz.govt.nzqa.healthchecks.resource;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Path;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import jcifs.smb.SmbException;
import jcifs.smb.SmbFile;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * Add class description here
 *
 * @author $Author$
 * @version $Revision$
 * This for decouple integration test. SUCCESSFULL
 */
public class RemoteService implements Resource {

    private static final Logger LOG = LoggerFactory.getLogger(RemoteService.class);
    
    private String principal;
    private String credentials;
    private String contextFactory;
    private String serverUrl;
    private String protocol;
    
    private String toString;
    // url needs to be of the form:
    // smb://[[[domain;]username[:password]@]server[:port]/[[share/[dir/]file]]][?[param=value[param2=value2[...]]]
    
    /*
    private Path filePath;
    private String domain;
    private String baseUrl;
    private String username;
    private String password;*/
    
    
    public void setPrincipal(String principal) {
        this.principal = principal;
    }

    public void setCredentials(String credentials) {
        this.credentials = credentials;
    }

    public void setContextFactory(String contextFactory) {
        this.contextFactory = contextFactory;
    }

    public void setServerUrl(String serverUrl) {
        this.serverUrl = serverUrl;
    }

    public void setProtocol(String protocol) {
        this.protocol = protocol;
    }
    
    /** {@inheritDoc} */
    @Override
    public boolean ping() throws ResourceNotAvailableException {
        
        LOG.info("Remote Service Data:-->"+"principal "+principal+" credentials"+credentials+" contextFactory "
                +contextFactory+" serverUrl "+serverUrl+" protocol "+protocol);
        
        return true;
    }


    /*
    public void setFilePath(Path filePath) {
        this.filePath = filePath;
    }


    public RemoteService setDomain(String domain) {
        this.domain = domain;
        return this;
    }


    public RemoteService setBaseUrl(String baseUrl) {
        this.baseUrl = baseUrl;
        return this;
    }


    public RemoteService setUsername(String username) {
        this.username = username;
        return this;
    }


    public RemoteService setPassword(String password) {
        this.password = password;
        return this;
    }


    @Override
    public String toString() {

        return new ToStringBuilder(this, ToStringStyle.SIMPLE_STYLE).append(toString).toString();
    }


    private String smbPathToString() {

        return new StringBuilder("smb://")
                .append(domain).append(';')
                .append("******:")
                .append("******@")
                .append(baseUrl).append('/')
                .toString();
    }
    */
    @Override
    public String toString() {

        return new ToStringBuilder(this, ToStringStyle.SIMPLE_STYLE).append(toString).toString();
    }
}
