package org.test.taskmanager;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.test.taskmanager.repository.PlanRepository;
import org.test.taskmanager.repository.TaskRepository;
import org.test.taskmanager.resource.Plans;
import org.test.taskmanager.resource.Tasks;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MainAppTests {
	@Autowired private PlanRepository planRepository;
	@Autowired private TaskRepository taskRepository;
	@Autowired private DateFormat dateFormat;
	@Autowired private Plans plansResource;
	@Autowired private Tasks tasksResource;

	@Autowired private HttpServletRequest httpServletRequest;

	@Test
	public void contextLoads() {
		assertNotNull(planRepository);
		assertNotNull(taskRepository);
		assertNotNull(dateFormat);
		assertNotNull(plansResource);
		assertNotNull(tasksResource);

		assertNotNull(httpServletRequest);
	}

	@Test
	public void validDatePattern() {
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat(AppDatePattern.DATE_FORMAT);
		
		assertEquals(sdf.format(date),dateFormat.format(date));
	}
}
