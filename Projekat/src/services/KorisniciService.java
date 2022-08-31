package services;

import java.util.Collection;

import javax.annotation.PostConstruct;
import javax.servlet.ServletContext;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import beans.Korisnik;
import beans.Menadzer;
import dao.KorisnikDAO;

@Path("/")
public class KorisniciService {

	 @Context
	ServletContext ctx;
	
	 @PostConstruct
		// ctx polje je null u konstruktoru, mora se pozvati nakon konstruktora (@PostConstruct anotacija)
		public void init() {
			// Ovaj objekat se instancira viï¿½e puta u toku rada aplikacije
			// Inicijalizacija treba da se obavi samo jednom
			if (ctx.getAttribute("korisnikDAO") == null) {
		    	String contextPath = ctx.getRealPath("");
				ctx.setAttribute("korisnikDAO", new KorisnikDAO(contextPath));
			}
		}
	
	
	@GET
	@Path("/korisnici")
	@Produces(MediaType.APPLICATION_JSON)
	public Collection<Korisnik> getKorisnike() {
		KorisnikDAO dao = (KorisnikDAO) ctx.getAttribute("korisnikDAO");
		return dao.findAll();
	}
	
	@GET
	@Path("/menadzeri")
	@Produces(MediaType.APPLICATION_JSON)
	public Collection<Menadzer> getMenadzere() {
		KorisnikDAO dao = (KorisnikDAO) ctx.getAttribute("korisnikDAO");
		return dao.findAllMenadzere();
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
