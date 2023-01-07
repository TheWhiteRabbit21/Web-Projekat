package dao;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import beans.Clanarina;
import beans.Korisnik;
import beans.Kupac;
import beans.Menadzer;
import beans.SportskiObjekat;
import beans.Trener;
import enums.Pol;
import enums.Uloga;



public class KorisnikDAO {

	private String ctx;
	
    private HashMap<String, Korisnik> korisnici = new HashMap<>();
    
    private HashMap<String, Kupac> kupci = new HashMap<>();
    private HashMap<String, Menadzer> menadzeri = new HashMap<>();
    private HashMap<String, Trener> treneri = new HashMap<>();
    private HashMap<Integer, Clanarina> clanarine = new HashMap<>();
    
    private Clanarina trenutnaClanarina = new Clanarina();
    private Korisnik trenutniKorisnik = new Korisnik();
    
	public KorisnikDAO() {
		
	}

    /***
	 * @param contextPath Putanja do aplikacije u Tomcatu. Mo�e se pristupiti samo iz servleta.
	 */

	public KorisnikDAO(String contextPath) {
		loadUsers(contextPath);
		loadClanarine(contextPath);
		loadClanarinaIdJSON(contextPath);
		ctx = contextPath;
		
	}

    /**
	 * Vra�a korisnika za prosle�eno korisni�ko ime i �ifru. Vra�a null ako korisnik ne postoji
	 * @param username
	 * @param password
	 * @return
	 */
	
	public Collection<Korisnik> findAll() {
		return korisnici.values();
	}
	
	public Collection<Menadzer> findAllMenadzere(){

		Collection<Menadzer> temp = new ArrayList<Menadzer>();
		
		for(Map.Entry<String, Menadzer> entry : menadzeri.entrySet()) 
		{
				temp.add(entry.getValue());
		}
		
		return temp;
	}
	
	public Collection<Trener> findAllTrenere() {
    	
    	Collection<Trener> temp = new ArrayList<Trener>();
    	
    	for(Map.Entry<String, Trener> entry : treneri.entrySet())
    	{
    		temp.add(entry.getValue());
    	}    	
    	
    	return temp;
    }
	
	public Collection<Kupac> findAllKupce(){

		Collection<Kupac> temp = new ArrayList<Kupac>();
		
		for(Map.Entry<String, Kupac> entry : kupci.entrySet()) 
		{
				temp.add(entry.getValue());
		}
		
		return temp;
	}
	
	public Korisnik find(String username, String password) {
		
		loadUsers(ctx);
		
		if (!korisnici.containsKey(username)) {
			return null;
		}
		Korisnik korisnik = korisnici.get(username);
		if (!korisnik.getPassword().equals(password)) {
			return null;
		}
		return korisnik;
	}

    public boolean find(String username) {
    	
//    	for(Map.Entry<String, Korisnik> entry : korisnici.entrySet())
//			System.out.println("Key = " + entry.getKey() +
//					", Username = " + entry.getValue().getUsername());
//		
//		System.out.println("--------------------------------");
//
//		System.out.println("prosledjen username find funkciji: " + username);
		
    	loadUsers(ctx);
		
		
		if (!korisnici.containsKey(username)) {
			return false;
		}
		
		return true;
	}

    public Menadzer findMenadzer(String username) {
    		
    	for(Map.Entry<String, Menadzer> entry : menadzeri.entrySet())
    	{
    		if(entry.getKey() == username)
    		{
    			return entry.getValue();
    		}
    	}    	
    	
    	return null;
    		
    }
    
    public void setTrenutniKorisnik(Korisnik trenutniKorisnik) {
		this.trenutniKorisnik = trenutniKorisnik;
	}
    
