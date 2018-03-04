package org.test.taskmanager.resource;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.test.taskmanager.ResourcesFactory;
import org.test.taskmanager.model.Plan;
import org.test.taskmanager.repository.PlanRepository;
import org.test.taskmanager.repository.TaskRepository;

@Component
@Path("/api/plans")
public class Plans extends ResourcesFactory<Plan> {
    @Autowired
    private PlanRepository repository;

    @Autowired
    private TaskRepository taskRepository;

	@Override
    protected PlanRepository getRepository() { return repository; }
    protected TaskRepository getTaskRepository() { return taskRepository; }
    
    @GET
    @Path("/{id}/tasks")
    @Transactional(readOnly=true)
    public final Response getTasks(@PathParam("id") Long id) {
        Plan result = findOne(id);

        return Response.ok(getTaskRepository().findAllByPlan(result)).build();
    }
}
