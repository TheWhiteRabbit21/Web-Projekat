package dao;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.Collection;
import java.util.HashMap;
import java.util.StringTokenizer;

import beans.SportskiObjekat;

public class SportskiObjekatDAO {

	private HashMap<String, SportskiObjekat> sportskiObjekti = new HashMap<String, SportskiObjekat>();
	
	public SportskiObjekatDAO() {
		
	}
	
	/***
	 * @param contextPath Putanja do aplikacije u Tomcatu. Mo�e se pristupiti samo iz servleta.
	 */
	public SportskiObjekatDAO(String contextPath) {
		loadSportskiObjekti(contextPath);
	}
	
	
	public Collection<SportskiObjekat> findAll(){
		return sportskiObjekti.values();
	}
	
	
	private void loadSportskiObjekti(String contextPath) {
		
		BufferedReader in = null;
		try {
			File file = new File(contextPath + "/sportskiObjekti.txt");
			System.out.println(file.getCanonicalPath());
			in = new BufferedReader(new FileReader(file));
			String line, naziv = "", tipObjekta = "", sadrzaj = "", status = "", mapa = "";
			String logo = "", prosecnaOcena = "", radnoVreme = "";
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
					prosecnaOcena = st.nextToken().trim();
					radnoVreme = st.nextToken().trim();
				}
				sportskiObjekti.put(naziv, new SportskiObjekat(naziv, tipObjekta, sadrzaj, status,
						mapa, logo, Double.parseDouble(prosecnaOcena), radnoVreme));
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
	
	
	
	
	
	
}
