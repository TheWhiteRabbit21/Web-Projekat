package beans;

@SuppressWarnings("serial")
public class Menadzer extends Korisnik{

	private String sportskiObjekat;
	
	public Menadzer() {
		super();
	}
	
	public Menadzer(Korisnik km) {
		
		super(km);
		this.sportskiObjekat = "";
	}
	
	public Menadzer(Menadzer m) {
		
	}
	
	public Menadzer(Korisnik km, String so) {
		
		super(km);
		this.sportskiObjekat = so;
	}

	public String getSportskiObjekat() {
		return sportskiObjekat;
	}

	public void setSportskiObjekat(String sportskiObjekat) {
		this.sportskiObjekat = sportskiObjekat;
	}
	
	
}