    private void loadUsers(String contextPath) {
		
    	String filePath = contextPath + "users.json";
    	FileWriter fileWriter = null;
    	BufferedReader in = null;
    	File file = null;
    	
    	korisnici.clear();
    	
    	try {
			file = new File(filePath);
    		in = new BufferedReader(new FileReader(file));
    		
    		ObjectMapper objectMapper = new ObjectMapper();
			objectMapper.configure(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY, true);
			objectMapper.configure(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT, true);
			
			Korisnik[] ko = objectMapper.readValue(file, Korisnik[].class);

			for(Korisnik k : ko)
			{
				korisnici.put(k.getUsername(), k);
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
			System.out.println("Fajl users.json je prazan.");
		} finally {
			if (in != null) {
				try {
					in.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
    	    	
    	loadAdministrators(contextPath);
    	loadKupce(contextPath);
    	loadMenadzers(contextPath);
    	loadTrenere(contextPath);

    }

    public void loadKupce(String contextPath) {
    	
    	String filePath = contextPath + "kupci.json";
    	FileWriter fileWriter = null;
    	BufferedReader in = null;
    	File file = null;
    	
    	kupci.clear();
    	
    	try {
			file = new File(filePath);
    		in = new BufferedReader(new FileReader(file));
    		
    		ObjectMapper objectMapper = new ObjectMapper();
			objectMapper.configure(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY, true);
			objectMapper.configure(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT, true);
			
			Kupac[] ku = objectMapper.readValue(file, Kupac[].class);

			for(Kupac k : ku)
			{
				kupci.put(k.getUsername(), k);
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
			System.out.println("Fajl kupci.json je prazan.");
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
    
    public void loadMenadzers(String contextPath) {
    	
    	
    	
    	String filePath = contextPath + "menadzeri.json";
    	FileWriter fileWriter = null;
    	BufferedReader in = null;
    	File file = null;
    	
    	menadzeri.clear();
    	
    	try {
			file = new File(filePath);
    		in = new BufferedReader(new FileReader(file));
    		
    		ObjectMapper objectMapper = new ObjectMapper();
			objectMapper.configure(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY, true);
			objectMapper.configure(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT, true);
			
			Menadzer[] me = objectMapper.readValue(file, Menadzer[].class);

			for(Menadzer m : me)
			{
				menadzeri.put(m.getUsername(), m);
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
			System.out.println("Fajl menadzeri.json je prazan.");
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
   
    public void loadTrenere(String contextPath) {
    	
    	String filePath = contextPath + "treneri.json";
    	FileWriter fileWriter = null;
    	BufferedReader in = null;
    	File file = null;
    	
    	treneri.clear();
    	
    	try {
			file = new File(filePath);
    		in = new BufferedReader(new FileReader(file));
    		
    		ObjectMapper objectMapper = new ObjectMapper();
			objectMapper.configure(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY, true);
			objectMapper.configure(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT, true);
			
			Trener[] te = objectMapper.readValue(file, Trener[].class);

			for(Trener t : te)
			{
				treneri.put(t.getUsername(), t);
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
			System.out.println("Fajl treneri.json je prazan.");
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
    
    public void loadClanarine(String contextPath) {
    	
    	String filePath = contextPath + "clanarine.json";
    	FileWriter fileWriter = null;
    	BufferedReader in = null;
    	File file = null;
    	
    	clanarine.clear();
    	
    	try {
			file = new File(filePath);
    		in = new BufferedReader(new FileReader(file));
    		
    		ObjectMapper objectMapper = new ObjectMapper();
			objectMapper.configure(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY, true);
			objectMapper.configure(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT, true);
			
			Clanarina[] cl = objectMapper.readValue(file, Clanarina[].class);

			for(Clanarina c : cl)
			{
				clanarine.put(c.getId(), c);
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
			System.out.println("Fajl clanarine.json je prazan.");
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
    
    public Integer loadClanarinaIdJSON(String contextPath) {
    	
    	String filePath = contextPath + "clanarinaId.json";
    	FileWriter fileWriter = null;
    	BufferedReader in = null;
    	File file = null;
    	
    	try {
			file = new File(filePath);
    		in = new BufferedReader(new FileReader(file));
    		
    		ObjectMapper objectMapper = new ObjectMapper();
			objectMapper.configure(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY, true);
			objectMapper.configure(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT, true);
			
			Integer clId = objectMapper.readValue(file, Integer.class);
			
			return clId;
			
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
			System.out.println("Fajl clanarinaId.json je prazan.");
		} finally {
			if (in != null) {
				try {
					in.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		return -2;//ako je clanarinaId -2 ne bi trebalo da se koristi
    	
    }
    
	public void dodaj(Korisnik k, String contextPath){

		try
		{
			File file = new File(contextPath + "/users.json");
			ObjectMapper objectMapper = new ObjectMapper();

			objectMapper.configure(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY, true);
			objectMapper.configure(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT, true);
			ArrayList<Korisnik> temp = new ArrayList<>();
			trenutniKorisnik = k;

			Korisnik[] car = objectMapper.readValue(file, Korisnik[].class);
			
			for(Korisnik g : car)
			{
				temp.add(g);
			}
			
			temp.add(k);
			objectMapper.writeValue(new File(contextPath + "/users.json"), temp);
			
			loadUsers(contextPath);

		}
		catch (Exception ex) {
			
			try {
			
			System.out.println("usao u catch exception KorisnikDAO.dodaj");
			ObjectMapper objectMapper = new ObjectMapper();

			objectMapper.configure(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY, true);
			objectMapper.configure(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT, true);
			ArrayList<Korisnik> temp = new ArrayList<>();
			
			temp.add(k);
			trenutniKorisnik = k;
			objectMapper.writeValue(new File(contextPath + "/users.json"), temp);
			
			loadUsers(contextPath);
			//System.out.println(ex);
			//ex.printStackTrace();
			
			}catch (IOException e) {
				
			} finally {
				
			}
		} finally {
			
		}
	}
	
	public void dodajKupca(Korisnik km, String contextPath) {
		
		try
		{
			
			File file = new File(contextPath + "/kupci.json");
			ObjectMapper objectMapper = new ObjectMapper();

			objectMapper.configure(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY, true);
			objectMapper.configure(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT, true);
			ArrayList<Kupac> temp = new ArrayList<>();
			//trenutniKorisnik = k;

			Kupac[] ku = objectMapper.readValue(file, Kupac[].class);
			//System.out.println("register User: "+ car);
			
			for(Kupac g : ku)
			{
				temp.add(g);
			}
			
			Kupac k = new Kupac(km);
			
			temp.add(k);
			objectMapper.writeValue(new File(contextPath + "/kupci.json"), temp);
			
			kupci.put(k.getUsername(), k);
			
			Korisnik tempK = new Korisnik(k.getUsername(), k.getPassword(), k.getIme(), k.getPrezime(), k.getPol(), k.getDatumRodjenja(), Uloga.KUPAC, false);
			dodaj(tempK, contextPath);

		}
		catch (Exception ex) {
			
			try {
			
			//System.out.println("usao u catch exception KorisnikDAO.dodajMenadzera");
			ObjectMapper objectMapper = new ObjectMapper();

			objectMapper.configure(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY, true);
			objectMapper.configure(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT, true);
			ArrayList<Kupac> temp = new ArrayList<>();
			
			Kupac k = new Kupac(km);
			
			temp.add(k);
			objectMapper.writeValue(new File(contextPath + "/kupci.json"), temp);
			
			kupci.put(k.getUsername(), k);
			
			Korisnik tempK = new Korisnik(k.getUsername(), k.getPassword(), k.getIme(), k.getPrezime(), k.getPol(), k.getDatumRodjenja(), Uloga.KUPAC, false);
			dodaj(tempK, contextPath);
			//System.out.println(ex);
			//ex.printStackTrace();
			
			}catch (IOException e) {
				
			} finally {
				
			}
		} finally {
			
		}
		
		
		
		
	}
	
	public void dodajMenadzera(Korisnik km, String contextPath) {
		
		try
		{
			
			File file = new File(contextPath + "/menadzeri.json");
			ObjectMapper objectMapper = new ObjectMapper();

			objectMapper.configure(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY, true);
			objectMapper.configure(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT, true);
			ArrayList<Menadzer> temp = new ArrayList<>();
			//trenutniKorisnik = k;

			Menadzer[] me = objectMapper.readValue(file, Menadzer[].class);
			//System.out.println("register User: "+ car);
			
			for(Menadzer g : me)
			{
				temp.add(g);
			}
			
			Menadzer m = new Menadzer(km);
			
			temp.add(m);
			objectMapper.writeValue(new File(contextPath + "/menadzeri.json"), temp);
			
			menadzeri.put(m.getUsername(), m);
			
			Korisnik tempK = new Korisnik(m.getUsername(), m.getPassword(), m.getIme(), m.getPrezime(), m.getPol(), m.getDatumRodjenja(), Uloga.MENADZER, false);
			dodaj(tempK, contextPath);
			
			//loadUsers(contextPath);
			//Korisnik r = korisnici.put(k.getUsername(), k);
			//System.out.println(korisnici);

		}
		catch (Exception ex) {
			
			try {
			
			//System.out.println("usao u catch exception KorisnikDAO.dodajMenadzera");
			ObjectMapper objectMapper = new ObjectMapper();

			objectMapper.configure(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY, true);
			objectMapper.configure(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT, true);
			ArrayList<Menadzer> temp = new ArrayList<>();
			
			Menadzer m = new Menadzer(km);
			
			temp.add(m);
			objectMapper.writeValue(new File(contextPath + "/menadzeri.json"), temp);
			
			menadzeri.put(m.getUsername(), m);
			
			Korisnik tempK = new Korisnik(m.getUsername(), m.getPassword(), m.getIme(), m.getPrezime(), m.getPol(), m.getDatumRodjenja(), Uloga.MENADZER, false);
			dodaj(tempK, contextPath);
			//System.out.println(ex);
			//ex.printStackTrace();
			
			}catch (IOException e) {
				
			} finally {
				
			}
		} finally {
			
		}
		
	}
	
	public void dodajMenadzera(Menadzer m, String contextPath) {
		
		try
		{
			
			File file = new File(contextPath + "/menadzeri.json");
			ObjectMapper objectMapper = new ObjectMapper();

			objectMapper.configure(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY, true);
			objectMapper.configure(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT, true);
			ArrayList<Menadzer> temp = new ArrayList<>();
			//trenutniKorisnik = k;

			Menadzer[] me = objectMapper.readValue(file, Menadzer[].class);
			//System.out.println("register User: "+ car);
			
			for(Menadzer g : me)
			{
				temp.add(g);
			}
			temp.add(m);
			objectMapper.writeValue(new File(contextPath + "/menadzeri.json"), temp);
			
			menadzeri.put(m.getUsername(), m);
			
			Korisnik tempK = new Korisnik(m.getUsername(), m.getPassword(), m.getIme(), m.getPrezime(), 
					m.getPol(), m.getDatumRodjenja(), Uloga.MENADZER, false);
			dodaj(tempK, contextPath);
			
			//loadUsers(contextPath);
			//Korisnik r = korisnici.put(k.getUsername(), k);
			//System.out.println(korisnici);

		}
		catch (Exception ex) {
			
			try {
			
			//System.out.println("usao u catch exception KorisnikDAO.dodajMenadzera");
			ObjectMapper objectMapper = new ObjectMapper();

			objectMapper.configure(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY, true);
			objectMapper.configure(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT, true);
			ArrayList<Menadzer> temp = new ArrayList<>();
			
			temp.add(m);
			objectMapper.writeValue(new File(contextPath + "/menadzeri.json"), temp);
			
			menadzeri.put(m.getUsername(), m);
			
			Korisnik tempK = new Korisnik(m.getUsername(), m.getPassword(), m.getIme(), m.getPrezime(), 
					m.getPol(), m.getDatumRodjenja(), Uloga.MENADZER, false);
			dodaj(tempK, contextPath);
			//System.out.println(ex);
			//ex.printStackTrace();
			
			}catch (IOException e) {
				
			} finally {
				
			}
		} finally {
			
		}
		
	}
	
	public void dodajTrenera(Korisnik km, String contextPath) {
		
		try
		{
			
			File file = new File(contextPath + "/treneri.json");
			ObjectMapper objectMapper = new ObjectMapper();

			objectMapper.configure(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY, true);
			objectMapper.configure(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT, true);
			ArrayList<Trener> temp = new ArrayList<>();
			//trenutniKorisnik = k;

			Trener[] te = objectMapper.readValue(file, Trener[].class);
			//System.out.println("register User: "+ car);
			
			for(Trener g : te)
			{
				temp.add(g);
			}
			
			Trener t = new Trener(km);
			
			temp.add(t);
			objectMapper.writeValue(new File(contextPath + "/treneri.json"), temp);
			
			treneri.put(t.getUsername(), t);
			
			Korisnik tempK = new Korisnik(t.getUsername(), t.getPassword(), t.getIme(), t.getPrezime(), 
					t.getPol(), t.getDatumRodjenja(), Uloga.TRENER, false);
			dodaj(tempK, contextPath);
			
			//loadUsers(contextPath);
			//Korisnik r = korisnici.put(k.getUsername(), k);
			//System.out.println(korisnici);

		}
		catch (Exception ex) {
			
			try {
			
			//System.out.println("usao u catch exception KorisnikDAO.dodajMenadzera");
			ObjectMapper objectMapper = new ObjectMapper();

			objectMapper.configure(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY, true);
			objectMapper.configure(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT, true);
			ArrayList<Trener> temp = new ArrayList<>();
			
			Trener t = new Trener(km);
			
			temp.add(t);
			objectMapper.writeValue(new File(contextPath + "/treneri.json"), temp);
			
			treneri.put(t.getUsername(), t);
			
			Korisnik tempK = new Korisnik(t.getUsername(), t.getPassword(), t.getIme(), t.getPrezime(), 
					t.getPol(), t.getDatumRodjenja(), Uloga.TRENER, false);
			dodaj(tempK, contextPath);
			//System.out.println(ex);
			//ex.printStackTrace();
			
			}catch (IOException e) {
				
			} finally {
				
			}
		} finally {
			
		}
		
	}
	
	public String getTrenutniKorisnik() {
		//System.out.println("Trenutni korisnik je: " + trenutniKorisnik.getUsername());
		return trenutniKorisnik.getUsername();
	}

	public void loadAdministrators(String contextPath) {
		
		
		String filePath = contextPath + "administratori.txt";
    	BufferedReader in = null;
    	File file = null;
		
    	try {
			file = new File(filePath);
			
			//Ovo ispisuje putanju u konzolu
			//System.out.println(file.getCanonicalPath());
			
			in = new BufferedReader(new FileReader(file));
			String line, username = "", password = "", ime = "", prezime = "", polString = "", datumRodjenja = "";
			StringTokenizer st;
			while ((line = in.readLine()) != null) {
				line = line.trim();
				if (line.equals("") || line.indexOf('#') == 0)
					continue;
				st = new StringTokenizer(line, ";");
				while (st.hasMoreTokens()) {
					username = st.nextToken().trim();
					password = st.nextToken().trim();
					ime = st.nextToken().trim();
					prezime = st.nextToken().trim();
					polString = st.nextToken().trim();
					datumRodjenja = st.nextToken().trim();
				}
				
				Pol pol = null;
				
				if(polString.equalsIgnoreCase("MUSKO")) {
					pol = Pol.MUSKO;
				}
				else {pol = Pol.ZENSKO;}
				
				korisnici.put(username, new Korisnik(username, password, ime, prezime,
						pol, datumRodjenja, Uloga.ADMINISTRATOR, false));
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
	
	public void izmeni(Korisnik k, String contextPath) {
		
		ArrayList<Korisnik> temp = new ArrayList<>();
		
    	for(HashMap.Entry<String, Korisnik> entry : korisnici.entrySet()) 
    	{
			//System.out.println("Key = " + entry.getKey() + ", Username = " + entry.getValue().getUsername());
    	
			if(k.getUsername().equalsIgnoreCase(entry.getValue().getUsername())) {
				entry.getValue().setIme(k.getIme());
				entry.getValue().setPrezime(k.getPrezime());
				entry.getValue().setDatumRodjenja(k.getDatumRodjenja());
				entry.getValue().setPassword(k.getPassword());
				entry.getValue().setPol(k.getPol());
			}
			temp.add(entry.getValue());
    	}
    	
		upisiUFajl(temp, contextPath);
		
	}
	
	public void upisiUFajl(ArrayList<Korisnik> kf, String contextPath) {
		
		try
		{
		ObjectMapper objectMapper = new ObjectMapper();

		objectMapper.configure(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY, true);
		objectMapper.configure(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT, true);
		
		objectMapper.writeValue(new File(contextPath + "/users.json"), kf);
		}
		
		catch (Exception ex) {
			System.out.println(ex);
			ex.printStackTrace();
			
		} finally {
			
		}
		
	}

	public void upisiUFajlMenadzere(ArrayList<Menadzer> me, String contextPath) {
	
		try
		{
		ObjectMapper objectMapper = new ObjectMapper();

		objectMapper.configure(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY, true);
		objectMapper.configure(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT, true);
		
		objectMapper.writeValue(new File(contextPath + "/menadzeri.json"), me);
		}
		
		catch (Exception ex) {
			System.out.println(ex);
			ex.printStackTrace();
			
		} finally {
			
		}
		
		
	}
	
	private void upisiUFajlKupce(ArrayList<Kupac> ku, String contextPath) {
		
		try
		{
		ObjectMapper objectMapper = new ObjectMapper();

		objectMapper.configure(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY, true);
		objectMapper.configure(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT, true);
		
		objectMapper.writeValue(new File(contextPath + "/kupci.json"), ku);
		}
		
		catch (Exception ex) {
			System.out.println(ex);
			ex.printStackTrace();
			
		} finally {
			
		}
	}
	
	private void upisiUFajlClanarine(String contextPath) {
		
		ArrayList<Clanarina> cl = new ArrayList<Clanarina>();
		
		for(Map.Entry<Integer, Clanarina> entry : clanarine.entrySet())
    	{
    		cl.add(entry.getValue());
    	}
		
		try
		{
			ObjectMapper objectMapper = new ObjectMapper();

			objectMapper.configure(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY, true);
			objectMapper.configure(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT, true);
		
			objectMapper.writeValue(new File(contextPath + "/clanarine.json"), cl);
		}
		
		catch (Exception ex) {
			System.out.println(ex);
			ex.printStackTrace();
			
		} finally {
			
		}
		
	}
	
	private void upisiUFajlClanarinaId(Integer id, String contextPath) {
		
		try
		{
		ObjectMapper objectMapper = new ObjectMapper();

		objectMapper.configure(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY, true);
		objectMapper.configure(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT, true);
		
		objectMapper.writeValue(new File(contextPath + "/clanarinaId.json"), id);
		}
		
		catch (Exception ex) {
			System.out.println(ex);
			ex.printStackTrace();
			
		} finally {
			
		}
	}
	
	public void dodeliSportskiObjekatMenadzeru(SportskiObjekat sportskiObjekat, String contextPath) {

		ArrayList<Menadzer> me = new ArrayList<>();
		
		for(Map.Entry<String, Menadzer> entry : menadzeri.entrySet())
    	{
    		if(entry.getKey().equals(sportskiObjekat.getMenadzer()))
    		{
    			entry.getValue().setSportskiObjekat(sportskiObjekat.getNaziv());
    		}
    		me.add(entry.getValue());
    	}
		
		upisiUFajlMenadzere(me, contextPath);
		
	}

	@SuppressWarnings("deprecation")
	public void platiClanarinu(Clanarina clanarina, String contextPath) {
		
		loadClanarine(contextPath);
		
		if(loadClanarinaIdJSON(contextPath) == -2) {
			upisiUFajlClanarinaId(0, contextPath);
		}
		
		//nakon ovoga trenutni korisnik ima clanarinu na false i nove bodove
		proveriIZavrsiTrenutnuClanarinuTrenutnogKupca(contextPath);
		
		ArrayList<Kupac> tempKupci = new ArrayList<Kupac>();

		
		for(Map.Entry<String, Kupac> entry : kupci.entrySet())
    	{
			if(entry.getKey().equals(trenutniKorisnik.getUsername()))
	    	{
				
				Date datumPlacanja = new Date();
    			Date datumIsteka = new Date();
				
    			datumIsteka.setMonth(datumPlacanja.getMonth()+1);
    			
				//ako kupac nema trenutno nikakvu clanarinu
    			if(entry.getValue().getIdClanarine() == 0) {
    			
	    			int tempClanarinaId = loadClanarinaIdJSON(contextPath);
	    			tempClanarinaId++;
	    			upisiUFajlClanarinaId(tempClanarinaId, contextPath);
	    			
	    			entry.getValue().setIdClanarine(tempClanarinaId);
	    			
	    			trenutnaClanarina = new Clanarina(tempClanarinaId, clanarina.getTip(), 
	    					datumPlacanja, datumIsteka, clanarina.getCena(), 
	    					entry.getValue().getUsername(), true, clanarina.getBrojTermina());
	    			
	    			clanarine.put(tempClanarinaId, trenutnaClanarina);
	    			
	    			upisiUFajlClanarine(contextPath);
				
    			}	//kupac ima neaktivnu clanarinu
    			else {
    				trenutnaClanarina = new Clanarina(entry.getValue().getIdClanarine(), clanarina.getTip(), 
	    					datumPlacanja, datumIsteka, clanarina.getCena(), 
	    					entry.getValue().getUsername(), true, clanarina.getBrojTermina());
    				clanarine.put(entry.getValue().getIdClanarine(), trenutnaClanarina);
	    			
	    			upisiUFajlClanarine(contextPath);
    			}
    			
	    	}
			tempKupci.add(entry.getValue());
				
    	}
		
		upisiUFajlKupce(tempKupci, contextPath);
		
	}
		
		
		
		
//    		if(entry.getKey().equals(trenutniKorisnik.getUsername()))
//    		{
    			//ako kupac nema trenutno aktivnu clanarinu
//    			if(entry.getValue().getIdClanarine() == 0) {
//    			
//    			int tempClanarinaId = loadClanarinaIdJSON(contextPath);
//    			tempClanarinaId++;
//    			upisiUFajlClanarinaId(tempClanarinaId, contextPath);
//    			
//    			Date datumPlacanja = new Date();
//    			Date datumIsteka = new Date();
//    			
//    			datumIsteka.setMonth(datumPlacanja.getMonth()+1);
//    			
//    			entry.getValue().setIdClanarine(tempClanarinaId);
//    			
//    			trenutnaClanarina = new Clanarina(tempClanarinaId, clanarina.getTip(), 
//    					datumPlacanja, datumIsteka, clanarina.getCena(), 
//    					entry.getValue().getUsername(), true, clanarina.getBrojTermina());
//    			
//    			clanarine.put(tempClanarinaId, trenutnaClanarina);
//    			
//    			upisiUFajlClanarine(contextPath);
//    		}
//    		else 
//    		{	
//    			//ako kupac ima clanarinu vec
//    			for(Map.Entry<Integer, Clanarina> entry2 : clanarine.entrySet())
//    	    	{
//    				if(entry2.getKey() == entry.getValue().getIdClanarine()) {
//    					
//    					Double tempBrojBodova = entry.getValue().getBrojSakupljenihBodova();
//    					
//    					if(entry2.getValue().getTip().equalsIgnoreCase("Osnovna")) {
//    						if(entry2.getValue().getBrojTerminaInt() == 0) {
//    							tempBrojBodova += 6;
//    						}
//    						else {
//    							tempBrojBodova += 5/entry2.getValue().getBrojTerminaInt();
//    						}
//    					}
//    					else if(entry2.getValue().getTip().equalsIgnoreCase("Srednja")) {
//    						tempBrojBodova += 10/entry2.getValue().getBrojTerminaInt();
//    					}
//    					else if(entry2.getValue().getTip().equalsIgnoreCase("Najbolja")) {
//    						tempBrojBodova += 10/entry2.getValue().getBrojTerminaInt();
//    					}
//    					
//    					
//    					
//    					
//    					
//    					entry2.getValue().setBrojTerminaInt(clanarina.getBrojTerminaInt());
//    					entry2.getValue().setBrojTermina(clanarina.getBrojTermina());
//    					entry2.getValue().setCena(clanarina.getCena());
//    					entry2.getValue().setTip(clanarina.getTip());
//    					Date datumPlacanja = new Date();
//    	    			Date datumIsteka = new Date();
//    	    			
//    	    			datumIsteka.setMonth(datumPlacanja.getMonth()+1);
//    	    			
//    	    			entry2.getValue().setDatumPlacanja(datumPlacanja);
//    	    			entry2.getValue().setDatumIVremeIsteka(datumIsteka);
//    	    			entry2.getValue().setStatus(true);
//    	    			
//    	    			trenutnaClanarina = entry2.getValue();
//    				}
//    				
//    	    	}
//    			upisiUFajlClanarine(contextPath);
//    		}
//    			
//    		}

	

	private void proveriIZavrsiTrenutnuClanarinuTrenutnogKupca(String contextPath) {
		
		ArrayList<Kupac> tempKupci = new ArrayList<Kupac>();
		
		//nadji kupca koji je uplatio clanarinu
		for(Map.Entry<String, Kupac> entry : kupci.entrySet())
    	{	//kupac koji je platio clanarinu
    		if(entry.getKey().equals(trenutniKorisnik.getUsername()))
    		{	//ako nije jos uplatio ni jednu clanarinu
    			if(entry.getValue().getIdClanarine() == 0) {
    				System.out.println("Kupac trenutno nema clanarinu!");
    			} //ako ima uplacenu neku clanarinu proveravamo da li je aktivna
    			else {	//prolazimo kroz clanarine
    				for(Map.Entry<Integer, Clanarina> entry2 : clanarine.entrySet())
        	    	{	//nasao clanarinu korisnika koji uplacuje novu
    					if(entry2.getKey() == entry.getValue().getIdClanarine()) {
    						//clanarina nije aktivna pa ne dodajemo nikakve nove bodove
    						if(!entry2.getValue().getStatus()) {
    							System.out.println("Kupac trenutno nema aktivnu clanarinu!");
    						} //clanarina jeste aktivna pa treba da dodamo bodove pre nego sto novu clanarinu aktiviramo
    						else {
    							Double tempBrojBodova = entry.getValue().getBrojSakupljenihBodova();
    							
    							if(entry2.getValue().getTip().equalsIgnoreCase("Osnovna")) {
    	    						if(entry2.getValue().getBrojTerminaInt() == 0) {
    	    							tempBrojBodova += 6.0;
    	    						}
    	    						else {
    	    							tempBrojBodova += 5.0/entry2.getValue().getBrojTerminaInt();
    	    						}
    	    					}
    	    					else if(entry2.getValue().getTip().equalsIgnoreCase("Srednja")) {
    	    						if(entry2.getValue().getBrojTerminaInt() == 0) {
    	    							tempBrojBodova += 12.0;
    	    						}
    	    						else {
    	    							tempBrojBodova += 10.0/entry2.getValue().getBrojTerminaInt();
    	    						}
    	    					}
    	    					else if(entry2.getValue().getTip().equalsIgnoreCase("Najbolja")) {
    	    						tempBrojBodova += 15.0;
    	    					}
    							entry.getValue().setBrojSakupljenihBodova(tempBrojBodova);
    							entry2.getValue().setStatus(false);
    							upisiUFajlClanarine(contextPath);
    						}
    					}
        	    	}
    			}
    		}
    		tempKupci.add(entry.getValue());
    	}
		
		upisiUFajlKupce(tempKupci, contextPath);
		
	}

	
	
	
	
	
	
	
}
