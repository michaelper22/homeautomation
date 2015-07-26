
package com.michael.homeautomation.entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author michael
 */
@Entity
@Table(name = "arduinoswitch")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ArduinoSwitch.findAll", query = "SELECT a FROM ArduinoSwitch a"),
    @NamedQuery(name = "ArduinoSwitch.findById", query = "SELECT a FROM ArduinoSwitch a WHERE a.id = :id"),
    @NamedQuery(name = "ArduinoSwitch.findByDescription", query = "SELECT a FROM ArduinoSwitch a WHERE a.description = :description"),
    @NamedQuery(name = "ArduinoSwitch.findByHostname", query = "SELECT a FROM ArduinoSwitch a WHERE a.hostname = :hostname")})
public class ArduinoSwitch implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Size(max = 100)
    @Column(name = "description")
    private String description;
    @Size(max = 300)
    @Column(name = "note")
    private String note;
    @Size(max = 60)
    @Column(name = "hostname")
    private String hostname;
    @Column(name = "port")
    private Integer port;
    @Column(name = "pin")
    private Short pin;

    public ArduinoSwitch() {
    }

    public ArduinoSwitch(Integer id) {
	this.id = id;
    }

    public Integer getId() {
	return id;
    }

    public void setId(Integer id) {
	this.id = id;
    }

    public String getDescription() {
	return description;
    }

    public void setDescription(String description) {
	this.description = description;
    }

    public String getNote() {
	return note;
    }

    public void setNote(String note) {
	this.note = note;
    }

    public String getHostname() {
	return hostname;
    }

    public void setHostname(String hostname) {
	this.hostname = hostname;
    }

    public Integer getPort() {
	return port;
    }

    public void setPort(Integer port) {
	this.port = port;
    }

    public Short getPin() {
	return pin;
    }

    public void setPin(Short pin) {
	this.pin = pin;
    }

    @Override
    public int hashCode() {
	int hash = 0;
	hash += (id != null ? id.hashCode() : 0);
	return hash;
    }

    @Override
    public boolean equals(Object object) {
	// TODO: Warning - this method won't work in the case the id fields are not set
	if (!(object instanceof ArduinoSwitch)) {
	    return false;
	}
	ArduinoSwitch other = (ArduinoSwitch) object;
	if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
	    return false;
	}
	return true;
    }

    @Override
    public String toString() {
	return "com.michael.homeautomation.entities.ArduinoSwitch[ id=" + id + " ]";
    }

}
