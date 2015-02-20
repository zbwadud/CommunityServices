/*
 * Copyright (c) 2015 New Zealand Qualifications Authority.
 * All rights reserved.
 */

package nz.govt.nzqa.healthchecks.resource;

/**
 * Add class description here
 *
 * @author $Author$
 * @version $Revision$
 */
public class ResourceNotAvailableException extends Exception {

    private ResourceNotAvailableException() {
        super();
    }


    private ResourceNotAvailableException(String message) {
        super(message);
    }


    public ResourceNotAvailableException(String message, Throwable cause) {
        super(message, cause);
    }


    private ResourceNotAvailableException(Throwable cause) {
        super(cause);
    }


    private ResourceNotAvailableException(String message, Throwable cause, boolean enableSuppression,
            boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
