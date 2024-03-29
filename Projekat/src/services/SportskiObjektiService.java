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

import beans.Clanarina;
import beans.IstorijaTreninga;
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
	
	@GET
	@Path("/sportskiObjektiPoOceni/{pretragaOcena}")
	@Produces(MediaType.APPLICATION_JSON)
	public Collection<SportskiObjekat> getSportskeObjektePoOceni(@PathParam("pretragaOcena") String pretragaOcena) {
		SportskiObjekatDAO dao = (SportskiObjekatDAO) ctx.getAttribute("sportskiObjekatDAO");
		return dao.findAllPoOceni(pretragaOcena);
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
	
	@POST
	@Path("/platiClanarinu")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response platiClanarinu(Clanarina clanarina, @Context HttpServletRequest request) {
		
		KorisnikDAO dao = (KorisnikDAO) ctx.getAttribute("korisnikDAO");
		
		String contextPath = ctx.getRealPath("");
		
		dao.platiClanarinu(clanarina, contextPath);
		
		return Response.status(200).build();
	}	
	
	@POST
	@Path("/otkaziTrening")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response platiClanarinu(Trening trening, @Context HttpServletRequest request) {
		
		SportskiObjekatDAO dao = (SportskiObjekatDAO) ctx.getAttribute("sportskiObjekatDAO");
		
		String contextPath = ctx.getRealPath("");
		
		dao.otkaziTrening(trening, contextPath);
		
		return Response.status(200).build();
	}
	
	@GET
	@Path("/prikaziTreningeSportskogObjekta/{sportskiObjekat}")
	@Produces(MediaType.APPLICATION_JSON)
	public Collection<Trening> getTreningeSportskogObjekta(@PathParam("sportskiObjekat") String sportskiObjekat){

		SportskiObjekatDAO dao = (SportskiObjekatDAO) ctx.getAttribute("sportskiObjekatDAO");

		if(sportskiObjekat.contains("%20")){
			sportskiObjekat.replace("%20", " ");
		}
		
		Collection<Trening> treninzi = new ArrayList<Trening>();
				
		String contextPath = ctx.getRealPath("");
		treninzi = dao.getSadrzajSportskogObjekta(sportskiObjekat, contextPath);
		return treninzi;
	}
	
	@GET
	@Path("/prikaziGrupneTreningeTrenera/{trener}")
	@Produces(MediaType.APPLICATION_JSON)
	public Collection<Trening> getGrupneTreningeTrenera(@PathParam("trener") String trener){

		SportskiObjekatDAO dao = (SportskiObjekatDAO) ctx.getAttribute("sportskiObjekatDAO");

		if(trener.contains("%20")){
			trener.replace("%20", " ");
		}
		
		Collection<Trening> treninzi = new ArrayList<Trening>();
				
		treninzi = dao.getTreneroveGrupneTreninge(trener);
		return treninzi;
	}
	
	@GET
	@Path("/prikaziOstaleTreningeTrenera/{trener}")
	@Produces(MediaType.APPLICATION_JSON)
	public Collection<Trening> getOstaleTreningeTrenera(@PathParam("trener") String trener){

		SportskiObjekatDAO dao = (SportskiObjekatDAO) ctx.getAttribute("sportskiObjekatDAO");

		if(trener.contains("%20")){
			trener.replace("%20", " ");
		}
		
		Collection<Trening> treninzi = new ArrayList<Trening>();
				
		treninzi = dao.getTreneroveOstaleTreninge(trener);
		return treninzi;
	}
	
	@GET
	@Path("/prikaziPersonalneTreningeTrenera/{trener}")
	@Produces(MediaType.APPLICATION_JSON)
	public Collection<Trening> getPersonalneTreningeTrenera(@PathParam("trener") String trener){

		SportskiObjekatDAO dao = (SportskiObjekatDAO) ctx.getAttribute("sportskiObjekatDAO");

		if(trener.contains("%20")){
			trener.replace("%20", " ");
		}
		
		Collection<Trening> treninzi = new ArrayList<Trening>();
				
		treninzi = dao.getTrenerovePersonalneTreninge(trener);
		return treninzi;
	}
	
	@GET
	@Path("pretraga/findObjekat/{pretragaNaziv}&{pretragaTip}&{pretragaOcena}")
	@Produces(MediaType.APPLICATION_JSON)
	public Collection<SportskiObjekat> pretragaSportskihObjekata(@PathParam("pretragaNaziv") String pretragaNaziv,
			@PathParam("pretragaTip") String pretragaTip, @PathParam("pretragaOcena") String pretragaOcena){

		SportskiObjekatDAO dao = (SportskiObjekatDAO) ctx.getAttribute("sportskiObjekatDAO");

		if(pretragaNaziv.contains("%20")){
			pretragaNaziv.replace("%20", " ");
		}
		
		if(pretragaTip.contains("%20")){
			pretragaTip.replace("%20", " ");
		}
		
		String contextPath = ctx.getRealPath("");
		Collection<SportskiObjekat> so = dao.pretraziSportskeObjekte(pretragaNaziv, pretragaTip, pretragaOcena, contextPath);

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
	@Path("/prikaziClanarinu")
	@Produces(MediaType.APPLICATION_JSON)
	public Clanarina prikaziClanarinu(){

		try
		{
		SportskiObjekatDAO dao = (SportskiObjekatDAO) ctx.getAttribute("sportskiObjekatDAO");

		//System.out.println(sportskiObjekat);
		
		String contextPath = ctx.getRealPath("");
		
		Clanarina cl = dao.getClanarinuZaPrikazati(contextPath);

		return cl;
		}
		catch(Exception e){
			System.out.println(e);
			return null;
		}
	}
	
	@GET
	@Path("/prikaziTrening")
	@Produces(MediaType.APPLICATION_JSON)
	public Trening prikaziTrening(){

		try
		{
		SportskiObjekatDAO dao = (SportskiObjekatDAO) ctx.getAttribute("sportskiObjekatDAO");

		//System.out.println(sportskiObjekat);
		
		String contextPath = ctx.getRealPath("");
		
		Trening tr = dao.getTreningZaPrikazati(contextPath);

		return tr;
		}
		catch(Exception e){
			System.out.println(e);
			return null;
		}
	}
	
	@POST
	@Path("/prijaviTrening")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response prijaviTrening(Trening trening, @Context HttpServletRequest request) {
		
		SportskiObjekatDAO dao = (SportskiObjekatDAO) ctx.getAttribute("sportskiObjekatDAO");
		KorisnikDAO kDao = (KorisnikDAO) ctx.getAttribute("korisnikDAO");
		
		String contextPath = ctx.getRealPath("");
		
		String kupac = kDao.getTrenutniKorisnik();
		
		if(kDao.proveriKorisnikovuClanarinu(contextPath)) {
			int istorijaTreningaId = dao.prijaviTrening(trening, kupac, contextPath);
			kDao.prijaviTrening(istorijaTreningaId, trening, kupac, contextPath);
		}
		else {
			System.out.println("Korisnik nema aktivnu clanarinu pa ne moze da prijavi trening!");
		}
		
		return Response.status(200).build();
	}
	
	@GET
	@Path("/sadrzajSportskihObjekata")
	@Produces(MediaType.APPLICATION_JSON)
	public Collection<Trening> getSadrzajSportskihObjekata(){
		
		try{
			SportskiObjekatDAO dao = (SportskiObjekatDAO) ctx.getAttribute("sportskiObjekatDAO");

			//System.out.println(sportskiObjekat);
		
			String contextPath = ctx.getRealPath("");
		
			Collection<Trening> so = dao.getSadrzajSportskihObjekata(contextPath);

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
		
		String k = daoK.getTrenutniKorisnik();
			
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
	@Path("/pretraga/findTipObjekta/{pretragaString}&{pretragaOcena}")
	@Produces(MediaType.APPLICATION_JSON)
	public Collection<SportskiObjekat> pretragaSportskihObjekataPoTipu(@PathParam("pretragaString") String pretragaString, @PathParam("pretragaOcena") String pretragaOcena){

		SportskiObjekatDAO dao = (SportskiObjekatDAO) ctx.getAttribute("sportskiObjekatDAO");

		if(pretragaString.contains("%20")){
			pretragaString.replace("%20", " ");
		}
		
		//String [] parts = pretragaString.split(",");
		//SportskiObjekat sportskiObjekat = null;
		//System.out.println(parts);
		/*try{
			sportskiObjekat = new SportskiObjekat(pretragaString, -1, -1);
		}
		catch(Exception e){
			System.out.println(e);
		}*/

		//System.out.println(sportskiObjekat);
		String contextPath = ctx.getRealPath("");
		
		Collection<SportskiObjekat> so = dao.pretraziSportskeObjektePoTipuObjekta(pretragaString, pretragaOcena, contextPath);

		return so;
	}
	
	@GET
	@Path("/pretraga/findNaziv/{pretragaString}&{pretragaOcena}")
	@Produces(MediaType.APPLICATION_JSON)
	public Collection<SportskiObjekat> pretragaSportskihObjekataPoNazivu(@PathParam("pretragaString") String pretragaString, @PathParam("pretragaOcena") String pretragaOcena){

		SportskiObjekatDAO dao = (SportskiObjekatDAO) ctx.getAttribute("sportskiObjekatDAO");

		if(pretragaString.contains("%20")){
			pretragaString.replace("%20", " ");
		}
		
		String contextPath = ctx.getRealPath("");
		Collection<SportskiObjekat> so = dao.pretraziSportskeObjektePoNazivu(pretragaString, pretragaOcena, contextPath);

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
	
	@POST
	@Path("/sadrzajPageEdit")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response prikaziSadrzajEdit(Trening trening, @Context HttpServletRequest request) {
		
		SportskiObjekatDAO dao = (SportskiObjekatDAO) ctx.getAttribute("sportskiObjekatDAO");
		String contextPath = ctx.getRealPath("");
		
		dao.setSadrzajZaPrikazati(trening, contextPath);
		
		return Response.status(200).build();
	}
	
	@POST
	@Path("/clanarinaPage")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response prikaziClanarinu(Clanarina clanarina, @Context HttpServletRequest request) {
		
		SportskiObjekatDAO dao = (SportskiObjekatDAO) ctx.getAttribute("sportskiObjekatDAO");
		String contextPath = ctx.getRealPath("");
				
		dao.setClanarinuZaPrikazati(clanarina, contextPath);
		
		return Response.status(200).build();
	}
	
	@POST
	@Path("/personalniTreningPage")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response prikaziPersonalniTreningTrenera(Trening trening, @Context HttpServletRequest request) {
		
		SportskiObjekatDAO dao = (SportskiObjekatDAO) ctx.getAttribute("sportskiObjekatDAO");
		String contextPath = ctx.getRealPath("");
				
		//dao.setClanarinuZaPrikazati(clanarina, contextPath);
		dao.setTreningZaPrikazati(trening, contextPath);
		
		return Response.status(200).build();
	}
	
	@GET
	@Path("/getSadrzajZaPrikazati")
	@Produces(MediaType.APPLICATION_JSON)
	public Trening getSadrzajZaPrikazati() {
		SportskiObjekatDAO dao = (SportskiObjekatDAO) ctx.getAttribute("sportskiObjekatDAO");
		String contextPath = ctx.getRealPath("");
		return dao.getSadrzajZaPrikazati(contextPath);
	}
	
	@POST
	@Path("/updateSadrzaj")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public void saveSadrzaj(Trening trening) {
		
		System.out.println("Saving sadrzaj: " + trening);
		
		SportskiObjekatDAO dao = (SportskiObjekatDAO) ctx.getAttribute("sportskiObjekatDAO");
		String contextPath = ctx.getRealPath("");
		
		dao.izmeniTrening(trening, contextPath);
	}

	@GET
	@Path("/korisnikovaIstorijaTreninga")
	@Produces(MediaType.APPLICATION_JSON)
	public Collection<IstorijaTreninga> getIstorijeTreningaKupca(){
		
		SportskiObjekatDAO dao = (SportskiObjekatDAO) ctx.getAttribute("sportskiObjekatDAO");
		KorisnikDAO kDao = (KorisnikDAO) ctx.getAttribute("korisnikDAO");
		
		String contextPath = ctx.getRealPath("");
		
		String trenutniKorisnik = kDao.getTrenutniKorisnik();
		
		Collection<IstorijaTreninga> temp = dao.getAllKorisnikovuIstorijuTreninga(trenutniKorisnik, contextPath);
		
		return temp;
	}
	
	@GET
	@Path("sort/sportskiObjekat/naziv/{direction}")
	@Produces(MediaType.APPLICATION_JSON)
	public Collection<SportskiObjekat> sortSportskeObjekteName(@PathParam("direction") String direction) {
		SportskiObjekatDAO dao = (SportskiObjekatDAO) ctx.getAttribute("sportskiObjekatDAO");
		String contextPath = ctx.getRealPath("");
		return dao.sortByName(direction, contextPath);
	}
	
	@GET
	@Path("sort/sportskiObjekat/lokacija/{direction}")
	@Produces(MediaType.APPLICATION_JSON)
	public Collection<SportskiObjekat> sortSportskeObjekteLocation(@PathParam("direction") String direction) {
		SportskiObjekatDAO dao = (SportskiObjekatDAO) ctx.getAttribute("sportskiObjekatDAO");
		String contextPath = ctx.getRealPath("");
		return dao.sortByLocation(direction, contextPath);
	}
	
	@GET
	@Path("sort/sportskiObjekat/prosecnaOcena/{direction}")
	@Produces(MediaType.APPLICATION_JSON)
	public Collection<SportskiObjekat> sortSportskeObjekteAverageRating(@PathParam("direction") String direction) {
		SportskiObjekatDAO dao = (SportskiObjekatDAO) ctx.getAttribute("sportskiObjekatDAO");
		String contextPath = ctx.getRealPath("");
		return dao.sortByAverageRating(direction, contextPath);
	}
	
	@GET
	@Path("filter/sportskiObjekat/tip/{tip}")
	@Produces(MediaType.APPLICATION_JSON)
	public Collection<SportskiObjekat> filterSportskeObjekteTip(@PathParam("tip") String tip) {
		SportskiObjekatDAO dao = (SportskiObjekatDAO) ctx.getAttribute("sportskiObjekatDAO");
		String contextPath = ctx.getRealPath("");
		return dao.filterByTypeSO(tip, contextPath);
	}
	
	@GET
	@Path("filterTipoviSO")
	@Produces(MediaType.APPLICATION_JSON)
	public Collection<String> getFilterTipoveSO() {
		SportskiObjekatDAO dao = (SportskiObjekatDAO) ctx.getAttribute("sportskiObjekatDAO");
		String contextPath = ctx.getRealPath("");
		return dao.getFilterTipoveSO(contextPath);
	}
	
	@GET
	@Path("filter/sportskiObjekat/otvoreni")
	@Produces(MediaType.APPLICATION_JSON)
	public Collection<SportskiObjekat> getOtvoreniSportskiObjekti() {
		SportskiObjekatDAO dao = (SportskiObjekatDAO) ctx.getAttribute("sportskiObjekatDAO");
		String contextPath = ctx.getRealPath("");
		return dao.getOtvoreneSO(contextPath);
	}
	
	@GET
	@Path("/pretraga/treningCena/{pretragaCena}")
	@Produces(MediaType.APPLICATION_JSON)
	public Collection<Trening> pretragaTreningaPoCeni(@PathParam("pretragaCena") String pretragaCena){

		SportskiObjekatDAO dao = (SportskiObjekatDAO) ctx.getAttribute("sportskiObjekatDAO");
		
		String contextPath = ctx.getRealPath("");
		Collection<Trening> tr = dao.pretraziTreningePoCeni(pretragaCena, contextPath);

		return tr;
	}
	
	@GET
	@Path("/pretraga/treningTrajanje/{pretragaTrajanje}")
	@Produces(MediaType.APPLICATION_JSON)
	public Collection<Trening> pretragaTreningaPoTrajanju(@PathParam("pretragaTrajanje") String pretragaTrajanje){

		SportskiObjekatDAO dao = (SportskiObjekatDAO) ctx.getAttribute("sportskiObjekatDAO");
		
		String contextPath = ctx.getRealPath("");
		Collection<Trening> tr = dao.pretraziTreningePoTrajanju(pretragaTrajanje, contextPath);

		return tr;
	}
	
	@GET
	@Path("sort/trening/cena/{direction}")
	@Produces(MediaType.APPLICATION_JSON)
	public Collection<Trening> sortTreningeByPrice(@PathParam("direction") String direction) {
		SportskiObjekatDAO dao = (SportskiObjekatDAO) ctx.getAttribute("sportskiObjekatDAO");
		String contextPath = ctx.getRealPath("");
		return dao.sortTreningeByPrice(direction, contextPath);
	}
	
	@GET
	@Path("sort/trening/trajanje/{direction}")
	@Produces(MediaType.APPLICATION_JSON)
	public Collection<Trening> sortTreningeByDuration(@PathParam("direction") String direction) {
		SportskiObjekatDAO dao = (SportskiObjekatDAO) ctx.getAttribute("sportskiObjekatDAO");
		String contextPath = ctx.getRealPath("");
		return dao.sortTreningeByDuration(direction, contextPath);
	}
	
	
	@GET
	@Path("filter/trening/tip/{tip}")
	@Produces(MediaType.APPLICATION_JSON)
	public Collection<Trening> filterTreningeByTip(@PathParam("tip") String tip) {
		SportskiObjekatDAO dao = (SportskiObjekatDAO) ctx.getAttribute("sportskiObjekatDAO");
		String contextPath = ctx.getRealPath("");
		return dao.filterByTypeTreninga(tip, contextPath);
	}
	
	@GET
	@Path("filterTipoviTreninga")
	@Produces(MediaType.APPLICATION_JSON)
	public Collection<String> getFilterTipoveTreninga() {
		SportskiObjekatDAO dao = (SportskiObjekatDAO) ctx.getAttribute("sportskiObjekatDAO");
		String contextPath = ctx.getRealPath("");
		return dao.getFilterTipoveTreninga(contextPath);
	}
	
	
	
	
	
	
	
	
	
	
	
	
}
