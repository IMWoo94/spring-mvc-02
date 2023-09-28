package hello.login.web.filter;

import java.io.IOException;
import java.util.UUID;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class LogFilter implements Filter {

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		Filter.super.init(filterConfig);
		log.info("log LogFilter init");
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws
		IOException,
		ServletException {
		log.info("log LogFilter doFilter");
		HttpServletRequest httpRequest = (HttpServletRequest)request;
		String requestURI = httpRequest.getRequestURI();

		String uuid = UUID.randomUUID().toString();

		try {
			log.info("REQUEST [{}][{}]", uuid, requestURI);
			chain.doFilter(request, response);
		} catch (Exception e) {
			throw e;
		} finally {
			log.info("RESPONSE [{}][{}]", uuid, requestURI);
		}
	}

	@Override
	public void destroy() {
		Filter.super.destroy();
		log.info("log LogFilter destroy");
	}
}
