package com.bakhshi.Main.Model.materials.frame;

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
public class Frame implements Serializable {
	
	public Frame() {
		super();
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = -5543723413771045852L;

	@GenericGenerator(
            name = "Frame-sequence",
            strategy = "enhanced-sequence",
            parameters = {
                    @org.hibernate.annotations.Parameter(name = "prefer_sequence_per_entity", value = "true"),
                    @org.hibernate.annotations.Parameter(name = "optimizer", value = "hilo"),
                    @org.hibernate.annotations.Parameter(name = "initial_value", value = "0"),
                    @org.hibernate.annotations.Parameter(name = "increment_size", value = "1")})

    @Id
    @GeneratedValue(generator = "Frame-sequence")
    private long id;
	
	@Column
	private String code;
	
	@Column
	private String size;
	
	@Column
	private String producer;
	
	
	@Column
	private int number;
	
	
	@OneToMany(mappedBy = "frame",cascade = CascadeType.REMOVE,fetch = FetchType.EAGER)
    List<FrameTransAction> frameTransActions;
	

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
	
	public List<FrameTransAction> getFrameTransActions() {
		return frameTransActions;
	}

	public void setFrameTransActions(List<FrameTransAction> frameTransActions) {
		this.frameTransActions = frameTransActions;
	}

	public String getProducer() {
		return producer;
	}

	public void setProducer(String producer) {
		this.producer = producer;
	}

	public Frame(String code, String size, String producer, int number) {
		super();
		this.code = code;
		this.size = size;
		this.producer = producer;
		this.number = number;
	}

	@Override
	public String toString() {
		return "Frame [id=" + id + ", code=" + code + ", size=" + size
				+ ", producer=" + producer + ", number=" + number
				+ ", frameTransActions=" + frameTransActions + "]";
	}



	
	
}
