package beans;

public class SportskiObjekat {

	private String naziv;
	private String tipObjekta;
	private String sadrzaj;	//grupni/personalni treninzi, sauna itd.
	private String status;	//radi/ne radi
	private String mapa;	//hardcode/lokacija
	private String logo;	//hardcode
	private double prosecnaOcena;
	private String radnoVreme;
	
	public SportskiObjekat(String naziv, String tipObjekta, String sadrzaj, String status, String mapa,
			String logo, double prosOcena, String radnoVreme) {
		this.naziv = naziv;
		this.tipObjekta = tipObjekta;
		this.sadrzaj = sadrzaj;
		this.status = status;
		this.mapa = mapa;
		this.logo = logo;
		this.prosecnaOcena = prosOcena;
		this.radnoVreme = radnoVreme;
	}
	
	public SportskiObjekat(String naziv, String tipObjekta) {
		super();
		this.naziv = naziv;
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
	
}
