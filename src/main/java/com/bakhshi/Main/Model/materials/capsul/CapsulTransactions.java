package com.bakhshi.Main.Model.materials.capsul;

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
public class CapsulTransactions implements Serializable{

	public CapsulTransactions() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = -4866668405413581165L;

	@GenericGenerator(
            name = "CapsulTransactions-sequence",
            strategy = "enhanced-sequence",
            parameters = {
                    @org.hibernate.annotations.Parameter(name = "prefer_sequence_per_entity", value = "true"),
                    @org.hibernate.annotations.Parameter(name = "optimizer", value = "hilo"),
                    @org.hibernate.annotations.Parameter(name = "initial_value", value = "0"),
                    @org.hibernate.annotations.Parameter(name = "increment_size", value = "1")})

    @Id
    @GeneratedValue(generator = "CapsulTransactions-sequence")
    private long id;
	
	@ManyToOne
	@JoinColumn(name="capsul_id")
	private Capsul capsul;
	
	@ManyToOne
    @JoinColumn(name = "date_id")
    private MyDate date;
	
	@Column
	private String delivery;
	
	@Column
	private String tranferee;
	
	@Column
	private String inputOrOutput;
	
	@Column
	private int number;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Capsul getCapsul() {
		return capsul;
	}

	public void setCapsul(Capsul capsul) {
		this.capsul = capsul;
	}

	public MyDate getDate() {
		return date;
	}

	public void setDate(MyDate date) {
		this.date = date;
	}

	public String getDelivery() {
		return delivery;
	}

	public void setDelivery(String delivery) {
		this.delivery = delivery;
	}

	public String getTranferee() {
		return tranferee;
	}

	public void setTranferee(String tranferee) {
		this.tranferee = tranferee;
	}

	public String getInputOrOutput() {
		return inputOrOutput;
	}

	public void setInputOrOutput(String inputOrOutput) {
		this.inputOrOutput = inputOrOutput;
	}

	
	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public CapsulTransactions(Capsul capsul, MyDate date, String delivery,
			String tranferee, String inputOrOutput,int number) {
		super();
		this.capsul = capsul;
		this.date = date;
		this.delivery = delivery;
		this.tranferee = tranferee;
		this.inputOrOutput = inputOrOutput;
		this.number=number;
	}

	@Override
	public String toString() {
		return "CapsulTransactions [id=" + id + ", capsul=" + capsul
				+ ", date=" + date.toString() + ", delivery=" + delivery + ", tranferee="
				+ tranferee + ", inputOrOutput=" + inputOrOutput + ", number="
				+ number + "]";
	}

	
	

}
