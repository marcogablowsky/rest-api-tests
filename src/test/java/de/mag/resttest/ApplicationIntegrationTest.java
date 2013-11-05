package de.mag.resttest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.testng.annotations.Test;

/**
 * Integration test to bootstrap the root {@link ApplicationContext}.
 * 
 */
public class ApplicationIntegrationTest extends AbstractIntegrationTest {

	@Autowired
	ApplicationContext context;

	@Test
	public void initializesRootApplicationContext() {

	}

}
