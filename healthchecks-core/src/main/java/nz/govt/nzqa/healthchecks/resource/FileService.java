/*
 * Copyright (c) 2015 New Zealand Qualifications Authority.
 * All rights reserved.
 */

package nz.govt.nzqa.healthchecks.resource;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.nio.file.Path;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import jcifs.smb.SmbException;
import jcifs.smb.SmbFile;


/**
 * Add class description here
 *
 * @author $Author$
 * @version $Revision$
 */
public class FileService implements Resource {


    private Path filePath;
    private String domain;
    private String baseUrl;
    private String username;
    private String password;

    // url needs to be of the form:
    // smb://[[[domain;]username[:password]@]server[:port]/[[share/[dir/]file]]][?[param=value[param2=value2[...]]]


    public boolean ping() throws ResourceNotAvailableException {
        try {
            if (filePath != null) {
                if (!filePath.isAbsolute()) {
                    ClassLoader classLoader = this.getClass().getClassLoader();
                    URL resource = classLoader.getResource(filePath.toString());
                    File file = null;
                    if (resource != null) {
                        file = new File(resource.toURI());
                        return file.exists() && file.canRead();
                    }
                    else {
                        return false;
                    }
                }
                else {
                    File file = filePath.toFile();
                    return file.exists() && file.canRead();
                }
            }
            else {
                String smbPath = new StringBuilder("smb://")
                        .append(domain).append(';')
                        .append(username).append(':')
                        .append(password).append('@')
                        .append(baseUrl).append('/')
                        .toString();
                SmbFile smbFile = new SmbFile(smbPath);
                return smbFile.canRead();
            }
        }
        catch (URISyntaxException|MalformedURLException|SmbException e) {
            throw new ResourceNotAvailableException(e.getMessage(), e);
        }
    }


    @Override
    public String toString() {

        return new ToStringBuilder(this, ToStringStyle.SIMPLE_STYLE).toString();
    }


    public void setFilePath(Path filePath) {
        this.filePath = filePath;
    }


    public FileService setDomain(String domain) {
        this.domain = domain;
        return this;
    }


    public FileService setBaseUrl(String baseUrl) {
        this.baseUrl = baseUrl;
        return this;
    }


    public FileService setUsername(String username) {
        this.username = username;
        return this;
    }


    public FileService setPassword(String password) {
        this.password = password;
        return this;
    }
}
