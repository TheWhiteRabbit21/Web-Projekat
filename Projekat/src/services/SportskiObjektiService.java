package services;

import java.util.ArrayList;
import java.util.Collection;

import javax.annotation.PostConstruct;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import beans.SportskiObjekat;
import beans.Trening;
import dao.KorisnikDAO;
import dao.SportskiObjekatDAO;

@Path("/")
public class SportskiObjektiService {

    @Context
	ServletContext ctx;
	
	@PostConstruct
	// ctx polje je null u konstruktoru, mora se pozvati nakon konstruktora (@PostConstruct anotacija)
	public void init() {
		// Ovaj objekat se instancira viï¿½e puta u toku rada aplikacije
		// Inicijalizacija treba da se obavi samo jednom
		if (ctx.getAttribute("sportskiObjekatDAO") == null) {
	    	String contextPath = ctx.getRealPath("");
			ctx.setAttribute("sportskiObjekatDAO", new SportskiObjekatDAO(contextPath));
		}
	}

	@GET
	@Path("/sportskiObjekti")
	@Produces(MediaType.APPLICATION_JSON)
	public Collection<SportskiObjekat> getSportskeObjekte() {
		SportskiObjekatDAO dao = (SportskiObjekatDAO) ctx.getAttribute("sportskiObjekatDAO");
		return dao.findAll();
	}
	
	@POST
	@Path("/dodajSportskiObjekat")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response dodajSportskiObjekat(SportskiObjekat sportskiObjekat, @Context HttpServletRequest request) {
		
		SportskiObjekatDAO dao = (SportskiObjekatDAO) ctx.getAttribute("sportskiObjekatDAO");
		KorisnikDAO kDao = (KorisnikDAO) ctx.getAttribute("korisnikDAO");
		boolean postojiSportskiObjekat = dao.find(sportskiObjekat.getNaziv());
		
		System.out.println(postojiSportskiObjekat + " //dodaj klasa, ako true -> postoji vec sa tim imenom sportski objekat");

		if (postojiSportskiObjekat == true) {
			//return Response.status(400).entity("Korisnicko ime vec postoji!").build();
			return Response.status(400).build();
		}
		
		if(sportskiObjekat.getSadrzaj() == null) {
			sportskiObjekat.setSadrzaj(new ArrayList<String>());
		}
		
		String contextPath = ctx.getRealPath("");
		dao.dodaj(sportskiObjekat, contextPath);
		kDao.dodeliSportskiObjekatMenadzeru(sportskiObjekat, contextPath);
		
		return Response.status(200).build();
	}	
	
	
	@POST
	@Path("/dodajSadrzaj")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response dodajSadrzaj(Trening trening, @Context HttpServletRequest request) {
		
		SportskiObjekatDAO dao = (SportskiObjekatDAO) ctx.getAttribute("sportskiObjekatDAO");
		boolean postojiSadrzaj = dao.find(trening.getNaziv());
		
		System.out.println(postojiSadrzaj + " //dodaj klasa, ako true -> postoji vec sa tim imenom sadrzaj");

		if (postojiSadrzaj == true) {
			//return Response.status(400).entity("Korisnicko ime vec postoji!").build();
			return Response.status(400).build();
		}
		
		String contextPath = ctx.getRealPath("");
		dao.dodajSadrzaj(trening, contextPath);
		
		return Response.status(200).build();
	}	
	
	
	
	
	@GET
	@Path("/pretraga/{pretragaString}")
	@Produces(MediaType.APPLICATION_JSON)
	public Collection<SportskiObjekat> pretragaSportskihObjekata(@PathParam("pretragaString") String pretragaString){

		SportskiObjekatDAO dao = (SportskiObjekatDAO) ctx.getAttribute("sportskiObjekatDAO");

		if(pretragaString.contains("%20")){
			pretragaString.replace("%20", " ");
		}
		
		String [] parts = pretragaString.split(",");
		SportskiObjekat sportskiObjekat = null;
		
		try{
			sportskiObjekat = new SportskiObjekat(parts[0], parts[1]);
		}
		catch(Exception e){
			System.out.println(e);
		}

		System.out.println(sportskiObjekat);
		String contextPath = ctx.getRealPath("");
		Collection<SportskiObjekat> so = dao.pretraziSportskeObjekte(sportskiObjekat, contextPath);

		return so;

	}
	
	
	
