package ctdDemo.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Bank {
	private @Id @GeneratedValue Long id;
	private String name;
	private String details;
	private String address;
	
	private Bank() {
		
	}
	public Bank(String name, String details, String address) {
		super();
		this.name = name;
		this.details = details;
		this.address = address;
		
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDetails() {
		return details;
	}
	public void setDetails(String details) {
		this.details = details;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	
}
