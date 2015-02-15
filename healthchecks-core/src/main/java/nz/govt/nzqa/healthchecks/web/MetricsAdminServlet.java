/*
 * Copyright (c) 2015 New Zealand Qualifications Authority.
 * All rights reserved.
 */

package nz.govt.nzqa.healthchecks.web;

import javax.servlet.annotation.WebServlet;

import com.codahale.metrics.servlets.AdminServlet;


/**
 * Add class description here
 *
 * @author $Author$
 * @version $Revision$
 */
@WebServlet(urlPatterns = "/metrics/*", loadOnStartup = 2)
public class MetricsAdminServlet extends AdminServlet {

}
