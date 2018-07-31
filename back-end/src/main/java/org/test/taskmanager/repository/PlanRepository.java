package org.test.taskmanager.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.test.taskmanager.model.Plan;

@Repository
public interface PlanRepository extends JpaRepository<Plan,Long> { }
