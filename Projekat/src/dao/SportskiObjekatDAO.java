package dao;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import beans.Clanarina;
import beans.IstorijaTreninga;
import beans.SportskiObjekat;
import beans.Trening;

public class SportskiObjekatDAO {

	private String ctx;
	
	private HashMap<String, SportskiObjekat> sportskiObjekti = new HashMap<String, SportskiObjekat>();
	private HashMap<String, Trening> sadrzaj = new HashMap<String, Trening>();
	private HashMap<Integer, IstorijaTreninga> istorijaTreninga = new HashMap<Integer, IstorijaTreninga>();
	private SportskiObjekat sportskiObjekatZaPrikazati = new SportskiObjekat();
	private Trening treningZaPrikazati = new Trening();
	private Clanarina clanarinaZaPrikazati = new Clanarina();
	
	private Collection<String> tipoviSO = new ArrayList<String>();
	private Collection<String> tipoviTreninga = new ArrayList<String>();
	
	public SportskiObjekatDAO() {
		
	}
	
	/***
	 * @param contextPath Putanja do aplikacije u Tomcatu. Mo�e se pristupiti samo iz servleta.
	 */
	
	public SportskiObjekatDAO(String contextPath) {
		loadSportskiObjekti(contextPath);
		loadSadrzaj(contextPath);
		loadIstorijaTreningaIdJSON(contextPath);
		loadIstorijaTreninga(contextPath);
		ctx = contextPath;
	}
	
	public Collection<SportskiObjekat> findAll(){
		loadSportskiObjekti(ctx);
		return sportskiObjekti.values();
	}
	
	public boolean find(String name) {
		
		loadSportskiObjekti(ctx);
		
		if(!sportskiObjekti.containsKey(name)) {
			return false;
		}
		
		return true;
	}
	
