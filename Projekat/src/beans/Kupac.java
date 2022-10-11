package beans;

import java.util.ArrayList;
import java.util.Collection;

@SuppressWarnings("serial")
public class Kupac extends Korisnik {

	
	
	private double clanarina;
	private Collection<String> sportskiObjekti;
	private double brojSakupljenihBodova;
	//private TipKupca tipKupca;
	
	public Kupac() {
		super();
	}
	
	public Kupac(Korisnik km) {
		super(km);
		this.clanarina = 0;
		this.brojSakupljenihBodova = 0;
		this.sportskiObjekti = new ArrayList<String>();
	}



	public double getClanarina() {
		return clanarina;
	}

	public void setClanarina(double clanarina) {
		this.clanarina = clanarina;
	}

	public Collection<String> getSportskiObjekti() {
		return sportskiObjekti;
	}

	public void setSportskiObjekti(Collection<String> sportskiObjekti) {
		this.sportskiObjekti = sportskiObjekti;
	}

	public double getBrojSakupljenihBodova() {
		return brojSakupljenihBodova;
	}

	public void setBrojSakupljenihBodova(double brojSakupljenihBodova) {
		this.brojSakupljenihBodova = brojSakupljenihBodova;
	}
	
	
	
	
	
}
