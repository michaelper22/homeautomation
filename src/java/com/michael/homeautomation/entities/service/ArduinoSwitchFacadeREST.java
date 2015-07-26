package com.michael.homeautomation.entities.service;

import com.michael.homeautomation.entities.ArduinoSwitch;
import java.io.StringReader;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.json.Json;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.json.JsonObject;
import javax.json.JsonReader;

/**
 *
 * @author michael
 */
@Stateless
@Path("switch")

public class ArduinoSwitchFacadeREST extends AbstractFacade<ArduinoSwitch> {

    @PersistenceContext(unitName = "homeautomationPU")
    private EntityManager em;

    Client client;
    WebTarget target;

    public ArduinoSwitchFacadeREST() {
	super(ArduinoSwitch.class);
    }

    @PostConstruct
    public void init() {
	client = ClientBuilder.newClient();
	target = client.target("http://{host}:{port}/");
    }

    private WebTarget resolveHostAndPort(WebTarget wTarget, ArduinoSwitch aSwitch) {
	return wTarget.resolveTemplate("host", aSwitch.getHostname())
		.resolveTemplate("port", aSwitch.getPort());
    }

    public void turnOn(ArduinoSwitch aSwitch) {
	pinRequest(aSwitch, 1);
    }

    public void turnOff(ArduinoSwitch aSwitch) {
	pinRequest(aSwitch, 0);
    }

    public void pinRequest(ArduinoSwitch aSwitch, int value) {
	String result = resolveHostAndPort(target.path("digital/{pin}/{value}"), aSwitch)
		.resolveTemplate("pin", aSwitch.getPin())
		.resolveTemplate("value", value)
		.request()
		.get(String.class);
    }

    @PreDestroy
    public void destroy() {
	client.close();
    }

    @POST
    @Override
    @Consumes({"application/xml", "application/json"})
    public void create(ArduinoSwitch entity) {
	super.create(entity);
    }

    @PUT
    @Path("{id}")
    @Consumes({"application/xml", "application/json"})
    public void edit(@PathParam("id") Integer id, ArduinoSwitch entity) {
	super.edit(entity);
    }

    @DELETE
    @Path("{id}")
    public void remove(@PathParam("id") Integer id) {
	super.remove(super.find(id));
    }

    @GET
    @Path("{id}")
    @Produces({"application/xml", "application/json"})
    public ArduinoSwitch find(@PathParam("id") Integer id) {
	return super.find(id);
    }

    @GET
    @Override
    @Produces({"application/xml", "application/json"})
    public List<ArduinoSwitch> findAll() {
	return super.findAll();
    }

    @GET
    @Path("{from}/{to}")
    @Produces({"application/xml", "application/json"})
    public List<ArduinoSwitch> findRange(@PathParam("from") Integer from, @PathParam("to") Integer to) {
	return super.findRange(new int[]{from, to});
    }

    @GET
    @Path("count")
    @Produces("text/plain")
    public String countREST() {
	return String.valueOf(super.count());
    }

    @Override
    protected EntityManager getEntityManager() {
	return em;
    }

    public boolean ping(ArduinoSwitch aSwitch) {
	boolean result;

	try {
	    String jsonResult = resolveHostAndPort(target.path("ping"), aSwitch)
		    .request()
		    .get(String.class);

	    JsonReader jsonReader = Json.createReader(new StringReader(jsonResult));
	    JsonObject jsonObject = jsonReader.readObject();

	    result = jsonObject.getInt("ping") == 1;
	} catch (Exception e) {
	    result = false;
	}

	return result;
    }

}
