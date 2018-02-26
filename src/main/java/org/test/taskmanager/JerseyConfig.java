package org.test.taskmanager;

import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.stereotype.Component;
import org.test.taskmanager.service.Plans;

@Component
public class JerseyConfig extends ResourceConfig {
    public JerseyConfig() {
        register(Plans.class);
    }
}
