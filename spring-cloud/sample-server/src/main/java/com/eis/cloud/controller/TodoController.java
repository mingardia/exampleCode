package com.eis.cloud.controller;

import com.eis.cloud.model.TodoModel;
import com.eis.cloud.service.TodoService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;


/**
 * Created by mingardia on 1/31/16.
 */
@Component
@Path("todo")
public class TodoController {

    @Autowired
    TodoService service;

    @Path("/")
    @GET
    @Produces(value = MediaType.APPLICATION_JSON + "," + MediaType.APPLICATION_XML)
    public Response getTodo() {

        TodoModel t = service.getTodo();

        Response rc = Response.ok(t).build();
        return rc;
    }


}



