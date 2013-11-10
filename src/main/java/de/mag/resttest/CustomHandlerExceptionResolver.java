package de.mag.resttest;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.DefaultHandlerExceptionResolver;

public class CustomHandlerExceptionResolver extends DefaultHandlerExceptionResolver {

	@Override
	protected ModelAndView handleHttpMessageNotReadable(HttpMessageNotReadableException ex,
			HttpServletRequest request, HttpServletResponse response, Object handler) throws IOException {
		response.getOutputStream().print(ex.getLocalizedMessage());
		response.sendError(HttpServletResponse.SC_NO_CONTENT);
		return new ModelAndView();
	}

}
