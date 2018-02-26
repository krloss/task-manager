package org.test.taskmanager.service;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.test.taskmanager.model.Task;

@Repository
public interface TaskRepository extends CrudRepository<Task,Long> { }
