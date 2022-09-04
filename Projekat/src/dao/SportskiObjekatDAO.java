package dao;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import beans.SportskiObjekat;
import beans.Trening;

public class SportskiObjekatDAO {

	private String ctx;
	
	private HashMap<String, SportskiObjekat> sportskiObjekti = new HashMap<String, SportskiObjekat>();
	private HashMap<String, Trening> sadrzaj = new HashMap<String, Trening>();
	private SportskiObjekat sportskiObjekatZaPrikazati = new SportskiObjekat();
	
	public SportskiObjekatDAO() {
		
	}
	
	/***
	 * @param contextPath Putanja do aplikacije u Tomcatu. Mo�e se pristupiti samo iz servleta.
	 */
	
	public SportskiObjekatDAO(String contextPath) {
		loadSportskiObjekti(contextPath);
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
			ex.printStackTrace();
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
			ex.printStackTrace();
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
			//System.out.println("usao u KorisnikDAO.dodaj");
			//System.out.println(contextPath);
			File file = new File(contextPath + "/sadrzaj.json");
			ObjectMapper objectMapper = new ObjectMapper();

			objectMapper.configure(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY, true);
			objectMapper.configure(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT, true);
			ArrayList<Trening> temp = new ArrayList<>();
			//trenutniKorisnik = k;

			Trening[] tr = objectMapper.readValue(file, Trening[].class);
			//System.out.println("register User: "+ car);
			
			for(Trening g : tr)
			{
				temp.add(g);
			}
			temp.add(trening);
			objectMapper.writeValue(new File(contextPath + "/sadrzaj.json"), temp);
			
			loadSadrzaj(contextPath);

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
			
			}catch (IOException e) {
				
			} finally {
				
			}
		} finally {
			
		}
		
		
	}
	
	public Collection<SportskiObjekat> pretraziSportskeObjekte(SportskiObjekat sportskiObjekat, String contextPath){

		HashMap<String, SportskiObjekat> vrati = new HashMap<String, SportskiObjekat>();
		HashMap<String, SportskiObjekat> brisi = new HashMap<String, SportskiObjekat>();

		for(SportskiObjekat so: sportskiObjekti.values()) {
			vrati.put(so.getNaziv(), so);
		}

		for(SportskiObjekat so : vrati.values())
		{
			if (!(so.getNaziv().contains(sportskiObjekat.getNaziv())) && (!brisi.containsKey(so.getNaziv())))
	         {
				System.out.print("izbrisao je jedan!");
	             brisi.put(so.getNaziv(),so);
	         }

			if (!(so.getTipObjekta().contains(sportskiObjekat.getTipObjekta())) && (!brisi.containsKey(so.getNaziv())))
	         {
	             brisi.put(so.getNaziv(),so);
	         }

		}

		for(SportskiObjekat so: brisi.values()) {
			vrati.remove(so.getNaziv());
		}

		System.out.println(vrati.values());
		
		return vrati.values();

	}
	
	public Collection<SportskiObjekat> pretraziSportskeObjektePoTipuObjekta(SportskiObjekat sportskiObjekat, String contextPath){

		HashMap<String, SportskiObjekat> vrati = new HashMap<String, SportskiObjekat>();
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
		
		return vrati.values();

	}
	
	public Collection<SportskiObjekat> pretraziSportskeObjektePoNazivu(SportskiObjekat sportskiObjekat, String contextPath){

		HashMap<String, SportskiObjekat> vrati = new HashMap<String, SportskiObjekat>();
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
		
		return vrati.values();

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

	public SportskiObjekat getSportskiObjekatZaPrikazati(String contextPath) {
		return sportskiObjekatZaPrikazati;
	}

	
	public Collection<Trening> getSadrzajSportskogObjekta(String contextPath) {
		return sadrzaj.values();
	}

	
	
	
	
	
	
}
