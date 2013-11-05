package de.mag.resttest;

import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;

/**
 * Base class to implement integration tests using the root
 * {@link ApplicationConfig}.
 * 
 */
@ContextConfiguration(classes = ApplicationConfig.class)
public abstract class AbstractIntegrationTest extends
		AbstractTestNGSpringContextTests {

}
