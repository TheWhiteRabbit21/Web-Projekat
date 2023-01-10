package beans;

import java.util.ArrayList;
import java.util.Collection;

import enums.Tip;

@SuppressWarnings("serial")
public class Kupac extends Korisnik {

	
	private Collection<Integer> istorijaTreningaIds;
	private double brojSakupljenihBodova;
	private int idClanarine;
	private TipKupca tipKupca;
	
	public Kupac() {
		super();
	}
	
	public Kupac(Korisnik km) {
		super(km);
		this.istorijaTreningaIds = new ArrayList<Integer>();
		this.brojSakupljenihBodova = 0;
		this.idClanarine = 0;
		this.tipKupca = new TipKupca(Tip.BRONZANI, 0.0);
	}

	public Collection<Integer> getIstorijaTreningaIds() {
		return istorijaTreningaIds;
	}

	public void setIstorijaTreningaIds(Collection<Integer> istorijaTreningaIds) {
		this.istorijaTreningaIds = istorijaTreningaIds;
	}

	public double getBrojSakupljenihBodova() {
		return brojSakupljenihBodova;
	}

	public void setBrojSakupljenihBodova(double brojSakupljenihBodova) {
		this.brojSakupljenihBodova = brojSakupljenihBodova;
	}
	
	public int getIdClanarine() {
		return idClanarine;
	}

	public void setIdClanarine(int idClanarine) {
		this.idClanarine = idClanarine;
	}

	public TipKupca getTipKupca() {
		return tipKupca;
	}

	public void setTipKupca(TipKupca tipKupca) {
		this.tipKupca = tipKupca;
	}

	
	
	
}
