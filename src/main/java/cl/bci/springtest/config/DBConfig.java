package cl.bci.springtest.config;

import org.h2.server.web.WebServlet;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * H2 console configuration class - String Boot
 *
 * @author Jesus Garcia
 * @since 1.0
 * @version jdk-11.0.7
 */
@Configuration
public class DBConfig {

	@Bean
	ServletRegistrationBean<WebServlet> h2servletRegistration() {
		ServletRegistrationBean<WebServlet> registrationBean = new ServletRegistrationBean<WebServlet>(new WebServlet());
		registrationBean.addUrlMappings("/h2-console/*");
		return registrationBean;
	}

}
