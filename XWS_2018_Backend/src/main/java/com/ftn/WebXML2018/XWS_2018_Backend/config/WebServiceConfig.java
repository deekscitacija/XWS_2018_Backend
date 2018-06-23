package com.ftn.WebXML2018.XWS_2018_Backend.config;

import org.apache.cxf.Bus;
import org.apache.cxf.jaxws.EndpointImpl;

import javax.xml.ws.Endpoint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.ws.config.annotation.EnableWs;
import org.springframework.ws.config.annotation.WsConfigurerAdapter;
import org.springframework.ws.transport.http.MessageDispatcherServlet;
import org.springframework.ws.wsdl.wsdl11.DefaultWsdl11Definition;
import org.springframework.xml.xsd.SimpleXsdSchema;
import org.springframework.xml.xsd.XsdSchema;

@EnableWs
@Configuration
public class WebServiceConfig extends WsConfigurerAdapter {

	@Bean
	public ServletRegistrationBean servletRegistrationBean(ApplicationContext context) {
		MessageDispatcherServlet servlet = new MessageDispatcherServlet();
		servlet.setApplicationContext(context);
		servlet.setTransformWsdlLocations(true);
		
		return new ServletRegistrationBean(servlet, "/agentEndpoint/*");
	};
	
	@Bean
	public XsdSchema agentSchema(){
		return new SimpleXsdSchema(new ClassPathResource("agentEndpointSchema.xsd"));
	}

	@Bean("gimmewsdl")
	public DefaultWsdl11Definition wsdlDefinition(XsdSchema s) {
		DefaultWsdl11Definition def = new DefaultWsdl11Definition();
		def.setSchema(s);
		def.setLocationUri("/agentEndpoint");
		def.setTargetNamespace("http://ftn-booking.com/agentEndpoint");
		def.setPortTypeName("AgentEndpointPort");
		
		return def;
	}
}