	@GET
	@Path("/prikaziSportskiObjekat")
	@Produces(MediaType.APPLICATION_JSON)
	public SportskiObjekat prikaziSportskiObjekat(){

		try
		{
		SportskiObjekatDAO dao = (SportskiObjekatDAO) ctx.getAttribute("sportskiObjekatDAO");

		//System.out.println(sportskiObjekat);
		
		String contextPath = ctx.getRealPath("");
		
		SportskiObjekat so = dao.getSportskiObjekatZaPrikazati(contextPath);

		return so;
		}
		catch(Exception e){
			System.out.println(e);
			return null;
		}
		

	}
	
	
	
	@GET
	@Path("/sadrzajSportskogObjekta")
	@Produces(MediaType.APPLICATION_JSON)
	public Collection<Trening> getSadrzajSportskogObjekta(){
		
		try{
			SportskiObjekatDAO dao = (SportskiObjekatDAO) ctx.getAttribute("sportskiObjekatDAO");

			//System.out.println(sportskiObjekat);
		
			String contextPath = ctx.getRealPath("");
		
			Collection<Trening> so = dao.getSadrzajSportskogObjekta(contextPath);

			return so;
		}
		catch(Exception e){
			System.out.println(e);
			return null;
		}
		

	}
	
	
	
	
	@GET
	@Path("/prikaziSportskiObjekat/Menadzer")
	@Produces(MediaType.APPLICATION_JSON)
	public SportskiObjekat prikaziSportskiObjekatMenadzer(){

		try{
		SportskiObjekatDAO dao = (SportskiObjekatDAO) ctx.getAttribute("sportskiObjekatDAO");
		KorisnikDAO daoK = (KorisnikDAO) ctx.getAttribute("korisnikDAO");
		
		String k = daoK.getTrenutniKorisnikUsername();
			
		//Korisnik ko = (Korisnik) ctx.getAttribute("korisnik");
		
		//System.out.println(sportskiObjekat);
		String contextPath = ctx.getRealPath("");
		SportskiObjekat so = dao.prikaziSportskiObjekatMenadzer(k, contextPath);

		return so;
		}
		catch(Exception e){
			System.out.println(e);
			return null;
		}
		

	}

	@GET
	@Path("/pretraga/findTipObjekta/{pretragaString}")
	@Produces(MediaType.APPLICATION_JSON)
	public Collection<SportskiObjekat> pretragaSportskihObjekataPoTipu(@PathParam("pretragaString") String pretragaString){

		SportskiObjekatDAO dao = (SportskiObjekatDAO) ctx.getAttribute("sportskiObjekatDAO");

		if(pretragaString.contains("%20")){
			pretragaString.replace("%20", " ");
		}
		
		//String [] parts = pretragaString.split(",");
		SportskiObjekat sportskiObjekat = null;
		//System.out.println(parts);
		try{
			sportskiObjekat = new SportskiObjekat(pretragaString, -1, -1);
		}
		catch(Exception e){
			System.out.println(e);
		}

		//System.out.println(sportskiObjekat);
		String contextPath = ctx.getRealPath("");
		Collection<SportskiObjekat> so = dao.pretraziSportskeObjektePoTipuObjekta(sportskiObjekat, contextPath);

		return so;

	}
	
	@GET
	@Path("/pretraga/findNaziv/{pretragaString}")
	@Produces(MediaType.APPLICATION_JSON)
	public Collection<SportskiObjekat> pretragaSportskihObjekataPoNazivu(@PathParam("pretragaString") String pretragaString){

		SportskiObjekatDAO dao = (SportskiObjekatDAO) ctx.getAttribute("sportskiObjekatDAO");

		if(pretragaString.contains("%20")){
			pretragaString.replace("%20", " ");
		}
		
		SportskiObjekat sportskiObjekat = null;
		
		try{
			sportskiObjekat = new SportskiObjekat(pretragaString);
		}
		catch(Exception e){
			System.out.println(e);
		}

		//System.out.println(sportskiObjekat);
		String contextPath = ctx.getRealPath("");
		Collection<SportskiObjekat> so = dao.pretraziSportskeObjektePoNazivu(sportskiObjekat, contextPath);

		return so;

	}
	
	
	@POST
	@Path("/sportskiObjekatPage")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response prikaziSportskiObjekat(SportskiObjekat sportskiObjekat, @Context HttpServletRequest request) {
		
		SportskiObjekatDAO dao = (SportskiObjekatDAO) ctx.getAttribute("sportskiObjekatDAO");
		String contextPath = ctx.getRealPath("");
		
		if(sportskiObjekat.getSadrzaj() == null) {
			sportskiObjekat.setSadrzaj(new ArrayList<String>());
		}
		
		dao.setSportskiObjekatZaPrikazati(sportskiObjekat, contextPath);
		
		return Response.status(200).build();
	}	
	
	
	
	
	
	

}
