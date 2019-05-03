package com.bakhshi.Main.Model.materials.material;

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
public class MatrialTransaction implements Serializable {

	
	
	public MatrialTransaction() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = -8014020284067768758L;

	@GenericGenerator(
            name = "MatrialTransaction-sequence",
            strategy = "enhanced-sequence",
            parameters = {
                    @org.hibernate.annotations.Parameter(name = "prefer_sequence_per_entity", value = "true"),
                    @org.hibernate.annotations.Parameter(name = "optimizer", value = "hilo"),
                    @org.hibernate.annotations.Parameter(name = "initial_value", value = "0"),
                    @org.hibernate.annotations.Parameter(name = "increment_size", value = "1")})

    @Id
    @GeneratedValue(generator = "MatrialTransaction-sequence")
    private long id;
	
	@ManyToOne
	@JoinColumn(name="material_id")
	private Materials material;
	
	@ManyToOne
    @JoinColumn(name = "date_id")
	private MyDate date;

	@Column
    private int number;
    
	@Column
	private double weight;
	
	@Column
	private String by; 
	
	@Column
	private String inputOrOutput;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Materials getMaterial() {
		return material;
	}

	public void setMaterial(Materials material) {
		this.material = material;
	}

	public MyDate getDate() {
		return date;
	}

	public void setDate(MyDate date) {
		this.date = date;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public double getWeight() {
		return weight;
	}

	public void setWeight(double weight) {
		this.weight = weight;
	}

	public String getBy() {
		return by;
	}

	public void setBy(String by) {
		this.by = by;
	}

	public String getInputOrOutput() {
		return inputOrOutput;
	}

	public void setInputOrOutput(String inputOrOutput) {
		this.inputOrOutput = inputOrOutput;
	}

	public MatrialTransaction(Materials material, MyDate date, 
			int number, double weight, String by,
			String inputOrOutput) {
		super();
		this.material = material;
		this.date = date;
		this.number = number;
		this.weight = weight;
		this.by = by;
		this.inputOrOutput = inputOrOutput;
	}

	@Override
	public String toString() {
		return "MatrialTransaction [id=" + id + ", material=" + material
				+ ", date=" + date.toString() +  ", number=" + number
				+ ", weight=" + weight + ", by=" + by
				+ ", inputOrOutput=" + inputOrOutput + "]";
	}
	
	

}
