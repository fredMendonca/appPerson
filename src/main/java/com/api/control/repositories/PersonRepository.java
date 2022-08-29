package com.api.control.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.api.control.models.PersonModel;


@Repository
public interface PersonRepository extends JpaRepository<PersonModel, Integer> {

}
