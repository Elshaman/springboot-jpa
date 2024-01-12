package com.brude.springboot.jpa.springbootjpa;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.Transactional;

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
		//findOne();
		//create();
		//createScan();
		update();
	
	}

	@Transactional
	public void createScan(){
		Scanner scanner = new Scanner(System.in);
		String firstName = scanner.next();
		String lastName = scanner.next();
		String programmingLanguaje = scanner.next();
		scanner.close();

		Person person = new Person(null, firstName, lastName , programmingLanguaje);

		Person personNew = repository.save(person);
		System.out.println(personNew);

		repository.findById(person.getId()).ifPresent(System.out::println);
	}


	@Transactional
	public void update(){
		Scanner scanner = new Scanner(System.in);
		System.out.println("ingrese id de persona a acutalizar");
		Long id = scanner.nextLong();

		Optional<Person> person = repository.findById(id);
		if(person.isPresent()){
			Person p = person.orElseThrow();
			System.out.println(p);
			System.out.println("Ingrese el nuevo lenguaje de programacion");
			String programmingLanguaje = scanner.next();
			p.setProgrammingLanguaje(programmingLanguaje);
			//porque save siempre retorna el objeto nuevo
			Person updPerson = repository.save(p);
			System.out.println(updPerson);
		}else{
			System.out.println("el usuario no existe");
		}

		/*Optional<Person> person = repository.findById(id);
		person.ifPresent( p -> {
			System.out.println(p);
			System.out.println("Ingrese el nuevo lenguaje de programacion");
			String programmingLanguaje = scanner.next();
			p.setProgrammingLanguaje(programmingLanguaje);
			//porque save siempre retorna el objeto nuevo
			Person updPerson = repository.save(p);
			System.out.println(updPerson);
		});
		*/

		scanner.close();
	}

	public void create(){
		Person person = new Person(null , "Yessie", "Chaverra", "RUBY");
		Person personNew = repository.save(person);
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
