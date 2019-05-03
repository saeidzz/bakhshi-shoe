package com.bakhshi.Main.Model;


import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table
public class Employee  implements Serializable {
	
	
	public Employee() {
		super();
		// TODO Auto-generated constructor stub
	}



	private static final long serialVersionUID = -2032103069642593891L;
	
	
	
	@GenericGenerator(
            name = "employee-sequence",
            strategy = "enhanced-sequence",
            parameters = {
                    @org.hibernate.annotations.Parameter(name = "prefer_sequence_per_entity", value = "true"),
                    @org.hibernate.annotations.Parameter(name = "optimizer", value = "hilo"),
                    @org.hibernate.annotations.Parameter(name = "initial_value", value = "0"),
                    @org.hibernate.annotations.Parameter(name = "increment_size", value = "1")})

    @Id
    @GeneratedValue(generator = "employee-sequence")
    private long id;
    
	@Column
    private String name;
    
    @Column
    private String melliCode;
    
    @Column
    private String phoneNumber;
    
    @ManyToOne
    @JoinColumn(name = "date_id")
    private MyDate contractDate ;    
  
    
    @Column
    private double salery;;
    
    @Column
    private double advanceMoney;      
    
    @Column
    private String cardNumber;
    
    
	@OneToMany(mappedBy = "employee",cascade = CascadeType.REMOVE,fetch = FetchType.EAGER)
    List<Absent> absents;
	
	
	@OneToMany(mappedBy = "employee",cascade = CascadeType.REMOVE,fetch = FetchType.EAGER)
    List<Off> offs;

	public Employee(String name, String melliCode, String phoneNumber,
			MyDate contractDate, double salery,
			double advanceMoney, String cardNumber) {
		
		this.name = name;
		this.melliCode = melliCode;
		this.phoneNumber = phoneNumber;
		this.contractDate = contractDate;
		this.salery = salery;
		this.advanceMoney = advanceMoney;
		this.cardNumber = cardNumber;
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

	public String getMelliCode() {
		return melliCode;
	}

	public void setMelliCode(String melliCode) {
		this.melliCode = melliCode;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public MyDate getContractDate() {
		return contractDate;
	}

	public void setContractDate(MyDate contractDate) {
		this.contractDate = contractDate;
	}

	

	public double getSalery() {
		return salery;
	}

	public void setSalery(double salery) {
		this.salery = salery;
	}

	public double getAdvanceMoney() {
		return advanceMoney;
	}

	public void setAdvanceMoney(double advanceMoney) {
		this.advanceMoney = advanceMoney;
	}

	public String getCardNumber() {
		return cardNumber;
	}

	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
	}
	


	public List<Absent> getAbsents() {
		return absents;
	}

	public void setAbsents(List<Absent> absents) {
		this.absents = absents;
	}

	public List<Off> getOffs() {
		return offs;
	}

	public void setOffs(List<Off> offs) {
		this.offs = offs;
	}

	@Override
	public String toString() {
		return "کارگر [شماره=" + id + ", نام=" + name + ", کد ملی="
				+ melliCode + ", شماره تلفن=" + phoneNumber
				+ ", تاریخ  قرار داد=" + contractDate + ", مقدار حقوق=" + salery + ", مساعده="
				+ advanceMoney + ", شماره کارت=" + cardNumber + "]";
	}
    
    
    

  

  
    
    
	
}