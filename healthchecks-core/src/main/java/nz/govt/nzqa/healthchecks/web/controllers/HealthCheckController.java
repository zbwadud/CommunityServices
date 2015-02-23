/*
 * Copyright (c) 2015 New Zealand Qualifications Authority.
 * All rights reserved.
 */
package nz.govt.nzqa.healthchecks.web.controllers;

import java.net.URI;
import java.util.HashMap;
import java.util.Map;
import java.util.SortedSet;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.core.UriInfo;

import com.codahale.metrics.health.HealthCheck;
import com.codahale.metrics.health.HealthCheckRegistry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

//        import com.fasterxml.jackson.jaxrs.annotation.JacksonFeatures;


/**
 * Add class description here
 *
 * @author $Author$
 * @version $Revision$
 */
@Controller
@Path("hc")
public class HealthCheckController {

    @Context
    UriInfo uriInfo;

    @Autowired
    private HealthCheckRegistry healthChecks;



    @GET
    @Produces(MediaType.TEXT_HTML)
//    @JacksonFeatures(serializationEnable = {SerializationFeature.INDENT_OUTPUT})
    public Response doMenu() {
        StringBuilder view = new StringBuilder().append("<html>\n"
                + "<body>\n"
                + "<a href=\"metrics\">Admin Servlet</a>\n"
                + "</body>\n"
                + "</html>\n");
        return Response.status(Response.Status.OK).entity(view).build();
    }


    @GET
    @Path("/status")
    @Produces(MediaType.APPLICATION_JSON)
//    @JacksonFeatures(serializationEnable = {SerializationFeature.INDENT_OUTPUT})
    public Response doOverallStatus() {
        final Map<String, HealthCheck.Result> results = healthChecks.runHealthChecks();

        Status status = Response.Status.OK;
        for (HealthCheck.Result result : results.values()) {
            if (!result.isHealthy()) {
                status = Response.Status.INTERNAL_SERVER_ERROR;
                break;
            }
        }
        return Response.status(status).entity(results).build();
    }


/*    @RequestMapping(value = "/list", method = RequestMethod.GET)
    @ResponseBody
    @Produces(MediaType.TEXT_HTML)
    public String listServices() {
        Map<RequestMappingInfo, HandlerMethod> handlerMethods = this.handlerMapping.getHandlerMethods();
        StringBuilder listing = new StringBuilder();
        listing.append("<dl>");
        for (Map.Entry<RequestMappingInfo, HandlerMethod> entry : handlerMethods.entrySet()) {
            Set<String> patterns = entry.getKey().getPatternsCondition().getPatterns();
            String url = "";
            if (!patterns.isEmpty()) {
                url = patterns.iterator().next();
            }
            listing.append("<dt><b>[<a href=\"").append(servletContext.getContextPath()).append(url)
                    .append("\" target=\"_blank\">").append(url).append("</a>]</b></dt>");
            MethodParameter[] methodParameters = entry.getValue().getMethodParameters();
            listing.append("<dd>Parameters:</dd>");
            for (MethodParameter methodParameter : methodParameters) {
                formatParameters(listing, methodParameter);
            }
        }
        listing.append("</dl>");
        return "<!DOCTYPE html><html><body>" + listing + "</body></html>";
    }*/


    @GET
    @Path("/list")
    @Produces(MediaType.APPLICATION_JSON)
//    @JacksonFeatures(serializationEnable = {SerializationFeature.INDENT_OUTPUT})
    public Response list() {
        SortedSet<String> names = healthChecks.getNames();
        Map<String, String> namesMap = new HashMap<>();
        String template = uriInfo.getAbsolutePathBuilder().toTemplate();
        for (String name : names) {
            String uri = template.replace("list", name);
            namesMap.put(name, uri);
        };
        return Response.status(Status.OK).entity(namesMap).build();
    }


    @GET
    @Path("/{name}")
    @Produces(MediaType.APPLICATION_JSON)
//    @JacksonFeatures(serializationEnable = {SerializationFeature.INDENT_OUTPUT})
    public Response doCheck(@PathParam("name") String checkName) {
        HealthCheck.Result result = healthChecks.runHealthCheck(checkName);
        Status status = (result.isHealthy() ? Response.Status.OK : Response.Status.INTERNAL_SERVER_ERROR);

        return Response.status(status).entity(result).build();
    }
}

