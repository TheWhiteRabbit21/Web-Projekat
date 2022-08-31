package services;

import java.util.regex.Pattern;

import javax.annotation.PostConstruct;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import beans.Korisnik;
import beans.Kupac;
import beans.Menadzer;
import dao.KorisnikDAO;
import enums.Uloga;

@Path("/register")
public class RegistracijaService {

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

    
    
    
    
    @POST
	@Path("/korisnik")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response register(Korisnik korisnik, @Context HttpServletRequest request){

		//System.out.println(korisnik);

		KorisnikDAO korisnikDao = (KorisnikDAO) ctx.getAttribute("korisnikDAO");
		boolean loggedUser = korisnikDao.find(korisnik.getUsername());
		
		System.out.println(loggedUser + " //register klasa, ako true -> postoji vec sa tim imenom korisnik");

		if (loggedUser == true) {
			//return Response.status(400).entity("Korisnicko ime vec postoji!").build();
			return Response.status(400).build();
		}

		boolean ime=isValidExpression(korisnik.getIme());
		boolean prezime=isValidExpression(korisnik.getPrezime());

		if(!ime) return Response.status(401).build();
		if(!prezime) return Response.status(402).build();

		String contextPath = ctx.getRealPath("");
		
		if(korisnik.getUloga() == Uloga.MENADZER)
		{
			korisnikDao.dodajMenadzera(korisnik, contextPath);
		}
		if(korisnik.getUloga() == Uloga.TRENER)
		{
			//korisnikDao.dodajTrenera(korisnik, contextPath);
		}
		
		return Response.status(200).build();
	}
    
    
    
    
    
	@POST
	@Path("/kupac")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response register(Kupac kupac, @Context HttpServletRequest request){

		//System.out.println(korisnik);

		KorisnikDAO korisnikDao = (KorisnikDAO) ctx.getAttribute("korisnikDAO");
		boolean loggedUser = korisnikDao.find(kupac.getUsername());
		
		System.out.println(loggedUser + " //register klasa, ako true -> postoji vec sa tim imenom korisnik");

		if (loggedUser == true) {
			//return Response.status(400).entity("Korisnicko ime vec postoji!").build();
			return Response.status(400).build();
		}

		boolean ime=isValidExpression(kupac.getIme());
		boolean prezime=isValidExpression(kupac.getPrezime());

		if(!ime) return Response.status(401).build();
		if(!prezime) return Response.status(402).build();

		String contextPath = ctx.getRealPath("");
		korisnikDao.dodaj(kupac, contextPath);
		
		return Response.status(200).build();
	}

	
	
	@POST
	@Path("/menadzer")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response register(Menadzer menadzer, @Context HttpServletRequest request){

		//System.out.println(korisnik);

		KorisnikDAO korisnikDao = (KorisnikDAO) ctx.getAttribute("korisnikDAO");
		boolean loggedUser = korisnikDao.find(menadzer.getUsername());
		
		System.out.println(loggedUser + " //register klasa, ako true -> postoji vec sa tim imenom korisnik");

		if (loggedUser == true) {
			//return Response.status(400).entity("Korisnicko ime vec postoji!").build();
			return Response.status(400).build();
		}

		boolean ime=isValidExpression(menadzer.getIme());
		boolean prezime=isValidExpression(menadzer.getPrezime());

		if(!ime) return Response.status(401).build();
		if(!prezime) return Response.status(402).build();

		String contextPath = ctx.getRealPath("");
		//korisnikDao.dodaj(menadzer, contextPath);
		korisnikDao.dodajMenadzera(menadzer, contextPath);
		
		return Response.status(200).build();
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	private boolean isValidExpression(String word) {
		String regex = "^[\\p{L} ]*$";
		return Pattern.matches(regex, word);
	}
	
	@POST
	@Path("/updatejson")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public void saveKorisnik(Korisnik korisnik) {
		
		System.out.println("Saving korisnik: " + korisnik);
		
		KorisnikDAO korisnikDao = (KorisnikDAO) ctx.getAttribute("korisnikDAO");

		String contextPath = ctx.getRealPath("");
		
		//korisnikDao.obrisi(korisnik, contextPath);
		//korisnikDao.dodaj(korisnik, contextPath);
		
		korisnikDao.izmeni(korisnik, contextPath);
		
		
	}
	
	
	

}
