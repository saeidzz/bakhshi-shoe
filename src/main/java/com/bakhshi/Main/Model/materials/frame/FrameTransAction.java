package com.bakhshi.Main.Model.materials.frame;

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
public class FrameTransAction implements Serializable {

	public FrameTransAction() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 6258337372400656592L;
	
	@GenericGenerator(
            name = "FrameTransAction-sequence",
            strategy = "enhanced-sequence",
            parameters = {
                    @org.hibernate.annotations.Parameter(name = "prefer_sequence_per_entity", value = "true"),
                    @org.hibernate.annotations.Parameter(name = "optimizer", value = "hilo"),
                    @org.hibernate.annotations.Parameter(name = "initial_value", value = "0"),
                    @org.hibernate.annotations.Parameter(name = "increment_size", value = "1")})

    @Id
    @GeneratedValue(generator = "FrameTransAction-sequence")
	private long id;
	@ManyToOne
    @JoinColumn(name = "date_id")
    private MyDate date ;

	@ManyToOne
	@JoinColumn(name="frame_id")
	private Frame frame;
	
	@Column
	private int number;
	
	@Column
	private String inputOrOutput;

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

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}



	public String getInputOrOutput() {
		return inputOrOutput;
	}

	public void setInputOrOutput(String inputOrOutput) {
		this.inputOrOutput = inputOrOutput;
	}
	
	

	public Frame getFrame() {
		return frame;
	}

	public void setFrame(Frame frame) {
		this.frame = frame;
	}

	public FrameTransAction(MyDate date, Frame frame, int number,
		String inputOrOutput) {
		super();
		this.date = date;
		this.frame = frame;
		this.number = number;
		this.inputOrOutput = inputOrOutput;
	}

	@Override
	public String toString() {
		return "FrameTransAction [id=" + id + ", date=" + date.toString() + ", frame="
				+ frame + ", number=" + number + ", inputOrOutput=" + inputOrOutput + "]";
	}

	

}
