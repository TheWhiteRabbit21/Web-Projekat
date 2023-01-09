package services;

import java.util.Collection;

import javax.annotation.PostConstruct;
import javax.servlet.ServletContext;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import beans.Clanarina;
import beans.Korisnik;
import beans.Menadzer;
import beans.Trener;
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
	
	@GET
	@Path("/treneri")
	@Produces(MediaType.APPLICATION_JSON)
	public Collection<Trener> getTrenere() {
		KorisnikDAO dao = (KorisnikDAO) ctx.getAttribute("korisnikDAO");
		return dao.findAllTrenere();
	}
	
	@GET
	@Path("/getTrenutniKorisnik")
	//@Produces(MediaType.APPLICATION_JSON)
	public String getTrenutniKorisnik() {
		KorisnikDAO dao = (KorisnikDAO) ctx.getAttribute("korisnikDAO");
		return dao.getTrenutniKorisnik();
	}
	
	@GET
	@Path("/clanarine")
	@Produces(MediaType.APPLICATION_JSON)
	public Collection<Clanarina> getClanarine() {
		KorisnikDAO dao = (KorisnikDAO) ctx.getAttribute("korisnikDAO");
		String contextPath = ctx.getRealPath("");
		return dao.findAllClanarineTxt(contextPath);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
