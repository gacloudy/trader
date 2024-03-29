package com.trader;

import java.util.Optional;

import org.apache.catalina.connector.Connector;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.server.WebServerFactoryCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.apache.coyote.ajp.AbstractAjpProtocol;

@Configuration
public class AppConfig {

	@Bean
	public WebServerFactoryCustomizer<TomcatServletWebServerFactory> servletContainer() {
		return server ->
			Optional.ofNullable(server)
					.ifPresent(s -> s.addAdditionalTomcatConnectors(redirectConnector()));
	}
 
	@SuppressWarnings("rawtypes")
	private Connector redirectConnector() {
		Connector connector = new Connector("AJP/1.3");
		connector.setScheme("http");
		connector.setPort(8009);
		connector.setRedirectPort(8443);
		connector.setSecure(false);
		connector.setAllowTrace(false);
		((AbstractAjpProtocol) connector.getProtocolHandler()).setSecretRequired(false);
		
		return connector;
	}
}