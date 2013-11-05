package de.mag.resttest;

import javax.annotation.Resource;

import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.ContextHierarchy;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.testng.annotations.BeforeClass;

/**
 * Base class to derive concrete web test classes from.
 * 
 */
@WebAppConfiguration
@ContextHierarchy({ @ContextConfiguration(classes = ApplicationConfig.class),
		@ContextConfiguration(classes = WebApplicationConfig.class) })
public abstract class AbstractWebIntegrationTest extends AbstractTestNGSpringContextTests {

	@Resource
	WebApplicationContext wac;

	protected MockMvc mvc;

	@BeforeClass
	public void setup() {
		mvc = MockMvcBuilders.webAppContextSetup(wac).build();
	}
}