	private void loadSportskiObjekti(String contextPath) {
		
		String filePath = contextPath + "/sportskiObjekti.json";
    	FileWriter fileWriter = null;
    	BufferedReader in = null;
    	File file = null;
    	sportskiObjekti.clear();
    	
    	try {
			file = new File(filePath);
    		in = new BufferedReader(new FileReader(file));
    		
    		ObjectMapper objectMapper = new ObjectMapper();
			objectMapper.configure(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY, true);
			objectMapper.configure(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT, true);
			
			SportskiObjekat[] so = objectMapper.readValue(file, SportskiObjekat[].class);

			for(SportskiObjekat s : so)
			{
				sportskiObjekti.put(s.getNaziv(), s);
			}
    		
		} catch (FileNotFoundException fnfe) {
			try {
				if(file.createNewFile()) {
					System.out.println("File created: " + file.getName());
				}
				else {
					System.out.println("File not created");
				}
				
			} catch (IOException e) {
				e.printStackTrace();
			}finally {
				if (fileWriter != null) {
					try {
						fileWriter.close();
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
		}	catch (Exception ex) {
			//ex.printStackTrace();
			System.out.println("Fajl sportskiObjekti.json je prazan.");
		} finally {
			if (in != null) {
				try {
					in.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		
		//loadSportskiObjektiTXT(contextPath);
		
	}
	
	@SuppressWarnings("unused")
	private void loadSportskiObjektiTXT(String contextPath) {
		
		BufferedReader in = null;
		try {
			File file = new File(contextPath + "/sportskiObjekti.txt");
			
			//Ovo ispisuje putanju u konzolu
			//System.out.println(file.getCanonicalPath());
			
			in = new BufferedReader(new FileReader(file));
			String line, naziv = "", tipObjekta = "", sadrzaj = "", status = "", mapa = "";
			String logo = "", prosecnaOcena = "", radnoVreme = "", menadzer = "";
			StringTokenizer st;
			while ((line = in.readLine()) != null) {
				line = line.trim();
				if (line.equals("") || line.indexOf('#') == 0)
					continue;
				st = new StringTokenizer(line, ";");
				while (st.hasMoreTokens()) {
					naziv = st.nextToken().trim();
					tipObjekta = st.nextToken().trim();
					sadrzaj = st.nextToken().trim();
					status = st.nextToken().trim();
					mapa = "-";
					logo = "-";
					menadzer = "-";
					prosecnaOcena = st.nextToken().trim();
					radnoVreme = st.nextToken().trim();
				}
				//sportskiObjekti.put(naziv, new SportskiObjekat(naziv, tipObjekta, sadrzaj, status, mapa, logo, Double.parseDouble(prosecnaOcena), radnoVreme, menadzer));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if ( in != null ) {
				try {
					in.close();
				}
				catch (Exception e) { }
			}
		}
		
	}
		
	private void loadSadrzaj(String contextPath) {
		
		String filePath = contextPath + "/sadrzaj.json";
    	FileWriter fileWriter = null;
    	BufferedReader in = null;
    	File file = null;
    	sadrzaj.clear();
    	
    	try {
			file = new File(filePath);
    		in = new BufferedReader(new FileReader(file));
    		
    		ObjectMapper objectMapper = new ObjectMapper();
			objectMapper.configure(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY, true);
			objectMapper.configure(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT, true);
			
			Trening[] tr = objectMapper.readValue(file, Trening[].class);

			for(Trening s : tr)
			{
				sadrzaj.put(s.getNaziv(), s);
			}
    		
		} catch (FileNotFoundException fnfe) {
			try {
				if(file.createNewFile()) {
					System.out.println("File created: " + file.getName());
				}
				else {
					System.out.println("File not created");
				}
				
			} catch (IOException e) {
				e.printStackTrace();
			}finally {
				if (fileWriter != null) {
					try {
						fileWriter.close();
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
		}	catch (Exception ex) {
			//ex.printStackTrace();
			System.out.println("Fajl sadrzaj.json je prazan.");
		} finally {
			if (in != null) {
				try {
					in.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		
	}
	
	public void dodaj(SportskiObjekat s, String contextPath){

		try
		{
			//System.out.println("usao u KorisnikDAO.dodaj");
			//System.out.println(contextPath);
			File file = new File(contextPath + "/sportskiObjekti.json");
			ObjectMapper objectMapper = new ObjectMapper();

			objectMapper.configure(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY, true);
			objectMapper.configure(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT, true);
			ArrayList<SportskiObjekat> temp = new ArrayList<>();
			//trenutniKorisnik = k;

			SportskiObjekat[] so = objectMapper.readValue(file, SportskiObjekat[].class);
			//System.out.println("register User: "+ car);
			
			for(SportskiObjekat g : so)
			{
				temp.add(g);
			}
			temp.add(s);
			objectMapper.writeValue(new File(contextPath + "/sportskiObjekti.json"), temp);
			
			loadSportskiObjekti(contextPath);

		}
		catch (Exception ex) {
			
			try {
			
			//System.out.println("usao u catch exception KorisnikDAO.dodaj");
			ObjectMapper objectMapper = new ObjectMapper();

			objectMapper.configure(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY, true);
			objectMapper.configure(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT, true);
			ArrayList<SportskiObjekat> temp = new ArrayList<>();
			
			temp.add(s);
			objectMapper.writeValue(new File(contextPath + "/sportskiObjekti.json"), temp);
			
			//System.out.println(ex);
			//ex.printStackTrace();
			
			loadSportskiObjekti(contextPath);
			
			}catch (IOException e) {
				
			} finally {
				
			}
		} finally {
			
		}
	}
	
	public void dodajSadrzaj(Trening trening, String contextPath) {
		
		try
		{
			File file = new File(contextPath + "/sadrzaj.json");
			ObjectMapper objectMapper = new ObjectMapper();

			objectMapper.configure(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY, true);
			objectMapper.configure(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT, true);
			ArrayList<Trening> temp = new ArrayList<>();

			Trening[] tr = objectMapper.readValue(file, Trening[].class);
			
			for(Trening g : tr)
			{
				temp.add(g);
			}
			
			temp.add(trening);
			objectMapper.writeValue(new File(contextPath + "/sadrzaj.json"), temp);
			
			loadSadrzaj(contextPath);
			upisiSadrzajUSportskiObjekat(trening, contextPath);
		}
		catch (Exception ex) {
			
			try {
			
			//System.out.println("usao u catch exception KorisnikDAO.dodaj");
			ObjectMapper objectMapper = new ObjectMapper();

			objectMapper.configure(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY, true);
			objectMapper.configure(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT, true);
			ArrayList<Trening> temp = new ArrayList<>();
			
			temp.add(trening);
			objectMapper.writeValue(new File(contextPath + "/sadrzaj.json"), temp);
			
			//System.out.println(ex);
			//ex.printStackTrace();
			
			loadSadrzaj(contextPath);
			upisiSadrzajUSportskiObjekat(trening, contextPath);
			
			}catch (IOException e) {
				
			} finally {
				
			}
		} finally {
			
		}
		
		
	}
	
	public Collection<SportskiObjekat> pretraziSportskeObjekte(String sportskiObjekatNaziv, String sportskiObjekatTip, String prosecnaOcena, String contextPath){

		Collection<SportskiObjekat> ret = new ArrayList<SportskiObjekat>();
		
		for(Map.Entry<String, SportskiObjekat> entry : sportskiObjekti.entrySet()) 
		{
			if(prosecnaOcena.equalsIgnoreCase("0")) {
				if(entry.getValue().getNaziv().contains(sportskiObjekatNaziv) && entry.getValue().getTipObjekta().contains(sportskiObjekatTip)){
					ret.add(entry.getValue());
				}
			}
			else if(prosecnaOcena.equalsIgnoreCase("1-2"))
			{
				if(entry.getValue().getNaziv().contains(sportskiObjekatNaziv) && entry.getValue().getTipObjekta().contains(sportskiObjekatTip) && 
						entry.getValue().getProsecnaOcena() >= 1.0 && entry.getValue().getProsecnaOcena() <= 2.0){
					ret.add(entry.getValue());
				}
			}
			else if(prosecnaOcena.equalsIgnoreCase("2-3"))
			{
				if(entry.getValue().getNaziv().contains(sportskiObjekatNaziv) && entry.getValue().getTipObjekta().contains(sportskiObjekatTip) && 
						entry.getValue().getProsecnaOcena() >= 2.0 && entry.getValue().getProsecnaOcena() <= 3.0){
					ret.add(entry.getValue());
				}
			}
			else if(prosecnaOcena.equalsIgnoreCase("3-4"))
			{
				if(entry.getValue().getNaziv().contains(sportskiObjekatNaziv) && entry.getValue().getTipObjekta().contains(sportskiObjekatTip) && 
						entry.getValue().getProsecnaOcena() >= 3.0 && entry.getValue().getProsecnaOcena() <= 4.0){
					ret.add(entry.getValue());
				}
			}
			else if(prosecnaOcena.equalsIgnoreCase("4-5"))
			{
				if(entry.getValue().getNaziv().contains(sportskiObjekatNaziv) && entry.getValue().getTipObjekta().contains(sportskiObjekatTip) && 
						entry.getValue().getProsecnaOcena() >= 4.0 && entry.getValue().getProsecnaOcena() <= 5.0){
					ret.add(entry.getValue());
				}
			}
			
		}


		return ret;
	}
	
	public Collection<SportskiObjekat> pretraziSportskeObjektePoTipuObjekta(String sportskiObjekatTip, String prosecnaOcena, String contextPath){
		
		/*HashMap<String, SportskiObjekat> vrati = new HashMap<String, SportskiObjekat>();
		HashMap<String, SportskiObjekat> brisi = new HashMap<String, SportskiObjekat>();

		for(SportskiObjekat so: sportskiObjekti.values()) {
			vrati.put(so.getNaziv(), so);
		}

		for(SportskiObjekat so : vrati.values())
		{
			if (!(so.getTipObjekta().contains(sportskiObjekat.getTipObjekta())) && (!brisi.containsKey(so.getNaziv())))
	         {
	             brisi.put(so.getNaziv(),so);
	         }
		}

		for(SportskiObjekat so: brisi.values()) {
			vrati.remove(so.getNaziv());
		}

		System.out.println(vrati.values());
		
		return vrati.values();*/

		Collection<SportskiObjekat> ret = new ArrayList<SportskiObjekat>();
		
		for(Map.Entry<String, SportskiObjekat> entry : sportskiObjekti.entrySet()) 
		{
			if(prosecnaOcena.equalsIgnoreCase("0")) {
				if(entry.getValue().getTipObjekta().contains(sportskiObjekatTip)){
					ret.add(entry.getValue());
				}
			}
			else if(prosecnaOcena.equalsIgnoreCase("1-2"))
			{
				if(entry.getValue().getTipObjekta().contains(sportskiObjekatTip) && entry.getValue().getProsecnaOcena() >= 1.0
						&& entry.getValue().getProsecnaOcena() <= 2.0){
					ret.add(entry.getValue());
				}
			}
			else if(prosecnaOcena.equalsIgnoreCase("2-3"))
			{
				if(entry.getValue().getTipObjekta().contains(sportskiObjekatTip) && entry.getValue().getProsecnaOcena() >= 2.0
						&& entry.getValue().getProsecnaOcena() <= 3.0){
					ret.add(entry.getValue());
				}
			}
			else if(prosecnaOcena.equalsIgnoreCase("3-4"))
			{
				if(entry.getValue().getTipObjekta().contains(sportskiObjekatTip) && entry.getValue().getProsecnaOcena() >= 3.0
						&& entry.getValue().getProsecnaOcena() <= 4.0){
					ret.add(entry.getValue());
				}
			}
			else if(prosecnaOcena.equalsIgnoreCase("4-5"))
			{
				if(entry.getValue().getTipObjekta().contains(sportskiObjekatTip) && entry.getValue().getProsecnaOcena() >= 4.0
						&& entry.getValue().getProsecnaOcena() <= 5.0){
					ret.add(entry.getValue());
				}
			}
			
		}
		
		
		return ret;
		
	}
	
	public Collection<SportskiObjekat> pretraziSportskeObjektePoNazivu(String sportskiObjekatNaziv, String prosecnaOcena, String contextPath){

		/*HashMap<String, SportskiObjekat> vrati = new HashMap<String, SportskiObjekat>();
		HashMap<String, SportskiObjekat> brisi = new HashMap<String, SportskiObjekat>();

		for(SportskiObjekat so: sportskiObjekti.values()) {
			vrati.put(so.getNaziv(), so);
		}

		for(SportskiObjekat so : vrati.values())
		{
			if (!(so.getNaziv().contains(sportskiObjekat.getNaziv())) && (!brisi.containsKey(so.getNaziv())))
	         {
				//System.out.print("izbrisao je jedan!");
	             brisi.put(so.getNaziv(),so);
	         }

		}

		for(SportskiObjekat so: brisi.values()) {
			vrati.remove(so.getNaziv());
		}

		System.out.println(vrati.values());
		
		return vrati.values();*/

		Collection<SportskiObjekat> ret = new ArrayList<SportskiObjekat>();
		
		for(Map.Entry<String, SportskiObjekat> entry : sportskiObjekti.entrySet()) 
		{
			if(prosecnaOcena.equalsIgnoreCase("0")) {
				if(entry.getValue().getNaziv().contains(sportskiObjekatNaziv)){
					ret.add(entry.getValue());
				}
			}
			else if(prosecnaOcena.equalsIgnoreCase("1-2"))
			{
				if(entry.getValue().getNaziv().contains(sportskiObjekatNaziv) && entry.getValue().getProsecnaOcena() >= 1.0
						&& entry.getValue().getProsecnaOcena() <= 2.0){
					ret.add(entry.getValue());
				}
			}
			else if(prosecnaOcena.equalsIgnoreCase("2-3"))
			{
				if(entry.getValue().getNaziv().contains(sportskiObjekatNaziv) && entry.getValue().getProsecnaOcena() >= 2.0
						&& entry.getValue().getProsecnaOcena() <= 3.0){
					ret.add(entry.getValue());
				}
			}
			else if(prosecnaOcena.equalsIgnoreCase("3-4"))
			{
				if(entry.getValue().getNaziv().contains(sportskiObjekatNaziv) && entry.getValue().getProsecnaOcena() >= 3.0
						&& entry.getValue().getProsecnaOcena() <= 4.0){
					ret.add(entry.getValue());
				}
			}
			else if(prosecnaOcena.equalsIgnoreCase("4-5"))
			{
				if(entry.getValue().getNaziv().contains(sportskiObjekatNaziv) && entry.getValue().getProsecnaOcena() >= 4.0
						&& entry.getValue().getProsecnaOcena() <= 5.0){
					ret.add(entry.getValue());
				}
			}
			
		}
		
		
		return ret;
		
		
	}
	
	public SportskiObjekat prikaziSportskiObjekatMenadzer(String menadzer, String contextPath) {
		
		//KorisnikDAO dao = (KorisnikDAO) ctx.getAttribute("korisnikDAO");
		
		for(Map.Entry<String, SportskiObjekat> entry : sportskiObjekti.entrySet()) 
		{
			if(entry.getValue().getMenadzer().equals(menadzer)) {
				return entry.getValue();
			}
		}
		
		return null;
		
		
	}

	public void setSportskiObjekatZaPrikazati(SportskiObjekat sportskiObjekat, String contextPath) {
		sportskiObjekatZaPrikazati = sportskiObjekat;
	}
	
	public void setSadrzajZaPrikazati(Trening trening, String contextPath) {
		treningZaPrikazati = trening;
	}

	public Trening getSadrzajZaPrikazati(String contextPath) {
		return treningZaPrikazati;
	}
	
	public void setClanarinuZaPrikazati(Clanarina clanarina, String contextPath) {
		clanarinaZaPrikazati = clanarina;
	}
	
	public SportskiObjekat getSportskiObjekatZaPrikazati(String contextPath) {
		return sportskiObjekatZaPrikazati;
	}
	
	public Clanarina getClanarinuZaPrikazati(String contextPath) {
		return clanarinaZaPrikazati;
	}

	public Collection<Trening> getSadrzajSportskihObjekata(String contextPath) {
		return sadrzaj.values();
	}
	
	public Collection<Trening> getSadrzajSportskogObjekta(String sportskiObjekat, String contextPath) {

		Collection<Trening> tempSadrzaj = new ArrayList<Trening>();
		
		for(Map.Entry<String, Trening> entry : sadrzaj.entrySet())
    	{
    		
    			if(entry.getValue().getSportskiObjekat().equals(sportskiObjekat))
    			{
    				tempSadrzaj.add(entry.getValue());
    			}	
    	}
		
		return tempSadrzaj;
		
	}

	public void upisiSadrzajUSportskiObjekat(Trening trening, String contextPath) {
		
		Collection<String> tempSadrzaj = new ArrayList<String>();
		tempSadrzaj.add(trening.getNaziv());
		
		for(Map.Entry<String, SportskiObjekat> entry : sportskiObjekti.entrySet())
    	{
    		if(entry.getKey().equals(trening.getSportskiObjekat()))
    		{
    			if(entry.getValue().getSadrzaj().isEmpty())
    			{
    				entry.getValue().setSadrzaj(tempSadrzaj);
    			}
    			else{
    				entry.getValue().getSadrzaj().add(trening.getNaziv());
    			}
    				
    		}
    	}
		
		upisiSportskeObjekteUFajl(contextPath);
	}

	public void upisiSportskeObjekteUFajl(String contextPath) {
		
		try
		{
		ObjectMapper objectMapper = new ObjectMapper();

		objectMapper.configure(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY, true);
		objectMapper.configure(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT, true);
		
		Collection<SportskiObjekat> tempSportskiObjekti = new ArrayList<SportskiObjekat>();
		
		tempSportskiObjekti = sportskiObjekti.values();
		
		objectMapper.writeValue(new File(contextPath + "/sportskiObjekti.json"), tempSportskiObjekti);
		}
		
		catch (Exception ex) {
			System.out.println(ex);
			ex.printStackTrace();
			
		} finally {
			
		}
	
	}

	public Collection<Trening> getTreneroveGrupneTreninge(String trener) {
		
		Collection<Trening> treninzi = new ArrayList<Trening>();
		
		for(Map.Entry<String, Trening> entry : sadrzaj.entrySet())
    	{
    		if(entry.getValue().getTrener().equals(trener))
    		{
    			if(!entry.getValue().isDeleted() && entry.getValue().getTip().equalsIgnoreCase("grupni")) 
    			{    			    				
    				treninzi.add(entry.getValue());
    			}
    		}
    	}
		
		return treninzi;
	}
	
	public Collection<Trening> getTreneroveOstaleTreninge(String trener) {
		
		Collection<Trening> treninzi = new ArrayList<Trening>();
		
		for(Map.Entry<String, Trening> entry : sadrzaj.entrySet())
    	{
			if(entry.getValue().getTrener().equals(trener))
    		{
    			if(!entry.getValue().isDeleted() && !entry.getValue().getTip().equalsIgnoreCase("grupni") && !entry.getValue().getTip().equalsIgnoreCase("personalni")) 
    			{    			    				
    				treninzi.add(entry.getValue());
    			}
    		}
    	}
		
		return treninzi;
	}

	public Collection<Trening> getTrenerovePersonalneTreninge(String trener) {
	
		Collection<Trening> treninzi = new ArrayList<Trening>();
	
		for(Map.Entry<String, Trening> entry : sadrzaj.entrySet())
		{
			if(entry.getValue().getTrener().equals(trener))
    		{
    			if(!entry.getValue().isDeleted() && entry.getValue().getTip().equalsIgnoreCase("personalni")) 
    			{    			    				
    				treninzi.add(entry.getValue());
    			}
    		}
	}
	
	return treninzi;
}

	public void izmeniTrening(Trening t, String contextPath) {
		
		ArrayList<Trening> temp = new ArrayList<>();
		
		for(HashMap.Entry<String, Trening> entry : sadrzaj.entrySet()) 
    	{
			//System.out.println("Key = " + entry.getKey() + ", Username = " + entry.getValue().getUsername());
    	
			if(t.getNaziv().equalsIgnoreCase(entry.getValue().getNaziv())) {
				entry.getValue().setCena(t.getCena());
				entry.getValue().setOpis(t.getOpis());
				entry.getValue().setSlika(t.getSlika());
				entry.getValue().setTrajanje(t.getTrajanje());
				entry.getValue().setTip(t.getTip());
				
			}
			temp.add(entry.getValue());
    	}
    	
		upisiSportskeObjekteUFajl(contextPath);
		upisiSadrzajUFajl(temp, contextPath);
		
	}
	
	public void upisiSadrzajUFajl(ArrayList<Trening> t, String contextPath) {
		try
		{
		ObjectMapper objectMapper = new ObjectMapper();

		objectMapper.configure(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY, true);
		objectMapper.configure(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT, true);
		
		objectMapper.writeValue(new File(contextPath + "/sadrzaj.json"), t);
		}
		
		catch (Exception ex) {
			System.out.println(ex);
			ex.printStackTrace();
			
		} finally {
			
		}
	}

	public Integer prijaviTrening(Trening trening, String kupac, String contextPath) {

		Date currentDate = new Date();
		
		loadIstorijaTreninga(contextPath);
		
		if(loadIstorijaTreningaIdJSON(contextPath) == -2) {
			upisiUFajlIstorijaTreningaIdJSON(0, contextPath);
		}
		
		int lastITId = loadIstorijaTreningaIdJSON(contextPath);
		lastITId++;
		upisiUFajlIstorijaTreningaIdJSON(lastITId, contextPath);
		
		IstorijaTreninga istT = new IstorijaTreninga(lastITId, currentDate, trening.getSportskiObjekat(), trening.getNaziv(), kupac, trening.getTrener());
		if(!istorijaTreninga.containsKey(istT.getId())) {
			istorijaTreninga.put(istT.getId(), istT);
		}
		
		ArrayList<IstorijaTreninga> tempIstorijaTreninga = new ArrayList<IstorijaTreninga>();
		
		for(Map.Entry<Integer, IstorijaTreninga> entry : istorijaTreninga.entrySet())
    	{
			tempIstorijaTreninga.add(entry.getValue());
    	}
		
		upisiUFajlIstorijuTreninga(tempIstorijaTreninga, contextPath);
		return lastITId;
		
	}

	private void upisiUFajlIstorijuTreninga(ArrayList<IstorijaTreninga> tempIstorijaTreninga, String contextPath) {


		ArrayList<IstorijaTreninga> it = new ArrayList<IstorijaTreninga>();
		
		for(Map.Entry<Integer, IstorijaTreninga> entry : istorijaTreninga.entrySet())
    	{
    		it.add(entry.getValue());
    	}
		
		try
		{
			ObjectMapper objectMapper = new ObjectMapper();

			objectMapper.configure(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY, true);
			objectMapper.configure(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT, true);
		
			objectMapper.writeValue(new File(contextPath + "/istorijaTreninga.json"), it);
		}
		
		catch (Exception ex) {
			System.out.println(ex);
			ex.printStackTrace();
			
		} finally {
			
		}
		
	}

	private void upisiUFajlIstorijaTreningaIdJSON(int id, String contextPath) {

		try
		{
		ObjectMapper objectMapper = new ObjectMapper();

		objectMapper.configure(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY, true);
		objectMapper.configure(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT, true);
		
		objectMapper.writeValue(new File(contextPath + "/istorijaTreningaId.json"), id);
		}
		
		catch (Exception ex) {
			System.out.println(ex);
			ex.printStackTrace();
			
		} finally {
			
		}
		
	}

	private int loadIstorijaTreningaIdJSON(String contextPath) {
		
		String filePath = contextPath + "istorijaTreningaId.json";
    	FileWriter fileWriter = null;
    	BufferedReader in = null;
    	File file = null;
    	
    	try {
			file = new File(filePath);
    		in = new BufferedReader(new FileReader(file));
    		
    		ObjectMapper objectMapper = new ObjectMapper();
			objectMapper.configure(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY, true);
			objectMapper.configure(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT, true);
			
			Integer itId = objectMapper.readValue(file, Integer.class);
			
			return itId;
			
		} catch (FileNotFoundException fnfe) {
			try {
				if(file.createNewFile()) {
					System.out.println("File created: " + file.getName());
				}
				else {
					System.out.println("File not created");
				}
				
			} catch (IOException e) {
				e.printStackTrace();
			}finally {
				if (fileWriter != null) {
					try {
						fileWriter.close();
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
		}	catch (Exception ex) {
			//ex.printStackTrace();
			System.out.println("Fajl istorijaTreningaId.json je prazan.");
		} finally {
			if (in != null) {
				try {
					in.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		return -2;//ako je istorijaTreningaId -2 ne bi trebalo da se koristi
		
	}

	private void loadIstorijaTreninga(String contextPath) {

		String filePath = contextPath + "istorijaTreninga.json";
    	FileWriter fileWriter = null;
    	BufferedReader in = null;
    	File file = null;
    	
    	istorijaTreninga.clear();
    	
    	try {
			file = new File(filePath);
    		in = new BufferedReader(new FileReader(file));
    		
    		ObjectMapper objectMapper = new ObjectMapper();
			objectMapper.configure(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY, true);
			objectMapper.configure(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT, true);
			
			IstorijaTreninga[] it = objectMapper.readValue(file, IstorijaTreninga[].class);

			for(IstorijaTreninga i : it)
			{
				istorijaTreninga.put(i.getId(), i);
			}
			
		} catch (FileNotFoundException fnfe) {
			try {
				if(file.createNewFile()) {
					System.out.println("File created: " + file.getName());
				}
				else {
					System.out.println("File not created");
				}
				
			} catch (IOException e) {
				e.printStackTrace();
			}finally {
				if (fileWriter != null) {
					try {
						fileWriter.close();
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
		}	catch (Exception ex) {
			//ex.printStackTrace();
			System.out.println("Fajl istorijaTreninga.json je prazan.");
		} finally {
			if (in != null) {
				try {
					in.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		
	}

	public Collection<IstorijaTreninga> getAllIstorijaTreninga(String contextPath) {
		return istorijaTreninga.values();

	}

	public Collection<IstorijaTreninga> getAllKorisnikovuIstorijuTreninga(String trenutniKorisnik, String contextPath) {

		Collection<IstorijaTreninga> it = new ArrayList<IstorijaTreninga>();
		
		for(Map.Entry<Integer, IstorijaTreninga> entry : istorijaTreninga.entrySet())
    	{
			if(trenutniKorisnik.equals(entry.getValue().getKupac()))
				it.add(entry.getValue());
    	}
		
		
		return it;
	}

	public void setTreningZaPrikazati(Trening trening, String contextPath) {
		treningZaPrikazati = trening;		
	}

	public Trening getTreningZaPrikazati(String contextPath) {
		return treningZaPrikazati;
	}

	public void otkaziTrening(Trening trening, String contextPath) {

		ArrayList<Trening> treninzi = new ArrayList<Trening>();
		
		for(Map.Entry<String, Trening> entry : sadrzaj.entrySet())
    	{
			if(entry.getValue().getNaziv().equals(trening.getNaziv())) {
				entry.getValue().setDeleted(true);
			}
			treninzi.add(entry.getValue());
    	}
		
		
		
		upisiSadrzajUFajl(treninzi, contextPath);
		
	}

	public Collection<SportskiObjekat> findAllPoOceni(String pretragaOcena) {

		Collection<SportskiObjekat> ret = new ArrayList<SportskiObjekat>();
		
		for(Map.Entry<String, SportskiObjekat> entry : sportskiObjekti.entrySet())
    	{
			if(pretragaOcena.equalsIgnoreCase("1-2")) {
				if(entry.getValue().getProsecnaOcena() >= 1.0 && entry.getValue().getProsecnaOcena() <= 2.0) {
					ret.add(entry.getValue());
				}
			}
			else if(pretragaOcena.equalsIgnoreCase("2-3")) {
				if(entry.getValue().getProsecnaOcena() >= 2.0 && entry.getValue().getProsecnaOcena() <= 3.0) {
					ret.add(entry.getValue());
				}
			}
			else if(pretragaOcena.equalsIgnoreCase("3-4")) {
				if(entry.getValue().getProsecnaOcena() >= 3.0 && entry.getValue().getProsecnaOcena() <= 4.0) {
					ret.add(entry.getValue());
				}
			}
			else if(pretragaOcena.equalsIgnoreCase("4-5")) {
				if(entry.getValue().getProsecnaOcena() >= 4.0 && entry.getValue().getProsecnaOcena() <= 5.0) {
					ret.add(entry.getValue());
				}
			}
    	}
		
		return ret;
	}

	
	Comparator<SportskiObjekat> compareByNaziv = new Comparator<SportskiObjekat>() {
		@Override
		public int compare(SportskiObjekat s1, SportskiObjekat s2) {
			return s1.getNaziv().compareTo(s2.getNaziv());
		}
	};
	
	Comparator<SportskiObjekat> compareByLokacija = new Comparator<SportskiObjekat>() {
		@Override
		public int compare(SportskiObjekat s1, SportskiObjekat s2) {
			return s1.getMapa().compareTo(s2.getMapa());
		}
	};
	
	Comparator<SportskiObjekat> compareByProsecnaOcena = new Comparator<SportskiObjekat>() {
		@Override
		public int compare(SportskiObjekat s1, SportskiObjekat s2) {
			return s1.getProsecnaOcena().compareTo(s2.getProsecnaOcena());
		}
	};
	
	Comparator<Trening> compareByCena = new Comparator<Trening>() {
		@Override
		public int compare(Trening t1, Trening t2) {
			return t1.getCena().compareTo(t2.getCena());
		}
	};
	
	Comparator<Trening> compareByTrajanje = new Comparator<Trening>() {
		@Override
		public int compare(Trening t1, Trening t2) {
			return t1.getTrajanje().compareTo(t2.getTrajanje());
		}
	};
	
	public Collection<SportskiObjekat> sortByName(String direction, String contextPath) {

		ArrayList<SportskiObjekat> ret = new ArrayList<SportskiObjekat>();

		for(Map.Entry<String, SportskiObjekat> entry : sportskiObjekti.entrySet())
    	{
			ret.add(entry.getValue());
    	}
		
		if(direction.equals("asc") || direction.equals("0")) {
			Collections.sort(ret, compareByNaziv);
		}
		else if(direction.equals("desc")) {
			Collections.sort(ret, compareByNaziv);
			Collections.reverse(ret);
		}
		
		return ret;
	}
	
	public Collection<SportskiObjekat> sortByLocation(String direction, String contextPath) {

		ArrayList<SportskiObjekat> ret = new ArrayList<SportskiObjekat>();

		for(Map.Entry<String, SportskiObjekat> entry : sportskiObjekti.entrySet())
    	{
			ret.add(entry.getValue());
    	}
		
		if(direction.equals("asc") || direction.equals("0")) {
			Collections.sort(ret, compareByLokacija);
		}
		else if(direction.equals("desc")) {
			Collections.sort(ret, compareByLokacija);
			Collections.reverse(ret);
		}
		
		return ret;
	}
	
	public Collection<SportskiObjekat> sortByAverageRating(String direction, String contextPath) {

		ArrayList<SportskiObjekat> ret = new ArrayList<SportskiObjekat>();

		for(Map.Entry<String, SportskiObjekat> entry : sportskiObjekti.entrySet())
    	{
			ret.add(entry.getValue());
    	}
		
		if(direction.equals("asc") || direction.equals("0")) {
			Collections.sort(ret, compareByProsecnaOcena);
		}
		else if(direction.equals("desc")) {
			Collections.sort(ret, compareByProsecnaOcena);
			Collections.reverse(ret);
		}
		
		return ret;
	}

	
	public Collection<SportskiObjekat> filterByTypeSO(String tip, String contextPath) {
		
		ArrayList<SportskiObjekat> ret = new ArrayList<SportskiObjekat>();

		for(Map.Entry<String, SportskiObjekat> entry : sportskiObjekti.entrySet())
    	{
			if(tip.equals("0")) {
				ret.add(entry.getValue());
			}
			else if(entry.getValue().getTipObjekta().equalsIgnoreCase(tip)) {
				ret.add(entry.getValue());
			}
    	}
		
		return ret;
	}

	public Collection<String> getFilterTipoveSO(String contextPath) {

		for(Map.Entry<String, SportskiObjekat> entry : sportskiObjekti.entrySet())
    	{
			if(!tipoviSO.contains(entry.getValue().getTipObjekta()))
			tipoviSO.add(entry.getValue().getTipObjekta());
    	}
		
		return tipoviSO;
	}

	public Collection<SportskiObjekat> getOtvoreneSO(String contextPath) {
		
		ArrayList<SportskiObjekat> ret = new ArrayList<SportskiObjekat>();
		
		for(Map.Entry<String, SportskiObjekat> entry : sportskiObjekti.entrySet())
    	{
			if(entry.getValue().getStatus().equalsIgnoreCase("radi"))
			ret.add(entry.getValue());
    	}
		
		return ret;
	}

	public Collection<Trening> pretraziTreningePoCeni(String pretragaCena, String contextPath) {

		ArrayList<Trening> ret = new ArrayList<Trening>();
		
		for(Map.Entry<String, Trening> entry : sadrzaj.entrySet()) 
		{
			if(pretragaCena.equals("-1")) {
				ret.add(entry.getValue());
			}
			else if(pretragaCena.equals("0")) {
				if(entry.getValue().getCena() == 0) {
					ret.add(entry.getValue());
				}
			}
			else if(pretragaCena.equals("0-500")) {
				if(entry.getValue().getCena() >= 0 && entry.getValue().getCena() <= 500) {
					ret.add(entry.getValue());
				}
			}
			else if(pretragaCena.equals("500-1000")) {
				if(entry.getValue().getCena() >= 500 && entry.getValue().getCena() <= 1000) {
					ret.add(entry.getValue());
				}
			}
			else if(pretragaCena.equals("1000-1500")) {
				if(entry.getValue().getCena() >= 1000 && entry.getValue().getCena() <= 1500) {
					ret.add(entry.getValue());
				}
			}
			else if(pretragaCena.equals("1500+")) {
				if(entry.getValue().getCena() >= 1500) {
					ret.add(entry.getValue());
				}
			}
			
		}
		
		return ret;
	}
	
	public Collection<Trening> pretraziTreningePoTrajanju(String pretragaTrajanje, String contextPath) {

		ArrayList<Trening> ret = new ArrayList<Trening>();
		
		for(Map.Entry<String, Trening> entry : sadrzaj.entrySet()) 
		{
			if(pretragaTrajanje.equals("-1")) {
				ret.add(entry.getValue());
			}
			else if(pretragaTrajanje.equals("10-30")) {
				if(entry.getValue().getTrajanje() >= 10.0 && entry.getValue().getTrajanje() <= 30.0) {
					ret.add(entry.getValue());
				}
			}
			else if(pretragaTrajanje.equals("30-60")) {
				if(entry.getValue().getTrajanje() >= 30.0 && entry.getValue().getTrajanje() <= 60.0) {
					ret.add(entry.getValue());
				}
			}
			else if(pretragaTrajanje.equals("60+")) {
				if(entry.getValue().getTrajanje() >= 60.0) {
					ret.add(entry.getValue());
				}
			}
			
		}
		
		return ret;
	}

	
	public Collection<Trening> sortTreningeByPrice(String direction, String contextPath) {

		ArrayList<Trening> ret = new ArrayList<Trening>();

		for(Map.Entry<String, Trening> entry : sadrzaj.entrySet())
    	{
			ret.add(entry.getValue());
    	}
		
		if(direction.equals("asc") || direction.equals("0")) {
			Collections.sort(ret, compareByCena);
		}
		else if(direction.equals("desc")) {
			Collections.sort(ret, compareByCena);
			Collections.reverse(ret);
		}
		
		return ret;
	}
	
	public Collection<Trening> sortTreningeByDuration(String direction, String contextPath) {

		ArrayList<Trening> ret = new ArrayList<Trening>();

		for(Map.Entry<String, Trening> entry : sadrzaj.entrySet())
    	{
			ret.add(entry.getValue());
    	}
		
		if(direction.equals("asc") || direction.equals("0")) {
			Collections.sort(ret, compareByTrajanje);
		}
		else if(direction.equals("desc")) {
			Collections.sort(ret, compareByTrajanje);
			Collections.reverse(ret);
		}
		
		return ret;
	}

	public Collection<Trening> filterByTypeTreninga(String tip, String contextPath) {

		ArrayList<Trening> ret = new ArrayList<Trening>();

		for(Map.Entry<String, Trening> entry : sadrzaj.entrySet())
    	{
			if(tip.equals("0")) {
				ret.add(entry.getValue());
			}
			else if(entry.getValue().getTip().equalsIgnoreCase(tip)) {
				ret.add(entry.getValue());
			}
    	}
		
		return ret;
	}

	public Collection<String> getFilterTipoveTreninga(String contextPath) {

		for(Map.Entry<String, Trening> entry : sadrzaj.entrySet())
    	{
			if(!tipoviTreninga.contains(entry.getValue().getTip()))
			tipoviTreninga.add(entry.getValue().getTip());
    	}
		
		return tipoviTreninga;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
