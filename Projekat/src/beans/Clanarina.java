package beans;

import java.io.Serializable;
import java.util.Calendar;

import enums.Status;

@SuppressWarnings("serial")
public class Clanarina implements Serializable{

	private int id;
	private String tip;
	private Calendar datumPlacanja;
	private Calendar datumIVremeIsteka;
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
	public Clanarina() {
		super();
	}
	
	
	public Clanarina(int tempId, String tip, Calendar datumPlacanja, Calendar datumIsteka, 
			String cena, String username, Status status, String brojTermina) {
		super();
		this.id = tempId;
		this.tip = tip;
		this.datumPlacanja = datumPlacanja;
		this.datumIVremeIsteka = datumIsteka;
		this.cena = cena;
		this.kupac = username;
		this.status = status;
		this.brojTermina = brojTermina;
		if(brojTermina.equals("neograniceno"))
		{
			this.brojTerminaInt = -1;
		}else {
			this.brojTerminaInt = Integer.parseInt(brojTermina);			
		}
			
		
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
	public Calendar getDatumPlacanja() {
		return datumPlacanja;
	}
	public void setDatumPlacanja(Calendar datumPlacanja) {
		this.datumPlacanja = datumPlacanja;
	}
	public Calendar getDatumIVremeIsteka() {
		return datumIVremeIsteka;
	}
	public void setDatumIVremeIsteka(Calendar datumIVremeIsteka) {
		this.datumIVremeIsteka = datumIVremeIsteka;
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