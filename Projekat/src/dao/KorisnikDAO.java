package dao;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import beans.Korisnik;



public class KorisnikDAO {

    private HashMap<String, Korisnik> korisnici = new HashMap<>();
    private Korisnik trenutniKorisnik = new Korisnik();
    
	public KorisnikDAO() {
		
	}

    /***
	 * @param contextPath Putanja do aplikacije u Tomcatu. Mo�e se pristupiti samo iz servleta.
	 */

	public KorisnikDAO(String contextPath) {
		loadUsers(contextPath);
	}

    /**
	 * Vra�a korisnika za prosle�eno korisni�ko ime i �ifru. Vra�a null ako korisnik ne postoji
	 * @param username
	 * @param password
	 * @return
	 */
	
	
	public Korisnik find(String username, String password) {
		if (!korisnici.containsKey(username)) {
			return null;
		}
		Korisnik korisnik = korisnici.get(username);
		if (!korisnik.getLozinka().equals(password)) {
			return null;
		}
		return korisnik;
	}

    public boolean find(String username) {
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
    	
    	try {
			file = new File(filePath);
    		in = new BufferedReader(new FileReader(file));
    		
    		ObjectMapper objectMapper = new ObjectMapper();
			objectMapper.configure(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY, true);
			objectMapper.configure(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT, true);
			
			Korisnik[] car = objectMapper.readValue(file, Korisnik[].class);

			for(Korisnik k : car)
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
				
				
				/*fileWriter = new FileWriter(file);
				
				ObjectMapper objectMapper = new ObjectMapper();
				objectMapper.configure(SerializationFeature.INDENT_OUTPUT, true);
				objectMapper.getFactory().configure(JsonGenerator.Feature.ESCAPE_NON_ASCII, true);
				
				String string = objectMapper.writeValueAsString(users);
				fileWriter.write(string);*/
				
				
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
    	
    	
    	
    	
    	
		/*try
		{
			File file = new File(contextPath + "/users.json");
			//System.out.println(contextPath);
			ObjectMapper objectMapper = new ObjectMapper();
			objectMapper.configure(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY, true);
			objectMapper.configure(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT, true);
			
			Korisnik[] car = objectMapper.readValue(file, Korisnik[].class);
			//System.out.println("load User: "+car);
			
			for(Korisnik k : car)
			{
				korisnici.put(k.getUsername(),k);
			}
			
			//System.out.println(korisnici);
			
		}
		catch (Exception ex) {
			System.out.println(ex);
			ex.printStackTrace();
		} finally {
			
		}*/
    
    }

	public void dodaj(Korisnik k, String contextPath){

		try
		{
			System.out.println("usao u KorisnikDAO.dodaj");
			//System.out.println(contextPath);
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
				// TODO: handle exception
			} finally {
				
			}
		} finally {
			
		}
	}

	public String getTrenutniKorisnikUsername() {
		System.out.println("Trenutni korisnik je: " + trenutniKorisnik.getUsername());
		return trenutniKorisnik.getUsername();
	}

























}
