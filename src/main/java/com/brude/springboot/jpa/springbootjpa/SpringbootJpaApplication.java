package com.brude.springboot.jpa.springbootjpa;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.Transactional;

import com.brude.springboot.jpa.springbootjpa.dtos.PersonDto;
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
		//update();
		//delete2();
		//customizedQuerie2();
		//customizedDto();
		//customizedDistinct();
		//customizedDistinctLanguajeProgramming();
		//customizedDistinctCount();
		//customizedConcat();
		//customizedBetween();
		//BetweenConvencionNames();
		//BetweenConvencionNamesConOrder();
		//prueba();
		//Agregados();
		//longitud();
		//minMaxLong();
		resumenFuncionesAgregadas();
	
	}

	@Transactional(readOnly = true)
	public void resumenFuncionesAgregadas(){
		System.out.println("resumen funciones agregadas");
		Object[] resumeReg = (Object[]) repository.getResumeAggregationFunctions();
		System.out.println("min: " + resumeReg[0] + 
		                   ", max: " + resumeReg[1] + 
						   ",sum: " + resumeReg[2] +
						   ",avg: " + resumeReg[3] + 
						   ",count: " + resumeReg[4] );
		
	}

	@Transactional(readOnly = true)
	public void minMaxLong(){
		System.out.println("===minima long====");
		System.out.println(repository.getMinLength());
		System.out.println("===maxima long====");
		System.out.println(repository.getMaxLength());

	}

	@Transactional(readOnly = true)
	public void longitud(){
		System.out.println("consulta por nombre y su largo");
		List<Object[]> regs = repository.getPersonNameLength();
		regs.forEach( reg-> {
			String name = (String) reg[0];
			Integer longitud = (Integer) reg[1];
			System.out.println("nombre: " + name + ", Longitud: " + longitud);

		});
	}


	@Transactional(readOnly = true)
	public void Agregados(){
		Long conteo = repository.totalPerson();
		Long min = repository.minId();
		Long max = repository.maxId();
		System.out.println("======count====");
		System.out.println("num opersonas: " + conteo);
		System.out.println("======min=====");
		System.out.println("min id: " + min);
		System.out.println("======max=====");
		System.out.println("max id: " + max);
	}

	@Transactional(readOnly = true)
	public void prueba(){
		List <Person> persons = repository.findAllByOrderByFirstNameDescLastNameAsc();
		persons.forEach(System.out::println);
	}

	@Transactional(readOnly = true)
	public void BetweenConvencionNamesConOrder(){
		System.out.println("consulta por id between con order");
		List<Person> list = repository.findByLastNameBetweenOrderByIdDescFirstNameAsc("T" , "X");
		list.forEach(System.out::println);
	}

	@Transactional(readOnly = true)
	public void BetweenConvencionNames(){
		System.out.println("consulta por id between");
		List<Person> list = repository.findByLastNameBetween("T" , "X");
		list.forEach(System.out::println);
	}

	@Transactional(readOnly = true)
	public void customizedBetweenLastNameParam(){
		System.out.println("consulta por id between");
		List<Person> list = repository.findAllBetweenLastName("T" , "X");
		list.forEach(System.out::println);
	}

	@Transactional(readOnly = true)
	public void customizedBetweenLastName(){
		System.out.println("consulta por id between");
		List<Person> list = repository.findAllBetwwenLastName();
		list.forEach(System.out::println);
	}

	@Transactional(readOnly = true)
	public void customizedBetween(){
		System.out.println("consulta por id between");
		List<Person> list = repository.findAllBetweenId();
		list.forEach(System.out::println);
	}



	@Transactional(readOnly = true)
	public void customizedUpper(){
		System.out.println("consulta por nombres de personas");
		List<String> list = repository.findFullNameUpper();
		list.forEach(System.out::println);
	}

	@Transactional(readOnly = true)
	public void customizedConcat(){
		System.out.println("consulta por nombres de personas");
		List<String> list = repository.findFullNameConcat();
		list.forEach(System.out::println);
	}


	@Transactional(readOnly = true)
	public void customizedDistinct(){
		System.out.println("consulta por nombres de personas");
		List<String> list = repository.findAllNamesDistinct();
		list.forEach(System.out::println);
	}

	@Transactional(readOnly = true)
	public void customizedDistinctCount(){
		System.out.println("consulta por lenguajes conteo");
		Long conteo = repository.findAllPLanguajesDistinctCount();
		System.out.println("Numero de lenguajes: " + conteo);
	}

	@Transactional(readOnly = true)
	public void customizedDistinctLanguajeProgramming(){
		System.out.println("consulta por lenguajes distinct");
		List<String> list = repository.findAllPLanguajesDistinct();
		list.forEach(System.out::println);
	}

	@Transactional(readOnly= true)
	public void customizedDto(){
		System.out.println("consulta que puebla y devuelve objeto dto");
		List<PersonDto> personDtos = repository.findAllPersonDto();
		personDtos.forEach(System.out::println);
	}


	@Transactional(readOnly= true)
	public void customizedQuerie2(){
		System.out.println("====Consulta por objeto persona y lenguaje =====");
		 List<Object[]> personRegs = repository.findAllMixPerson();
		 personRegs.forEach(reg ->{
				System.out.println("pL=" + reg[1] + ", person=" + reg[0]);
		 });

		 System.out.println("====devuelve Entity ====");
		 List<Person> people = repository.findAllClassPerson();
		 //people.forEach(person -> System.out.println(person));
		 people.forEach(System.out::println);
	}

	@Transactional(readOnly= true)
	public void customizedQueries(){
		Scanner scanner = new Scanner(System.in);
		System.out.println("===============consulta solo nombre por id===========");
		System.out.println("Ingrese id");
		Long id = scanner.nextLong();
		scanner.close();
		String name = repository.getNameById(id);
		System.out.println(name);
		System.out.println("===============consulta full nombre por id===========");
		String fullname = repository.getFullNameById(id);
		System.out.println(fullname);
		System.out.println("===============consulta campos personalizados por id===========");
		Optional<Object[]> optionalReg = repository.obtenerPersonDataFullById(id);
		if(optionalReg.isPresent()){
			Object[] personalReg = optionalReg.get();
			System.out.println("id" + personalReg[0] + ":nombre" + personalReg[1]);
		}
		
		System.out.println("===============consulta campos personalizados lista===========");
		List<Object[]> regs = repository.obtenerPersonDataFullList();
		regs.forEach(personReg -> System.out.println("id" + personReg[0] + ":nombre" + personReg[1]));
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
	public void delete(){
		repository.findAll().forEach(System.out::println);
		Scanner scanner = new Scanner(System.in);
		System.out.println("ingrese id a eliminar");
		Long id = scanner.nextLong();

		repository.deleteById(id);
		repository.findAll().forEach(System.out::println);
		scanner.close();
	}

	@Transactional
	public void delete2(){
		repository.findAll().forEach(System.out::println);
		Scanner scanner = new Scanner(System.in);
		System.out.println("ingrese id a eliminar");
		Long id = scanner.nextLong();

		Optional<Person> optionalPerson = repository.findById(id);
		optionalPerson.ifPresentOrElse(
			repository::delete, 
			() -> System.out.println("No se encuentra la persona a eliminar"));

	
		repository.findAll().forEach(System.out::println);
		scanner.close();
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
		System.out.println(personNew);
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
