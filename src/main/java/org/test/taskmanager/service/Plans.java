package org.test.taskmanager.service;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import org.springframework.stereotype.Service;

@Service
@Path("/api/plans")
public class Plans {
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String edi() {
        return "{x:10, y:20}";
    }
}
