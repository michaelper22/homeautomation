
package com.michael.homeautomation.switches;

import com.michael.homeautomation.entities.ArduinoSwitch;
import com.michael.homeautomation.entities.service.ArduinoSwitchFacadeREST;
import java.util.List;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

@ManagedBean
@RequestScoped
public class ListSwitchesBackingBean {
    @EJB
    ArduinoSwitchFacadeREST facade;
    
    public List<ArduinoSwitch> getAllSwitches(){
	return facade.findAll();
    }
    
    public String turnSwitchOn(ArduinoSwitch aSwitch){
	facade.turnOn(aSwitch);
	return "";
    }
    
    public String turnSwitchOff(ArduinoSwitch aSwitch){
	facade.turnOff(aSwitch);
	return "";
    }
    
    public String ping(ArduinoSwitch aSwitch){
	boolean result = facade.ping(aSwitch);
	
	FacesMessage facesMessage;
	
	if(result){
	    facesMessage = new FacesMessage(FacesMessage.SEVERITY_INFO,
		    "Ping successful", null);
	}
	else {
	    facesMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR,
		    "Ping failed", null);
	}
	
	FacesContext.getCurrentInstance().addMessage(null, facesMessage);
	 
	return "";
    }
}
