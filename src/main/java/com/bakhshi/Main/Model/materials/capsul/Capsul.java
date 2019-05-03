package com.bakhshi.Main.Model.materials.capsul;

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

@Table
@Entity
public class Capsul implements Serializable {
	
	
	public Capsul() {
		super();
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = -5753109563751041515L;

	@GenericGenerator(
            name = "Capsul-sequence",
            strategy = "enhanced-sequence",
            parameters = {
                    @org.hibernate.annotations.Parameter(name = "prefer_sequence_per_entity", value = "true"),
                    @org.hibernate.annotations.Parameter(name = "optimizer", value = "hilo"),
                    @org.hibernate.annotations.Parameter(name = "initial_value", value = "0"),
                    @org.hibernate.annotations.Parameter(name = "increment_size", value = "1")})

    @Id
    @GeneratedValue(generator = "Capsul-sequence")
	private long id;
	
	
	
	@Column
	private String code;
	
	@Column
	private String size;
	
	@Column
	private int number;
	
	@OneToMany(mappedBy = "capsul",cascade = CascadeType.REMOVE,fetch = FetchType.EAGER)
    List<CapsulTransactions> transactions;
	
	

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

	

	public List<CapsulTransactions> getTransactions() {
		return transactions;
	}

	public void setTransactions(List<CapsulTransactions> transactions) {
		this.transactions = transactions;
	}

	public Capsul(String code, String size, int number) {
		super();
		this.code = code;
		this.size = size;
		this.number = number;
	}

	@Override
	public String toString() {
		return "Capsul [id=" + id + ",code=" + code
				+ ", size=" + size + ", number=" + number + "]";
	}

	
	
	
}
