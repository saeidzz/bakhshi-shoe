package com.bakhshi.Main.Model;

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

import com.bakhshi.Main.Model.factor.EVASaleFactor;
import com.bakhshi.Main.Model.factor.PVCSaleFactor;
import com.bakhshi.Main.Model.factor.SaleFactor;
import com.bakhshi.Main.Model.materials.capsul.CapsulTransactions;
import com.bakhshi.Main.Model.materials.frame.FrameTransAction;
import com.bakhshi.Main.Model.materials.material.MatrialTransaction;
import com.bakhshi.Main.Model.materials.shoeSoles.ShoeSoles;
import com.bakhshi.Main.Model.productions.Production;
import com.bakhshi.Main.Model.storeHouse.PropertyTransaction;
import com.bakhshi.Main.Model.storeHouse.storedShoeSole.StoredShoeSoleTransaction;

@Entity
@Table
public class MyDate implements Serializable {
	



	private static final long serialVersionUID = -2710289598955165869L;
	@GenericGenerator(
            name = "mydate-sequence",
            strategy = "enhanced-sequence",
            parameters = {
                    @org.hibernate.annotations.Parameter(name = "prefer_sequence_per_entity", value = "true"),
                    @org.hibernate.annotations.Parameter(name = "optimizer", value = "hilo"),
                    @org.hibernate.annotations.Parameter(name = "initial_value", value = "0"),
                    @org.hibernate.annotations.Parameter(name = "increment_size", value = "1")})

    @Id
    @GeneratedValue(generator = "mydate-sequence")
	private long id;
	@Column
	private int year;
	@Column
	private int Month;
	@Column
	private int day;
	
	@OneToMany(mappedBy = "date",cascade = CascadeType.REMOVE,fetch = FetchType.EAGER)
    private List<EVASaleFactor> evaFactors;
	
	@OneToMany(mappedBy = "date",cascade = CascadeType.REMOVE,fetch = FetchType.EAGER)
    private List<PVCSaleFactor> pvcFactors;
	
	@OneToMany(mappedBy = "date",cascade = CascadeType.REMOVE,fetch = FetchType.EAGER)
    private List<SaleFactor> saleFactors;
	
	@OneToMany(mappedBy = "date",cascade = CascadeType.REMOVE,fetch = FetchType.EAGER)
    private List<CapsulTransactions> capsulTransactions;
	
	@OneToMany(mappedBy = "date",cascade = CascadeType.REMOVE,fetch = FetchType.EAGER)
    private List<FrameTransAction> frameTransActions;
	
	@OneToMany(mappedBy = "date",cascade = CascadeType.REMOVE,fetch = FetchType.EAGER)
    private List<StoredShoeSoleTransaction> shoeSoleTransactions;
	
	
	@OneToMany(mappedBy = "date",cascade = CascadeType.REMOVE,fetch = FetchType.EAGER)
    private List<MatrialTransaction> materials;
	
	@OneToMany(mappedBy = "date",cascade = CascadeType.REMOVE,fetch = FetchType.EAGER)
    private List<ShoeSoles> showSoles;
	
	@OneToMany(mappedBy = "date",cascade = CascadeType.REMOVE,fetch = FetchType.EAGER)
    private List<Production> productions;
	

	@OneToMany(mappedBy = "date",cascade = CascadeType.REMOVE,fetch = FetchType.EAGER)
    private List<PropertyTransaction> propertyTransaction;
	
	
	@OneToMany(mappedBy = "date",cascade = CascadeType.REMOVE,fetch = FetchType.EAGER)
    private List<Absent> absents;
	

	@OneToMany(mappedBy = "contractDate",cascade = CascadeType.REMOVE,fetch = FetchType.EAGER)
    private List<Employee> employees;
	
	@OneToMany(mappedBy = "date",cascade = CascadeType.REMOVE,fetch = FetchType.EAGER)
    private List<Off> offs;
	
	
	
    
	public MyDate() {
		JalaliCalendar j=new JalaliCalendar();
		year=j.getYear();
		Month=j.getMonth();
		day=j.getDay();
	//	time=java.time.LocalTime.now();
	}

	public MyDate(int year, int month, int day) {
		super();
		this.year = year;
		Month = month;
		this.day = day;

	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public int getMonth() {
		return Month;
	}

	public void setMonth(int month) {
		Month = month;
	}

	public int getDay() {
		return day;
	}

	public void setDay(int day) {
		this.day = day;
	}

	//public LocalTime getTime() {
		//return time;
	//}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}	


	
	
	public List<EVASaleFactor> getEvaFactors() {
		return evaFactors;
	}

	public void setEvaFactors(List<EVASaleFactor> evaFactors) {
		this.evaFactors = evaFactors;
	}

	public List<PVCSaleFactor> getPvcFactors() {
		return pvcFactors;
	}

	public void setPvcFactors(List<PVCSaleFactor> pvcFactors) {
		this.pvcFactors = pvcFactors;
	}

	public List<SaleFactor> getSaleFactors() {
		return saleFactors;
	}

	public void setSaleFactors(List<SaleFactor> saleFactors) {
		this.saleFactors = saleFactors;
	}

	

	public List<CapsulTransactions> getCapsulTransactions() {
		return capsulTransactions;
	}

	public void setCapsulTransactions(List<CapsulTransactions> capsulTransactions) {
		this.capsulTransactions = capsulTransactions;
	}

	public List<FrameTransAction> getFrameTransActions() {
		return frameTransActions;
	}

	public void setFrameTransActions(List<FrameTransAction> frameTransActions) {
		this.frameTransActions = frameTransActions;
	}

	public List<MatrialTransaction> getMaterials() {
		return materials;
	}

	public void setMaterials(List<MatrialTransaction> materials) {
		this.materials = materials;
	}

	public List<ShoeSoles> getShowSoles() {
		return showSoles;
	}

	public void setShowSoles(List<ShoeSoles> showSoles) {
		this.showSoles = showSoles;
	}

	public List<Production> getProductions() {
		return productions;
	}

	public void setProductions(List<Production> productions) {
		this.productions = productions;
	}

	public List<Absent> getAbsents() {
		return absents;
	}

	public void setAbsents(List<Absent> absents) {
		this.absents = absents;
	}


	public List<Employee> getEmployees() {
		return employees;
	}

	public void setEmployees(List<Employee> employees) {
		this.employees = employees;
	}

	public List<Off> getOffs() {
		return offs;
	}

	public void setOffs(List<Off> offs) {
		this.offs = offs;
	}

	public List<PropertyTransaction> getPropertyTransaction() {
		return propertyTransaction;
	}

	public void setPropertyTransaction(List<PropertyTransaction> propertyTransaction) {
		this.propertyTransaction = propertyTransaction;
	}
	
	

	public List<StoredShoeSoleTransaction> getShoeSoleTransactions() {
		return shoeSoleTransactions;
	}

	public void setShoeSoleTransactions(
			List<StoredShoeSoleTransaction> shoeSoleTransactions) {
		this.shoeSoleTransactions = shoeSoleTransactions;
	}

	@Override
	public String toString() {
		return  year +"-" + Month + "-" + day;
	}




	
	
	
	
	
	



}
