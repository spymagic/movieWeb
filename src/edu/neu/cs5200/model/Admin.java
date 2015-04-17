package edu.neu.cs5200.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.*;

/**
 * Entity implementation class for Entity: Admin
 *
 */
@Entity

public class Admin extends People  {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int adminId;
	@Temporal( TemporalType.DATE )

	private Date dateOfHire;
	public int getAdminId() {
		return adminId;
	}
	public void setAdminId(int adminId) {
		this.adminId = adminId;
	}
	public Date getDateOfHire() {
		return dateOfHire;
	}
	public void setDateOfHire(Date dateOfHire) {
		this.dateOfHire = dateOfHire;
	}
	
}
