package com.api.control.controllers;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.api.control.dto.PersonDto;
import com.api.control.models.PersonModel;
import com.api.control.services.PersonService;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/")
public class PersonController {
	
	final PersonService personService;

	public PersonController(PersonService personService) {
		this.personService = personService;
	}
	
	@PostMapping("person")
	public ResponseEntity<Object> savePerson(@RequestBody @Valid PersonDto personDto) throws ParseException{
		personDto.setBirthday(converterStringtoDate(personDto.getBirthdayView()));
		
		var personModel = new PersonModel();
		BeanUtils.copyProperties(personDto, personModel);
		return ResponseEntity.status(HttpStatus.CREATED).body(personService.save(personModel));
		
	}
	
	@GetMapping("persons")	
	public ResponseEntity<List<PersonModel>> getAllPerson(){
		return ResponseEntity.status(HttpStatus.OK).body(personService.findAll());
	}
	
	@GetMapping("get_all_people")	
	public ResponseEntity<List<PersonModel>> findAllPerson(){
		return ResponseEntity.status(HttpStatus.OK).body(personService.findAll());
	}
	
	@GetMapping ("person/{id}")
	public ResponseEntity<Object> getOnePerson(@PathVariable(value="id") Integer id){
		Optional<PersonModel> personModelOptional = personService.findById(id);
		if(!personModelOptional.isPresent()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Person not found!");
		}
		return ResponseEntity.status(HttpStatus.OK).body(personModelOptional.get());
		
	}
	
	@DeleteMapping("person/{id}")
	public ResponseEntity<Object> deletePerson(@PathVariable(value="id") Integer id){
		Optional<PersonModel> personModelOptional = personService.findById(id);
		if(!personModelOptional.isPresent()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Person not found!");
		}
		personService.deleteById(id);
		return ResponseEntity.status(HttpStatus.OK).body("Person deleted sucessfully.");
	}
	
	@PutMapping("person/{id}")
	public ResponseEntity<Object> updatePerson(@PathVariable(value = "id") Integer id,
											   @RequestBody @Valid PersonDto personDto) throws ParseException{

		personDto.setBirthday(converterStringtoDate(personDto.getBirthdayView()));
		Optional<PersonModel> personModelOptional = personService.findById(id);
		
		if(!personModelOptional.isPresent()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Person not found!");
		}
		var personModel  = new PersonModel();
		personModel.setId(personModelOptional.get().getId());
		personModel.setName(personDto.getName());
		personModel.setBirthday(personDto.getBirthday());
		personModel.setNationality_social(personDto.getNationalitySocial());
		personModel.setSecurity_number_job(personDto.getSecurityNumberJob());
		
		
		return ResponseEntity.status(HttpStatus.OK).body(personService.save(personModel));
		
	}
	
	@GetMapping ("person/{id}/minimumAgeDifference")
	public ResponseEntity<Object> getMinimumAgeDif(@PathVariable(value="id") Integer id) throws ParseException{
		
		Optional<PersonModel> personModelOptional = personService.findById(id);
		
		PersonDto personAge = personService.getMinimumAge(converterDateToString(personModelOptional.get().getBirthday()), personModelOptional.get().getId());
		
		PersonDto personToShow = personService.populatePersonDto(personAge, personModelOptional);
		
		
		if(!personModelOptional.isPresent()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Person not found!");
		}
		return ResponseEntity.status(HttpStatus.OK).body(personToShow);
		
	}	
	
	private Date converterStringtoDate(String date) throws ParseException {
		String pattern = "dd/MM/yyyy";
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
		return simpleDateFormat.parse(date);
	}
	
	private String converterDateToString(Date date) throws ParseException{
		String pattern = "yyyy-MM-dd";
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
		return simpleDateFormat.format(date);
	}
	
	
	

}
