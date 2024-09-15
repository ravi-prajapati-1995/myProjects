package com.ravi.test.domain;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "pincode")
public class PinCode implements Serializable{
	
  /**
	 * 
	 */
	private static final long serialVersionUID = 123456l;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;

  @Column(name = "Postofficename", length = 50)
  private String postOfficeName;

  @Column(name = "Pincode")
  private int pincode;

  @Column(name = "District", length = 50)
  private String district;

  @Column(name = "City", length = 50)
  private String city;

  @Column(name = "State", length = 50)
  private String state;

@Override
public String toString() {
	return "PinCode [id=" + id + ", postOfficeName=" + postOfficeName + ", pincode=" + pincode + ", district="
			+ district + ", city=" + city + ", state=" + state + "]";
}

public int getId() {
	return id;
}

public void setId(int id) {
	this.id = id;
}

public String getPostOfficeName() {
	return postOfficeName;
}

public void setPostOfficeName(String postOfficeName) {
	this.postOfficeName = postOfficeName;
}

public int getPincode() {
	return pincode;
}

public void setPincode(int pincode) {
	this.pincode = pincode;
}

public String getDistrict() {
	return district;
}

public void setDistrict(String district) {
	this.district = district;
}

public String getCity() {
	return city;
}

public void setCity(String city) {
	this.city = city;
}

public String getState() {
	return state;
}

public void setState(String state) {
	this.state = state;
}

public static long getSerialversionuid() {
	return serialVersionUID;
}
 
}
