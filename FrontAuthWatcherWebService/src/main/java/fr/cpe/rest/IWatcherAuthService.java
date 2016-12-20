package fr.cpe.rest;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import fr.cpe.model.User;

@Path("/auth")
public interface IWatcherAuthService {
	
	@GET
	@Produces("application/json")
	@Path("/echo")
	String echo();
	
	/**
	 * Authenticate user
	 * 
	 * Consumes Json: 
	 * url:		http://localhost:8080/FrontAuthWatcherWebService/rest/watcherauth/auth
	 * body:	{	"login" : "tpwatcher", 
	 * 				"password" : "tp" }
	 * 
	 * Produces Json:
	 * 	if user exists: {	"id": 2,
	 *					  	"login": "tpwatcher",
	 *					  	"password": "tp",
	 *					  	"firstName": "tp",
	 *					  	"lastName": "tp",
	 *					  	"role": "WATCHER" }
	 *  if user do not exists: null
	 *  
	 * @param user
	 * @return
	 */
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/")
	User auth(User user);
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/cors ")
	Response authCORS(User user);

}
