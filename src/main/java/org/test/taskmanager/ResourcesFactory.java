package org.test.taskmanager;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;

import java.text.DateFormat;
import java.text.ParseException;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public abstract class ResourcesFactory<T> {
    @Autowired
    private DateFormat dateFormat;
    
	protected final Date parseDate(String date) {
		try {
			return dateFormat.parse(date);
        }
        catch(ParseException e) {
			throw new WebApplicationException(Response.Status.BAD_REQUEST);
		}
	}
    
    protected abstract PagingAndSortingRepository<T,Long> getRepository();
    
    protected Page<T> findAllByRequest(HttpServletRequest request) {
        return getRepository().findAll(createPageRequest(request));
    }
    
    protected boolean validate(T entity,Long id) { return true; }
    private boolean validate(T entity) { return validate(entity,null); }
    
    protected final PageRequest createPageRequest(HttpServletRequest request) {
        String page = request.getParameter("page");
        String size = request.getParameter("size");
        String sort = request.getParameter("sort");
        String property = request.getParameter("property");

        if(null == page) page = "0";
        if(null == size) size = "50";
        if(null == sort) sort = "ASC";
        if(null == property) property = "id";

        Sort.Direction direction = Sort.Direction.valueOf(sort.toUpperCase());
        return new PageRequest(Integer.parseInt(page),Integer.parseInt(size),direction,property);
    }

    @GET
    @Transactional(readOnly=true)
    public final Page<T> findAll(@Context HttpServletRequest request) {
        return findAllByRequest(request);
    }

    @GET
    @Path("/{id}")
    @Transactional(readOnly=true)
    public final T findOne(@PathParam("id") Long id) {
        T result = getRepository().findOne(id);

        if(null == result) throw new WebApplicationException(Response.Status.NOT_FOUND);
        
        return result;
    }
    
    @POST
    public final Response insert(@Valid T entity) {
        if(!validate(entity)) throw new WebApplicationException(Response.Status.NOT_ACCEPTABLE);

        entity = getRepository().save(entity);
        return Response.status(Response.Status.CREATED).entity(entity).build();
    }

    @PUT
    @Path("/{id}")
    public final T update(@PathParam("id") Long id, @Valid T entity) {
        if(!validate(entity,id)) throw new WebApplicationException(Response.Status.NOT_ACCEPTABLE);
        if(!getRepository().exists(id)) throw new WebApplicationException(Response.Status.NOT_FOUND);

        return getRepository().save(entity);
    }

    @DELETE
    @Path("/{id}")
    public final void delete(@PathParam("id") Long id) {
        if(!getRepository().exists(id)) throw new WebApplicationException(Response.Status.NOT_FOUND);

        getRepository().delete(id);
        Response.noContent().build();
    }
}
