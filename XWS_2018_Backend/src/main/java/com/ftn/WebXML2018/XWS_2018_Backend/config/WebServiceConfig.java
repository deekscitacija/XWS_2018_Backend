package com.ftn.WebXML2018.XWS_2018_Backend.config;

import org.apache.cxf.Bus;
import org.apache.cxf.jaxws.EndpointImpl;

import javax.xml.ws.Endpoint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.ws.config.annotation.EnableWs;

import com.ftn.WebXML2018.XWS_2018_Backend.service.AgentEndpointService;

@EnableWs
@Configuration
public class WebServiceConfig {

	@Autowired
	private Bus bus;
	
	@Autowired
	private AgentEndpointService agentEndpointService;
	
	@Bean
    public Endpoint endpoint() {
        EndpointImpl endpoint = new EndpointImpl(bus, agentEndpointService);
        endpoint.publish("/agentEndpointService");  // putanja_do_spring_boot_aplikacije + putanja_do_cxf_servleta + endpoint_path
        return endpoint;                            // putanja_do_cxf_servleta definisana u application.properties 
    }
}
