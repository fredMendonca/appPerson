package com.api.control.models;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity(name = "TBL_PERSON")
@Table(name = "TBL_PERSON")
public class PersonModel implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	
	@Column(nullable = false)
	private Date birthday;
	
	@Column(nullable = false, length = 70)
	private String name;
	
	@Column(nullable = true, length = 70)
	private String nationality_social;
	
	@Column(nullable = true, length = 10)
	private Integer security_number_job;

	public PersonModel() {
		super();
	}
		
	public PersonModel(Integer id, Date birthday, String name, String nationality_social, Integer security_number_job) {
		super();
		this.id = id;
		this.birthday = birthday;
		this.name = name;
		this.nationality_social = nationality_social;
		this.security_number_job = security_number_job;
	}



	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}	

	

	public String getNationality_social() {
		return nationality_social;
	}

	public void setNationality_social(String nationality_social) {
		this.nationality_social = nationality_social;
	}

	public Integer getSecurity_number_job() {
		return security_number_job;
	}

	public void setSecurity_number_job(Integer security_number_job) {
		this.security_number_job = security_number_job;
	}

	
	
	

	

	
}
