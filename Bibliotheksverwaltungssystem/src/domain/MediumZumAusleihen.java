package domain;

import java.util.Date;

import domain.Medium.Medium;

public class MediumZumAusleihen {
	
	private Date ausleihefrist;
	private Medium medium;
	
	
	public void getMedium(Medium medium) {
		this.ausleihefrist = new Date();
		this.medium = medium;
	}


	public Date getAusleihefrist() {
		return ausleihefrist;
	}


	public void setAusleihefrist(Date ausleihefrist) {
		this.ausleihefrist = ausleihefrist;
	}


	public Medium getMedium() {
		return medium;
	}


	public void setMedium(Medium medium) {
		this.medium = medium;
	}


	@Override
	public String toString() {
		return "MediumZumAusleihen [ausleihefrist=" + ausleihefrist + ", medium=" + medium.toString() + "]";
	}
	
	
}
