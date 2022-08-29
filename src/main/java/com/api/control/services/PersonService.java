package com.api.control.services;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.api.control.dto.PersonDto;
import com.api.control.models.PersonModel;
import com.api.control.repositories.PersonRepository;


@Service
public class PersonService {

	final PersonRepository personRepository;
	@Value("${spring.datasource.url}")
    public String urlDB;
	@Value("${spring.datasource.username}")
    public String userDB;
	@Value("${spring.datasource.password}")
    public String pswDB;
		
	public PersonService(PersonRepository personRepository) {
		this.personRepository = personRepository;
	}
	
	@Transactional
	public PersonModel save(PersonModel personModel) {
		return personRepository.save(personModel);
	}
	
	public List<PersonModel> findAll(){
		return personRepository.findAll();
	}
	
	public Optional<PersonModel> findById(Integer id){
		return personRepository.findById(id);
	}
	
	@Transactional
	public void delete(PersonModel personModel) {
		personRepository.delete(personModel);
	}
	
	@Transactional
	public void deleteById(Integer id) {
		personRepository.deleteById(id);
	}
	
	public PersonDto getMinimumAge(String birthday, Integer idPerson) {		
		
		return findMinAgeDifference(birthday, idPerson);		
	}
	
	public PersonDto populatePersonDto(PersonDto person1, Optional<PersonModel> pessoa2) throws ParseException {
		PersonDto personDto = new PersonDto();
		personDto.setAgeDifference(person1.getAgeDifference());
		personDto.setId(person1.getId());
		personDto.setName(person1.getName());
		personDto.setBirthdayView(person1.getBirthdayView());
		personDto.setBirthday(person1.getBirthday());
		personDto.setNationalitySocial(person1.getNationalitySocial());
		personDto.setSecurityNumberJob(person1.getSecurityNumberJob());
		
		
		personDto.setPerson2Id(pessoa2.get().getId());
		personDto.setPerson2Name(pessoa2.get().getName());
		personDto.setPerson2NationalitySocial(pessoa2.get().getNationality_social());
		personDto.setPerson2SecurityNumberJob(pessoa2.get().getSecurity_number_job());
		personDto.setPerson2Birthday(pessoa2.get().getBirthday());
		personDto.setPerson2BirthdayView(converterDateToString(pessoa2.get().getBirthday()));
		
		
		return personDto;
	}
	
	public PersonDto findMinAgeDifference(String date, Integer id){
		PersonDto personDto = new PersonDto();
		
		Statement stmt = null;
		ResultSet rs = null;
		
		try {
			Connection con = null;
			con = DriverManager.getConnection(urlDB,userDB,pswDB);
			
			String jpql = "SELECT id,birthday,name,nationality_social,security_number_job,(ABS(TIMESTAMPDIFF(DAY, birthday, '"+date+"'))) AS age FROM TBL_PERSON tp where id != "+id+" order by age asc LIMIT 1";
			stmt = con.createStatement();
			rs = stmt.executeQuery(jpql);
			
			while (rs.next()){
				 personDto.setId(Integer.parseInt(rs.getString(1)));
				 personDto.setBirthdayView(converterDateToString(rs.getDate(2)));
				 personDto.setBirthday(rs.getDate(2));
				 personDto.setName(rs.getString(3));
				 personDto.setNationalitySocial(rs.getString(4));
				 personDto.setSecurityNumberJob(Integer.parseInt(rs.getString(5)));
				 personDto.setAgeDifference(Integer.parseInt(rs.getString(6)))	;
			}
			
		} catch (Exception ex) {
			System.out.println("SQLException: " + ex.getMessage());
		}
		return personDto;
	}
	
	private String converterDateToString(Date date) throws ParseException{
		String pattern = "yyyy-MM-dd";
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
		return simpleDateFormat.format(date);
	}
	
	
	
	
}
