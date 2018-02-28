package org.test.taskmanager;

import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.stereotype.Component;
import org.test.taskmanager.resource.Plans;
import org.test.taskmanager.resource.Tasks;

@Component
public class JerseyConfig extends ResourceConfig {
    public JerseyConfig() {
        register(Plans.class);
        register(Tasks.class);
    }
}
