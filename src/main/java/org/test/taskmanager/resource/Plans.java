package org.test.taskmanager.resource;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.validation.Valid;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.test.taskmanager.model.Plan;
import org.test.taskmanager.repository.PlanRepository;

@Component
@Transactional
@Path("/api/plans")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class Plans {
    @Autowired
    private PlanRepository planRepository;
    
    @GET
    @Transactional(readOnly=true)
    public Page<Plan> findAll(@QueryParam("page") Integer page,
        @QueryParam("size") Integer size, @QueryParam("sort") String sort) {
        
        if(null == page) page = 0;
        if(null == size) size = 30;
        if(null == sort) sort = "ASC";
        
        Sort.Direction direction = Sort.Direction.valueOf(sort.toUpperCase());
        Page<Plan> result = planRepository.findAll(new PageRequest(page,size,direction,"name"));

        for(Plan it : result)  Hibernate.initialize(it.getTasks());

        return result;
    }

    @GET
    @Path("/{id}")
    @Transactional(readOnly=true)
    public Plan findOne(@PathParam("id") Long id) {
        Plan result = planRepository.findOne(id);

        if(null == result) throw new WebApplicationException(Response.Status.NOT_FOUND);
        
        Hibernate.initialize(result.getTasks());
        return result;
    }

    @POST
    public Plan insert(@Valid Plan plan) {
        Response.status(Response.Status.CREATED).entity(plan);

        Response.status(Response.Status.CREATED).build();
        return planRepository.save(plan);
    }

    @PUT
    @Path("/{id}")
    public Plan update(@PathParam("id") Long id, @Valid Plan plan) {
        if(id != plan.getId()) throw new WebApplicationException(Response.Status.NOT_ACCEPTABLE);
        if(!planRepository.exists(id)) throw new WebApplicationException(Response.Status.NOT_FOUND);

        return planRepository.save(plan);
    }

    @DELETE
    @Path("/{id}")
    public void delete(@PathParam("id") Long id) {
        if(!planRepository.exists(id)) throw new WebApplicationException(Response.Status.NOT_FOUND);

        planRepository.delete(id);
        Response.noContent().build();
    }
}
