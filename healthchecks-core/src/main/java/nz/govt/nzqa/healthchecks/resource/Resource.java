/*
 * Copyright (c) 2015 New Zealand Qualifications Authority.
 * All rights reserved.
 */

package nz.govt.nzqa.healthchecks.resource;

import java.sql.SQLException;


/**
 * Represents some resource that will determine it's own status, whether the resource is available or not.
 *
 * @author $Author$
 * @version $Revision$
 */
public interface Resource {

    /**
     * Ping the resource to determine whether it is available or not.
     *
     * @return {@code true} if the resource is available otherwise {@code false}
     * @throws ResourceNotAvailableException
     */
    boolean ping() throws ResourceNotAvailableException;

    @Override
    String toString();
}
