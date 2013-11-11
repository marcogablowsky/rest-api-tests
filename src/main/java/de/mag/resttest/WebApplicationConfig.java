package de.mag.resttest;

import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.mvc.annotation.ResponseStatusExceptionResolver;
import org.springframework.web.servlet.mvc.method.annotation.ExceptionHandlerExceptionResolver;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages = "de.mag.resttest.web", excludeFilters = @Filter({ Service.class,
		Configuration.class }))
public class WebApplicationConfig extends WebMvcConfigurerAdapter {

	@Bean
	public CommonsMultipartResolver multipartResolver() {
		CommonsMultipartResolver resolver = new CommonsMultipartResolver();
		return resolver;
	}

	// @Override
	// public void configureMessageConverters(List<HttpMessageConverter<?>>
	// converters) {
	// MappingJacksonHttpMessageConverter jacksonConverter = new
	// MappingJacksonHttpMessageConverter();
	// converters.add(jacksonConverter);
	// super.configureMessageConverters(converters);
	// }

	@Override
	public void configureHandlerExceptionResolvers(List<HandlerExceptionResolver> exceptionResolvers) {
		// When overriding this method we need to define the whole ExceptionResolver
		// stack
		exceptionResolvers.add(new ResponseStatusExceptionResolver());
		// This is our custom implementation instead of
		// DefaultHandlerExceptionResolver.
		exceptionResolvers.add(new CustomHandlerExceptionResolver());
		exceptionResolvers.add(new ExceptionHandlerExceptionResolver());
	}
}
