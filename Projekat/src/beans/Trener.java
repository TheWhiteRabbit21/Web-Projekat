package beans;

import java.util.ArrayList;
import java.util.Collection;

@SuppressWarnings("serial")
public class Trener extends Korisnik{

	Collection<Integer> istorijaTreningaIds;

	public Trener() {
		super();
	}
	
	public Trener(Korisnik km) {
		super(km);
		this.istorijaTreningaIds = new ArrayList<Integer>();
	}
	
	public Trener(Trener t) {
		
	}

	public Collection<Integer> getIstorijaTreningaIds() {
		return istorijaTreningaIds;
	}

	public void setIstorijaTreningaIds(Collection<Integer> istorijaTreningaIds) {
		this.istorijaTreningaIds = istorijaTreningaIds;
	}
	
}
