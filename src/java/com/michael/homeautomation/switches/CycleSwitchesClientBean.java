
package com.michael.homeautomation.switches;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;

@Named
@RequestScoped
public class CycleSwitchesClientBean {
    Client client;
    WebTarget target;
    
    @Inject
    CycleSwitchesBackingBean bean;
    
    @PostConstruct
    public void init(){
	client = ClientBuilder.newClient();
	target = client.target("http://arduino.local/digital/");
    }
    
    public void turnOn(){
	pinRequest(bean.getPinNumber(), 1);
    }
    
    public void turnOff(){
	pinRequest(bean.getPinNumber(), 0);
    }
    
    public void pinRequest(int pin, int value){
	String result = target.path("{pin}/{value}").resolveTemplate("pin", pin).resolveTemplate("value", value).request().get(String.class);
    }
    
    
    
    @PreDestroy
    public void destroy(){
	client.close();
    }
}
