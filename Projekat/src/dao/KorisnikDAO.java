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
import java.util.StringTokenizer;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import beans.Korisnik;
import enums.Pol;
import enums.Uloga;



public class KorisnikDAO {

	private String ctx;
	
    private HashMap<String, Korisnik> korisnici = new HashMap<>();
    private Korisnik trenutniKorisnik = new Korisnik();
    
	public KorisnikDAO() {
		
	}

    /***
	 * @param contextPath Putanja do aplikacije u Tomcatu. Mo�e se pristupiti samo iz servleta.
	 */

	public KorisnikDAO(String contextPath) {
		loadUsers(contextPath);
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
				korisnici.put(k.getUsername(),k);
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
    	    	
    	loadAdministrators(contextPath);

    }

	public void dodaj(Korisnik k, String contextPath){

		try
		{
			//System.out.println("usao u KorisnikDAO.dodaj");
			System.out.println(contextPath);
			File file = new File(contextPath + "/users.json");
			ObjectMapper objectMapper = new ObjectMapper();

			objectMapper.configure(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY, true);
			objectMapper.configure(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT, true);
			ArrayList<Korisnik> temp = new ArrayList<>();
			trenutniKorisnik = k;

			Korisnik[] car = objectMapper.readValue(file, Korisnik[].class);
			//System.out.println("register User: "+ car);
			
			for(Korisnik g : car)
			{
				temp.add(g);
			}
			temp.add(k);
			objectMapper.writeValue(new File(contextPath + "/users.json"), temp);
			
			loadUsers(contextPath);
			
			//Korisnik r = korisnici.put(k.getUsername(), k);
			
			//System.out.println(korisnici);

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
			
			//System.out.println(ex);
			//ex.printStackTrace();
			
			}catch (IOException e) {
				
			} finally {
				
			}
		} finally {
			
		}
	}
	
	public String getTrenutniKorisnikUsername() {
		System.out.println("Trenutni korisnik je: " + trenutniKorisnik.getUsername());
		return trenutniKorisnik.getUsername();
	}

	public void loadAdministrators(String contextPath) {
		
		
		String filePath = contextPath + "administratori.txt";
    	BufferedReader in = null;
    	File file = null;
		
    	try {
			file = new File(filePath);
			
			//Ovo ispisuje putanju u konzolu
			System.out.println(file.getCanonicalPath());
			
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
	
	
	
	
	
	
	
	
	
	


}
