package domain.AusleiheSystem;

import java.text.SimpleDateFormat;
import java.util.Date;

import domain.Medium.Medium;

public class MediumZumAusleihen {
	
	private Date ausleihefrist;
	private SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
	private String formattedDate;
	private Medium medium;
	private boolean verlängerbar;
	
	public MediumZumAusleihen(boolean verlängerbar, Medium medium) {
		this.ausleihefrist = new Date();
		formattedDate = formatter.format(this.ausleihefrist);
		this.verlängerbar = verlängerbar;
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
		return "MediumZumAusleihen [ausleihefrist=" + formattedDate  + ", verlängerbar="
				+ verlängerbar  +  ", medium=" + medium +  "]";
	}

}
