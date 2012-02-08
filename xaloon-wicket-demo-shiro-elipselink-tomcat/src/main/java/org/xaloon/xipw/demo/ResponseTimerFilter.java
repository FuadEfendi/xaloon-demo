package org.xaloon.xipw.demo;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

public class ResponseTimerFilter implements Filter {
	 protected FilterConfig config;

	public void init(FilterConfig filterConfig) throws ServletException {
		this.config = filterConfig;
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		long startTime = System.currentTimeMillis();
	    chain.doFilter(request, response);
	    long elapsed = System.currentTimeMillis() - startTime;
	    String name = "servlet";
	    if (request instanceof HttpServletRequest) {
	      name = ((HttpServletRequest) request).getRequestURI();
	    }

	    config.getServletContext().log(name + " took " + elapsed + " ms");
	  
	}

	public void destroy() {
		// TODO Auto-generated method stub
		
	}

}
