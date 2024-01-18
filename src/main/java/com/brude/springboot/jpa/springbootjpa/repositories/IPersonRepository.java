package com.brude.springboot.jpa.springbootjpa.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.brude.springboot.jpa.springbootjpa.dtos.PersonDto;
import com.brude.springboot.jpa.springbootjpa.entities.Person;
import java.util.List;
import java.util.Optional;


//generic del tipo persopn y el tipo de dato de la PK
public interface IPersonRepository extends CrudRepository<Person , Long> {

    @Query("select count(distinct(p.programmingLanguaje)) from Person p")
    Long findAllPLanguajesDistinctCount();

    @Query("select distinct(p.programmingLanguaje) from Person p")
    List<String> findAllPLanguajesDistinct();

    @Query("select distinct(p.firstName) from Person p")
    List<String> findAllNamesDistinct();

    @Query("select new com.brude.springboot.jpa.springbootjpa.dtos.PersonDto(p.firstName, p.lastName) from Person p")
    List<PersonDto> findAllPersonDto();

    @Query("select new Person(p.firstName, p.lastName) from Person p")
    List<Person> findAllClassPerson();

    @Query("select p.firstName from Person p where p.id=?1")
    String getNameById(Long id);

    @Query("select concat(p.firstName, ' ', p.lastName) as fullname from Person p where p.id=?1")
    String getFullNameById(Long id);

    @Query("select p from Person p where p.id = ?1")
    Optional<Person> findOne(Long id);

    @Query("select p from Person p where p.firstName like %?1%")
    Optional<Person> findOneLikeName(String name);

    @Query("select p, p.programmingLanguaje from Person p")
    List <Object[]> findAllMixPerson();

    @Query("select p.id, p.firstName, p.lastName, p.programmingLanguaje from Person p")
    List <Object[]> obtenerPersonDataFullList();

    @Query("select p.id, p.firstName, p.lastName, p.programmingLanguaje from Person p where p.id=?1")
    Optional<Object[]> obtenerPersonDataFullById(Long Id);

    //convencion por defecto
    Optional<Person> findByFirstNameContaining(String name);

    List<Person> findByProgrammingLanguaje(String programmingLanguaje);

    @Query("select p from Person p where p.programmingLanguaje=?1 and p.firstName=?2 ")
    List<Person> BuscarByProgrammingLanguaje(String programmingLanguaje, String firstName);

    List<Person> findByProgrammingLanguajeAndFirstName(String programmingLanguaje, String firstName);

    @Query("select p.firstName, p.programmingLanguaje from Person p")
    List<Object[]> obtenerPersonData();

     @Query("select p.firstName, p.programmingLanguaje from Person p where p.programmingLanguaje=?1 and p.firstName=?2")
    List<Object[]> obtenerPersonData(String programmingLanguaje, String firstName);


}
