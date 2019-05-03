package com.bakhshi.Main.Model.materials.material;

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
public class Materials implements Serializable {
	
	public Materials() {
		super();
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = -4212901162951222258L;
	@GenericGenerator(
            name = "MaterialsList-sequence",
            strategy = "enhanced-sequence",
            parameters = {
                    @org.hibernate.annotations.Parameter(name = "prefer_sequence_per_entity", value = "true"),
                    @org.hibernate.annotations.Parameter(name = "optimizer", value = "hilo"),
                    @org.hibernate.annotations.Parameter(name = "initial_value", value = "0"),
                    @org.hibernate.annotations.Parameter(name = "increment_size", value = "1")})

    @Id
    @GeneratedValue(generator = "MaterialsList-sequence")
	private long id;
	
	@Column
	private String type;
	
	@Column
    private int number;
    
	@Column
	private double weight;
	
	@Column
	private String code;
	

	
	
	@OneToMany(mappedBy = "material",cascade = CascadeType.REMOVE,fetch = FetchType.EAGER)
    List<MatrialTransaction> matrialTransactions;
	
	
	

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
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

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	

	public List<MatrialTransaction> getMatrialTransactions() {
		return matrialTransactions;
	}

	public void setMatrialTransactions(List<MatrialTransaction> matrialTransactions) {
		this.matrialTransactions = matrialTransactions;
	}

	public Materials(  int number, double weight,
			String code,String type) {
		super();
		this.type =type ;
		this.number = number;
		this.weight = weight;
		this.code = code;
	}

	@Override
	public String toString() {
		return "MaterialsList [id=" + id + ", type=" + type
				+ ", number=" + number + ", weight=" + weight + ", code="
				+ code + "]";
	}

	

}
