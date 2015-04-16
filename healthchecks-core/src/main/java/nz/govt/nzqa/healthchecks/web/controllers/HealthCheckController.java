/*
 * Copyright (c) 2015 New Zealand Qualifications Authority.
 * All rights reserved.
 */
package nz.govt.nzqa.healthchecks.web.controllers;

import java.net.URI;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.SortedSet;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.core.UriInfo;

import com.codahale.metrics.health.HealthCheck;
import com.codahale.metrics.health.HealthCheckRegistry;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.glassfish.jersey.client.ClientConfig;
import org.glassfish.jersey.server.ExtendedResourceContext;
import org.glassfish.jersey.server.model.Resource;
import org.glassfish.jersey.server.model.ResourceMethod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;


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

    private static final String METRICS_URI = "../metrics/metrics";


    @GET
    @Produces(MediaType.TEXT_HTML)
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
    public Response list() throws JsonProcessingException {
        SortedSet<String> names = healthChecks.getNames();
        Map<String, String> namesMap = new HashMap<>();
        String template = uriInfo.getAbsolutePathBuilder().toTemplate();
        for (String name : names) {
            String uri = template.replace("list", name);
            namesMap.put(name, uri);
        }
        ObjectMapper mapper = new ObjectMapper();
        String resultString = mapper.writeValueAsString(namesMap);
        return Response.status(Status.OK).entity(resultString).type(MediaType.APPLICATION_JSON_TYPE).build();
    }


    @GET
    @Path("/{name}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response doCheck(@PathParam("name") String checkName) {
        HealthCheck.Result result = healthChecks.runHealthCheck(checkName);
        Status status = (result.isHealthy() ? Response.Status.OK : Response.Status.INTERNAL_SERVER_ERROR);

        return Response.status(status).entity(result).build();
    }

    @GET
    @Path("/metrics")
    @Produces(MediaType.APPLICATION_JSON)
    public Response metrics() throws JsonProcessingException {
        URI baseUri = uriInfo.getBaseUri();
        ClientConfig clientConfig = new ClientConfig();
        Client client = ClientBuilder.newClient(clientConfig);
        WebTarget target = client.target(baseUri);
        WebTarget metricsTarget = target.path(METRICS_URI);
        metricsTarget.queryParam("pretty", true);
        Response metricsResponse = metricsTarget.request(MediaType.TEXT_PLAIN_TYPE).get();
        return Response
                .status(metricsResponse.getStatus())
                .entity(metricsResponse.readEntity(String.class))
                .type(metricsResponse.getMediaType())
                .build();

    }
}

