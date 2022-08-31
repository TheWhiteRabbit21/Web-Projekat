package services;

import javax.annotation.PostConstruct;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import beans.Korisnik;
import dao.KorisnikDAO;

@Path("/login")
public class LoginService {
	
	@Context
	ServletContext ctx;
	
	public LoginService() {
		
	}
	
	@PostConstruct
	// ctx polje je null u konstruktoru, mora se pozvati nakon konstruktora (@PostConstruct anotacija)
	public void init() {
		// Ovaj objekat se instancira vi�e puta u toku rada aplikacije
		// Inicijalizacija treba da se obavi samo jednom
		if (ctx.getAttribute("korisnikDAO") == null) {
	    	String contextPath = ctx.getRealPath("");
			ctx.setAttribute("korisnikDAO", new KorisnikDAO(contextPath));
		}
	}
	
	@POST
	@Path("")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Korisnik login(Korisnik korisnik, @Context HttpServletRequest request) {
		
		KorisnikDAO korisnikDao = (KorisnikDAO) ctx.getAttribute("korisnikDAO");

		Korisnik loggedUser = korisnikDao.find(korisnik.getUsername(), korisnik.getPassword());
	
		//System.out.println("loggedUser= " + loggedUser);
	
		if (loggedUser == null) {
			//return Response.status(400).entity("Invalid username and/or password").build();
			return null;
		}
		
		korisnikDao.setTrenutniKorisnik(loggedUser);
		request.getSession().setAttribute("korisnik", loggedUser);
		
		return loggedUser;
		//return Response.status(200).build();
	}
	
	@POST
	@Path("/logout")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public void logout(@Context HttpServletRequest request) {
		request.getSession().invalidate();
	}
	
	@GET
	@Path("/currentUser")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Korisnik login(@Context HttpServletRequest request) {
		return (Korisnik) request.getSession().getAttribute("korisnik");
	}
}
