package org.test.taskmanager.service;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.test.taskmanager.model.Plan;
import org.test.taskmanager.SaveEventRepository;

@Repository
public interface PlanRepository extends SaveEventRepository<Plan>, CrudRepository<Plan,Long> { }
