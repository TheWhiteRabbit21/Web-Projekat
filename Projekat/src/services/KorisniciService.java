package services;

import java.util.ArrayList;
import java.util.Collection;

import javax.annotation.PostConstruct;
import javax.servlet.ServletContext;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import beans.Clanarina;
import beans.IstorijaTreninga;
import beans.Korisnik;
import beans.Kupac;
import beans.Menadzer;
import beans.Trener;
import dao.KorisnikDAO;
import dao.SportskiObjekatDAO;

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
	
	@GET
	@Path("/prikaziKupceKojiSuPosetiliSO/{sportskiObjekat}")
	@Produces(MediaType.APPLICATION_JSON)
	public Collection<Kupac> getTreningeSportskogObjekta(@PathParam("sportskiObjekat") String sportskiObjekat){

		SportskiObjekatDAO dao = (SportskiObjekatDAO) ctx.getAttribute("sportskiObjekatDAO");
		KorisnikDAO kDao = (KorisnikDAO) ctx.getAttribute("korisnikDAO");
		
		String contextPath = ctx.getRealPath("");

		if(sportskiObjekat.contains("%20")){
			sportskiObjekat.replace("%20", " ");
		}
		
		Collection<IstorijaTreninga> istorijaTreninga = new ArrayList<IstorijaTreninga>();
		istorijaTreninga = dao.getAllIstorijaTreninga(contextPath);
		
		Collection<Kupac> kupci = new ArrayList<Kupac>();
		
		kupci = kDao.getKupceKojiSuPosetiliSportskiObjekat(istorijaTreninga, sportskiObjekat, contextPath);
		return kupci;
	}
	
	@GET
	@Path("/pretraga/korisnikIme/{pretragaIme}")
	@Produces(MediaType.APPLICATION_JSON)
	public Collection<Korisnik> pretragaKorisnikaPoImenu(@PathParam("pretragaIme") String pretragaIme){

		KorisnikDAO dao = (KorisnikDAO) ctx.getAttribute("korisnikDAO");
		String contextPath = ctx.getRealPath("");
		
		if(pretragaIme.contains("%20")){
			pretragaIme.replace("%20", " ");
		}
		
		Collection<Korisnik> k = dao.pretraziKorisnikePoImenu(pretragaIme, contextPath);

		return k;
	}
	
	@GET
	@Path("/pretraga/korisnikPrezime/{pretragaPrezime}")
	@Produces(MediaType.APPLICATION_JSON)
	public Collection<Korisnik> pretragaKorisnikaPoPrezimenu(@PathParam("pretragaPrezime") String pretragaPrezime){

		KorisnikDAO dao = (KorisnikDAO) ctx.getAttribute("korisnikDAO");
		String contextPath = ctx.getRealPath("");
		
		if(pretragaPrezime.contains("%20")){
			pretragaPrezime.replace("%20", " ");
		}
		
		Collection<Korisnik> k = dao.pretraziKorisnikePoPrezimenu(pretragaPrezime, contextPath);

		return k;
	}
	
	@GET
	@Path("/pretraga/korisnikUsername/{pretragaUsername}")
	@Produces(MediaType.APPLICATION_JSON)
	public Collection<Korisnik> pretragaKorisnikaPoKorisnickomImenu(@PathParam("pretragaUsername") String pretragaUsername){

		KorisnikDAO dao = (KorisnikDAO) ctx.getAttribute("korisnikDAO");
		String contextPath = ctx.getRealPath("");
		
		if(pretragaUsername.contains("%20")){
			pretragaUsername.replace("%20", " ");
		}
		
		Collection<Korisnik> k = dao.pretraziKorisnikePoKorisnickomImenu(pretragaUsername, contextPath);

		return k;
	}
	
	@GET
	@Path("sort/korisnik/ime/{direction}")
	@Produces(MediaType.APPLICATION_JSON)
	public Collection<Korisnik> sortKorisnikName(@PathParam("direction") String direction) {
		KorisnikDAO dao = (KorisnikDAO) ctx.getAttribute("korisnikDAO");
		String contextPath = ctx.getRealPath("");
		return dao.sortByName(direction, contextPath);
	}
	
	@GET
	@Path("sort/korisnik/prezime/{direction}")
	@Produces(MediaType.APPLICATION_JSON)
	public Collection<Korisnik> sortKorisnikSurname(@PathParam("direction") String direction) {
		KorisnikDAO dao = (KorisnikDAO) ctx.getAttribute("korisnikDAO");
		String contextPath = ctx.getRealPath("");
		return dao.sortBySurname(direction, contextPath);
	}
	
	@GET
	@Path("sort/korisnik/korisnickoIme/{direction}")
	@Produces(MediaType.APPLICATION_JSON)
	public Collection<Korisnik> sortKorisnikUsername(@PathParam("direction") String direction) {
		KorisnikDAO dao = (KorisnikDAO) ctx.getAttribute("korisnikDAO");
		String contextPath = ctx.getRealPath("");
		return dao.sortByUsername(direction, contextPath);
	}
	
	@GET
	@Path("filter/korisnik/uloga/{uloga}")
	@Produces(MediaType.APPLICATION_JSON)
	public Collection<Korisnik> filterTreningeByTip(@PathParam("uloga") String uloga) {
		KorisnikDAO dao = (KorisnikDAO) ctx.getAttribute("korisnikDAO");
		String contextPath = ctx.getRealPath("");
		return dao.filterByUlogaKorisnika(uloga, contextPath);
	}
	
	
	
}
