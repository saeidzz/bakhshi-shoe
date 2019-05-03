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
public class PVCSaleFactor implements Serializable {
	
	public PVCSaleFactor() {
		super();
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 3131577159923672119L;

	@GenericGenerator(
            name = "PVCSaleFactor-sequence",
            strategy = "enhanced-sequence",
            parameters = {
                    @org.hibernate.annotations.Parameter(name = "prefer_sequence_per_entity", value = "true"),
                    @org.hibernate.annotations.Parameter(name = "optimizer", value = "hilo"),
                    @org.hibernate.annotations.Parameter(name = "initial_value", value = "1000"),
                    @org.hibernate.annotations.Parameter(name = "increment_size", value = "1")})

    @Id
    @GeneratedValue(generator = "PVCSaleFactor-sequence")
	private long id;
	
	@ManyToOne
    @JoinColumn(name = "customer_id")
	private Customer customer;
	
	@ManyToOne
    @JoinColumn(name = "date_id")
	private MyDate date;
	
	@Column
	private String productModel;
	
	@Column
	private double unitPrice;
	
	@Column
	private int number;
	
	@Column
	private String size;
	
	
	
	
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

	public String getProductModel() {
		return productModel;
	}

	public void setProductModel(String productModel) {
		this.productModel = productModel;
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


	public PVCSaleFactor(Customer customer, MyDate date, String productModel,
			double unitPrice, int number, String size) {
		super();
		this.customer = customer;
		this.date = date;
		this.productModel = productModel;
		this.unitPrice = unitPrice;
		this.number = number;
		this.size = size;
		WholePrice = number*unitPrice;
	}

	@Override
	public String toString() {
		return "PVCSaleFactor [id=" + id + ", customer=" + customer + ", date="
				+ date + ", productModel=" + productModel + ", unitPrice="
				+ unitPrice + ", number=" + number + ", size=" + size
				+ ", WholePrice=" + WholePrice + "]";
	}

	
	
	

	
	
	
}
