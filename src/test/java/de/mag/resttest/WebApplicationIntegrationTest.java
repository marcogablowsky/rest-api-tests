package de.mag.resttest;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.handler.HandlerExceptionResolverComposite;
import org.testng.Assert;
import org.testng.annotations.Test;

public class WebApplicationIntegrationTest extends AbstractWebIntegrationTest {

	@Autowired
	WebApplicationContext webAppContext;

	@Test
	public void initializesWebApplicationContext() {

	}

	@Test
	public void exceptionResolverStackMatchesDefaultResolverStackSetup() {
		Map<String, HandlerExceptionResolver> exceptionResolvers = webAppContext
				.getBeansOfType(HandlerExceptionResolver.class);
		Assert.assertEquals(exceptionResolvers.values().size(), 1);
		HandlerExceptionResolver resolver = exceptionResolvers.values().iterator().next();
		if (resolver instanceof HandlerExceptionResolverComposite) {
			HandlerExceptionResolverComposite composite = (HandlerExceptionResolverComposite) resolver;
			Assert.assertEquals(composite.getExceptionResolvers().size(), 3);
		}
	}
}
