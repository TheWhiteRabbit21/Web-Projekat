package beans;

import java.io.Serializable;

@SuppressWarnings("serial")
public class Trening implements Serializable {

	private String naziv;
	private String tip;
	private String sportskiObjekat;
	private Double trajanje;
	private String trener;
	private String opis;
	private String slika;
	private Integer cena;
	private boolean isDeleted;
	
	
	public Trening() {
		super();
	}
	
	public Integer getCena() {
		return cena;
	}
	public void setCena(Integer cena) {
		this.cena = cena;
	}
	public String getNaziv() {
		return naziv;
	}
	public void setNaziv(String naziv) {
		this.naziv = naziv;
	}
	public String getTip() {
		return tip;
	}
	public void setTip(String tip) {
		this.tip = tip;
	}
	public String getSportskiObjekat() {
		return sportskiObjekat;
	}
	public void setSportskiObjekat(String sportskiObjekat) {
		this.sportskiObjekat = sportskiObjekat;
	}
	public Double getTrajanje() {
		return trajanje;
	}
	public void setTrajanje(Double trajanje) {
		this.trajanje = trajanje;
	}
	public String getTrener() {
		return trener;
	}
	public void setTrener(String trener) {
		this.trener = trener;
	}
	public String getOpis() {
		return opis;
	}
	public void setOpis(String opis) {
		this.opis = opis;
	}
	public String getSlika() {
		return slika;
	}
	public void setSlika(String slika) {
		this.slika = slika;
	}

	public boolean isDeleted() {
		return isDeleted;
	}

	public void setDeleted(boolean isDeleted) {
		this.isDeleted = isDeleted;
	}
	
		
	
}
