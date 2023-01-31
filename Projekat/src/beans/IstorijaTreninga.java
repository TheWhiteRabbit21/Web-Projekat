package beans;

import java.util.Date;

public class IstorijaTreninga {

	int id;
	Date datumIVremePrijave;
	String datumIVremePrijaveString;
	String sportskiObjekat;
	String trening;
	String kupac;
	String trener;
	
	
	public IstorijaTreninga() {
		super();
	}
	
	@SuppressWarnings("deprecation")
	public IstorijaTreninga(int id, Date currentDate, String sportskiObjekat, String trening, String kupac, String trener) {

		this.id = id;
		this.datumIVremePrijave = currentDate;
		this.datumIVremePrijaveString = datumIVremePrijave.toLocaleString();
		this.sportskiObjekat = sportskiObjekat;
		this.trening = trening;
		this.kupac = kupac;
		this.trener = trener;
		
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getDatumIVremePrijave() {
		return datumIVremePrijave;
	}

	public void setDatumIVremePrijave(Date datumIVremePrijave) {
		this.datumIVremePrijave = datumIVremePrijave;
	}

	public String getDatumIVremePrijaveString() {
		return datumIVremePrijaveString;
	}

	public void setDatumIVremePrijaveString(String datumIVremePrijaveString) {
		this.datumIVremePrijaveString = datumIVremePrijaveString;
	}

	public String getSportskiObjekat() {
		return sportskiObjekat;
	}

	public void setSportskiObjekat(String sportskiObjekat) {
		this.sportskiObjekat = sportskiObjekat;
	}

	public String getTrening() {
		return trening;
	}

	public void setTrening(String trening) {
		this.trening = trening;
	}

	public String getKupac() {
		return kupac;
	}

	public void setKupac(String kupac) {
		this.kupac = kupac;
	}

	public String getTrener() {
		return trener;
	}

	public void setTrener(String trener) {
		this.trener = trener;
	}
	
	
	
	
	
}
