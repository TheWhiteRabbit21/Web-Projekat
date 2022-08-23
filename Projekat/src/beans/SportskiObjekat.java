package beans;

import java.io.Serializable;

@SuppressWarnings("serial")
public class SportskiObjekat implements Serializable{

	private String naziv;
	private String tipObjekta;
	private String sadrzaj;	//grupni/personalni treninzi, sauna itd.
	private String status;	//radi/ne radi
	private String mapa;	
	private String logo;	
	private double prosecnaOcena;
	private String radnoVreme;
	private String menadzer;
	
	public SportskiObjekat() {
		super();
	}
	
	public SportskiObjekat(String naziv, String tipObjekta, String sadrzaj, String status, String mapa,
			String logo, double prosecnaOcena, String radnoVreme, String menadzer) {
		this.naziv = naziv;
		this.tipObjekta = tipObjekta;
		this.sadrzaj = sadrzaj;
		this.status = status;
		this.mapa = mapa;
		this.logo = logo;
		this.prosecnaOcena = prosecnaOcena;
		this.radnoVreme = radnoVreme;
		this.menadzer = menadzer;
	}
	
	public SportskiObjekat(String naziv) {
		super();
		this.naziv = naziv;
	}

	public SportskiObjekat(String naziv, String tipObjekta) {
		super();
		this.naziv = naziv;
		this.tipObjekta = tipObjekta;
	}


	public SportskiObjekat(String tipObjekta, int i, int j) {
		super();
		this.tipObjekta = tipObjekta;
	}

	public String getNaziv() {
		return naziv;
	}
	public void setNaziv(String naziv) {
		this.naziv = naziv;
	}
	public String getTipObjekta() {
		return tipObjekta;
	}
	public void setTipObjekta(String tipObjekta) {
		this.tipObjekta = tipObjekta;
	}
	public String getSadrzaj() {
		return sadrzaj;
	}
	public void setSadrzaj(String sadrzaj) {
		this.sadrzaj = sadrzaj;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public double getProsecnaOcena() {
		return prosecnaOcena;
	}
	public void setProsecnaOcena(double prosecnaOcena) {
		this.prosecnaOcena = prosecnaOcena;
	}
	public String getRadnoVreme() {
		return radnoVreme;
	}
	public void setRadnoVreme(String radnoVreme) {
		this.radnoVreme = radnoVreme;
	}
	public String getMapa() {
		return mapa;
	}
	public void setMapa(String mapa) {
		this.mapa = mapa;
	}
	public String getLogo() {
		return logo;
	}
	public void setLogo(String logo) {
		this.logo = logo;
	}
	public String getMenadzer() {
		return menadzer;
	}
	public void setMenadzer(String menadzer) {
		this.menadzer = menadzer;
	}
	
}
