package beans;

import java.io.Serializable;

import enums.Pol;
import enums.Uloga;

@SuppressWarnings("serial")
public class Korisnik implements Serializable{

	private String username;
	private String password;
	private String ime;
	private String prezime;
	private Pol pol;
	private String datumRodjenja;
	private Uloga uloga;
	private boolean deleted;
	
	public Korisnik() {
		super();
	}

	public Korisnik(String username, String password, String ime, String prezime, Pol pol, String datumRodjenja,
			Uloga uloga, boolean deleted) {
		super();
		this.username = username;
		this.password = password;
		this.ime = ime;
		this.prezime = prezime;
		this.pol = pol;
		this.datumRodjenja = datumRodjenja;
		this.uloga = uloga;
		this.deleted = deleted;
	}

	public Korisnik(Korisnik k) {
		super();
		this.username = k.getUsername();
		this.password = k.getPassword();
		this.ime = k.getIme();
		this.prezime = k.getPrezime();
		this.pol = k.getPol();
		this.datumRodjenja = k.getDatumRodjenja();
		this.uloga = k.getUloga();
		this.deleted = k.isDeleted();
	}
	
	
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getIme() {
		return ime;
	}

	public void setIme(String ime) {
		this.ime = ime;
	}

	public String getPrezime() {
		return prezime;
	}

	public void setPrezime(String prezime) {
		this.prezime = prezime;
	}

	public Pol getPol() {
		return pol;
	}

	public void setPol(Pol pol) {
		this.pol = pol;
	}

	public String getDatumRodjenja() {
		return datumRodjenja;
	}

	public void setDatumRodjenja(String datumRodjenja) {
		this.datumRodjenja = datumRodjenja;
	}

	public Uloga getUloga() {
		return uloga;
	}

	public void setUloga(Uloga uloga) {
		this.uloga = uloga;
	}

	public boolean isDeleted() {
		return deleted;
	}

	public void setDeleted(boolean deleted) {
		this.deleted = deleted;
	}
	
}
