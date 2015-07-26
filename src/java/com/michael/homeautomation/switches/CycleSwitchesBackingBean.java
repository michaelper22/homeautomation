
package com.michael.homeautomation.switches;

import java.io.Serializable;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

@Named
@RequestScoped
public class CycleSwitchesBackingBean implements Serializable {
    int pinNumber;

    public int getPinNumber() {
	return pinNumber;
    }

    public void setPinNumber(int pinNumber) {
	this.pinNumber = pinNumber;
    }
    
    
}
