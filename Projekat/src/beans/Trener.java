package beans;

import java.util.ArrayList;
import java.util.Collection;

@SuppressWarnings("serial")
public class Trener extends Korisnik{

	Collection<IstorijaTreninga> istorijaTreninga;

	public Trener() {
		super();
	}
	
	
	
	
	public Trener(Korisnik km) {
		super(km);
		this.istorijaTreninga = new ArrayList<IstorijaTreninga>();
	}
	
	public Trener(Trener t) {
		
	}

	public Collection<IstorijaTreninga> getIstorijaTreninga() {
		return istorijaTreninga;
	}

	public void setIstorijaTreninga(Collection<IstorijaTreninga> istorijaTreninga) {
		this.istorijaTreninga = istorijaTreninga;
	}
	
}
