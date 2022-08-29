package com.api.control.dto;

import java.util.Date;

import javax.validation.constraints.NotBlank;

public class PersonDto {
	
	private Integer ageDifference;
	
	private Integer id;
	@NotBlank
	private String birthdayView;
	@NotBlank
	private String name;	
	private String nationalitySocial;	
	private Integer securityNumberJob;
	private Date birthday;
	
	
	private Integer person2Id;
	private String person2Name;
	private String person2NationalitySocial;
	private Integer person2SecurityNumberJob;
	private Date person2Birthday;
	private String person2BirthdayView;
	
	
			
	public PersonDto() {
		super();
	}

	public PersonDto(Integer ageDifference, Integer id, @NotBlank String birthdayView, @NotBlank String name,
			String nationalitySocial, Integer securityNumberJob, Date birthday, Integer person2Id, String person2Name,
			String person2NationalitySocial, Integer person2SecurityNumberJob, Date person2Birthday,
			String person2BirthdayView) {
		super();
		this.ageDifference = ageDifference;
		this.id = id;
		this.birthdayView = birthdayView;
		this.name = name;
		this.nationalitySocial = nationalitySocial;
		this.securityNumberJob = securityNumberJob;
		this.birthday = birthday;
		this.person2Id = person2Id;
		this.person2Name = person2Name;
		this.person2NationalitySocial = person2NationalitySocial;
		this.person2SecurityNumberJob = person2SecurityNumberJob;
		this.person2Birthday = person2Birthday;
		this.person2BirthdayView = person2BirthdayView;
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
	public String getNationalitySocial() {
		return nationalitySocial;
	}
	public void setNationalitySocial(String nationalitySocial) {
		this.nationalitySocial = nationalitySocial;
	}
	public Integer getSecurityNumberJob() {
		return securityNumberJob;
	}
	public void setSecurityNumberJob(Integer securityNumberJob) {
		this.securityNumberJob = securityNumberJob;
	}
	public String getBirthdayView() {
		return birthdayView;
	}
	public void setBirthdayView(String birthdayView) {
		this.birthdayView = birthdayView;
	}
	public Integer getAgeDifference() {
		return ageDifference;
	}
	public void setAgeDifference(Integer ageDifference) {
		this.ageDifference = ageDifference;
	}
	public Integer getPerson2Id() {
		return person2Id;
	}
	public void setPerson2Id(Integer person2Id) {
		this.person2Id = person2Id;
	}
	public String getPerson2Name() {
		return person2Name;
	}
	public void setPerson2Name(String person2Name) {
		this.person2Name = person2Name;
	}
	public String getPerson2NationalitySocial() {
		return person2NationalitySocial;
	}
	public void setPerson2NationalitySocial(String person2NationalitySocial) {
		this.person2NationalitySocial = person2NationalitySocial;
	}
	public Integer getPerson2SecurityNumberJob() {
		return person2SecurityNumberJob;
	}
	public void setPerson2SecurityNumberJob(Integer person2SecurityNumberJob) {
		this.person2SecurityNumberJob = person2SecurityNumberJob;
	}
	public Date getPerson2Birthday() {
		return person2Birthday;
	}
	public void setPerson2Birthday(Date person2Birthday) {
		this.person2Birthday = person2Birthday;
	}


	public String getPerson2BirthdayView() {
		return person2BirthdayView;
	}


	public void setPerson2BirthdayView(String person2BirthdayView) {
		this.person2BirthdayView = person2BirthdayView;
	}

	
	
	
}
