package services;

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
import dao.KorisnikDAO;

@Path("")
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
	@Path("/registracija")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response register(Korisnik korisnik, @Context HttpServletRequest request){

		System.out.println(korisnik);

		KorisnikDAO korisnikDAO = (KorisnikDAO) ctx.getAttribute("korisnikDAO");
		boolean loggedUser = korisnikDAO.find(korisnik.getKorisnickoIme());
		System.out.println(loggedUser+"register klasa");

		/*if (loggedUser == true) {
			//return Response.status(400).entity("Korisnicko ime vec postoji!").build();
			return Response.status(400).build();
		}

		boolean ime=isValidExpression(user.getIme());
		boolean prezime=isValidExpression(user.getPrezime());*/










		return Response.status(200).build();


	}














}
