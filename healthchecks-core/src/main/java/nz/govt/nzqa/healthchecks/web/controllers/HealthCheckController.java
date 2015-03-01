/*
 * Copyright (c) 2015 New Zealand Qualifications Authority.
 * All rights reserved.
 */
package nz.govt.nzqa.healthchecks.web.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.SortedSet;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.core.UriInfo;

import com.codahale.metrics.health.HealthCheck;
import com.codahale.metrics.health.HealthCheckRegistry;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.glassfish.jersey.server.ExtendedResourceContext;
import org.glassfish.jersey.server.model.Resource;
import org.glassfish.jersey.server.model.ResourceMethod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

//        import com.fasterxml.jackson.jaxrs.annotation.JacksonFeatures;


/**
 * Add class description here
 *
 * @author $Author$
 * @version $Revision$
 */
@Path("/")
@Controller
public class HealthCheckController {

    @Context
    UriInfo uriInfo;

    @Context
    private ExtendedResourceContext erContext;

    @Autowired
    private HealthCheckRegistry healthChecks;


    @GET
    @Produces(MediaType.TEXT_HTML)
//    @JacksonFeatures(serializationEnable = {SerializationFeature.INDENT_OUTPUT})
    public Response doMenu() {
        StringBuilder view = new StringBuilder().append("<html>\n"
                + "<body>\n"
                + "<a href=\"../metrics\">Admin Servlet</a>\n"
                + "</body>\n"
                + "</html>\n");
        return Response.status(Response.Status.OK).entity(view.toString()).build();
    }


    @GET
    @Path("/status")
    @Produces(MediaType.APPLICATION_JSON)
//    @JacksonFeatures(serializationEnable = {SerializationFeature.INDENT_OUTPUT})
    public Response doOverallStatus() {
        final Map<String, HealthCheck.Result> results = healthChecks.runHealthChecks();

        Map<String, String> unHealthyResults = new HashMap<>();
        Status status = Response.Status.OK;
        for (Map.Entry<String, HealthCheck.Result> entry : results.entrySet()) {
            if (!entry.getValue().isHealthy()) {
                status = Response.Status.INTERNAL_SERVER_ERROR;
                unHealthyResults.put(entry.getKey(), entry.getValue().getMessage());
            }
        }
        if (!unHealthyResults.isEmpty()) {
            return Response.status(status).entity(unHealthyResults).build();
        }
        return Response.status(status).build();
    }


    @GET
    @Path("/help")
    @Produces(MediaType.TEXT_HTML)
    public String listServices() {
        StringBuilder listing = new StringBuilder();
        listing.append("<dl>");
        List<Resource> rootResources = erContext.getResourceModel().getRootResources();
        for (Resource rootResource : rootResources) {
            listing.append("<dt>Name: ").append(rootResource.getName()).append("</dt>");
            listing.append("<dt>Path: ").append(rootResource.getPath()).append("</dt>");
            listing.append("<dt>Path Pattern: ").append(rootResource.getPathPattern()).append("</dt>");
            listing.append("<dt>Resource Locator: ").append(rootResource.getResourceLocator()).append("</dt>");
            listing.append("<dt>Resource Methods: ").append(rootResource.getResourceMethods()).append("</dt>");
            List<ResourceMethod> resourceMethods = rootResource.getResourceMethods();
            for (ResourceMethod resourceMethod : resourceMethods) {
                listing.append("<dd>Resource Method: ").append(resourceMethod.toString()).append("</dd>");
            }
        }
        listing.append("</dl>");
        return "<!DOCTYPE html><html><body>" + listing + "</body></html>";
    }


    @GET
    @Path("/list")
    @Produces(MediaType.APPLICATION_JSON)
//    @JacksonFeatures(serializationEnable = {SerializationFeature.INDENT_OUTPUT})
    public Response list() throws JsonProcessingException {
        SortedSet<String> names = healthChecks.getNames();
        Map<String, String> namesMap = new HashMap<>();
        String template = uriInfo.getAbsolutePathBuilder().toTemplate();
        for (String name : names) {
            String uri = template.replace("list", name);
            namesMap.put(name, uri);
        };
        ObjectMapper mapper = new ObjectMapper();
        String resultString = mapper.writeValueAsString(namesMap);
        return Response.status(Status.OK).entity(resultString).build();
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
