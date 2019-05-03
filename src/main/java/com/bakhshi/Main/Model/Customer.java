package com.bakhshi.Main.Model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.bakhshi.Main.Model.factor.EVASaleFactor;
import com.bakhshi.Main.Model.factor.PVCSaleFactor;
import com.bakhshi.Main.Model.factor.SaleFactor;

@Entity
@Table
public class Customer implements Serializable {

	
	public Customer() {
		super();
		// TODO Auto-generated constructor stub
	}
	private static final long serialVersionUID = 5932063459198776099L;
	@GenericGenerator(
            name = "customer-sequence",
            strategy = "enhanced-sequence",
            parameters = {
                    @org.hibernate.annotations.Parameter(name = "prefer_sequence_per_entity", value = "true"),
                    @org.hibernate.annotations.Parameter(name = "optimizer", value = "hilo"),
                    @org.hibernate.annotations.Parameter(name = "initial_value", value = "0"),
                    @org.hibernate.annotations.Parameter(name = "increment_size", value = "1")})

    @Id
    @GeneratedValue(generator = "customer-sequence")
    private long id;
	@Column
	private String name;
	@Column
	private String address;
	@Column
	private String phoneNumber;
	
	@OneToMany(mappedBy = "date",cascade = CascadeType.REMOVE,fetch = FetchType.EAGER)
    private List<EVASaleFactor> evaFactors;
	
	@OneToMany(mappedBy = "date",cascade = CascadeType.REMOVE,fetch = FetchType.EAGER)
    private List<PVCSaleFactor> pvcFactors;
	
	@OneToMany(mappedBy = "date",cascade = CascadeType.REMOVE,fetch = FetchType.EAGER)
    private List<SaleFactor> saleFactors;
	
	
	
	public Customer(String name, String address, String phoneNumber) {
		super();
		this.name = name;
		this.address = address;
		this.phoneNumber = phoneNumber;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	
	public List<EVASaleFactor> getEvaFactors() {
		return evaFactors;
	}
	public void setEvaFactors(List<EVASaleFactor> evaFactors) {
		this.evaFactors = evaFactors;
	}
	public List<PVCSaleFactor> getPvcFactors() {
		return pvcFactors;
	}
	public void setPvcFactors(List<PVCSaleFactor> pvcFactors) {
		this.pvcFactors = pvcFactors;
	}
	public List<SaleFactor> getSaleFactors() {
		return saleFactors;
	}
	public void setSaleFactors(List<SaleFactor> saleFactors) {
		this.saleFactors = saleFactors;
	}
	@Override
	public String toString() {
		return "Customer [id=" + id + ", name=" + name + ", address=" + address
				+ ", phoneNumber=" + phoneNumber + "]";
	}
	
	
	
	
	
}
