package com.bakhshi.Main.Model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
@Entity
@Table
public class Off implements Serializable {

	
	public Off() {
		super();
		// TODO Auto-generated constructor stub
	}

	private static final long serialVersionUID = -8951467382067511197L;

//مرخصی
	@GenericGenerator(
            name = "off-sequence",
            strategy = "enhanced-sequence",
            parameters = {
                    @org.hibernate.annotations.Parameter(name = "prefer_sequence_per_entity", value = "true"),
                    @org.hibernate.annotations.Parameter(name = "optimizer", value = "hilo"),
                    @org.hibernate.annotations.Parameter(name = "initial_value", value = "0"),
                    @org.hibernate.annotations.Parameter(name = "increment_size", value = "1")})

    @Id
    @GeneratedValue(generator = "off-sequence")
	private long id;
	
	@ManyToOne
    @JoinColumn(name = "date_id")
	private MyDate date;
	
	@ManyToOne
	@JoinColumn(name="employee_id")
	private Employee employee;

	
	
	public Off(MyDate offDay, Employee employee) {
		super();
		this.date = offDay;
		this.employee = employee;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public MyDate getOffDay() {
		return date;
	}

	public void setOffDay(MyDate offDay) {
		this.date = offDay;
	}

	public MyDate getDate() {
		return date;
	}

	public void setDate(MyDate date) {
		this.date = date;
	}

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	

		
	
	

}
