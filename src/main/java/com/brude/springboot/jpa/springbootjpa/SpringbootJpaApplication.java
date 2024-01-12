package com.brude.springboot.jpa.springbootjpa;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.brude.springboot.jpa.springbootjpa.entities.Person;
import com.brude.springboot.jpa.springbootjpa.repositories.IPersonRepository;

@SpringBootApplication
public class SpringbootJpaApplication implements CommandLineRunner{

	@Autowired
	private IPersonRepository repository;	
	public static void main(String[] args) {
		SpringApplication.run(SpringbootJpaApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		
		//list();
		findOne();

	}

	public void findOne(){
		
		//repository.findOneLikeName("Zu").ifPresent(System.out::println);
		repository.findByFirstNameContaining("Zu").ifPresent(System.out::println);
	}

	public void list(){
		//List<Person> persons = (List<Person>) repository.findAll();
		//convencion sobre codigo
		List<Person> persons = (List<Person>) repository.findByProgrammingLanguaje("PHP");
		//funciona con la anotacion query del repository 
		List<Person> persons2 = (List<Person>) repository.BuscarByProgrammingLanguaje("PHP" , "Dario");
		persons.stream().forEach(person  -> System.out.println(person));
		System.out.println("----------------");
		persons2.stream().forEach(person  -> System.out.println(person));
		List<Person> persons3 = (List<Person>) repository.
		                   findByProgrammingLanguajeAndFirstName("JAVA", "Danna");
		System.out.println("----------------");
		persons3.stream().forEach(person  -> System.out.println(person));
		System.out.println("----------------");
		List<Object[]> personsValues = repository.obtenerPersonData();
		//es una areeglo
		personsValues.stream().forEach(person -> System.out.println(person[0] + " es experto en " + person[1]));
		System.out.println("----------------");
		List<Object[]> personsValuesParam = repository.obtenerPersonData("PHP" ,  "Dario");
		//es una areeglo
		personsValuesParam.stream().forEach(person -> System.out.println(person[0] + " es experto en " + person[1]));
	}

}
