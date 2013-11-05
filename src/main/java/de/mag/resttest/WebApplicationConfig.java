package de.mag.resttest;

import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJacksonHttpMessageConverter;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

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

	@Override
	public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {

		MappingJacksonHttpMessageConverter jacksonConverter = new MappingJacksonHttpMessageConverter();
		converters.add(jacksonConverter);
		super.configureMessageConverters(converters);
	}

}
