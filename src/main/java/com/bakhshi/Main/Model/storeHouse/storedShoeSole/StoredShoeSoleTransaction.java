package com.bakhshi.Main.Model.storeHouse.storedShoeSole;

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
public class StoredShoeSoleTransaction implements Serializable {

	public StoredShoeSoleTransaction() {
		super();
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = -8862187721785899385L;

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
    @JoinColumn(name = "storedShoeSole_id")
	private StoredShoeSole storedShoeSole;
	
	@ManyToOne
    @JoinColumn(name = "date_id")
	private MyDate date;
	
	@Column
	private String inOrOut;
	
	@Column
	private int number;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public StoredShoeSole getStoredShoeSole() {
		return storedShoeSole;
	}

	public void setStoredShoeSole(StoredShoeSole storedShoeSole) {
		this.storedShoeSole = storedShoeSole;
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

	public StoredShoeSoleTransaction(StoredShoeSole storedShoeSole,
			MyDate date, String inOrOut, int number) {
		super();
		this.storedShoeSole = storedShoeSole;
		this.date = date;
		this.inOrOut = inOrOut;
		this.number = number;
	}

	@Override
	public String toString() {
		return "StoredShoeSoleTransactionDao [id=" + id + ", storedShoeSole="
				+ storedShoeSole + ", date=" + date.toString() + ", inOrOut=" + inOrOut
				+ ", number=" + number + "]";
	}
	
	
	

}
