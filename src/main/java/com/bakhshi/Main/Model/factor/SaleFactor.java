package com.bakhshi.Main.Model.factor;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.bakhshi.Main.Model.Customer;
import com.bakhshi.Main.Model.MyDate;

@Table
@Entity
public class SaleFactor implements Serializable {

	public SaleFactor() {
		super();
	}



	/**
	 * 
	 */
	private static final long serialVersionUID = 3193611071586607767L;
	@GenericGenerator(
            name = "SaleFactor-sequence",
            strategy = "enhanced-sequence",
            parameters = {
                    @org.hibernate.annotations.Parameter(name = "prefer_sequence_per_entity", value = "true"),
                    @org.hibernate.annotations.Parameter(name = "optimizer", value = "hilo"),
                    @org.hibernate.annotations.Parameter(name = "initial_value", value = "1000"),
                    @org.hibernate.annotations.Parameter(name = "increment_size", value = "1")})

    @Id
    @GeneratedValue(generator = "SaleFactor-sequence")
	private long id;
	
	@ManyToOne
    @JoinColumn(name = "customer_id")
	private Customer customer;
	
	@Column
	private String productName;
	
	@ManyToOne
    @JoinColumn(name = "date_id")
	private MyDate date;
	
	@Column
	private double unitPrice;
	
	@Column
	private int number;
	
	@Column
	private String size;
	
	@Column
	private String model;
	
	@Column
	private double WholePrice=unitPrice*number;
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}


	public double getUnitPrice() {
		return unitPrice;
	}

	public void setUnitPrice(double unitPrice) {
		this.unitPrice = unitPrice;
	}

	public double getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public double getWholePrice() {
		return WholePrice;
	}

	public void setWholePrice(double wholePrice) {
		WholePrice = wholePrice;
	}
	
	

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public MyDate getDate() {
		return date;
	}

	public void setDate(MyDate date) {
		this.date = date;
	}

	public String getSize() {
		return size;
	}

	public void setSize(String size) {
		this.size = size;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public SaleFactor(Customer customer, String productName, MyDate date,
			double unitPrice, int number, String size, String model ) {
		super();
		this.customer = customer;
		this.productName = productName;
		this.date = date;
		this.unitPrice = unitPrice;
		this.number = number;
		this.size = size;
		this.model = model;
		WholePrice =number*unitPrice;
	}

	@Override
	public String toString() {
		return "SaleFactor [id=" + id + ", customer=" + customer
				+ ", productName=" + productName + ", date=" + date
				+ ", unitPrice=" + unitPrice + ", number=" + number + ", size="
				+ size + ", model=" + model + ", WholePrice=" + WholePrice
				+ "]";
	}

	
}
