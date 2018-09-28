package com.example.demo.config;

import org.apache.cxf.Bus;
import javax.xml.ws.Endpoint;
import org.apache.cxf.jaxws.EndpointImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.example.demo.service.WebServiceInterface;

@Configuration
public class CxfConfig {
	@Autowired
    private Bus bus;

    @Autowired
    private WebServiceInterface testService;

    @Bean
    public Endpoint endpoint(){
        EndpointImpl endpoint = new EndpointImpl(bus, testService);
        endpoint.publish("/TestService");
        return endpoint;
    }
}
