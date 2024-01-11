package com.brude.springboot.jpa.springbootjpa.repositories;

import org.springframework.data.repository.CrudRepository;

import com.brude.springboot.jpa.springbootjpa.entities.Person;

//generic del tipo persopn y el tipo de dato de la PK
public interface IPersonRepository extends CrudRepository<Person , Long> {

    
}
