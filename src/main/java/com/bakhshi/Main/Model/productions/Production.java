package com.bakhshi.Main.Model.productions;

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
public class Production implements Serializable {
	
	public Production() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 562343029711434234L;

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
	private String code;
	
	@Column
	private int number;
	
	@Column
	private String productor;
	
	@Column
	private String size;
	
	@ManyToOne
    @JoinColumn(name = "date_id")
	private MyDate date;

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

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public String getProductor() {
		return productor;
	}

	public void setProductor(String productor) {
		this.productor = productor;
	}

	public String getSize() {
		return size;
	}

	public void setSize(String size) {
		this.size = size;
	}

	public MyDate getDate() {
		return date;
	}

	public void setDate(MyDate date) {
		this.date = date;
	}
	
	

	public Production(String code, int number, String productor, String size,
			MyDate date) {
		super();
		this.code = code;
		this.number = number;
		this.productor = productor;
		this.size = size;
		this.date = date;
	}

	@Override
	public String toString() {
		return "Production [id=" + id + ", code=" + code + ", number=" + number
				+ ", productor=" + productor + ", size=" + size + ", date="
				+ date.toString() + "]";
	}
	
	
	

}
