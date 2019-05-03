package com.bakhshi.Main.Model.materials.shoeSoles;

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

@Table
@Entity
public class ShoeSoles implements Serializable {

	public ShoeSoles() {
		super();
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = -5813046714383752079L;
	@GenericGenerator(
            name = "ShoeSoles-sequence",
            strategy = "enhanced-sequence",
            parameters = {
                    @org.hibernate.annotations.Parameter(name = "prefer_sequence_per_entity", value = "true"),
                    @org.hibernate.annotations.Parameter(name = "optimizer", value = "hilo"),
                    @org.hibernate.annotations.Parameter(name = "initial_value", value = "0"),
                    @org.hibernate.annotations.Parameter(name = "increment_size", value = "1")})

    @Id
    @GeneratedValue(generator = "ShoeSoles-sequence")
	private long id;
	
	@ManyToOne
    @JoinColumn(name = "date_id")
    private MyDate date;
	
	@Column
	private String code;
	
	@Column
	private String size;
	
	@Column
	private int number;
	
	@Column
	private double unitPrice;
	
	@Column
	private double wholePrice=number*unitPrice;
	
	@Column
    private String factorNumber;

	
	@Column
    private String savedBy;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public MyDate getDate() {
		return date;
	}

	public void setDate(MyDate date) {
		this.date = date;
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

	public int  getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public double getUnitPrice() {
		return unitPrice;
	}

	public void setUnitPrice(double unitPrice) {
		this.unitPrice = unitPrice;
	}

	public double getWholePrice() {
		return wholePrice;
	}

	public void setWholePrice(double wholePrice) {
		this.wholePrice = wholePrice;
	}

	public String getSavedBy() {
		return savedBy;
	}

	public void setSavedBy(String savedBy) {
		this.savedBy = savedBy;
	}
	
	

	public String getFactorNumber() {
		return factorNumber;
	}

	public void setFactorNumber(String factorNumber) {
		this.factorNumber = factorNumber;
	}

	public ShoeSoles(MyDate date, String code, String size, int number,
			double unitPrice, String savedBy,String factorNumber) {
		super();
		this.date = date;
		this.code = code;
		this.size = size;
		this.number = number;
		this.unitPrice = unitPrice;
		this.wholePrice = number*unitPrice;
		this.savedBy = savedBy;
		this.factorNumber=factorNumber;
	}

	@Override
	public String toString() {
		return "ShoeSoles [id=" + id + ", date=" + date + ", code=" + code
				+ ", size=" + size + ", number=" + number + ", unitPrice="
				+ unitPrice + ", wholePrice=" + wholePrice + ", factorNumber="
				+ factorNumber + ", savedBy=" + savedBy + "]";
	}


	

}
