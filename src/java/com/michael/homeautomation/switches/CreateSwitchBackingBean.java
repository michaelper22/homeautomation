
package com.michael.homeautomation.switches;

import com.michael.homeautomation.entities.ArduinoSwitch;
import com.michael.homeautomation.entities.service.ArduinoSwitchFacadeREST;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.bean.ManagedBean;
import javax.inject.Named;

@ManagedBean
@RequestScoped
public class CreateSwitchBackingBean {
    ArduinoSwitch arduinoSwitch = new ArduinoSwitch();
    
    @EJB
    ArduinoSwitchFacadeREST facade;

    public ArduinoSwitch getArduinoSwitch() {
	return arduinoSwitch;
    }

    public void setArduinoSwitch(ArduinoSwitch arduinoSwitch) {
	this.arduinoSwitch = arduinoSwitch;
    }
    
    public String addSwitch(){
	facade.create(arduinoSwitch);
	return "/index?faces-redirect=true";
    }
}
