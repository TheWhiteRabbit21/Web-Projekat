package beans;

import java.io.Serializable;

import enums.Tip;

@SuppressWarnings("serial")
public class TipKupca implements Serializable{

	private Tip tip;
	private double procenatPopust;
	private int trazeniBrojBodovaZaSledeciStatus;
	
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
	public int getTrazeniBrojBodovaZaSledeciStatus() {
		return trazeniBrojBodovaZaSledeciStatus;
	}
	public void setTrazeniBrojBodovaZaSledeciStatus(int trazeniBrojBodovaZaSledeciStatus) {
		this.trazeniBrojBodovaZaSledeciStatus = trazeniBrojBodovaZaSledeciStatus;
	}
	
	
	
	
	
	
	
}
