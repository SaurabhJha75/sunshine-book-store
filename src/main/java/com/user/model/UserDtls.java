package com.user.model;
 
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
 
@Entity
public class UserDtls {
 
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
 
	private String fullname;
 
	private String address;
 
	private String email;
 
	private String qualification;
 
	private String password;
	private String role;
	private String mobileNumber;
 
	public int getId() {
		return id;
	}
 
	public void setId(int id) {
		this.id = id;
	}
 
	public String getMobileNumber() {
		return mobileNumber;
	}
 
	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}
 
	public String getFullname() {
		return fullname;
	}
 
	public void setFullname(String fullname) {
		this.fullname = fullname;
	}
 
	public String getAddress() {
		return address;
	}
 
	public void setAddress(String address) {
		this.address = address;
	}
 
	public String getEmail() {
		return email;
	}
 
	public void setEmail(String email) {
		this.email = email;
	}
 
	public String getQualification() {
		return qualification;
	}
 
	public void setQualification(String qualification) {
		this.qualification = qualification;
	}
 
	public String getPassword() {
		return password;
	}
 
	public void setPassword(String password) {
		this.password = password;
	}
 
	public String getRole() {
		return role;
	}
 
	public void setRole(String role) {
		this.role = role;
	}
 
	
	@Override
	public String toString() {
		return "UserDtls [id=" + id + ", fullname=" + fullname + ", address=" + address + ", email=" + email
				+ ", qualification=" + qualification + ", password=" + password + ", role=" + role + ", mobileNumber="
				+ mobileNumber + "]";
	}
 
	
}