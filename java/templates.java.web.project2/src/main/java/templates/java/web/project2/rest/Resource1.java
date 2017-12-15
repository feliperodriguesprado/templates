package templates.java.web.project2.rest;

import java.util.Date;
import java.util.Random;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import templates.java.web.project2.jaxb.Jaxb1;

@Path("/resource1")
public class Resource1 {

	/*@GET
	@Produces(MediaType.TEXT_PLAIN)
	public String method1() {
		
		Jaxb1 jaxb1 = new Jaxb1();
		jaxb1.setId(new Random().nextInt());
		jaxb1.setDate(new Date());
		jaxb1.setDescription("Text plain in application REST.");
		
		return jaxb1.toString();
	}*/
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Jaxb1 method2() {
		
		Jaxb1 jaxb1 = new Jaxb1();
		jaxb1.setId(new Random().nextInt());
		jaxb1.setDate(new Date());
		jaxb1.setDescription("JSON object in application REST.");
		
		return jaxb1;
	}

}
