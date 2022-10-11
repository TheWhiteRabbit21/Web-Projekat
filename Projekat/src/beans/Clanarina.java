package beans;

import java.io.Serializable;

import enums.Status;

@SuppressWarnings("serial")
public class Clanarina implements Serializable{

	private int id;
	private String tip;
	private String datumPlacanja;
	private String datumIVremeVazenja;
	private String cena;
	private String kupac;
	private Status status;
	private String brojTermina;
	private int brojTerminaInt;
	private boolean beskonacnoTermina;
	
	public Clanarina(String tip, String brojTermina, String cena) {
		super();
		this.tip = tip;
		this.brojTermina = brojTermina;
		this.cena = cena;
		
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTip() {
		return tip;
	}
	public void setTip(String tip) {
		this.tip = tip;
	}
	public String getDatumPlacanja() {
		return datumPlacanja;
	}
	public void setDatumPlacanja(String datumPlacanja) {
		this.datumPlacanja = datumPlacanja;
	}
	public String getDatumIVremeVazenja() {
		return datumIVremeVazenja;
	}
	public void setDatumIVremeVazenja(String datumIVremeVazenja) {
		this.datumIVremeVazenja = datumIVremeVazenja;
	}
	public String getCena() {
		return cena;
	}
	public void setCena(String cena) {
		this.cena = cena;
	}
	public String getKupac() {
		return kupac;
	}
	public void setKupac(String kupac) {
		this.kupac = kupac;
	}
	public Status getStatus() {
		return status;
	}
	public void setStatus(Status status) {
		this.status = status;
	}
	public String getBrojTermina() {
		return brojTermina;
	}
	public void setBrojTermina(String brojTermina) {
		this.brojTermina = brojTermina;
	}
	public int getBrojTerminaInt() {
		return brojTerminaInt;
	}
	public void setBrojTerminaInt(int brojTerminaInt) {
		this.brojTerminaInt = brojTerminaInt;
	}
	public boolean isBeskonacnoTermina() {
		return beskonacnoTermina;
	}
	public void setBeskonacnoTermina(boolean beskonacnoTermina) {
		this.beskonacnoTermina = beskonacnoTermina;
	}
	
	
	
	
}
