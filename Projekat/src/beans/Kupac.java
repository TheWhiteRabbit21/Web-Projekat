package beans;

import java.util.ArrayList;
import java.util.Collection;

@SuppressWarnings("serial")
public class Kupac extends Korisnik {

	
	private Collection<String> poseceniSportskiObjekti;
	private double brojSakupljenihBodova;
	private int idClanarine;
	private TipKupca tipKupca;
	
	public Kupac() {
		super();
	}
	
	public Kupac(Korisnik km) {
		super(km);
		this.poseceniSportskiObjekti = new ArrayList<String>();
		this.brojSakupljenihBodova = 0;
		this.idClanarine = 0;
	}

	public Collection<String> getPoseceniSportskiObjekti() {
		return poseceniSportskiObjekti;
	}

	public void setPoseceniSportskiObjekti(Collection<String> poseceniSportskiObjekti) {
		this.poseceniSportskiObjekti = poseceniSportskiObjekti;
	}

	public double getBrojSakupljenihBodova() {
		return brojSakupljenihBodova;
	}

	public void setBrojSakupljenihBodova(double brojSakupljenihBodova) {
		this.brojSakupljenihBodova = brojSakupljenihBodova;
	}
	
	public int getIdClanarine() {
		return idClanarine;
	}

	public void setIdClanarine(int idClanarine) {
		this.idClanarine = idClanarine;
	}

	public TipKupca getTipKupca() {
		return tipKupca;
	}

	public void setTipKupca(TipKupca tipKupca) {
		this.tipKupca = tipKupca;
	}

	
	
	
}
