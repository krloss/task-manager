package org.test.taskmanager.resource;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Path;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;
import org.test.taskmanager.ResourcesFactory;
import org.test.taskmanager.model.Task;
import org.test.taskmanager.repository.TaskRepository;

@Component
@Path("/api/tasks")
public class Tasks extends ResourcesFactory<Task> {
    @Autowired
    private TaskRepository repository;

	@Override
    protected TaskRepository getRepository() { return repository; }
    
    @Override
    protected boolean validate(Task entity, Long id) {
        if(null == id) return true;

        return id == entity.getId();
    }

    @Override
    protected Page<Task> findAllByRequest(HttpServletRequest request) {
        String createdAt = request.getParameter("createdAt");

        if(null == createdAt) return super.findAllByRequest(request);

        return repository.findAllByCreatedAt(parseDate(createdAt),createPageRequest(request));
    }
}
