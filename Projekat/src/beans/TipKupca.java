package beans;

import java.io.Serializable;

import enums.Tip;

@SuppressWarnings("serial")
public class TipKupca implements Serializable{

	private Tip tip;
	private double procenatPopust;
	
	public TipKupca() {
		super();
	}
	
	public TipKupca(Tip tip, double popust) {
		this.tip = tip;
		this.procenatPopust = popust;
	}
	
	public Tip getTip() {
		return tip;
	}
	public void setTip(Tip tip) {
		this.tip = tip;
	}
	public double getProcenatPopust() {
		return procenatPopust;
	}
	public void setProcenatPopust(double procenatPopust) {
		this.procenatPopust = procenatPopust;
	}
	
}
