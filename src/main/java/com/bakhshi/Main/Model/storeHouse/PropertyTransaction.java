package com.bakhshi.Main.Model.storeHouse;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.bakhshi.Main.Model.MyDate;

@Entity
@Table
public class PropertyTransaction implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 2883336197396252695L;

	@GenericGenerator(
            name = "PropertyTransaction-sequence",
            strategy = "enhanced-sequence",
            parameters = {
                    @org.hibernate.annotations.Parameter(name = "prefer_sequence_per_entity", value = "true"),
                    @org.hibernate.annotations.Parameter(name = "optimizer", value = "hilo"),
                    @org.hibernate.annotations.Parameter(name = "initial_value", value = "0"),
                    @org.hibernate.annotations.Parameter(name = "increment_size", value = "1")})

    @Id
    @GeneratedValue(generator = "PropertyTransaction-sequence")
    private long id;
	
	@ManyToOne
    @JoinColumn(name = "property_id")
	private Property property;
	
	@ManyToOne
    @JoinColumn(name = "date_id")
	private MyDate date;
	
	@Column
	private String transactor;
	
	@Column
	private String inOrOut;
	
	private int number;

	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Property getProperty() {
		return property;
	}

	public void setProperty(Property property) {
		this.property = property;
	}

	public String getTransactor() {
		return transactor;
	}

	public void setTransactor(String transactor) {
		this.transactor = transactor;
	}

	public MyDate getDate() {
		return date;
	}

	public void setDate(MyDate date) {
		this.date = date;
	}

	public String getInOrOut() {
		return inOrOut;
	}

	public void setInOrOut(String inOrOut) {
		this.inOrOut = inOrOut;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public PropertyTransaction(Property property, MyDate date,
			String transactor, String inOrOut, int number) {
		super();
		this.property = property;
		this.date = date;
		this.transactor = transactor;
		this.inOrOut = inOrOut;
		this.number = number;
	}

	@Override
	public String toString() {
		return "PropertyTransaction [id=" + id + ", property=" + property
				+ ", date=" + date.toString() + ", transactor=" + transactor
				+ ", inOrOut=" + inOrOut + ", number=" + number + "]";
	}

	public PropertyTransaction() {
		super();
		// TODO Auto-generated constructor stub
	}

   
	
	
	
	
	
	
}
