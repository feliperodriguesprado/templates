package templates.java.web.project2.rest;

import java.util.List;
import java.util.logging.Logger;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;
import javax.ws.rs.core.Response.Status;
import javax.xml.bind.JAXBElement;

import templates.java.web.project2.data.Data;
import templates.java.web.project2.jaxb.Jaxb1;

@Path("/resource1")
public class Resource1 {

	@Path("method1")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response method1() {

		Logger.getGlobal().info("Exemplo de uma requisição GET sem parâmetros.");

		GenericEntity<List<Jaxb1>> entity = new GenericEntity<List<Jaxb1>>(Data.getInstance().getJaxbList()) {
		};

		return Response.status(Status.OK).entity(entity).build();

		// Se necessário utilizar controle de acesso HTTP (CORS):

		// return
		// Response.status(Status.OK).entity(entity).header("Access-Control-Allow-Origin",
		// "*")
		// .header("Access-Control-Allow-Methods", "GET").build();
	}

	@Path("method2")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response method2(@QueryParam("id") String id) {

		Logger.getGlobal().info("Exemplo de uma requisição GET com um parâmetro query (variável id) na URL.");

		Jaxb1 jaxb;
		ResponseBuilder response = null;

		try {
			jaxb = Data.getInstance().getJaxbById(Long.parseLong(id));
			response = Response.status(Status.OK).entity(jaxb);
		} catch (Exception e) {
			response = Response.status(506).entity("Objeto de ID " + id + " não encontrado");
		}

		return response.build();
	}

	@Path("method3/{id}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response method3(@PathParam("id") String id) {

		Logger.getGlobal().info("Exemplo de uma requisição GET com um parâmetro path na URL.");

		Jaxb1 jaxb;
		ResponseBuilder response = null;

		try {
			jaxb = Data.getInstance().getJaxbById(Long.parseLong(id));
			response = Response.status(Status.OK).entity(jaxb);
		} catch (Exception e) {
			response = Response.status(506).entity("Objeto de ID " + id + " não encontrado");
		}

		return response.build();
	}

	@Path("method4")
	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Jaxb1 method4(JAXBElement<Jaxb1> jaxb) {

		Logger.getGlobal().info("Exemplo de uma requisição PUT.");

		Jaxb1 jaxb1 = jaxb.getValue();
		jaxb1.setId(System.currentTimeMillis());
		Data.getInstance().getJaxbList().add(jaxb1);

		return jaxb1;
	}

	@Path("method5")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response method5(JAXBElement<Jaxb1> jaxb) {

		Logger.getGlobal().info("Exemplo de uma requisição POST.");

		long id;
		Jaxb1 jaxb1;
		ResponseBuilder response;

		try {
			id = jaxb.getValue().getId();
			jaxb1 = Data.getInstance().getJaxbById(id);
			jaxb1.setDescription(jaxb.getValue().getDescription());
			response = Response.status(Status.OK).entity(jaxb1);
		} catch (Exception e) {
			response = Response.status(507)
					.entity("Não foi possível atualizar objeto de ID: " + jaxb.getValue().getId());
		}

		return response.build();
	}

	@Path("method6/{id}")
	@DELETE
	public Response method6(@PathParam("id") String id) {

		Logger.getGlobal().info("Exemplo de uma requisição DELETE.");

		Jaxb1 jaxb;
		int jaxbIndex = -1;
		ResponseBuilder response;

		try {
			jaxb = Data.getInstance().getJaxbById(Long.parseLong(id));
			jaxbIndex = Data.getInstance().getJaxbList().indexOf(jaxb);
			Data.getInstance().getJaxbList().remove(jaxbIndex);
			response = Response.status(Status.OK).entity("ID " + id + " removido com sucesso");
		} catch (Exception e) {
			response = Response.status(508).entity("ID " + id + " não encontrado para ser removido");
		}

		return response.build();
	}

}
