package com.bakhshi.Main.Model.storeHouse.storedShoeSole;

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
public class StoredShoeSole implements Serializable {

	
	public StoredShoeSole() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = -3307306967737400246L;
	/**
	 * 
	 */
	@GenericGenerator(name = "StoredShoeSole-sequence", strategy = "enhanced-sequence", parameters = {
			@org.hibernate.annotations.Parameter(name = "prefer_sequence_per_entity", value = "true"),
			@org.hibernate.annotations.Parameter(name = "optimizer", value = "hilo"),
			@org.hibernate.annotations.Parameter(name = "initial_value", value = "0"),
			@org.hibernate.annotations.Parameter(name = "increment_size", value = "1") })
	@Id
	@GeneratedValue(generator = "StoredShoeSole-sequence")
	private long id;

	@Column
	private String code;
	@Column
	private String size;
	@Column
	private int number;
	@Column
	private String color;
	
	@OneToMany(mappedBy = "storedShoeSole",cascade = CascadeType.REMOVE,fetch = FetchType.EAGER)
    List<StoredShoeSoleTransaction> transactions;
	

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getSize() {
		return size;
	}

	public void setSize(String size) {
		this.size = size;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public List<StoredShoeSoleTransaction> getTransactions() {
		return transactions;
	}

	public void setTransactions(List<StoredShoeSoleTransaction> transactions) {
		this.transactions = transactions;
	}

	public StoredShoeSole(String code, String size, int number, String color) {
		super();
		this.code = code;
		this.size = size;
		this.number = number;
		this.color = color;
	}

	@Override
	public String toString() {
		return "StoredShoeSole [id=" + id + ", code=" + code + ", size=" + size
				+ ", number=" + number + ", color=" + color + "]";
	}
	
	

}
