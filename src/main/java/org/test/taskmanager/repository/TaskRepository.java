package org.test.taskmanager.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.test.taskmanager.model.Plan;
import org.test.taskmanager.model.Task;

@Repository
public interface TaskRepository extends JpaRepository<Task,Long> {
    Page<Task> findAllByCreatedAt(Date createdAt, Pageable pageable);
    List<Task> findAllByPlan(Plan plan);
}
