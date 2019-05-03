package com.bakhshi.Main.Model.storeHouse;

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


@Entity
@Table
public class Property implements Serializable {

	
	public Property() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 171718867257922052L;

	@GenericGenerator(
            name = "absent-sequence",
            strategy = "enhanced-sequence",
            parameters = {
                    @org.hibernate.annotations.Parameter(name = "prefer_sequence_per_entity", value = "true"),
                    @org.hibernate.annotations.Parameter(name = "optimizer", value = "hilo"),
                    @org.hibernate.annotations.Parameter(name = "initial_value", value = "0"),
                    @org.hibernate.annotations.Parameter(name = "increment_size", value = "1")})

    @Id
    @GeneratedValue(generator = "absent-sequence")
	private long id;
	
	@Column
	private String title;
	
	@Column
	private int number;
	


	@OneToMany(mappedBy = "property",cascade = CascadeType.REMOVE,fetch = FetchType.EAGER)
    List<PropertyTransaction> transactions;
	
	public Property(String title, int number) {
		super();
		this.title = title;
		this.number = number;
		
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	
	public List<PropertyTransaction> getTransactions() {
		return transactions;
	}

	public void setTransactions(List<PropertyTransaction> transactions) {
		this.transactions = transactions;
	}

	@Override
	public String toString() {
		return "Property [id=" + id + ", title=" + title + ", number=" + number
				+ "]";
	}

	
		
	
}
